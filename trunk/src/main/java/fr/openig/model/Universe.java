package fr.openig.model;

import java.util.ArrayList;
import java.util.List;

public class Universe {

	private List<Planet> planets = new ArrayList<Planet>();

	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}
	
	public void addPlanet(Planet planet) {
		planets.add(planet);
	}
	
}
