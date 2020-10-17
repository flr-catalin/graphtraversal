package com.catalin.project.graphtraversal.v2.algorithm;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;

public class BreadthFirstSearch<V> {
	
	private Queue<V> vertexQueue;
	
	private Set<V> traversalSet;
	
	private V startingVertex;
	
	private DefaultDirectedGraph<V, WeightedEdge> graph;
	
	public BreadthFirstSearch(V startingVertex, DefaultDirectedGraph<V, WeightedEdge> graph) {
		super();
		this.vertexQueue = new LinkedList<>();
		this.traversalSet = new LinkedHashSet<>();
		this.startingVertex = startingVertex;
		this.graph = graph;
	}

	public void execute() {
		vertexQueue.add(startingVertex);
		
		while (!vertexQueue.isEmpty()) {
			startingVertex = vertexQueue.poll();
			traversalSet.add(startingVertex);
			
			List<V> neighborListOf = Graphs.successorListOf(graph, startingVertex);
			for (V city : neighborListOf) {
				if (!traversalSet.contains(city))
				vertexQueue.add(city);
				traversalSet.add(startingVertex);
			}
		}
	}
	
	public Set<V> getTraversalSet() {
		return this.traversalSet;
	}

}
