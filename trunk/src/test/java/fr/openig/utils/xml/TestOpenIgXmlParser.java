package fr.openig.utils.xml;

import junit.framework.TestCase;
import fr.openig.model.Planet;
import fr.openig.model.Universe;

public class TestOpenIgXmlParser extends TestCase {

	public void testGetPlanetsFromXmlData() {
		Universe universe = OpenIgXmlParser.parse(this.getClass().getResourceAsStream("/data.xml"));
		
		assertNotNull(universe);
		assertEquals(1, universe.getPlanets().size());
		
		Planet planet = universe.getPlanets().get(0);
		assertNotNull(planet);
		assertEquals("Terre", planet.getName());
		assertEquals(5, planet.getType());
		assertEquals(500000, planet.getPopulation());
		assertEquals(30d, planet.getX());
		assertEquals(30d, planet.getY());
		assertEquals(0.27f, planet.getSatisfaction());
		assertEquals(250l, planet.getImpot());
	}
	
}
