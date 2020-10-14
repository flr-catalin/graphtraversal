package com.catalin.project.graphtraversal;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;

public class DemoApp {
	
	public static final int JFRAME_WIDTH = 250;
	
	public static final int JFRAME_HEIGHT = 300;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				createAndShowGui();
			}
			
		});
	}
	
	private static void createAndShowGui() {
		JFrame frame = new JFrame("DEMO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ListenableGraph<String, DefaultEdge> listenableGraph = buildGraph();
		JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<>(listenableGraph);
		
		mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
		layout.execute(graphAdapter.getDefaultParent());
		
		mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
		frame.add(graphComponent);
		frame.setSize(new Dimension(JFRAME_WIDTH, JFRAME_HEIGHT));
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	private static ListenableGraph<String, DefaultEdge> buildGraph() {
		Graph<String, DefaultEdge> graph = GraphTypeBuilder
				.undirected()
				.allowingMultipleEdges(true)
				.allowingSelfLoops(true)
				.vertexSupplier(SupplierUtil.createStringSupplier())
				.edgeSupplier(SupplierUtil.createDefaultEdgeSupplier())
				.buildGraph();
		
		DefaultListenableGraph<String, DefaultEdge> listenableGraph = new DefaultListenableGraph<>(graph);
		
		String x1 = "x1";
		String x2 = "x2";
		String x3 = "x3";
		
		listenableGraph.addVertex(x1);
		listenableGraph.addVertex(x2);
		listenableGraph.addVertex(x3);
		
		listenableGraph.addEdge(x1, x2);
		listenableGraph.addEdge(x2, x3);
		listenableGraph.addEdge(x3, x1);
		
		return listenableGraph;
	}
	
}
