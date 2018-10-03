package domain.mediator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection implements Runnable
{
   private static int count;
   private static ServerSocket welcomeSocket;
   private final static int PORT = 6789;
   private Model model;

   /**
    * @param model
    * @throws IOException
    */
   public ServerConnection(Model model) throws IOException
   {
      this.model = model;
      welcomeSocket = new ServerSocket(PORT);
   }

   /* (non-Javadoc)
    * @see java.lang.Runnable#run()
    */
   @Override
   public void run()
   {
      try
      {
         while (true)
         {
            System.out.println("Waiting for a client...");
            // Wait, on welcoming socket for contract by client
            Socket connectionSocket = welcomeSocket.accept();

            // Start a thread with the client communication
            ServerCommunication c = new ServerCommunication(connectionSocket,
                  model);
            System.out.println("Client connected");
            ((ServerModelManager) model).getPair()[1] = c;
            new Thread(c, "Communication #" + count).start();
            count++;
         }
      }
      catch (Exception e)
      {
         System.out.println("Broke");
      }
   }
}