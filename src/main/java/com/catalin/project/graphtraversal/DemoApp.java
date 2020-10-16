package com.catalin.project.graphtraversal;

import static com.catalin.project.graphtraversal.datatypes.City.FAGARAS;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;

import com.catalin.project.graphtraversal.algorithm.AlgorithmFactory;
import com.catalin.project.graphtraversal.datatypes.City;
import com.catalin.project.graphtraversal.datatypes.EmptyEdge;
import com.catalin.project.graphtraversal.util.GraphBuilder;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxStyleUtils;

public class DemoApp {
	
	private static JFrame mainFrame;
	
	private static mxCompactTreeLayout layout;
	
	private static Graph<City, EmptyEdge> graph;
	
	private static JGraphXAdapter<City, EmptyEdge> graphAdapter;
	
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
		
		graph = GraphBuilder.getInstance().buildDefaultGraph();
		graphAdapter = new JGraphXAdapter<>(graph);
		layout = new mxCompactTreeLayout(graphAdapter);
	}
	
	private static void createAndShowGui() {
		executeLayout();
		createFrame();
		
		AlgorithmFactory.getInstance().DFS(graph, FAGARAS);
		AlgorithmFactory.getInstance().BFS(graph, FAGARAS);
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
	
}
