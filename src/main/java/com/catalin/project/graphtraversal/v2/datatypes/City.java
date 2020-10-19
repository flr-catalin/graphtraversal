package com.catalin.project.graphtraversal.v2.datatypes;

/**
 * The city vertex implementation.
 * 
 * @author Catalin Florea
 */
public enum City {

	FAGARAS("FA"),
	SIBIU("SB"),
	BUCURESTI("BU"),
	ORADEA("OR"), 
	ARAD("AR"),
	RAMNICU("RA"),
	PITESTI("PI"),
	GIURGIU("GI"),
	URZICENI("UR"), 
	ZERIND("ZE"),
	TIMISOARA("TI"), 
	CRAIOVA("CR"),
	HARSOVA("HA"),
	VASLUI("VA"),
	LUGOJ("LU"),
	DROBETA("DR"),
	EFORIE("EF"), 
	IASI("IA"),
	MEHADIA("ME"),
	NEAMT("NE");
	
	/** The two letter code. */
	private String twoLetterCode;
	
	/** The heuristic. */
	private String heuristic;
	
	/**
	 * Creates a new city object.
	 * 
	 * @param twoLetterCode the two letter code
	 */
	City(String twoLetterCode) {
		this.twoLetterCode = twoLetterCode;
	}
	
	/**
	 * Gets the heuristic.
	 * 
	 * @return the heuristic
	 */
	public String getHeuristic() {
		return this.heuristic;
	}
	
	/**
	 * Sets the heuristic
	 * 
	 * @param heuristic the heuristic
	 */
	public void setHeuristic(String heuristic) {
		this.heuristic = heuristic;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return heuristic != "0" ? this.twoLetterCode + " : " + this.heuristic : this.twoLetterCode;
	}
	
}
