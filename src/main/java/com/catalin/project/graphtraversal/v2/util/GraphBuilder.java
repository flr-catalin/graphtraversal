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
		WeightedEdge fagaras_sibiu = graph.addEdge(FAGARAS, SIBIU);
		WeightedEdge fagaras_bucuresti = graph.addEdge(FAGARAS, BUCURESTI);
		WeightedEdge sibiu_oradea = graph.addEdge(SIBIU, ORADEA);
		WeightedEdge sibiu_arad = graph.addEdge(SIBIU, ARAD);
		WeightedEdge sibiu_ramnicu = graph.addEdge(SIBIU, RAMNICU);
		WeightedEdge bucuresti_giurgiu = graph.addEdge(BUCURESTI, GIURGIU);
		WeightedEdge bucuresti_urziceni = graph.addEdge(BUCURESTI, URZICENI);
		WeightedEdge oradea_zerind = graph.addEdge(ORADEA, ZERIND);
		WeightedEdge arad_timisoara = graph.addEdge(ARAD, TIMISOARA);
		WeightedEdge ramnicu_craiova = graph.addEdge(RAMNICU, CRAIOVA);
		WeightedEdge ramnicu_pitesti = graph.addEdge(RAMNICU, PITESTI);
		WeightedEdge urziceni_vaslui = graph.addEdge(URZICENI, VASLUI);
		WeightedEdge urziceni_harsova = graph.addEdge(URZICENI, HARSOVA);
		WeightedEdge timisoara_lugoj = graph.addEdge(TIMISOARA, LUGOJ);
		WeightedEdge harsova_eforie = graph.addEdge(HARSOVA, EFORIE);
		WeightedEdge vaslui_iasi = graph.addEdge(VASLUI, IASI);
		WeightedEdge lugoj_mehadia = graph.addEdge(LUGOJ, MEHADIA);
		WeightedEdge iasi_neamt = graph.addEdge(IASI, NEAMT);
		WeightedEdge mehadia_drobeta = graph.addEdge(MEHADIA, DROBETA);
		
		graph.setEdgeWeight(oradea_zerind, 71);
		graph.setEdgeWeight(sibiu_oradea, 151);
		graph.setEdgeWeight(sibiu_arad, 140);
		graph.setEdgeWeight(arad_timisoara, 118);
		graph.setEdgeWeight(timisoara_lugoj, 111);
		graph.setEdgeWeight(lugoj_mehadia, 70);
		graph.setEdgeWeight(mehadia_drobeta, 75);
		graph.setEdgeWeight(ramnicu_craiova, 146);
		graph.setEdgeWeight(sibiu_ramnicu, 80);
		graph.setEdgeWeight(ramnicu_pitesti, 97);
		graph.setEdgeWeight(fagaras_sibiu, 99);
		graph.setEdgeWeight(fagaras_bucuresti, 211);
		graph.setEdgeWeight(bucuresti_giurgiu, 99);
		graph.setEdgeWeight(bucuresti_urziceni, 85);
		graph.setEdgeWeight(urziceni_harsova, 98);
		graph.setEdgeWeight(harsova_eforie, 86);
		graph.setEdgeWeight(urziceni_vaslui, 142);
		graph.setEdgeWeight(vaslui_iasi, 92);
		graph.setEdgeWeight(iasi_neamt, 87);
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
	}

}
