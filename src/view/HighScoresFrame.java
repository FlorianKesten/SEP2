package view;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import domain.mediator.DatabaseManager;

public class HighScoresFrame extends JFrame
{
   private JPanel[] panels;
   private JPanel generalPanel;
   DatabaseManager q;
   ArrayList<String> players;

   /**
    * High Score frame constructor
    */
   public HighScoresFrame()
   {
      super("High Scores");
      initializeComponents();
      addComponentsToFrame();
   }
   /**
    * initializes components
    */
   private void initializeComponents()
   {
      q = new DatabaseManager();
      players = q.showlist();
      panels = new JPanel[players.size()];
      generalPanel = new JPanel();
   }
   
   /**
    * adds components to frame
    */
   private void addComponentsToFrame()
   {
      panels = new JPanel[players.size()];

      generalPanel = new JPanel();
      generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.Y_AXIS));

      for (int i = 0; i < panels.length; i++)
      {

         panels[i] = new JPanel();

         panels[i].setLayout(new FlowLayout(FlowLayout.CENTER));

         JLabel playerName = new JLabel(players.get(i));

         panels[i].add(playerName);

         generalPanel.add(panels[i]);
      }

      setContentPane(generalPanel);
   }

   /**
    * start the instance of High-Score window
    */
   public void startHS()
   {
      setVisible(true);
      setResizable(true);
      setSize(1000, 1000);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }
}