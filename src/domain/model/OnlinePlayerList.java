package domain.model;

import java.util.ArrayList;

public class OnlinePlayerList
{
   private ArrayList<Object[]> active;
   private Object[] pair;

   /**
    * Constructor for OnlinePlayerList
    */
   public OnlinePlayerList()
   {

      this.active = new ArrayList<Object[]>();
      this.pair = new Object[2];
   }

   /**
    * Returns an arraylist of players who are currently playing
    * @return
    */
   public ArrayList<Object[]> getActive()
   {
      return active;
   }

   /**
    * returns an ArrayList of online players
    * @return
    */
   public ArrayList<String> getOnlinePlayers()
   {
      ArrayList<String> usernames = new ArrayList<String>();

      for (int i = 0; i < active.size(); i++)
      {
         usernames.add((String) active.get(i)[0]);
      }

      return usernames;
   }

   /**
    * adds a pair of players 
    */
   public void addPair()
   {
      active.add(pair);
      pair = new Object[2];

   }

   /**
    * Returns a pair of players
    * @return
    */
   public Object[] getPair()
   {
      return pair;
   }

   /**
    * 
    */
   public void setPair()
   {
      // TO DO
   }
}