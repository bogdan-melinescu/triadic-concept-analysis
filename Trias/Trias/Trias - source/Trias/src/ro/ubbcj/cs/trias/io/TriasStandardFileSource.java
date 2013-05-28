package ro.ubbcj.cs.trias.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import de.unikassel.cs.kde.trias.model.Context;
import de.unikassel.cs.kde.trias.model.Triple;

public class TriasStandardFileSource {

	private static final Logger log = Logger.getLogger(TriasStandardFileSource.class);

	private File file;
	private String separator;

	public TriasStandardFileSource(File file, String separator) {
		this.file = file;
		this.separator = separator;
	}

	@SuppressWarnings("unchecked")
	public Context<String> getItemlist() throws IOException {

		final List<Triple<String>> list = new LinkedList<Triple<String>>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(this.file));
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(this.separator);
				if (tokens.length != 3) {
					throw new RuntimeException("Invalid file: "
							+ file.getName());
				}
				list.add(new Triple<String>(tokens[0], tokens[1], tokens[2]));
			}
			br.close();
			
			/*
			 * copy results into array of triples
			 */
			final Triple<String>[] itemList = new Triple[list.size()];
			int itemCtr = 0;
			for (final Triple<String> triple : list) {
				itemList[itemCtr++] = triple;
			}
			list.clear();
			return new Context<String>(itemList);
		} catch (Exception ex) {
			throw new IOException("Error reading file: " + ex);
		}
	}
}
