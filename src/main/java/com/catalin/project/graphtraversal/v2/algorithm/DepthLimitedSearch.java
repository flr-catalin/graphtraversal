package com.catalin.project.graphtraversal.v2.algorithm;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;

public class DepthLimitedSearch<V> {
	
	private Set<V> traversalSet;
	
	private V startingVertex;
	
	private DefaultDirectedGraph<V, WeightedEdge> graph;
	
	private int limit;
	
	public DepthLimitedSearch(V startingVertex, DefaultDirectedGraph<V, WeightedEdge> graph, int limit) {
		super();
		this.traversalSet = new LinkedHashSet<>();
		this.startingVertex = startingVertex;
		this.graph = graph;
		this.limit = limit;
	}
	
	public void execute() {
		execute(graph, startingVertex, limit);
	}

	public boolean execute(DefaultDirectedGraph<V, WeightedEdge> graph, V startingVertex, int limit) {
		if (limit <= 0) {
			return false;
		}
		
		traversalSet.add(startingVertex);
		
		List<V> neighborListOf = Graphs.successorListOf(graph, startingVertex);
		for (V city : neighborListOf) {
			if (execute(graph, city, limit - 1)) {
				return true;
			}
		}
		
		return false;
	}

	public Set<V> getTraversalSet(int setCounter) {
		return this.traversalSet;
	}
	
}
