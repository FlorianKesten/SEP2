package domain.mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observer;

public class CMMProxy implements Model
{

   private static ObjectInputStream in;
   private static ObjectOutputStream out;
   private final int PORT = 6789;
   private final String HOST = "localhost";
   private Socket socket;
   private String username;
   private Model model;
   Thread t;

   /**
    * @param model
    */
   public CMMProxy(Model model)
   {
      this.model = model;
   }

   /* (non-Javadoc)
    * @see domain.mediator.Model#createGame(java.util.Observer)
    */
   @Override
   // In the current implementation of the code all we do is tell the server we
   // are connecting to play TTT
   public void createGame(Observer o)
   {
      try
      {
         ((ClientModelManager) model).addObserver(o);

         // Currently serves no purpose. Tells the server that the player
         // wants to play TicTacToe and asks for online players list.
         out.writeObject(new String("request;onlineplayers;tictactoe;end"));
         System.out.println(
               "Sending " + "'request;onlineplayers;tictactoe;'" + "to server");
         Object recieved = in.readObject();
         ArrayList<String> usernames = (ArrayList<String>) recieved;
         for (int i = 0; i < usernames.size(); i++)
         {
            System.out.println(i + " " + usernames.get(i));
         }
         if (username.equals("login;" + usernames.get(1)))
         {
            notifyAboutMessage(in.readObject().toString());
         }

      }
      catch (IOException | ClassNotFoundException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   /* (non-Javadoc)
    * @see domain.mediator.Model#interact(java.lang.String)
    */
   @Override
   public void interact(String mark)
   {

      try
      {
         out.writeObject("interract;" + mark);
         System.out.println("Sending " + "'" + mark + "'" + "to server");

         String inmark = (String) in.readObject();
         System.out.println("Received " + "'" + inmark + "'" + "from server");
         model.notifyAboutMessage(inmark);
         if(inmark.split(";")[3].equals("cont"))
        	 notifyAboutMessage(in.readObject().toString());

      }
      catch (IOException | ClassNotFoundException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   /* (non-Javadoc)
    * @see domain.mediator.Model#notifyAboutMessage(java.lang.String)
    */
   @Override
   public void notifyAboutMessage(String mark)
   {
      model.notifyAboutMessage(mark);

   }

   /* (non-Javadoc)
    * @see domain.mediator.Model#logIn(java.lang.String)
    */
   @Override
   public void logIn(String username)
   {
      this.username = username;
      // Establish the connection
      try
      {
         socket = new Socket(HOST, PORT);
         ;
         System.out.println("Client > connected to server");
         out = new ObjectOutputStream(socket.getOutputStream());
         in = new ObjectInputStream(socket.getInputStream());
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      // Register username in server's online list
      try
      {
         out.writeObject(username);
         System.out.println("Sending " + "'" + username + "'" + "to server");

         String response = (String) in.readObject();
         System.out.println("'" + response + "'");

      }
      catch (IOException | ClassNotFoundException e)
      {

         e.printStackTrace();
      }
   }
}