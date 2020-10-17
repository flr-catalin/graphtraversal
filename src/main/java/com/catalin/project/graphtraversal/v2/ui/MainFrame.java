package com.catalin.project.graphtraversal.v2.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.catalin.project.graphtraversal.v2.util.GraphBuilder;

/**
 * This class is the main JFrame implementation.
 * 
 * @author Catalin Florea
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/** The title of the main frame. */
	private final static String TITLE = "Title";
	
	/**
	 * Creates a new main frame object.
	 */
	public MainFrame() {
		initialiseUi();
	}
	
	/**
	 * Initialises the user interface properties.
	 */
	private void initialiseUi() {
		GraphBuilder graphBuilder = GraphBuilder.getInstance();
		DirectedWeightedGraphPanel directedWeightedGraphPanel = new DirectedWeightedGraphPanel(graphBuilder.buildDefaultDirectedWeightGraph());
		add(directedWeightedGraphPanel.getGraphComponent());
		
		setResizable(false);
		pack();
		
		setTitle(TITLE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * The entry point of the application.
	 * 
	 * @param args empty
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame mainFrame = new MainFrame();
			mainFrame.setVisible(true);
		});
	}
	
}
