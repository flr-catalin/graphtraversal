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

import java.util.Collection;
import java.util.Set;

import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import com.catalin.project.graphtraversal.v2.datatypes.City;
import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;
import com.mxgraph.model.mxCell;

/**
 * Utilitary class for modifying graphs.
 * 
 * @author Catalin Florea
 */
public class GraphModifier {

	/** The instance. */
	private static final GraphModifier INSTANCE = new GraphModifier();
	
	/**
	 * The protected constructor.
	 **/
	protected GraphModifier() {
		super();
	}
	
	/**
	 * Gets the single instance.
	 * 
	 * @return the instance
	 */
	public static GraphModifier getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Updates the heuristic value.
	 * 
	 * @param graphAdapter the graph adapter
	 * @param cell the cell
	 * @param heuristic the heuristic
	 */
	public void updateHeuristic(JGraphXAdapter<City, WeightedEdge> graphAdapter, mxCell cell, Integer heuristic) {
		graphAdapter.getModel().beginUpdate();
		try {
			graphAdapter.getCellToVertexMap().get(cell).setHeuristic(heuristic.toString());
			graphAdapter.updateCellSize(cell, true);
		} finally {
			graphAdapter.getModel().endUpdate();
			graphAdapter.refresh();
		}
	}
	
	/**
	 * Clears the heuristic values.
	 * 
	 * @param graphAdapter the graph adapter
	 */
	public void clearHeuristics(JGraphXAdapter<City, WeightedEdge> graphAdapter) {
		graphAdapter.getModel().beginUpdate();
		try {
			Collection<City> cities = graphAdapter.getCellToVertexMap().values();
			
			for (City city : cities) {
				city.setHeuristic("0");
				graphAdapter.updateCellSize(city, true);
			}
		} finally {
			graphAdapter.getModel().endUpdate();
			graphAdapter.refresh();
		}
	}
	
	/**
	 * Initialises the best first search heuristics.
	 * 
	 * @param graphAdapter the graph adapter
	 */
	public void initialiseBestFirstSearchHeuristics(JGraphXAdapter<City, WeightedEdge> graphAdapter) {
		graphAdapter.getModel().beginUpdate();
		try {
			Collection<City> cities = graphAdapter.getCellToVertexMap().values();
			for (City city : cities) {
				switch (city) {
				case ARAD:
					city.setHeuristic("621");
					break;
				case BUCURESTI:
					city.setHeuristic("207");
					break;
				case CRAIOVA:
					city.setHeuristic("387");
					break;
				case DROBETA:
					city.setHeuristic("481");
					break;
				case EFORIE:
					city.setHeuristic("0");
					break;
				case FAGARAS:
					city.setHeuristic("352");
					break;
				case GIURGIU:
					city.setHeuristic("214");
					break;
				case HARSOVA:
					city.setHeuristic("91");
					break;
				case IASI:
					city.setHeuristic("356");
					break;
				case LUGOJ:
					city.setHeuristic("561");
					break;
				case MEHADIA:
					city.setHeuristic("507");
					break;
				case NEAMT:
					city.setHeuristic("367");
					break;
				case ORADEA:
					city.setHeuristic("621");
					break;
				case PITESTI:
					city.setHeuristic("313");
					break;
				case RAMNICU:
					city.setHeuristic("359");
					break;
				case SIBIU:
					city.setHeuristic("404");
					break;
				case TIMISOARA:
					city.setHeuristic("614");
					break;
				case URZICENI:
					city.setHeuristic("175");
					break;
				case VASLUI:
					city.setHeuristic("297");
					break;
				case ZERIND:
					city.setHeuristic("626");
					break;
				}
			}
		} finally {
			graphAdapter.refresh();
			graphAdapter.getModel().endUpdate();
		}
	}
	
	/**
	 * Clears the weights.
	 * 
	 * @param graph the graph
	 * @param graphAdapter the graph adaptor
	 */
	public void clearWeights(DefaultDirectedWeightedGraph<City, WeightedEdge> graph, JGraphXAdapter<City, WeightedEdge> graphAdapter) {
		graphAdapter.getModel().beginUpdate();
		try {
			Collection<WeightedEdge> edges = graphAdapter.getCellToEdgeMap().values();
			for (WeightedEdge weightedEdge : edges) {
				graph.setEdgeWeight(weightedEdge, 0);
			}
		} finally {
			graphAdapter.getModel().endUpdate();
			graphAdapter.refresh();
		}
	}
	
	/**
	 * Initialises uniform cost search weights.
	 * 
	 * @param graph the graph
	 * @param graphAdapter the graph adapter
	 */
	public void initialiseUniformCostSearchWeights(DefaultDirectedWeightedGraph<City, WeightedEdge> graph, JGraphXAdapter<City, WeightedEdge> graphAdapter) {
		graphAdapter.getModel().beginUpdate();
		try {
			Set<WeightedEdge> edgeSet = graph.edgeSet();
			for (WeightedEdge edge : edgeSet) {
				if (FAGARAS.equals(graph.getEdgeSource(edge))
						&& SIBIU.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 99);
				} else if (FAGARAS.equals(graph.getEdgeSource(edge))
						&& BUCURESTI.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 211);
				} else if (SIBIU.equals(graph.getEdgeSource(edge))
						&& ORADEA.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 151);
				} else if (SIBIU.equals(graph.getEdgeSource(edge))
						&& ARAD.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 140);
				} else if (SIBIU.equals(graph.getEdgeSource(edge))
						&& RAMNICU.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 80);
				} else if (BUCURESTI.equals(graph.getEdgeSource(edge))
						&& GIURGIU.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 99);
				} else if (BUCURESTI.equals(graph.getEdgeSource(edge))
						&& URZICENI.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 85);
				} else if (ORADEA.equals(graph.getEdgeSource(edge))
						&& ZERIND.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 71);
				} else if (ARAD.equals(graph.getEdgeSource(edge))
						&& TIMISOARA.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 118);
				} else if (RAMNICU.equals(graph.getEdgeSource(edge))
						&& CRAIOVA.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 146);
				} else if (RAMNICU.equals(graph.getEdgeSource(edge))
						&& PITESTI.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 97);
				} else if (URZICENI.equals(graph.getEdgeSource(edge))
						&& VASLUI.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 142);
				} else if (URZICENI.equals(graph.getEdgeSource(edge))
						&& HARSOVA.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 98);
				} else if (TIMISOARA.equals(graph.getEdgeSource(edge))
						&& LUGOJ.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 111);
				} else if (HARSOVA.equals(graph.getEdgeSource(edge))
						&& EFORIE.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 86);
				} else if (VASLUI.equals(graph.getEdgeSource(edge))
						&& IASI.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 92);
				} else if (LUGOJ.equals(graph.getEdgeSource(edge))
						&& MEHADIA.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 70);
				} else if (IASI.equals(graph.getEdgeSource(edge))
						&& NEAMT.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 87);
				} else if (MEHADIA.equals(graph.getEdgeSource(edge))
						&& DROBETA.equals(graph.getEdgeTarget(edge))) {
					graph.setEdgeWeight(edge, 75);
				}
			}
		} finally {
			graphAdapter.getModel().endUpdate();
			graphAdapter.refresh();
		}
	}
	
}
