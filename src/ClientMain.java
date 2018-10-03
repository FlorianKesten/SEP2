import controller.Controller;
import view.GameMenuGUI;
import domain.mediator.ClientModelManager;
import domain.mediator.Model;


public class ClientMain
{
   /**
    * Runs the client
    */
   public static void main(String args[])
   {
      try
      {
         Model model = new ClientModelManager();
         GameMenuGUI view = new GameMenuGUI();
         Controller controller = new Controller(view, null, model);

         view.start(controller);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}