package com.catalin.project.graphtraversal.v2.algorithm;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.City;
import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;
import com.mxgraph.model.mxICell;

/**
 * This class implements the uniform cost search.
 * 
 * @author Catalin Florea
 */
public class UniformCostSearch {
	
	/** The vertex queue. */
	private Queue<City> vertexQueue;
	
	/** The traversal set. */
	private Set<City> traversalSet;
	
	/** The starting vertex. */
	private City startingVertex;
	
	/** The graph. */
	private DefaultDirectedGraph<City, WeightedEdge> graph;
	
	/** The JGraph to mxGraph adapter. */
	private JGraphXAdapter<City, WeightedEdge> graphAdapter;
	
	/**
	 * Creates a new uniform cost search object.
	 * 
	 * @param startingVertex the starting vertex
	 * @param graph the graph
	 */
	public UniformCostSearch(City startingVertex, DefaultDirectedGraph<City, WeightedEdge> graph, JGraphXAdapter<City, WeightedEdge> graphAdapter) {
		super();
		this.vertexQueue = new PriorityQueue<>(new Comparator<City>() {

			@Override
			public int compare(City o1, City o2) {
				if (o1.getHeuristic() > o2.getHeuristic()) {
					return 1;
				} else if (o1.getHeuristic() < o2.getHeuristic()) {
					return -1;
				}
				return 0;
			}
			
		});
		this.traversalSet = new LinkedHashSet<>();
		this.startingVertex = startingVertex;
		this.graph = graph;
		this.graphAdapter = graphAdapter;
	}
	
	/**
	 * Executes the search.
	 */
	public void execute() {
		vertexQueue.add(startingVertex);
		
		while (!vertexQueue.isEmpty()) {
			startingVertex = vertexQueue.poll();
			traversalSet.add(startingVertex);
			
			List<City> neighborListOf = Graphs.successorListOf(graph, startingVertex);
			for (City vertex : neighborListOf) {
				vertex.setHeuristic(startingVertex.getHeuristic() + getPathCost(vertex));
				
				if (!vertexQueue.contains(vertex) || !traversalSet.contains(vertex)) {
					vertexQueue.add(vertex);
				} else if (vertexQueue.contains(vertex) && vertex.getHeuristic() > startingVertex.getHeuristic()) {
					mxICell cityCell = graphAdapter.getVertexToCellMap().get(vertex);
					mxICell parentCell = graphAdapter.getVertexToCellMap().get(startingVertex);
					cityCell.setParent(parentCell);
					vertexQueue.add(vertex);
				}
			}
		}
	}
	
	/**
	 * Executes the search with a goal.
	 * 
	 * @param goalVertex the goal
	 */
	public void execute(City goalVertex) {
		vertexQueue.add(startingVertex);
		
		while (!vertexQueue.isEmpty()) {
			startingVertex = vertexQueue.poll();
			traversalSet.add(startingVertex);
			
			if (startingVertex.equals(goalVertex)) {
				return;
			}
			
			List<City> neighborListOf = Graphs.successorListOf(graph, startingVertex);
			for (City vertex : neighborListOf) {
				vertex.setHeuristic(startingVertex.getHeuristic() + getPathCost(vertex));
				
				if (!vertexQueue.contains(vertex) || !traversalSet.contains(vertex)) {
					vertexQueue.add(vertex);
				} else if (vertexQueue.contains(vertex) && vertex.getHeuristic() > startingVertex.getHeuristic()) {
					mxICell cityCell = graphAdapter.getVertexToCellMap().get(vertex);
					mxICell parentCell = graphAdapter.getVertexToCellMap().get(startingVertex);
					cityCell.setParent(parentCell);
					vertexQueue.add(vertex);
				}
			}
		}
	}

	/**
	 * Calculates the path cost
	 * 
	 * @param city the edge target
	 * @return the path cost
	 */
	private int getPathCost(City city) {
		WeightedEdge weightedEdge = null;
		Set<WeightedEdge> edgeSet = graph.edgeSet();
		for (WeightedEdge edge : edgeSet) {
			if (startingVertex.equals(graph.getEdgeSource(edge))
					&& city.equals(graph.getEdgeTarget(edge))) {
				weightedEdge = edge;
			}
		}
		
		return (int) graph.getEdgeWeight(weightedEdge);
	}
	
	/**
	 * Gets the traversal set.
	 * 
	 * @return the traversal set
	 */
	public Set<City> getTraversalSet() {
		return this.traversalSet;
	}
	
}
