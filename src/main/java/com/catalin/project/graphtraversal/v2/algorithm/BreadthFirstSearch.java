package com.catalin.project.graphtraversal.v2.algorithm;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;

/**
 * This class implements the breadth first search.
 * 
 * @author Catalin Florea
 *
 * @param <V> the vertex type
 */
public class BreadthFirstSearch<V> {
	
	/** The vertex queue. */
	private Queue<V> vertexQueue;
	
	/** The traversal set. */
	private Set<V> traversalSet;
	
	/** The starting vertex. */
	private V startingVertex;
	
	/** The graph. */
	private DefaultDirectedGraph<V, WeightedEdge> graph;
	
	/**
	 * Creates a new breadth first search object.
	 * 
	 * @param startingVertex the starting vertex
	 * @param graph the graph
	 */
	public BreadthFirstSearch(V startingVertex, DefaultDirectedGraph<V, WeightedEdge> graph) {
		super();
		this.vertexQueue = new LinkedList<>();
		this.traversalSet = new LinkedHashSet<>();
		this.startingVertex = startingVertex;
		this.graph = graph;
	}

	/**
	 * Executes the search.
	 */
	public void execute() {
		vertexQueue.add(startingVertex);
		
		while (!vertexQueue.isEmpty()) {
			startingVertex = vertexQueue.poll();
			traversalSet.add(startingVertex);
			
			List<V> neighborListOf = Graphs.successorListOf(graph, startingVertex);
			for (V vertex : neighborListOf) {
				if (!traversalSet.contains(vertex)) {
					vertexQueue.add(vertex);
				}
				traversalSet.add(startingVertex);
			}
		}
	}
	
	/**
	 * Executes the search with a goal.
	 * 
	 * @param goalVertex the goal
	 */
	public void execute(V goalVertex) {
		vertexQueue.add(startingVertex);
		if (startingVertex.equals(goalVertex)) {
			return;
		}
		
		while (!vertexQueue.isEmpty()) {
			startingVertex = vertexQueue.poll();
			traversalSet.add(startingVertex);
			if (startingVertex.equals(goalVertex)) {
				return;
			}
			
			List<V> neighborListOf = Graphs.successorListOf(graph, startingVertex);
			for (V vertex : neighborListOf) {
				if (!traversalSet.contains(vertex)) {
					vertexQueue.add(vertex);
				}
				traversalSet.add(startingVertex);
				if (startingVertex.equals(goalVertex)) {
					return;
				}
			}
		}
	}
	
	/**
	 * Gets the traversal set.
	 * 
	 * @return the traversal set
	 */
	public Set<V> getTraversalSet() {
		return this.traversalSet;
	}

}
