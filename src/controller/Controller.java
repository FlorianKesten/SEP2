package controller;

import java.util.Observable;
import view.*;
import domain.mediator.Model;

public class Controller
{
   private GameMenuGUI view;
   private Model model;
   private TicTacToeFrame gameView;

   // TO DO: remove game view from constructor
   /**
    * Controller constructor
    * @param view   The game menu GUI
    * @param gameView    The  GUI of the game
    * @param model     The main model
    */
   public Controller(GameMenuGUI view, TicTacToeFrame gameView, Model model)
   {
      this.gameView = gameView;
      this.view = view;
      this.model = model;
      ((Observable) this.model).addObserver(view);
   }

   /**
    * Sets the game GUI
    * @param gameView
    */
   public void setGameView(TicTacToeFrame gameView)
   {
      this.gameView = gameView;
   }

   /**
    * Executes on of the possible choices in the GUI
    * @param what  String: what we want to execute
    */
   public void execute(String what)
   {
      switch (what)
      {
         case "login": // Logs in the server
            String username = "login;" + view.getUsername();
            model.logIn(username);
            break;

         case "choosegame": // Starts choosegame protocol
            view.startGame();
            model.createGame(gameView);
            break;

         case "click": // in case of ingame inerraction with board
            model.interact(gameView.get("coordinates"));
            break;

      }
   }
}