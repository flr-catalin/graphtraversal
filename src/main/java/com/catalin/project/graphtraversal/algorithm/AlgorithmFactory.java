package com.catalin.project.graphtraversal.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import com.catalin.project.graphtraversal.datatypes.City;
import com.catalin.project.graphtraversal.datatypes.EmptyEdge;

public class AlgorithmFactory {
	
	private static final AlgorithmFactory INSTANCE = new AlgorithmFactory();
	
	protected AlgorithmFactory() {
		super();
	}
	
	public static AlgorithmFactory getInstance() {
		return INSTANCE;
	}
	
	public void DFS(Graph<City, EmptyEdge> graph, City startingCity) {
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
	
	public void BFS(Graph<City, EmptyEdge> graph, City startingCity) {
		Queue<City> frontier = new LinkedList<>(Arrays.asList(startingCity));
		Set<City> explored = new HashSet<>();
		
		System.out.println("BFS:");
		
		while (!frontier.isEmpty()) {
			startingCity = frontier.poll();
			explored.add(startingCity);
			
			System.out.println(startingCity);
			
			List<City> neighborListOf = Graphs.neighborListOf(graph, startingCity);
			for (City city : neighborListOf) {
				if (!explored.contains(city))
				frontier.add(city);
				explored.add(startingCity);
			}
		}
		
		System.out.println();
	}

}
