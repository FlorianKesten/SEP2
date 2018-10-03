package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import controller.Controller;
import domain.mediator.DatabaseManager;

public class GameMenuGUI extends JFrame implements ActionListener, Observer
{
   // Labels
   private JLabel nicknameLabel;

   // Text Fields
   private JTextField nicknameField;

   // Buttons
   private JButton buttonGames;
   private JButton buttonHighScores;
   private JButton buttonBack;
   private JButton buttonTTT;
   private JButton buttonChess;
   private JButton buttonCheckers;
   private JButton buttonLogIn;
   private JButton buttonExit;

   // Panels
   private JPanel mainPanel;
   private JPanel buttonPanel;
   private JPanel logoPanel;
   private JPanel nicknamePanel;
   private JPanel buttonPanel2;

   // Controller
   private Controller controller;

   private int isLogged = 0;

   /**
    * GameMenuGUI constructor
    */
   public GameMenuGUI()
   {
      super("Board Games Menu");
      createComponents();
      addComponentsToFrame();
      registerEventHandlers();
   }
   /**
    * creates components
    */
   private void createComponents()
   {
      nicknameLabel = new JLabel(
            "<html><i><font color=blue>What's your name?: </font></i></html>");
      nicknameField = new JTextField(16);

      buttonTTT = new JButton("Play Tic-Tac-Toe");
      buttonBack = new JButton("<= Back");
      buttonChess = new JButton("Play Chess");
      buttonCheckers = new JButton("Play Checkers");
      buttonLogIn = new JButton("Log In");
      buttonHighScores = new JButton("View High Scores");
      buttonGames = new JButton("Choose a game to play");
      buttonExit = new JButton("Exit");
   }

   
   /**
    * adds components to frame
    */
   private void addComponentsToFrame()
   {
      // Title Logo Panel
      logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
      logoPanel.add(new JLabel(new ImageIcon("images\\aaa.png")));
      logoPanel.add(new JLabel(new ImageIcon("images\\logotext.png")));

      // Panel for the nickname
      nicknamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
      nicknamePanel.add(nicknameLabel);
      nicknamePanel.add(nicknameField);
      nicknamePanel.add(buttonLogIn);

      // Middle Button Part Panel
      buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 24));
      buttonPanel.add(buttonGames);
      buttonPanel.add(buttonHighScores);
      buttonPanel.add(buttonExit);

      // Second Button Part Panel
      buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 24));
      buttonPanel2.add(buttonBack);
      buttonPanel2.add(buttonTTT);
      buttonPanel2.add(buttonChess);
      buttonPanel2.add(buttonCheckers);

      // disabling the not implemented ones
      buttonHighScores.setEnabled(true);
      buttonChess.setEnabled(false);
      buttonCheckers.setEnabled(false);
      buttonLogIn.setEnabled(true);

      // The Main Panel
      mainPanel = new JPanel();
      LayoutManager layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
      mainPanel.setLayout(layout);

      mainPanel.add(logoPanel);
      mainPanel.add(nicknamePanel);
      mainPanel.add(buttonPanel);
      mainPanel.add(buttonPanel2);

      buttonPanel2.setVisible(false);

      setContentPane(mainPanel);
   }

   /**
    * registers event handlers
    */
   private void registerEventHandlers()
   {
      buttonGames.addActionListener(this);
      buttonHighScores.addActionListener(this);
      buttonExit.addActionListener(this);
      buttonTTT.addActionListener(this);
      buttonChess.addActionListener(this);
      buttonCheckers.addActionListener(this);
      buttonBack.addActionListener(this);
      buttonLogIn.addActionListener(this);
   }

   /* (non-Javadoc)
    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
    */
   public void actionPerformed(ActionEvent e)
   {
      if (e.getSource() == buttonGames)
      {
         if (isLogged == 0)
         {
            JOptionPane.showMessageDialog(null, "Please log in first!");
         }
         else
         {
            buttonPanel.setVisible(false);
            buttonPanel2.setVisible(true);

            
            
         }
      }
      else if (e.getSource() == buttonBack)
      {
         buttonPanel2.setVisible(false);
         buttonPanel.setVisible(true);
      }
      else if (e.getSource() == buttonExit)
      {
         System.exit(0);
      }
      else if (e.getSource() == buttonTTT)
      {
         controller.execute("choosegame");
      }
      else if (e.getSource() == buttonChess)
      {
         // soon
      }
      else if (e.getSource() == buttonCheckers)
      {
         // soon
      }
      else if (e.getSource() == buttonLogIn)
      {
         controller.execute("login");
         buttonLogIn.setEnabled(false);
         buttonLogIn.setText("Logged In");
         nicknameField.setEditable(false);
         isLogged = 1;
      }
      else if (e.getSource() == buttonHighScores)
      {
         HighScoresFrame highScoresFrame = new HighScoresFrame();
         highScoresFrame.startHS();
      }
   }

   /**
    * starts instance of frame and assigns a controller for it
    */
   public void start(Controller controller)
   {
      this.controller = controller;
      setResizable(true);
      setVisible(true);
      setSize(625, 275);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   /**
    * @return username
    */
   public String getUsername()
   {
      return nicknameField.getText();
   }

   /**
    * starts tic-tac-toe game
    */
   public void startGame()
   {
      TicTacToeFrame tictactoe = new TicTacToeFrame();
      tictactoe.startGame(controller);
   }

   /* (non-Javadoc)
    * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
    */
   @Override
   public void update(Observable o, Object arg)
   {
      if (arg.equals("tictactoe"))
      {
         startGame();
      }
   }
}