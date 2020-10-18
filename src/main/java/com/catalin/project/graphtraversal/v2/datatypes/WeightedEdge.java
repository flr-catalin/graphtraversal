package com.catalin.project.graphtraversal.v2.datatypes;

import org.jgrapht.graph.DefaultWeightedEdge;

public class WeightedEdge extends DefaultWeightedEdge {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return getWeight() != 0f ? String.valueOf((int) getWeight()) : "";
	}

}
