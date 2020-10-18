package com.catalin.project.graphtraversal.v2.algorithm;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;

/**
 * This class implements the depth limited search.
 * 
 * @author Catalin Florea
 *
 * @param <V> the vertex type
 */
public class DepthLimitedSearch<V> {
	
	/** The traversal set. */
	private Set<V> traversalSet;
	
	/** The starting vertex. */
	private V startingVertex;
	
	/** The graph. */
	private DefaultDirectedGraph<V, WeightedEdge> graph;
	
	/** The limit. */
	private int limit;
	
	/**
	 * Creates a new depth limited search object.
	 * 
	 * @param startingVertex the starting vertex
	 * @param graph the graph
	 * @param limit the depth limit
	 */
	public DepthLimitedSearch(V startingVertex, DefaultDirectedGraph<V, WeightedEdge> graph, int limit) {
		super();
		this.traversalSet = new LinkedHashSet<>();
		this.startingVertex = startingVertex;
		this.graph = graph;
		this.limit = limit;
	}
	
	/**
	 * Executes the search.
	 */
	public void execute() {
		execute(graph, startingVertex, limit);
	}

	/**
	 * The recursive search implementation.
	 * 
	 * @param graph the graph
	 * @param startingVertex the starting vertex
	 * @param limit the depth limit
	 * @return whether the goal node was found
	 */
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

	/**
	 * Gets the traversal set.
	 * 
	 * @return the traversal set
	 */
	public Set<V> getTraversalSet() {
		return this.traversalSet;
	}
	
}
