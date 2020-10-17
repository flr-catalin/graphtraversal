package com.catalin.project.graphtraversal.v2.ui;

import static com.catalin.project.graphtraversal.v2.datatypes.City.FAGARAS;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import com.catalin.project.graphtraversal.v2.algorithm.DepthLimitedSearch;
import com.catalin.project.graphtraversal.v2.datatypes.City;
import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxStyleUtils;

/**
 * This is the JPanel implementation for directed weigthed graphs.
 * 
 * @author Catalin Florea
 */
public class DirectedWeightedGraphPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	/** The JGraphT graph */
	private DefaultDirectedGraph<City, WeightedEdge> graph;
	
	/** The mxGraph graph component */
	private mxGraphComponent graphComponent;
	
	/** The JGraph to mxGraph adapter. */
	private JGraphXAdapter<City, WeightedEdge> graphAdapter;
	
	/** The animator thread. */
	private Thread animator;
	
	/** The framerate in milliseconds */
	private int delay = 750;
	
	/**
	 * Creates a new directed weigthed graph panel object.
	 * 
	 * @param graph the graph
	 */
	public DirectedWeightedGraphPanel(DefaultDirectedWeightedGraph<City, WeightedEdge> graph) {
		this.graph = graph;
		this.graphAdapter = new JGraphXAdapter<City, WeightedEdge>(graph);
		this.graphComponent = new mxGraphComponent(graphAdapter);
		
		initialiseGraphComponent();
		
		animator = new Thread(this);
		animator.start();
	}
	
	/**
	 * Initialises the graph component.
	 */
	private void initialiseGraphComponent() {
		mxCompactTreeLayout compactTreeLayout = new mxCompactTreeLayout(graphAdapter);
		
		compactTreeLayout.setHorizontal(false);
		compactTreeLayout.setNodeDistance(50);
		compactTreeLayout.execute(graphAdapter.getDefaultParent());
		
		mxGraphModel graphModel = (mxGraphModel) graphComponent.getGraph().getModel();
		Collection<Object> cells = graphModel.getCells().values();
		
		mxStyleUtils.setCellStyles(graphModel, cells.toArray(), mxConstants.STYLE_ENDARROW, mxConstants.NONE);
		mxStyleUtils.setCellStyles(graphModel, cells.toArray(), mxConstants.STYLE_ROUNDED, Boolean.TRUE.toString());
		
		graphAdapter.setCellsMovable(false);
		graphAdapter.setEdgeLabelsMovable(false);
		graphAdapter.setVertexLabelsMovable(false);
	}
	
	/**
	 * Gets the mxGraph graph component.
	 * 
	 * @return the mxGraph graph component
	 */
	public mxGraphComponent getGraphComponent() {
		return this.graphComponent;
	}

	private void setCellColor(City cell, String color) {
		mxGraphModel graphModel = (mxGraphModel) graphComponent.getGraph().getModel();
		
		mxStyleUtils.setCellStyles(graphModel, new Object[] { graphAdapter.getVertexToCellMap().get(cell) },
				mxConstants.STYLE_FILLCOLOR, color);
	}
	
	/**
	 * The animation code.
	 */
	@Override
	public void run() {
//		DepthFirstSearch<City> dfs = new DepthFirstSearch<>(FAGARAS, graph);
//		dfs.execute();
//		Set<City> traversalSet = dfs.getTraversalSet();
//		Iterator<City> iterator = traversalSet.iterator();
		
//		BreadthFirstSearch<City> bfs = new BreadthFirstSearch<>(FAGARAS, graph);
//		bfs.execute();
//		Set<City> traversalSet = bfs.getTraversalSet();
//		Iterator<City> iterator = traversalSet.iterator();
		
		DepthLimitedSearch<City> dls = new DepthLimitedSearch<>(FAGARAS, graph, 5);
		dls.execute();
		Set<City> traversalSet = dls.getTraversalSet(0);
		Iterator<City> iterator = traversalSet.iterator();
		
		
		long beforeTime, timeDiff, sleep;
		
		beforeTime = System.currentTimeMillis();
		
		while (true) {
			if (iterator.hasNext()) {
				setCellColor(iterator.next(), "red");
			}
			
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = delay - timeDiff;
			
			if (sleep < 0) {
				sleep = 2;
			}
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				String message = String.format("Thread interrupted: %s", e.getMessage());
				
				JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			beforeTime = System.currentTimeMillis();
		}
	}
	
}
