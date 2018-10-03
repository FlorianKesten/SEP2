package domain.mediator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

import domain.model.Board;
import domain.model.OnlinePlayerList;
import domain.model.TicTacToe;

public class ServerModelManager implements Model
{

   private ServerConnection server;
   private Board activeBoard;
   private ArrayList<Board> boards;
   private OnlinePlayerList onlinePlayers;
   private DatabaseManager database;

   /**
    * 
    */
   public ServerModelManager()
   {
      boards = new ArrayList<Board>();
      this.activeBoard = new TicTacToe();
      boards.add(activeBoard);
      this.onlinePlayers = new OnlinePlayerList();
      database = new DatabaseManager();
      try
      {
         this.server = new ServerConnection(this);
         new Thread(server).start();
      }
      catch (IOException e)
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
      boards.get(0).interact(mark);
   }

   /* (non-Javadoc)
    * @see domain.mediator.Model#createGame(java.util.Observer)
    */
   @Override
   public void createGame(Observer p)
   {
      if (activeBoard.addPlayer(p))
      {
         this.activeBoard = new TicTacToe();
         this.boards.add(activeBoard);
      }

   }

   /* (non-Javadoc)
    * @see domain.mediator.Model#notifyAboutMessage(java.lang.String)
    */
   @Override
   public void notifyAboutMessage(String mark)
   {
      //nope
   }

   /* (non-Javadoc)
    * @see domain.mediator.Model#logIn(java.lang.String)
    */
   @Override
   public void logIn(String username)
   {
      getPair()[0] = username;
      onlinePlayers.addPair();
      database.createPlayer(username);
   }

   /**
    * @return
    */
   public ArrayList<String> getPlayerList()
   {
      return onlinePlayers.getOnlinePlayers();
   }

   /**
    * @return
    */
   public Object[] getPair()
   {
      return onlinePlayers.getPair();
   }

   /**
    * @return
    */
   public OnlinePlayerList getOnlinePlayers()
   {
      return onlinePlayers;
   }
}