package com.revature.karl.model;

public class Planet {
	
	private int id;
	private String name;
	private String inhabitants;
	private int population;
	private int garrison_id;
	private Garrison garrison;

	public Planet() {
		super();
	}
	
	public Planet(String name, String inhabitants, int population, int garrison_id) {
		this.name = name;
		this.inhabitants = inhabitants;
		this.population = population;
		this.garrison_id = garrison_id;
	}
	
	public Planet(int id, String name, String inhabitants, int population, int garrison_id) {
		this.id = id;
		this.name = name;
		this.inhabitants = inhabitants;
		this.population = population;
		this.garrison_id = garrison_id;
	}
	
	public Planet(int id, String name, String inhabitants, int population, int garrison_id, Garrison garrison) {
		this.id = id;
		this.name = name;
		this.inhabitants = inhabitants;
		this.population = population;
		this.garrison_id = garrison_id;
		this.garrison = garrison;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInhabitants() {
		return inhabitants;
	}

	public void setInhabitants(String inhabitants) {
		this.inhabitants = inhabitants;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getGarrison_id() {
		return garrison_id;
	}

	public void setGarrison_id(int garrison_id) {
		this.garrison_id = garrison_id;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this) {return true;}
		if (!(object instanceof Planet)) {return false;}
		
		if (this.getName().equals(((Planet) object).getName()) 
				&& this.getInhabitants() == (((Planet) object).getInhabitants())
				&& this.getPopulation() == (((Planet) object).getPopulation())
				&& this.getGarrison_id() == (((Planet) object).getGarrison_id())) {
			return true;
		} else {
			return false;
		}
	}

	public Garrison getGarrison() {
		return garrison;
	}

	public void setGarrison(Garrison garrison) {
		this.garrison = garrison;
	}
}
