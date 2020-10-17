package com.catalin.project.graphtraversal.v2.algorithm;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;

/**
 * This class implements the depth first search.
 * 
 * @author Catalin Florea
 *
 * @param <V> the vertex type
 */
public class DepthFirstSearch<V> {

	/** The vertex stack. */
	private Stack<V> vertexStack;
	
	/** The traversal set. */
	private Set<V> traversalSet;
	
	/** The starting vertex. */
	private V startingVertex;
	
	/** The graph. */
	private DefaultDirectedGraph<V, WeightedEdge> graph;
	
	/**
	 * Creates a new depth first search object.
	 * 
	 * @param startingVertex the starting vertex
	 * @param graph the graph
	 */
	public DepthFirstSearch(V startingVertex, DefaultDirectedGraph<V, WeightedEdge> graph) {
		super();
		this.vertexStack = new Stack<>();
		this.traversalSet = new LinkedHashSet<>();
		this.startingVertex = startingVertex;
		this.graph = graph;
	}

	/**
	 * Executes the search.
	 */
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
	
	/**
	 * Gets the traversal set.
	 * 
	 * @return the traversal set
	 */
	public Set<V> getTraversalSet() {
		return this.traversalSet;
	}
	
}
