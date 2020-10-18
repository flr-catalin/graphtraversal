package com.catalin.project.graphtraversal.v2.ui;

import static com.catalin.project.graphtraversal.v2.datatypes.City.EFORIE;
import static com.catalin.project.graphtraversal.v2.datatypes.City.FAGARAS;
import static com.catalin.project.graphtraversal.v2.datatypes.City.VASLUI;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import com.catalin.project.graphtraversal.v2.algorithm.BestFirstSearch;
import com.catalin.project.graphtraversal.v2.algorithm.BreadthFirstSearch;
import com.catalin.project.graphtraversal.v2.algorithm.DepthFirstSearch;
import com.catalin.project.graphtraversal.v2.algorithm.DepthLimitedSearch;
import com.catalin.project.graphtraversal.v2.algorithm.IterativeDeepeningSearch;
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
	private int delay = 500;
	
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
		mxStyleUtils.setCellStyles(graphModel, cells.toArray(), mxConstants.STYLE_FILLCOLOR, "cyan");
		
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

	/**
	 * Sets the cell color.
	 * 
	 * @param cell the cell
	 * @param color the color
	 */
	private void setCellColor(City cell, String color) {
		mxGraphModel graphModel = (mxGraphModel) graphComponent.getGraph().getModel();
		
		mxStyleUtils.setCellStyles(graphModel, new Object[] { graphAdapter.getVertexToCellMap().get(cell) },
				mxConstants.STYLE_FILLCOLOR, color);
	}
	
	/**
	 * Clears the cell color.
	 */
	private void clearCellColor() {
		mxGraphModel graphModel = (mxGraphModel) graphComponent.getGraph().getModel();
		Collection<Object> cells = graphModel.getCells().values();

		mxStyleUtils.setCellStyles(graphModel, cells.toArray(),
				mxConstants.STYLE_FILLCOLOR, "cyan");
	}
	
	/**
	 * The animation code.
	 */
	@Override
	public void run() {
		BreadthFirstSearch<City> bfs = new BreadthFirstSearch<>(FAGARAS, graph);
		bfs.execute(VASLUI);
		List<Set<City>> bfsTraversalSets = Arrays.asList(bfs.getTraversalSet());

		DepthFirstSearch<City> dfs = new DepthFirstSearch<>(FAGARAS, graph);
		dfs.execute(VASLUI);
		List<Set<City>> dfsTraversalSets = Arrays.asList(dfs.getTraversalSet());
		
		int dlsLimit = 3;
		DepthLimitedSearch<City> dls = new DepthLimitedSearch<>(FAGARAS, graph, dlsLimit);
		dls.execute(VASLUI);
		List<Set<City>> dlsTraversalSets = Arrays.asList(dls.getTraversalSet());

		int idsLimit = 5;
		IterativeDeepeningSearch<City> ids = new IterativeDeepeningSearch<>(FAGARAS, graph, idsLimit);
		ids.execute(VASLUI);
		List<Set<City>> idsTraversalSets = ids.getTraversalSets();
		
		BestFirstSearch<City> gbfs = new BestFirstSearch<>(FAGARAS, graph);
		gbfs.execute(EFORIE);
		Set<City> traversalSet = gbfs.getTraversalSet();
		List<Set<City>> gbfsTraversalSets = Arrays.asList(traversalSet);

		while (true) {
			animateTraversalSets(bfsTraversalSets, "Breadth First Search", VASLUI);
			animateTraversalSets(dfsTraversalSets, "Depth First Search", VASLUI);
			animateTraversalSets(dlsTraversalSets, "Depth Limited Search - Limit: " + dlsLimit, VASLUI);
			animateTraversalSets(idsTraversalSets, "Iterative Deepening Search - Limit: " + idsLimit, VASLUI);
			animateTraversalSets(gbfsTraversalSets, "Best First Search", EFORIE);
		}
	}

	/**
	 * Animates the traversal sets with a goal.
	 * 
	 * @param traversalSets the traversal sets
	 * @param title the new window title
	 * @param goal the goal
	 */
	private void animateTraversalSets(List<Set<City>> traversalSets, String title, City goalCity) {
		JFrame mainFrame = (JFrame) SwingUtilities.getRoot(graphComponent);
		mainFrame.setTitle(title);
		
		long beforeTime = System.currentTimeMillis();
		
		for (Set<City> set : traversalSets) {
			Iterator<City> setIterator = set.iterator();
			
			while (setIterator.hasNext()) {
				City next = setIterator.next();
				setCellColor(next, "red");
				
				if (goalCity != null && !setIterator.hasNext() && goalCity.equals(next)) {
					setCellColor(next, "blue");
				}
				
				long timeDiff = System.currentTimeMillis() - beforeTime;
				long sleep = delay - timeDiff;
				
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
			
			clearCellColor();
		}
	}
	
}
