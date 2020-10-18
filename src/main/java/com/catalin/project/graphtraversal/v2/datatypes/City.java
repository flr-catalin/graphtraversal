package com.catalin.project.graphtraversal.v2.datatypes;

/**
 * The city vertex implementation.
 * 
 * @author Catalin Florea
 */
public enum City {

	FAGARAS("FA", 352),
	SIBIU("SB", 404),
	BUCURESTI("BU", 207),
	ORADEA("OR", 621), 
	ARAD("AR", 621),
	RAMNICU("RA", 359), 
	PITESTI("PI", 313), 
	GIURGIU("GI", 214), 
	URZICENI("UR", 175), 
	ZERIND("ZE", 626), 
	TIMISOARA("TI", 614), 
	CRAIOVA("CR", 387), 
	HARSOVA("HA", 91), 
	VASLUI("VA", 297), 
	LUGOJ("LU", 561), 
	DROBETA("DR", 481), 
	EFORIE("EF", 0), 
	IASI("IA", 356), 
	MEHADIA("ME", 507), 
	NEAMT("NE", 367);
	
	/** The two letter code. */
	private String twoLetterCode;
	
	/** The heuristic. */
	private int heuristic;
	
	/**
	 * Creates a new city object.
	 * 
	 * @param twoLetterCode the two letter code
	 * @param heuristic the heuristic
	 */
	City(String twoLetterCode, int heuristic) {
		this.twoLetterCode = twoLetterCode;
		this.heuristic = heuristic;
	}
	
	/**
	 * Gets the heuristic.
	 * 
	 * @return the heuristic
	 */
	public int getHeuristic() {
		return this.heuristic;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return this.twoLetterCode + " : " + heuristic;
	}
	
}
