package com.catalin.project.graphtraversal.v2.algorithm;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;

public class DepthFirstSearch<V> {

	private Stack<V> vertexStack;
	
	private Set<V> traversalSet;
	
	private V startingVertex;
	
	private DefaultDirectedGraph<V, WeightedEdge> graph;
	
	public DepthFirstSearch(V startingVertex, DefaultDirectedGraph<V, WeightedEdge> graph) {
		super();
		this.vertexStack = new Stack<>();
		this.traversalSet = new LinkedHashSet<>();
		this.startingVertex = startingVertex;
		this.graph = graph;
	}

	public void execute() {
		vertexStack.push(startingVertex);
		
		while (!vertexStack.isEmpty()) {
			startingVertex = vertexStack.pop();
			if (!traversalSet.contains(startingVertex)) {
				traversalSet.add(startingVertex);
				
				List<V> neighborListOf = Graphs.successorListOf(graph, startingVertex);
				Collections.reverse(neighborListOf);
				for (V city : neighborListOf) {
					vertexStack.push(city);
				}
			}
		}
	}
	
	public Set<V> getTraversalSet() {
		return this.traversalSet;
	}
	
}
