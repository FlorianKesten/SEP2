package domain.model;

import java.util.Observer;

public interface Board 
{
	/**
	 * Sends the coordinates and type of mark to the model
	 * @param mark
	 */
	public void interact(String mark);
	/**
	 * Adds an observer
	 * @param o
	 */
	public void addObserver(Observer o);
	/**
	 * adds player
	 * @param p
	 * @return
	 */
	public boolean addPlayer(Observer p);
}
