package fr.openig.engine;

public class OpenIgMoney {

	// Instance de notre objet
	private static OpenIgMoney instance;
	
	// Argent
	private long money;
	
	public synchronized static OpenIgMoney getInstance() {
		if(instance == null)
			instance = new OpenIgMoney();
		
		return instance;
	}
	
	private OpenIgMoney() {
		money = 50000;
	}
	
	public synchronized long getMoney() {
		return money;
	}

	public synchronized void addMoney(long addMoney) {
		money += addMoney;
	}
	
}
