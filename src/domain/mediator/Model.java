package domain.mediator;

import java.util.Observer;

public interface Model
{
   /**
    * takes the coordinates from a GUI and sends it to the model
    * @param mark   coordinates and type of mark
    */
   public void interact(String mark);

   /**
    * Creates a new game
    * @param o
    */
   public void createGame(Observer o);

   /**
    * Logs in to the server
    * @param username
    */
   public void logIn(String username);

   /**
    * @param mark
    */
   public void notifyAboutMessage(String mark);

   
}