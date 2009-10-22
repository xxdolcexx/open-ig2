package fr.openig.utils.xml;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import fr.openig.model.Planet;
import fr.openig.model.Universe;

public class OpenIgXmlParser {

	public static final Universe parse(InputStream stream) {
		// Instanciation de Digester, le parseur XML
		Digester digester = new Digester();
		digester.setValidating(false);
		
		// Création d'un univers
		digester.addObjectCreate("open-ig2/universe", Universe.class);
		
		// Récupération des propriétés d'une planet
		digester.addObjectCreate("open-ig2/universe/planet", Planet.class);
		digester.addSetProperties("open-ig2/universe/planet", "name", "name");
		digester.addSetProperties("open-ig2/universe/planet", "type", "type");
		digester.addSetProperties("open-ig2/universe/planet", "population", "population");
		digester.addSetProperties("open-ig2/universe/planet/coordonate", "x", "x");
		digester.addSetProperties("open-ig2/universe/planet/coordonate", "y", "y");
		digester.addSetRoot("open-ig2/universe/planet", "addPlanet");
		
		// On parse le fichier
		try {
			return (Universe) digester.parse(stream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}
	}
}
