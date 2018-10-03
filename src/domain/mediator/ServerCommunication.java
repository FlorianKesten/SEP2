package domain.mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ServerCommunication implements Runnable, Observer
{
   private ArrayList<Socket> connectionsSocket;
   private ObjectOutputStream outToClient;
   private ObjectInputStream inFromClient;
   private Model model;

   /**
    * Constructor for ServerCommunication
    * @param connectionSocket
    * @param model
    * @throws IOException
    */
   public ServerCommunication(Socket connectionSocket, Model model)
         throws IOException
   {
      this.connectionsSocket = new ArrayList<>();
      this.model = model;
      outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
      inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
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
         {// Receives a String from client
            String[] input = ((String) inFromClient.readObject()).split(";");
            System.out.println(input[0]);
            // Reads protocol
            switch (input[0])
            {
               case "login": // If Client logs in with a username;
                  model.logIn(input[1]);
                  outToClient.writeObject(
                        new String("Confirmation;Username recorded"));
                  break;
               case "request": // If Client makes a request;
                  switch (input[1])
                  {
                     case "onlineplayers": // If request is to start a game
                        // Send a list of all online players
                        outToClient.writeObject(
                              ((ServerModelManager) model).getPlayerList());
                        switch (input[2])
                        {
                           case "tictactoe": // If request is for game of TTT
                              model.createGame(this);

                              break;
                           case "checkers": // If request is for game of
                                            // checkers
                              // Not implemented in current build
                              break;
                        }

                        break;
                     case "offline": // If the player wants to play offline the
                                     // server will only record score
                        // Not implemented in current build
                        break;
                     case "highscore": // If the player wants to to see the
                                       // highscores database
                        // Not implemented in current build
                        break;
                  }
                  break;
               case "interract": // If the player wants to play offline the
                                 // server will only record score
                  model.interact(input[1] + ";" + input[2]);
                  break;
               case "quit":
                  // Not implemented in current build
                  break;
            }

         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /* (non-Javadoc)
    * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
    */
   @Override
   public void update(Observable o, Object arg)
   {
      try
      {
         System.out.println("notified from board");

         outToClient.writeObject(arg);

      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }
}