package com.catalin.project.graphtraversal.v2.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.City;
import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;

public class AStarSearch {
	
	/** The open set. */
	private Set<City> openSet;
	
	/** The traversal set. */
	private Set<City> traversalSet;
	
	/** The current vertex. */
	private City currentVertex;
	
	/** The starting vertex. */
	private City startingVertex;
	
	/** The graph. */
	private DefaultDirectedGraph<City, WeightedEdge> graph;
	
	/** The heuristics map. */
	private Map<City, Integer> heuristics;
	
	/** Came from map. */
	private Map<City, City> cameFrom;

	/**
	 * Creates a new a star search object.
	 * 
	 * @param startingVertex the starting vertex
	 * @param graph the graph
	 * @param graphAdapter the graph adapter
	 */
	public AStarSearch(City startingVertex, DefaultDirectedGraph<City, WeightedEdge> graph) {
		super();
		this.openSet = new LinkedHashSet<>();
		this.traversalSet = new LinkedHashSet<>();
		this.currentVertex = startingVertex;
		this.startingVertex = startingVertex;
		this.graph = graph;
		this.heuristics = new HashMap<>();
		this.cameFrom = new HashMap<>();
	}
	
	/**
	 * Executes the search.
	 */
	public void execute() {
		System.out.println("All A* Search heuristic calculations:");
		openSet.add(currentVertex);
		
		heuristics.put(currentVertex, 0);
		
		while (!openSet.isEmpty()) {
			currentVertex = getFScoreMin();
			traversalSet.add(currentVertex);
			
			openSet.remove(currentVertex);
			List<City> neighborListOf = Graphs.successorListOf(graph, currentVertex);
			for (City vertex : neighborListOf) {
				cameFrom.put(vertex, currentVertex);
				int tentativeGScore = Integer.parseInt(vertex.getHeuristic()) + calculatePathCost(vertex);
				if (tentativeGScore < heuristics.getOrDefault(vertex, Integer.MAX_VALUE)) {
					heuristics.put(vertex, tentativeGScore);
					
					System.out.println("Node: " + vertex + "; Heuristic: " + heuristics.getOrDefault(vertex, Integer.MAX_VALUE));
					
					if (!openSet.contains(vertex)) {
						openSet.add(vertex);
					}
				}
			}
		}
		
		System.out.println();
	}
	
	/**
	 * Executes the search with a goal node.
	 */
	public void execute(City goalVertex) {
		System.out.println("All A* Search heuristic calculations:");
		openSet.add(currentVertex);
		
		heuristics.put(currentVertex, 0);
		
		while (!openSet.isEmpty()) {
			currentVertex = getFScoreMin();
			traversalSet.add(currentVertex);
			
			if (currentVertex.equals(goalVertex)) {
				return;
			}
			
			openSet.remove(currentVertex);
			List<City> neighborListOf = Graphs.successorListOf(graph, currentVertex);
			for (City vertex : neighborListOf) {
				cameFrom.put(vertex, currentVertex);
				int tentativeGScore = Integer.parseInt(vertex.getHeuristic()) + calculatePathCost(vertex);
				if (tentativeGScore < heuristics.getOrDefault(vertex, Integer.MAX_VALUE)) {
					heuristics.put(vertex, tentativeGScore);
					
					System.out.println("Node: " + vertex + "; Heuristic: " + heuristics.getOrDefault(vertex, Integer.MAX_VALUE));
					
					if (!openSet.contains(vertex)) {
						openSet.add(vertex);
					}
				}
			}
		}
		
		System.out.println();
	}
	
	/**
	 * Calculates the path cost
	 * 
	 * @param city the edge target
	 * @return the path cost
	 */
	private int calculatePathCost(City city) {
		if (startingVertex.equals(city)) {
			return 0;
		}
		
		int pathCost = 0;
		Set<WeightedEdge> edgeSet = graph.edgeSet();
		for (WeightedEdge edge : edgeSet) {
			if (city.equals(graph.getEdgeTarget(edge))
					&& cameFrom.get(city).equals(graph.getEdgeSource(edge))) {
				pathCost = (int) graph.getEdgeWeight(edge);
				break;
			}
		}
		
		return calculatePathCost(cameFrom.get(city)) + pathCost;
	}
	
	/**
	 * Gets the city with the lowest f score.
	 * 
	 * @return the city
	 */
	private City getFScoreMin() {
		Integer fScoreMin = Integer.MAX_VALUE;
		City cityWithFScoreMin = null;
		
		Iterator<City> iterator = openSet.iterator();
		
		while (iterator.hasNext()) {
			City next = iterator.next();
			if (heuristics.get(next) < fScoreMin) {
				fScoreMin = heuristics.get(next);
				cityWithFScoreMin = next;
			}
		}
		
		return cityWithFScoreMin;
	}
	
	/**
	 * Gets the traversal set.
	 * 
	 * @return the traversal set
	 */
	public Set<City> getTraversalSet() {
		return this.traversalSet;
	}
	
	/**
	 * Gets the heuristics map.
	 * 
	 * @return the heuristics map
	 */
	public Map<City, Integer> getHeuristics() {
		return this.heuristics;
	}

}
