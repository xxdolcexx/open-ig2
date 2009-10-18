package fr.openig.object;

import java.util.ArrayList;
import java.util.List;

public class Galaxy {

	private Sun sun;
	private List<Planet> planets;
	
	public Sun getSun() {
		return sun;
	}

	public void setSun(Sun sun) {
		this.sun = sun;
	}

	public void addPlanet(Planet planet) {
		if(planets == null)
			planets = new ArrayList<Planet>();
		
		planets.add(planet);
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}
	
}
