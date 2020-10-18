package com.catalin.project.graphtraversal.v2.ui;

import static com.catalin.project.graphtraversal.v2.datatypes.City.EFORIE;
import static com.catalin.project.graphtraversal.v2.datatypes.City.FAGARAS;
import static com.catalin.project.graphtraversal.v2.datatypes.City.VASLUI;

import java.awt.BorderLayout;
import java.awt.Color;
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
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import com.catalin.project.graphtraversal.v2.algorithm.BestFirstSearch;
import com.catalin.project.graphtraversal.v2.algorithm.BreadthFirstSearch;
import com.catalin.project.graphtraversal.v2.algorithm.DepthFirstSearch;
import com.catalin.project.graphtraversal.v2.algorithm.DepthLimitedSearch;
import com.catalin.project.graphtraversal.v2.algorithm.IterativeDeepeningSearch;
import com.catalin.project.graphtraversal.v2.algorithm.UniformCostSearch;
import com.catalin.project.graphtraversal.v2.datatypes.City;
import com.catalin.project.graphtraversal.v2.datatypes.WeightedEdge;
import com.catalin.project.graphtraversal.v2.util.GraphModifier;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
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
	private DefaultDirectedWeightedGraph<City, WeightedEdge> graph;
	
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
		super(new BorderLayout());
		
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
		graphComponent.getViewport().setOpaque(true);
		graphComponent.getViewport().setBackground(Color.decode("#EFC050"));
		
		graphAdapter.getModel().beginUpdate();
		
		mxCompactTreeLayout compactTreeLayout = new mxCompactTreeLayout(graphAdapter);
		
		compactTreeLayout.setHorizontal(false);
		compactTreeLayout.setNodeDistance(50);
		compactTreeLayout.execute(graphAdapter.getDefaultParent());
		
		mxGraphModel graphModel = (mxGraphModel) graphComponent.getGraph().getModel();
		Collection<Object> cells = graphModel.getCells().values();
		
		mxStyleUtils.setCellStyles(graphModel, cells.toArray(), mxConstants.STYLE_ENDARROW, mxConstants.NONE);
		mxStyleUtils.setCellStyles(graphModel, cells.toArray(), mxConstants.STYLE_ROUNDED, Boolean.TRUE.toString());
		mxStyleUtils.setCellStyles(graphModel, cells.toArray(), mxConstants.STYLE_FILLCOLOR, "#F3E0BE");
		mxStyleUtils.setCellStyles(graphModel, cells.toArray(), mxConstants.STYLE_FONTSIZE, "13");

		graphAdapter.clearSelection();
		graphAdapter.selectAll();
		Object[] selectionCells = graphAdapter.getSelectionCells();

		for (Object c : selectionCells) {
			mxCell cell = (mxCell) c;
			mxGeometry geometry = cell.getGeometry();

			if (cell.isVertex()) {
				geometry.setWidth(60);
				geometry.setHeight(20);
			}
		}
		
		graphAdapter.clearSelection();
		graphAdapter.setCellsSelectable(false);
		graphAdapter.setCellsMovable(false);
		graphAdapter.setEdgeLabelsMovable(false);
		graphAdapter.setVertexLabelsMovable(false);
		
		graphAdapter.getModel().endUpdate();
		graphAdapter.refresh();
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
				mxConstants.STYLE_FILLCOLOR, "#F3E0BE");
	}
	
	/**
	 * The animation code.
	 */
	@Override
	public void run() {
		GraphModifier.getInstance().clearHeuristics(graphAdapter);
		GraphModifier.getInstance().clearWeights(graph, graphAdapter);
		
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
		
		GraphModifier.getInstance().initialiseBestFirstSearchHeuristics(graphAdapter);		
		BestFirstSearch<City> gbfs = new BestFirstSearch<>(FAGARAS, graph);
		gbfs.execute(EFORIE);
		List<Set<City>> gbfsTraversalSets = Arrays.asList(gbfs.getTraversalSet());
		
		GraphModifier.getInstance().clearHeuristics(graphAdapter);
		GraphModifier.getInstance().initialiseUniformCostSearchWeights(graph, graphAdapter);
		UniformCostSearch ucs = new UniformCostSearch(FAGARAS, graph, graphAdapter);
		
		
		while (true) {
			GraphModifier.getInstance().clearHeuristics(graphAdapter);
			GraphModifier.getInstance().clearWeights(graph, graphAdapter);
			animateTraversalSets(bfsTraversalSets, "Breadth First Search", VASLUI);
			animateTraversalSets(dfsTraversalSets, "Depth First Search", VASLUI);
			animateTraversalSets(dlsTraversalSets, "Depth Limited Search - Limit: " + dlsLimit, VASLUI);
			animateTraversalSets(idsTraversalSets, "Iterative Deepening Search - Limit: " + idsLimit, VASLUI);
			GraphModifier.getInstance().initialiseBestFirstSearchHeuristics(graphAdapter);
			animateTraversalSets(gbfsTraversalSets, "Best First Search", EFORIE);
			GraphModifier.getInstance().clearHeuristics(graphAdapter);
			GraphModifier.getInstance().initialiseUniformCostSearchWeights(graph, graphAdapter);
			ucs.execute(VASLUI);
			List<Set<City>> ucsTraversalSets = Arrays.asList(ucs.getTraversalSet());
			animateTraversalSets(ucsTraversalSets, "Uniform Cost Search", VASLUI);
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
				setCellColor(next, "#92A8D1");
				
				if (goalCity != null && !setIterator.hasNext() && goalCity.equals(next)) {
					setCellColor(next, "#88B04B");
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

	/**
	 * Gets the delay.
	 * 
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * Sets the delay.
	 * 
	 * @param delay the delay
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
}
