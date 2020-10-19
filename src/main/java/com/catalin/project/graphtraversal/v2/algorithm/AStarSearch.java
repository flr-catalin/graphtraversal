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
	
	/** The g score. */
	private Map<City, Integer> gScore;
	
	/** The f score. */
	private Map<City, Integer> fScore;
	
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
		this.gScore = new HashMap<>();
		this.fScore = new HashMap<>();
		this.cameFrom = new HashMap<>();
	}
	
	/**
	 * Executes the search.
	 */
	public void execute() {
		openSet.add(currentVertex);
		
		gScore.put(currentVertex, 0);
		fScore.put(currentVertex, currentVertex.getHeuristic());
		
		while (!openSet.isEmpty()) {
			currentVertex = getFScoreMin();
			traversalSet.add(currentVertex);
			
			System.out.println("Chosen node: " + currentVertex);
			
			openSet.remove(currentVertex);
			List<City> neighborListOf = Graphs.successorListOf(graph, currentVertex);
			for (City vertex : neighborListOf) {
				cameFrom.put(vertex, currentVertex);
				int tentativeGScore = vertex.getHeuristic() + calculatePathCost(vertex);
				if (tentativeGScore < gScore.getOrDefault(vertex, Integer.MAX_VALUE)) {
					gScore.put(vertex, tentativeGScore);
					fScore.put(vertex, gScore.getOrDefault(vertex, Integer.MAX_VALUE));
					
					System.out.println("Node: " + vertex + "; f(node)=" + gScore.getOrDefault(vertex, Integer.MAX_VALUE));
					
					if (!openSet.contains(vertex)) {
						openSet.add(vertex);
					}
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
			if (gScore.get(next) < fScoreMin) {
				fScoreMin = gScore.get(next);
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

}
