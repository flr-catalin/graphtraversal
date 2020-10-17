package com.catalin.project.graphtraversal.v2.algorithm;

import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;

public class IterativeDeepeningSearch<V> extends DepthLimitedSearch<V> {

	public IterativeDeepeningSearch(V startingVertex, DefaultDirectedGraph<V, WeightedEdge> graph, int limit) {
		super(startingVertex, graph, limit);
	}

	public boolean execute(DefaultDirectedGraph<V, WeightedEdge> graph, V startingVertex, int limit) {
		for (int i = 1; i <= limit; i++) {
			if (super.execute(graph, startingVertex, i)) {
				return true;
			}
		}
		
		return false;
	}

}
