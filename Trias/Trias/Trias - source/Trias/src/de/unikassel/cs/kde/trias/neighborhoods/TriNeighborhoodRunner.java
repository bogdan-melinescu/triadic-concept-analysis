/**
 *  
 *  Trias Algorithm - Trias is an algorithm for computing triadic conepts which
 * 		fulfill minimal support constraints.
 *   
 *  Copyright (C) 2006 - 2009 Knowledge & Data Engineering Group, 
 *                            University of Kassel, Germany
 *                            http://www.kde.cs.uni-kassel.de/
 *  
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package de.unikassel.cs.kde.trias.neighborhoods;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import de.unikassel.cs.kde.trias.Trias;
import de.unikassel.cs.kde.trias.io.ModelReaderWriter;
import de.unikassel.cs.kde.trias.io.TriConceptReader;
import de.unikassel.cs.kde.trias.io.TriasDatabaseSource;
import de.unikassel.cs.kde.trias.model.Context;
import de.unikassel.cs.kde.trias.model.GraphEdge;
import de.unikassel.cs.kde.trias.model.TriConcept;

/**
 * 
 * @author:  rja
 * @version: $Id: TriNeighborhoodRunner.java,v 1.4 2009-04-23 06:53:15 rja Exp $
 * $Author: rja $
 * 
 */
public class TriNeighborhoodRunner {

	private static final Logger log = Logger.getLogger(TriNeighborhoodRunner.class);

	@SuppressWarnings("unchecked")
	public static void main(final String[] args) throws Exception {
		readFromDatabase();
		//readFromFile();
	}

	private static void readFromFile() throws IOException, Exception {
		/*
		 * load properties
		 */
		final Properties prop = new Properties();
		prop.load(TriNeighborhoodRunner.class.getClassLoader().getResourceAsStream("neighborhood.properties"));
		
		final TriConceptReader reader = new TriConceptReader();
		
		final Collection<TriConcept<String>> triLattice = reader.getTriLattice(prop.getProperty("lattice.file"));
		
		/*
		 * configure neighborhood generator
		 */
		final TriNeighborhoodGenerator<String> generator = new TriNeighborhoodGenerator<String>();
		final TriConcept<String>[] triConcepts = asArray(triLattice);
		
		log.info("Found " + triConcepts.length + " tri-concepts");
		
		generator.setConcepts(triConcepts);
		generator.setThresholds(getIntArrayFromString(prop.getProperty("neighborhood.thresholds"), ","));

		log.info("Starting to compute tri-neighborhoods");
		final Set<GraphEdge<TriConcept<String>>> graph = generator.getNeighborhoodGraph();

		log.info("Graph has " + graph.size() + " edges");
		
		
		final TriNeighborHoodFinder<String> finder = new TriNeighborHoodFinder<String>();
		final HashSet<HashSet<TriConcept<String>>> neighborhoods = finder.findNeighborhoods(graph);
		log.info("found " + neighborhoods.size() + " neighborhoods");
		int sum = 0;
		for (final Collection<TriConcept<String>> neighborhood : neighborhoods) {
			final int size = neighborhood.size();
			log.info(size + ": " + neighborhood);
			sum += size;
		}
		log.info("sum = " + sum);
		
		
		/*
		 * write graphviz graph
		 */
		final GraphWriter<String> writer = new GraphWriter(prop.getProperty("graph.file"));

		writer.setDimensionLabels(prop.getProperty("graph.labels").split(" "));

		writer.writeGraph(graph);
	}
	
	/** Copies the collection into an array.
	 * 
	 * @param triLattice
	 * @return
	 */
	private static TriConcept<String>[] asArray(final Collection<TriConcept<String>> triLattice) {
		final TriConcept<String>[] array = new TriConcept[triLattice.size()];
		int i = 0;
		for (final TriConcept<String> triConcept : triLattice) {
			array[i++] = triConcept;
		}
		return array;
	}
	
	private static void readFromDatabase() throws IOException, Exception {
		/*
		 * load properties
		 */
		final Properties prop = new Properties();
		prop.load(TriNeighborhoodRunner.class.getClassLoader().getResourceAsStream("database.properties"));
		/*
		 * get some data from database
		 */
		final Connection conn = TriasDatabaseSource.getConnection(
				prop.getProperty("db.url"),
				prop.getProperty("db.user"),
				prop.getProperty("db.pass")
		);
		
		final TriasDatabaseSource dataSource = new TriasDatabaseSource(
				conn, 
				prop.getProperty("db.query"),
				new Object[]{}
		);
		
		final Context<String> itemlist = dataSource.getItemlist();
		log.info("Got " + itemlist.getRelation().length + " triples from database");
		
		final ModelReaderWriter<String> mrw = new ModelReaderWriter<String>(itemlist);

		
		final Trias trias = new Trias();
		 
		/*
		 * configure trias
		 */
		trias.setItemList(mrw.getItemlist());
		trias.setNumberOfItemsPerDimension(mrw.getNumberOfItemsPerDimension());
		trias.setMinSupportPerDimension(getIntArrayFromString(prop.getProperty("trias.minSupport"), ","));
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
		generator.setThresholds(getIntArrayFromString(prop.getProperty("neighborhood.thresholds"), ","));

		log.info("Starting to compute tri-neighborhoods");
		final Set<GraphEdge<TriConcept<String>>> graph = generator.getNeighborhoodGraph();

		log.info("Graph has " + graph.size() + " edges");
		
		/*
		 * write graphviz graph
		 */
		final GraphWriter<String> writer = new GraphWriter(prop.getProperty("graph.file"));

		writer.setDimensionLabels(prop.getProperty("graph.labels").split(","));

		writer.writeGraph(graph);
	}
	
	/**
	 * Splits an input String each time delim is found and converts the 
	 * parts to integer.
	 *  
	 * @param input
	 * @param delim
	 * @return
	 */
	private static int[] getIntArrayFromString(final String input, final String delim) {
		final String[] parts = input.split(delim);
		final int[] result = new int[parts.length];
		for (int i=0; i < parts.length; i++) {
			result[i] = Integer.parseInt(parts[i]);
		}
		return result;
	}
}

