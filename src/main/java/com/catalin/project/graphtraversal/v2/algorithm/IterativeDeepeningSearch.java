package com.catalin.project.graphtraversal.v2.algorithm;

import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;

/**
 * This class implements the iterative deepening search.
 * 
 * @author Catalin Florea
 *
 * @param <V> the vertex type
 */
public class IterativeDeepeningSearch<V> extends DepthLimitedSearch<V> {

	/**
	 * Creates a new iterative deepening search object.
	 * 
	 * @param startingVertex the starting vertex
	 * @param graph the graph
	 * @param limit the limit
	 */
	public IterativeDeepeningSearch(V startingVertex, DefaultDirectedGraph<V, WeightedEdge> graph, int limit) {
		super(startingVertex, graph, limit);
	}

	/**
	 * Executes the search.
	 */
	public boolean execute(DefaultDirectedGraph<V, WeightedEdge> graph, V startingVertex, int limit) {
		for (int i = 1; i <= limit; i++) {
			if (super.execute(graph, startingVertex, i)) {
				return true;
			}
		}
		
		return false;
	}

}
