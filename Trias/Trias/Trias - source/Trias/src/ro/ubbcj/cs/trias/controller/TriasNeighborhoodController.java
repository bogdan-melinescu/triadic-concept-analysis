package ro.ubbcj.cs.trias.controller;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import ro.ubbcj.cs.trias.io.TriasDatabaseMetadataCollector;
import ro.ubbcj.cs.trias.io.TriasStandardFileSource;
import ro.ubbcj.cs.trias.ui.DataSource;
import ro.ubbcj.cs.trias.ui.TriasNeighborhoodsFrame;
import de.unikassel.cs.kde.trias.Trias;
import de.unikassel.cs.kde.trias.io.ModelReaderWriter;
import de.unikassel.cs.kde.trias.io.TriasDatabaseSource;
import de.unikassel.cs.kde.trias.model.Context;
import de.unikassel.cs.kde.trias.model.GraphEdge;
import de.unikassel.cs.kde.trias.model.TriConcept;
import de.unikassel.cs.kde.trias.neighborhoods.GraphWriter;
import de.unikassel.cs.kde.trias.neighborhoods.TriNeighborhoodGenerator;
import de.unikassel.cs.kde.trias.neighborhoods.TriNeighborhoodRunner;

/**
 * 
 * The controller for the trias neighborhood frame.
 * 
 */
public class TriasNeighborhoodController {

	private static final Logger log = Logger
			.getLogger(TriNeighborhoodRunner.class);

	private static final String DATABASE_INFO_FILE = "database.properties";

	private TriasNeighborhoodsFrame frame;

	private Context<String> itemList;
	private Connection connection;
	private TriasDatabaseMetadataCollector databaseMetadataCollector;

	/**
	 * If the data source is DATABASE, a file named database.properties
	 * containing the properties "db.url", "db.username", and "db.password" must
	 * exist in the working directory.
	 * 
	 * @param dataSource - the type of datasource
	 */
	public TriasNeighborhoodController(DataSource dataSource) {
		this.itemList = null;
		readProperties(dataSource);
		this.frame = new TriasNeighborhoodsFrame(dataSource, this);
		this.frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void readProperties(DataSource dataSource) {
		if (dataSource == DataSource.DATABASE) {
			try {
				Properties prop = new Properties();
				prop.load(new FileReader("database.properties"));
				this.connection = TriasDatabaseSource.getConnection(
						prop.getProperty("db.url"),
						prop.getProperty("db.username"),
						prop.getProperty("db.password"));
			} catch (Exception ex) {
				throw new RuntimeException("Error reading properties file: "
						+ ex);
			}
			this.databaseMetadataCollector = new TriasDatabaseMetadataCollector(
					connection);
		}

	}

	/**
	 * Makes the trias neighborhood frame visible.
	 */
	public void showFrame() {
		this.frame.setVisible(true);
	}

	/**
	 * Reads data from a file which must contain three strings separated by a
	 * specific separator, representing tuples which identify the relation that
	 * describes a triadic context.
	 * 
	 * @param file
	 *            - the input file
	 * @param separator
	 *            - the string used as separator
	 * @throws IOException
	 *             - if the input file cannot be read
	 */
	public void readDataFromFile(File file, String separator)
			throws IOException {
		TriasStandardFileSource fileSource = new TriasStandardFileSource(file,
				separator);
		this.itemList = fileSource.getItemlist();
	}

	/**
	 * Reads data from three columns in a table in a database, representing
	 * tuples which identify the relation that describes a triadic context.
	 * 
	 * @param tableName
	 *            - the name of the table
	 * @param columnName1
	 *            - the name of the first column
	 * @param columnName2
	 *            - the name of the second column
	 * @param columnName3
	 *            - the name of the third column
	 */
	public void readDataFromDatabase(String tableName, String columnName1,
			String columnName2, String columnName3) {
		String query = "SELECT " + columnName1 + ", " + columnName2 + ", "
				+ columnName3 + " FROM " + tableName;
		TriasDatabaseSource databaseSource = new TriasDatabaseSource(
				this.connection, query, new Object[0]);
		try {
			this.itemList = databaseSource.getItemlist();
		} catch (IOException ex) {
			throw new RuntimeException("Database error: " + ex);
		}
	}

	/**
	 * 
	 * @return a list of table names in the given database
	 */
	public List<String> getTableNames() {
		if (databaseMetadataCollector != null) {
			return databaseMetadataCollector.getTableNames();
		} else {
			return new ArrayList<String>();
		}
	}

	/**
	 * Returns the name of all columns of a given table in the database.
	 * 
	 * @param tableName - the name of the table
	 * @return the names of the columns of the given table
	 */
	public List<String> getColumnNames(String tableName) {
		if (databaseMetadataCollector != null) {
			return databaseMetadataCollector.getColumns(tableName);
		} else {
			return new ArrayList<String>();
		}
	}

	/**
	 * Computes the neighborhoods of triadic concepts for the given context (previously read from a file or database).
	 * 
	 * @param outputFile - the file where the graph of neighborhoods will be saved
	 * @param minSupport - the array of minSupports for concepts (contains 3 integers, one for each dimension of the context)
	 * @param thresholds - the array of thresholds for linking two concepts (contains 3 integers, one for each dimension of the context)
	 * @param openFile - whether the file should be open using graphviz upon creation
	 * 
	 * @throws IOException - if the output file cannot be written
	 * @throws RuntimeException - if openFile is true and graphviz is not installed on the system
	 */
	public void computeNeighborhoods(File outputFile, int[] minSupport,
			int[] thresholds, boolean openFile) throws IOException {

		final ModelReaderWriter<String> mrw = new ModelReaderWriter<String>(
				itemList);
		final Trias trias = new Trias();

		/*
		 * configure trias
		 */
		trias.setItemList(mrw.getItemlist());
		trias.setNumberOfItemsPerDimension(mrw.getNumberOfItemsPerDimension());
		trias.setMinSupportPerDimension(minSupport);
		trias.setTriConceptWriter(mrw);
		/*
		 * run trias
		 */
		log.info("Starting to compute tri-concepts");

		trias.doWork();
		/*
		 * configure neighborhood generator
		 */
		final TriNeighborhoodGenerator<String> generator = new TriNeighborhoodGenerator<String>();
		final TriConcept<String>[] triConcepts = mrw.getTriLattice();

		log.info("Found " + triConcepts.length + " tri-concepts");

		generator.setConcepts(triConcepts);
		generator.setThresholds(thresholds);

		log.info("Starting to compute tri-neighborhoods");
		final Set<GraphEdge<TriConcept<String>>> graph = generator
				.getNeighborhoodGraph();

		log.info("Graph has " + graph.size() + " edges");

		/*
		 * write graphviz graph
		 */
		final GraphWriter<String> writer = new GraphWriter<String>(outputFile);

		// TODO set dimension labels
		// writer.setDimensionLabels(prop.getProperty("graph.labels").split(","));

		writer.writeGraph(graph);
		if (openFile) {
			try {
				Runtime.getRuntime().exec(
						"gvedit.exe \"" + outputFile.getAbsolutePath() + "\"");
			} catch (Exception ex) {
				frame.putError("Could not open file. Graphviz not installed.");
			}
		}
	}
}
