package com.catalin.project.graphtraversal.datatypes;

import org.jgrapht.graph.DefaultWeightedEdge;

public class WeightedEdge extends DefaultWeightedEdge {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return String.valueOf((int) getWeight());
	}


}
