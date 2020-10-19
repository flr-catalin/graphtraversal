package com.catalin.project.graphtraversal.v2.util;

import static com.catalin.project.graphtraversal.v2.datatypes.City.ARAD;
import static com.catalin.project.graphtraversal.v2.datatypes.City.BUCURESTI;
import static com.catalin.project.graphtraversal.v2.datatypes.City.CRAIOVA;
import static com.catalin.project.graphtraversal.v2.datatypes.City.DROBETA;
import static com.catalin.project.graphtraversal.v2.datatypes.City.EFORIE;
import static com.catalin.project.graphtraversal.v2.datatypes.City.FAGARAS;
import static com.catalin.project.graphtraversal.v2.datatypes.City.GIURGIU;
import static com.catalin.project.graphtraversal.v2.datatypes.City.HARSOVA;
import static com.catalin.project.graphtraversal.v2.datatypes.City.IASI;
import static com.catalin.project.graphtraversal.v2.datatypes.City.LUGOJ;
import static com.catalin.project.graphtraversal.v2.datatypes.City.MEHADIA;
import static com.catalin.project.graphtraversal.v2.datatypes.City.NEAMT;
import static com.catalin.project.graphtraversal.v2.datatypes.City.ORADEA;
import static com.catalin.project.graphtraversal.v2.datatypes.City.PITESTI;
import static com.catalin.project.graphtraversal.v2.datatypes.City.RAMNICU;
import static com.catalin.project.graphtraversal.v2.datatypes.City.SIBIU;
import static com.catalin.project.graphtraversal.v2.datatypes.City.TIMISOARA;
import static com.catalin.project.graphtraversal.v2.datatypes.City.URZICENI;
import static com.catalin.project.graphtraversal.v2.datatypes.City.VASLUI;
import static com.catalin.project.graphtraversal.v2.datatypes.City.ZERIND;

import java.util.Set;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.City;
import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;

/**
 * Utilitary class for building graphs.
 *
 * @author Catalin Florea
 */
public class GraphBuilder {
	
	/** The instance. */
	private static final GraphBuilder INSTANCE = new GraphBuilder();
	
	/**
	 * The protected constructor.
	 **/
	protected GraphBuilder() {
		super();
	}
	
	/**
	 * Gets the single instance.
	 * 
	 * @return the instance
	 */
	public static GraphBuilder getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Creates a default directed weighted graph.
	 * 
	 * @return the default directed weighted graph.
	 */
	public DefaultDirectedWeightedGraph<City, WeightedEdge> buildDefaultDirectedWeightGraph() {
		DefaultDirectedWeightedGraph<City, WeightedEdge> graph = new DefaultDirectedWeightedGraph<>(WeightedEdge.class);
		
		addVertices(graph);
		addWeigthedEdges(graph);
		
		return graph;
	}

	/**
	 * Adds the weighted edges.
	 * 
	 * @param graph the graph
	 */
	private void addWeigthedEdges(DefaultDirectedWeightedGraph<City, WeightedEdge> graph) {
		graph.addEdge(FAGARAS, SIBIU);
		graph.addEdge(FAGARAS, BUCURESTI);
		graph.addEdge(SIBIU, ORADEA);
		graph.addEdge(SIBIU, ARAD);
		graph.addEdge(SIBIU, RAMNICU);
		graph.addEdge(BUCURESTI, GIURGIU);
		graph.addEdge(BUCURESTI, URZICENI);
		graph.addEdge(ORADEA, ZERIND);
		graph.addEdge(ARAD, TIMISOARA);
		graph.addEdge(RAMNICU, CRAIOVA);
		graph.addEdge(RAMNICU, PITESTI);
		graph.addEdge(URZICENI, VASLUI);
		graph.addEdge(URZICENI, HARSOVA);
		graph.addEdge(TIMISOARA, LUGOJ);
		graph.addEdge(HARSOVA, EFORIE);
		graph.addEdge(VASLUI, IASI);
		graph.addEdge(LUGOJ, MEHADIA);
		graph.addEdge(IASI, NEAMT);
		graph.addEdge(MEHADIA, DROBETA);
	}

	/**
	 * Adds the vertices.
	 * 
	 * @param graph the graph
	 */
	private void addVertices(DefaultDirectedWeightedGraph<City, WeightedEdge> graph) {
		graph.addVertex(FAGARAS);
		graph.addVertex(SIBIU);
		graph.addVertex(BUCURESTI);
		graph.addVertex(ORADEA);
		graph.addVertex(ARAD);
		graph.addVertex(RAMNICU);
		graph.addVertex(PITESTI);
		graph.addVertex(GIURGIU);
		graph.addVertex(URZICENI);
		graph.addVertex(ZERIND);
		graph.addVertex(TIMISOARA);
		graph.addVertex(CRAIOVA);
		graph.addVertex(HARSOVA);
		graph.addVertex(VASLUI);
		graph.addVertex(LUGOJ);
		graph.addVertex(DROBETA);
		graph.addVertex(EFORIE);
		graph.addVertex(IASI);
		graph.addVertex(MEHADIA);
		graph.addVertex(NEAMT);
		
		Set<City> vertexSet = graph.vertexSet();
		
		for (City city : vertexSet) {
			city.setHeuristic("999 (999)");
		}
	}

}
