package com.catalin.project.graphtraversal.util;

import static com.catalin.project.graphtraversal.datatypes.City.ARAD;
import static com.catalin.project.graphtraversal.datatypes.City.BUCURESTI;
import static com.catalin.project.graphtraversal.datatypes.City.CRAIOVA;
import static com.catalin.project.graphtraversal.datatypes.City.DROBETA;
import static com.catalin.project.graphtraversal.datatypes.City.EFORIE;
import static com.catalin.project.graphtraversal.datatypes.City.FAGARAS;
import static com.catalin.project.graphtraversal.datatypes.City.GIURGIU;
import static com.catalin.project.graphtraversal.datatypes.City.HARSOVA;
import static com.catalin.project.graphtraversal.datatypes.City.IASI;
import static com.catalin.project.graphtraversal.datatypes.City.LUGOJ;
import static com.catalin.project.graphtraversal.datatypes.City.MEHADIA;
import static com.catalin.project.graphtraversal.datatypes.City.NEAMT;
import static com.catalin.project.graphtraversal.datatypes.City.ORADEA;
import static com.catalin.project.graphtraversal.datatypes.City.PITESTI;
import static com.catalin.project.graphtraversal.datatypes.City.RAMNICU;
import static com.catalin.project.graphtraversal.datatypes.City.SIBIU;
import static com.catalin.project.graphtraversal.datatypes.City.TIMISOARA;
import static com.catalin.project.graphtraversal.datatypes.City.VASLUI;
import static com.catalin.project.graphtraversal.datatypes.City.VRANCEA;
import static com.catalin.project.graphtraversal.datatypes.City.ZERIND;

import org.jgrapht.Graph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;

import com.catalin.project.graphtraversal.datatypes.City;
import com.catalin.project.graphtraversal.datatypes.EmptyEdge;

public class GraphBuilder {
	
	private static final GraphBuilder INSTANCE = new GraphBuilder();
	
	protected GraphBuilder() {
		super();
	}
	
	public static GraphBuilder getInstance() {
		return INSTANCE;
	}
	
	public Graph<City, EmptyEdge> buildDefaultGraph() {
		Graph<City, EmptyEdge> graph = GraphTypeBuilder
				.undirected()
				.allowingMultipleEdges(true)
				.allowingSelfLoops(true)
				.vertexSupplier(SupplierUtil.createSupplier(City.class))
				.edgeSupplier(SupplierUtil.createSupplier(EmptyEdge.class))
				.buildGraph();
		
		graph.addVertex(FAGARAS);
		graph.addVertex(SIBIU);
		graph.addVertex(BUCURESTI);
		graph.addVertex(ORADEA);
		graph.addVertex(ARAD);
		graph.addVertex(RAMNICU);
		graph.addVertex(PITESTI);
		graph.addVertex(GIURGIU);
		graph.addVertex(VRANCEA);
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
		
		graph.addEdge(FAGARAS, SIBIU);
		graph.addEdge(FAGARAS, BUCURESTI);
		graph.addEdge(SIBIU, ORADEA);
		graph.addEdge(SIBIU, ARAD);
		graph.addEdge(SIBIU, RAMNICU);
		graph.addEdge(BUCURESTI, PITESTI);
		graph.addEdge(BUCURESTI, GIURGIU);
		graph.addEdge(BUCURESTI, VRANCEA);
		graph.addEdge(ORADEA, ZERIND);
		graph.addEdge(ARAD, TIMISOARA);
		graph.addEdge(RAMNICU, CRAIOVA);
		graph.addEdge(GIURGIU, HARSOVA);
		graph.addEdge(VRANCEA, VASLUI);
		graph.addEdge(TIMISOARA, LUGOJ);
		graph.addEdge(CRAIOVA, DROBETA);
		graph.addEdge(HARSOVA, EFORIE);
		graph.addEdge(VASLUI, IASI);
		graph.addEdge(LUGOJ, MEHADIA);
		graph.addEdge(IASI, NEAMT);
		
		return graph;
	}

}
