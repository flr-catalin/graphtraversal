package com.catalin.project.graphtraversal.v2.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;

/**
 * This class implements the iterative deepening search.
 * 
 * @author Catalin Florea
 *
 * @param <V> the vertex type
 */
public class IterativeDeepeningSearch<V> {

	/** The traversal sets. */
	private List<Set<V>> traversalSets;
	
	/** The starting vertex. */
	private V startingVertex;
	
	/** The graph. */
	private DefaultDirectedGraph<V, WeightedEdge> graph;
	
	/** The limit. */
	private int limit;
	
	/**
	 * Creates a new iterative deepening search object.
	 * 
	 * @param startingVertex the starting vertex
	 * @param graph the graph
	 * @param limit the limit
	 */
	public IterativeDeepeningSearch(V startingVertex, DefaultDirectedGraph<V, WeightedEdge> graph, int limit) {
		this.traversalSets = new ArrayList<>();
		this.startingVertex = startingVertex;
		this.graph = graph;
		this.limit = limit;
	}

	/**
	 * Executes the search.
	 */
	public void execute() {
		for (int i = 1; i <= limit; i++) {
			DepthLimitedSearch<V> dls = new DepthLimitedSearch<V>(startingVertex, graph, i);
			dls.execute();
			traversalSets.add(dls.getTraversalSet());
		}
	}
	
	/**
	 * Executes the search with a goal.
	 * 
	 * @param goalVertex the goal
	 */
	public void execute(V goalVertex) {
		for (int i = 1; i <= limit; i++) {
			DepthLimitedSearch<V> dls = new DepthLimitedSearch<V>(startingVertex, graph, i);
			dls.execute(goalVertex);
			traversalSets.add(dls.getTraversalSet());
			if (dls.getTraversalSet().contains(goalVertex)) {
				return;
			}
		}
	}
	
	/**
	 * Gets the traversal sets.
	 * 
	 * @return the traversal sets
	 */
	public List<Set<V>> getTraversalSets() {
		return this.traversalSets;
	}

}
