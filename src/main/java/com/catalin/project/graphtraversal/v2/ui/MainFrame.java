package com.catalin.project.graphtraversal.v2.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.catalin.project.graphtraversal.v2.util.GraphBuilder;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final static String TITLE = "Title";
	
	public MainFrame() {
		initialiseUi();
	}
	
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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame mainFrame = new MainFrame();
			mainFrame.setVisible(true);
		});
	}
	
}
