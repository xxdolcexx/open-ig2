package fr.openig.engine;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import fr.openig.model.Planet;
import fr.openig.object.generic.ControlBarGameObject;

public class OpenIgTimer extends Thread {

	/** Instance de l'objet **/
	private static OpenIgTimer instance;
	
	private long sleep = 5000;
	private boolean interupted = false;
	private Calendar calendar;
	private boolean pause = false;
	
	public synchronized static OpenIgTimer getInstance(Date date) {
		if(instance == null)
			instance = new OpenIgTimer(date);
		
		return instance;
	}
	
	private OpenIgTimer(Date date) {
		calendar = new GregorianCalendar();
		calendar.setTimeInMillis(date.getTime());
	}
	
	@Override
	public void run() {
		while(!interupted) {
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			
			if(!pause) {
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
				
				// Gestion de l'argent
				calculateMoney();
			}
		}
	}
	
	private void calculateMoney() {
		for(Planet planet : UniverseEngine.getInstance().getUniverse().getPlanets()) {
			long impot = (long) (planet.getImpot() * (planet.getPopulation() / 4) * planet.getSatisfaction());
			OpenIgMoney.getInstance().addMoney(impot);
		}
	}
	
	public synchronized void setSpeed(int speed) {
		switch (speed) {
			case ControlBarGameObject.PLAY:
				sleep = 4000;
				break;
			case ControlBarGameObject.FORWARD:
				sleep = 900;
				break;
			case ControlBarGameObject.FAST_FORWARD:
				sleep = 100;
				break;
		}
		
		pause = false;
	}

	public int getDayOfMonth() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getMonth() {
		return calendar.get(Calendar.MONTH);
	}
	
	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}
	
	public void setInterupted(boolean interupted) {
		this.interupted = interupted;
	}
	
	public void pause() {
		pause = true;
	}
	
}
