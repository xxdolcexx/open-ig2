package fr.openig.engine;

public class Universe {

	// Instance de l'objet
	private static Universe instance;
	
	public static synchronized Universe getInstance() {
		if(instance == null)
			return new Universe();
		
		return instance;
	}
	
	private Universe() {
		init();
	}
	
	private void init() {
		
	}
	
}
