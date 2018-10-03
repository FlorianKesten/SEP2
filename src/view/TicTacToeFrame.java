package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controller.Controller;

public class TicTacToeFrame extends JFrame implements Observer
{
   // Panels
   private JPanel gamePanel;
   private JPanel generalPanel;
   private JPanel matchupPanel;

   // Labels
   private JLabel matchupLabel;

   // Controller
   private Controller controller;

   // Button coordinates storage
   private String coordinates;

   // Buttons
   private JButton[][] buttons = new JButton[3][3];

   /**
    * Tic Tac Toe frame constructor
    */
   public TicTacToeFrame()
   {
      super("Tic-Tac-Toe");
      initializeComponents();
      addComponentsToFrame();
      registerEventHandlers();
   }

   /**
    * initiazises components
    */
   public void initializeComponents()
   {

      matchupLabel = new JLabel(
            "<HTML><I><font size=3 color=red> Current Match:</font></I> "
                  + "<font size=4>" + "Player 1" + "</font>"
                  + " <font size=2 color=blue>vs</font> " + "<font size=4>"
                  + "Player 2" + "</font></HTML>");
   }

   /**
    * adds components to frame
    */
   public void addComponentsToFrame()
   {
      // Panel with the player's names
      matchupPanel = new JPanel(new BorderLayout(0, 5));
      matchupPanel.add(matchupLabel, BorderLayout.NORTH);

      // The Game Panel
      gamePanel = new JPanel(new GridLayout(3, 3));

      for (int i = 0; i < 3; i++)
      {
         for (int j = 0; j < 3; j++)
         {
            buttons[i][j] = new JButton();
            buttons[i][j].setText("");
            buttons[i][j].setFont(new Font("Tahoma", Font.PLAIN, 55));
            gamePanel.add(buttons[i][j]);
         }
      }

      // GENERAL FRAME PANEL
      generalPanel = new JPanel(new BorderLayout());
      generalPanel.add(matchupPanel, BorderLayout.NORTH);
      generalPanel.add(gamePanel);

      setContentPane(generalPanel);
   }

   /**
    * registers event handlers
    */
   private void registerEventHandlers()
   {
      ButtonHandler buttonHandler = new ButtonHandler();
      for (int i = 0; i < 3; i++)
      {
         for (int j = 0; j < 3; j++)
         {
            buttons[i][j].addActionListener(buttonHandler);
         }
      }
   }

   // Reseting buttons at the end of the game
   /**
    * method to reset buttons that could be used sometime
    */
   public void resetButtons()
   {
      for (int i = 0; i < 3; i++)
      {
         for (int j = 0; j < 3; j++)
         {
            buttons[i][j].setText("");
            buttons[i][j].setEnabled(true);
         }
      }
   }

   private class ButtonHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JButton buttonClicked = (JButton) e.getSource(); // get the particular
                                                          // button that was
                                                          // clicked

         for (int i = 0; i < 3; i++)
         {
            for (int j = 0; j < 3; j++)
            {
               if (buttons[i][j] == buttonClicked)
               {
                  coordinates = i + ";" + j;
               }
            }
         }
         controller.execute("click");
      }
   }

   /**
    * returns coordinates
    * @param what
    * @return
    */
   public String get(String what)
   {
      System.out.println(coordinates + "gui");
      return coordinates;
   }

   /**
    * updates the right piece
    */
   public void update(String piece)
   {
      String[] coordinates;
      coordinates = piece.split(";");
      buttons[Integer.parseInt(coordinates[0])][Integer
            .parseInt(coordinates[1])].setText(coordinates[2]);
      buttons[Integer.parseInt(coordinates[0])][Integer
            .parseInt(coordinates[1])].setEnabled(false);
   }

   /**
    * starts instance of game frame and assigns a controller for it
    */
   public void startGame(Controller controller)
   {
      this.controller = controller;
      this.controller.setGameView(this);

      setVisible(true);
      setResizable(false);
      setSize(350, 475);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }

   /* (non-Javadoc)
    * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
    */
   @Override
   public void update(Observable o, Object arg)
   {
      if (arg.toString().split(";")[3].equals("notify"))
      {
         JOptionPane.showMessageDialog(null, arg.toString());
         String[] coordinates;
         coordinates = ((String) arg).split(";");
         buttons[Integer.parseInt(coordinates[0])][Integer
               .parseInt(coordinates[1])].setText(coordinates[2]);
         buttons[Integer.parseInt(coordinates[0])][Integer
               .parseInt(coordinates[1])].setEnabled(false);
         this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
         return;
      }
      String[] coordinates;
      coordinates = ((String) arg).split(";");
      buttons[Integer.parseInt(coordinates[0])][Integer
            .parseInt(coordinates[1])].setText(coordinates[2]);
      buttons[Integer.parseInt(coordinates[0])][Integer
            .parseInt(coordinates[1])].setEnabled(false);
   }
}