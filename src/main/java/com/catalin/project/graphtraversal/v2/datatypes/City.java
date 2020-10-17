package com.catalin.project.graphtraversal.v2.datatypes;

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
	
	private String twoLetterCode;
	
	City(String value) {
		this.twoLetterCode = value;
	}

	public String get2LC() {
		return this.twoLetterCode;
	}
	
	public City get(String twoLetterCode) {
		if (twoLetterCode == null) {
			return null;
		}
		
		for (City city : City.values()) {
			if (twoLetterCode.equals(city.get2LC())) {
				return city;
			}
		}
		
		return null;
	}
	
	public String getTwoLetterCode() {
		return twoLetterCode;
	}

	public void setTwoLetterCode(String twoLetterCode) {
		this.twoLetterCode = twoLetterCode;
	}

	@Override
	public String toString() {
		return this.get2LC();
	}
	
}
