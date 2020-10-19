package com.catalin.project.graphtraversal.v2.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.catalin.project.graphtraversal.v2.util.GraphBuilder;

/**
 * This class is the main JFrame implementation.
 * 
 * @author Catalin Florea
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JSlider slider;
	
	private String firstSearchName = "Breadth First Search";
	
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
		setTitle(firstSearchName);
		
		this.slider = new JSlider(400, 1000);
		this.slider.setMajorTickSpacing(100);
		this.slider.setMinorTickSpacing(0);
		this.slider.setPaintTicks(true);
		this.slider.setVisible(true);
		this.slider.setInverted(true);
		this.slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				directedWeightedGraphPanel.setDelay(slider.getValue());
			}
			
		});
		add(slider, BorderLayout.SOUTH);
		
		setResizable(false);
		pack();
		
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
