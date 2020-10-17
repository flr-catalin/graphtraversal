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
	URZICENI("VR"), 
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
	
	/**
	 * Creates a new city object.
	 * 
	 * @param twoLetterCode the two letter code
	 */
	City(String twoLetterCode) {
		this.twoLetterCode = twoLetterCode;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return this.twoLetterCode;
	}
	
}
