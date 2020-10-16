package com.catalin.project.graphtraversal.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.catalin.project.graphtraversal.datatypes.City;
import com.catalin.project.graphtraversal.datatypes.WeightedEdge;

public class AlgorithmFactory {
	
	private static final AlgorithmFactory INSTANCE = new AlgorithmFactory();
	
	protected AlgorithmFactory() {
		super();
	}
	
	public static AlgorithmFactory getInstance() {
		return INSTANCE;
	}
	
	public void DFS(DefaultDirectedGraph<City, WeightedEdge> graph, City startingCity) {
		Stack<City> stack = new Stack<>();
		stack.push(startingCity);
		
		System.out.println("DFS:");
		
		while (!stack.isEmpty()) {
			startingCity = stack.pop();
			if (startingCity.isNotDiscovered()) {
				startingCity.setDiscovered(true);
				
				System.out.println(startingCity);
				
				List<City> neighborListOf = Graphs.neighborListOf(graph, startingCity);
				Collections.reverse(neighborListOf);
				for (City city : neighborListOf) {
					stack.push(city);
				}
			}
		}
		
		System.out.println();
	}
	
	public void BFS(DefaultDirectedGraph<City, WeightedEdge> graph, City startingCity) {
		Queue<City> queue = new LinkedList<>(Arrays.asList(startingCity));
		Set<City> explored = new HashSet<>();
		
		System.out.println("BFS:");
		
		while (!queue.isEmpty()) {
			startingCity = queue.poll();
			explored.add(startingCity);
			
			System.out.println(startingCity);
			
			List<City> neighborListOf = Graphs.neighborListOf(graph, startingCity);
			for (City city : neighborListOf) {
				if (!explored.contains(city))
				queue.add(city);
				explored.add(startingCity);
			}
		}
		
		System.out.println();
	}
	
	public void DLS(DefaultDirectedGraph<City, WeightedEdge> graph, City startingCity, int limit) {
		System.out.println("DLS: ");
		
		DLS_RECURSIVE(graph, startingCity, limit);
		
		System.out.println();
	}
	
	public boolean DLS_RECURSIVE(DefaultDirectedGraph<City, WeightedEdge> graph, City startingCity, int limit) {
		if (limit <= 0) {
			return false;
		}
		
		System.out.println(startingCity);
		
		List<City> neighborListOf = Graphs.successorListOf(graph, startingCity);
		for (City city : neighborListOf) {
			if (DLS_RECURSIVE(graph, city, limit - 1)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean IDS(DefaultDirectedGraph<City, WeightedEdge> graph, City startingCity, int limit) {
		for (int i = 1; i <= limit; i++) {
			
			System.out.println("IDS: limit = " + i);
			
			if (DLS_RECURSIVE(graph, startingCity, i)) {
				return true;
			}

			System.out.println();
		}
		
		return false;
	}
	
	public void UCS(DefaultDirectedGraph<City, WeightedEdge> graph, City startingCity, int limit) {
	}
	
}
