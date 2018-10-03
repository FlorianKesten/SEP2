package domain.mediator;

import java.util.Observable;
import java.util.Observer;

public class ClientModelManager extends Observable implements Model
{

   private Model proxy;

   /**
    * Proxy
    */
   public ClientModelManager()
   {
      proxy = new CMMProxy(this);
   }

   /* (non-Javadoc)
    * @see domain.mediator.Model#createGame(java.util.Observer)
    */
   public void createGame(Observer o)
   {
      proxy.createGame(o);
   }

   /* (non-Javadoc)
    * @see domain.mediator.Model#interact(java.lang.String)
    */
   @Override
   public void interact(String mark)
   {
      proxy.interact(mark);
   }

   /* (non-Javadoc)
    * @see domain.mediator.Model#notifyAboutMessage(java.lang.String)
    */
   @Override
   public void notifyAboutMessage(String mark)
   {
      super.setChanged();
      super.notifyObservers(mark);
   }

   /* (non-Javadoc)
    * @see domain.mediator.Model#logIn(java.lang.String)
    */
   @Override
   public void logIn(String username)
   {
      proxy.logIn(username);
   }
}