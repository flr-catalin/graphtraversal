package com.catalin.project.graphtraversal;

import static com.catalin.project.graphtraversal.reference.City.ARAD;
import static com.catalin.project.graphtraversal.reference.City.BUCURESTI;
import static com.catalin.project.graphtraversal.reference.City.CRAIOVA;
import static com.catalin.project.graphtraversal.reference.City.DROBETA;
import static com.catalin.project.graphtraversal.reference.City.EFORIE;
import static com.catalin.project.graphtraversal.reference.City.FAGARAS;
import static com.catalin.project.graphtraversal.reference.City.GIURGIU;
import static com.catalin.project.graphtraversal.reference.City.HARSOVA;
import static com.catalin.project.graphtraversal.reference.City.IASI;
import static com.catalin.project.graphtraversal.reference.City.LUGOJ;
import static com.catalin.project.graphtraversal.reference.City.MEHADIA;
import static com.catalin.project.graphtraversal.reference.City.NEAMT;
import static com.catalin.project.graphtraversal.reference.City.ORADEA;
import static com.catalin.project.graphtraversal.reference.City.PITESTI;
import static com.catalin.project.graphtraversal.reference.City.RAMNICU;
import static com.catalin.project.graphtraversal.reference.City.SIBIU;
import static com.catalin.project.graphtraversal.reference.City.TIMISOARA;
import static com.catalin.project.graphtraversal.reference.City.VASLUI;
import static com.catalin.project.graphtraversal.reference.City.VRANCEA;
import static com.catalin.project.graphtraversal.reference.City.ZERIND;

import java.awt.Dimension;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;

import com.catalin.project.graphtraversal.reference.City;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxStyleUtils;

public class DemoApp {
	
	private static JFrame mainFrame;
	
	private static mxCompactTreeLayout layout;
	
	private static Graph<City, DefaultEdge> graph;
	
	private static JGraphXAdapter<City, DefaultEdge> graphAdapter;
	
	public static final int JFRAME_WIDTH = 1024;
	
	public static final int JFRAME_HEIGHT = 720;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				initialise();
				createAndShowGui();
			}
			
		});
	}
	
	private static void initialise() {
		mainFrame = new JFrame("DEMO");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		graph = buildGraph();
		graphAdapter = new JGraphXAdapter<>(graph);
		layout = new mxCompactTreeLayout(graphAdapter);
	}
	
	private static void createAndShowGui() {
		executeLayout();
		createFrame();
		
		DFS(FAGARAS);
	}

	private static mxCompactTreeLayout executeLayout() {
		layout.setHorizontal(false);
		layout.setNodeDistance(50);
		layout.execute(graphAdapter.getDefaultParent());
		
		return layout;
	}
	
	private static void createFrame() {
		mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
		mxGraphModel graphModel = (mxGraphModel) graphComponent.getGraph().getModel();
        Collection<Object> cells = graphModel.getCells().values();
		mxStyleUtils.setCellStyles(graphComponent.getGraph().getModel(),
                cells.toArray(), mxConstants.STYLE_ENDARROW, mxConstants.NONE);
		
		graphAdapter.setCellsMovable(false);
		graphAdapter.setEdgeLabelsMovable(false);
		graphAdapter.setVertexLabelsMovable(false);
		
		mainFrame.add(graphComponent);
		mainFrame.setSize(new Dimension(JFRAME_WIDTH, JFRAME_HEIGHT));
		mainFrame.setLocationByPlatform(true);
		mainFrame.setVisible(true);
	}

	private static Graph<City, DefaultEdge> buildGraph() {
		Graph<City, DefaultEdge> graph = GraphTypeBuilder
				.undirected()
				.allowingMultipleEdges(true)
				.allowingSelfLoops(true)
				.vertexSupplier(SupplierUtil.createSupplier(City.class))
				.edgeSupplier(SupplierUtil.createDefaultEdgeSupplier())
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
	
	private static void DFS(City startingNode) {
		Stack<City> stack = new Stack<>();
		stack.push(startingNode);
		
		while (!stack.isEmpty()) {
			startingNode = stack.pop();
			if (startingNode.isNotDiscovered()) {
				startingNode.setDiscovered(true);
				
				System.out.println(startingNode);
				
				List<City> neighborListOf = Graphs.neighborListOf(graph, startingNode);
				Collections.reverse(neighborListOf);
				for (City city : neighborListOf) {
					stack.push(city);
				}
			}
		}
	}
	
}
