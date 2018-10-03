import java.io.IOException;
import domain.mediator.Model;
import domain.mediator.ServerModelManager;
import domain.model.Board;
import domain.model.TicTacToe;

public class ServerMain
{
   /**
    * Runs the server
    * @throws ClassNotFoundException
    * @throws IOException
    */
   public static void main(String args[])
         throws ClassNotFoundException, IOException
   {
      Board board = new TicTacToe();
      Model serverModel = new ServerModelManager();

   }
}