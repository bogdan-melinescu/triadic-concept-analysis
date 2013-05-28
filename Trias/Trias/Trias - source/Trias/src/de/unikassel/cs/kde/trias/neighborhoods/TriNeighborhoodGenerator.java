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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import de.unikassel.cs.kde.trias.model.GraphEdge;
import de.unikassel.cs.kde.trias.model.TriConcept;
import de.unikassel.cs.kde.trias.model.TriConceptArrayComparator;

/**
 * Generates the neighborhood graph for a given list of triconcepts.
 *  
 * @author:  rja
 * @version: $Id: TriNeighborhoodGenerator.java,v 1.2 2009-04-23 06:53:15 rja Exp $
 * $Author: rja $
 * 
 */
public class TriNeighborhoodGenerator<T extends Comparable<T>> {

	/**
	 * FIXME: re-use from elsewhere! 
	 */
	private static final int DIMS = 3;


	private static final Logger log = Logger.getLogger(TriNeighborhoodGenerator.class);

	private TriConcept<T>[] concepts;

	private int thresholds[];

	private Integer[][] sortings;




	public TriNeighborhoodGenerator() {
		super();
	}

	public TriNeighborhoodGenerator(final TriConcept<T>[] concepts, final int[] thresholds) {
		super();
		this.concepts = concepts;
		this.thresholds = thresholds;
	}

	/** Computes the neighborhood graph.
	 * 
	 * @return The vertices of the neighborhood graph.
	 */
	public Set<GraphEdge<TriConcept<T>>> getNeighborhoodGraph() {
		/*
		 * result graph
		 */
		final Set<GraphEdge<TriConcept<T>>> graph = new HashSet<GraphEdge<TriConcept<T>>>();
		/*
		 * maps to each neighborhood-id the set of neighbors (triconcepts) belonging to 
		 * that neighborhood  
		 */
		final Map<Integer, Set<TriConcept<T>>> inverseNeighborMap = new HashMap<Integer, Set<TriConcept<T>>>();
		/*
		 * maps to each triconcept its current neighborhood-ID
		 */
		final Map<TriConcept<T>, Integer> neighborMap = new HashMap<TriConcept<T>, Integer>();
		/*
		 * get sortings for all three dimensions
		 */
		log.info("Sorting concepts");
		sortings = getSortings(concepts);
		/*
		 * merge tri-concepts into neighborhoods, which have the same extent
		 */
		log.info("Merging by extent");
		mergeFirstDim(inverseNeighborMap, neighborMap, graph);
		log.info("Merging by intent");
		mergeDim(inverseNeighborMap, neighborMap, graph, 1);
		log.info("Merging by modus");
		mergeDim(inverseNeighborMap, neighborMap, graph, 2);

		return graph;
	}


	/** Returns for permutations which represent an order over extent, intent,
	 * and modus of the given tri-concepts.
	 * 
	 * @param triconcepts
	 * @return
	 */
	private Integer[][] getSortings(final TriConcept<T>[] triconcepts) {
		final Integer[][] order = new Integer[DIMS][triconcepts.length];
		for (int dim = 0; dim < order.length; dim++) {
			/*
			 * initialize array with identity permutation
			 */
			for (int j = 0; j < triconcepts.length; j++) {
				order[dim][j] = j; 
			}
			/*
			 * get sorted permutation
			 */
			Arrays.sort(order[dim], new TriConceptArrayComparator<T>(triconcepts, dim));
		}
		return order;
	}


	private void mergeFirstDim(final Map<Integer, Set<TriConcept<T>>> inverseNeighborMap, final Map<TriConcept<T>, Integer> neighborMap, final Set<GraphEdge<TriConcept<T>>> graph) {
		final int currDim = 0;
		/*
		 * current ID of neighborhood
		 */
		int neighborId = 0;

		TriConcept<T> lastTriConcept = null; 
		/*
		 * iterate over tri-concepts ordered by currDim
		 */
		for (int j=0; j < concepts.length; j++) {
			final TriConcept<T> triConcept = concepts[sortings[currDim][j]];
			if (checkCondition(currDim, lastTriConcept, triConcept)) {
				/*
				 * first component has changed -> new neighborhood ID
				 */
				neighborId++;
				inverseNeighborMap.put(neighborId, new HashSet<TriConcept<T>>());	
				lastTriConcept = triConcept;
			} else {
				/*
				 * firstComponent has not changed -> add edge to graph
				 */
				graph.add(new GraphEdge<TriConcept<T>>(lastTriConcept, triConcept));
				lastTriConcept = triConcept;
			}
			/*
			 * assign neighborID to triconcept
			 */
			neighborMap.put(triConcept, neighborId);
			/*
			 * add triconcept to neighboorhood
			 */
			inverseNeighborMap.get(neighborId).add(triConcept);
		}
	}


	private void mergeDim(final Map<Integer, Set<TriConcept<T>>> inverseNeighborMap, final Map<TriConcept<T>, Integer> neighborMap, final Set<GraphEdge<TriConcept<T>>> graph, int currDim) {

		int neighborId = 0;
		int currNeighborId = 0;
		TriConcept<T> lastTriConcept = null;

		/*
		 * iterate over tri-concepts ordered by currDim
		 */			
		for (int j=0; j < concepts.length; j++) {
			final TriConcept<T> triConcept = concepts[sortings[currDim][j]];
			if (checkCondition(currDim, lastTriConcept, triConcept)) {
				/*
				 * second component has changed -> get neighborhood ID
				 */
				neighborId = neighborMap.get(triConcept);
				lastTriConcept = triConcept;
			} else {					
				/*
				 * firstComponent has not changed -> add edge to graph
				 */
				graph.add(new GraphEdge<TriConcept<T>>(lastTriConcept,triConcept));
				lastTriConcept = triConcept;
			}

			currNeighborId = neighborMap.get(triConcept);
			if (currNeighborId != neighborId) {
				/*
				 * join neighborhoods currNeighborId and neighborId
				 */
				// get members of second neighborhood
				final Set<TriConcept<T>> sndNeighborhood = inverseNeighborMap.get(currNeighborId);
				// update their neighborhood to neighborId
				for (final TriConcept<T> neighbor:sndNeighborhood) {
					neighborMap.put(neighbor, neighborId);
				}
				// change inverse mapping by adding set to neighborId-Neighboorhood
				inverseNeighborMap.remove(currNeighborId);
				inverseNeighborMap.get(neighborId).addAll(sndNeighborhood);
			}
		}
	}

	/**
	 * @param dim
	 * @param lastTriConcept
	 * @param triConcept
	 * @param thresholds
	 * @return
	 */
	private boolean checkCondition(final int dim, final TriConcept<T> lastTriConcept, final TriConcept<T> triConcept) {
		return lastTriConcept == null || ! (Arrays.equals(triConcept.getDim(dim),lastTriConcept.getDim(dim)) && triConcept.getDim(dim).length >=  thresholds[dim]);
	}

	public TriConcept<T>[] getConcepts() {
		return concepts;
	}

	public void setConcepts(TriConcept<T>[] concepts) {
		this.concepts = concepts;
	}

	public int[] getThresholds() {
		return thresholds;
	}

	public void setThresholds(int[] thresholds) {
		this.thresholds = thresholds;
	}

}

