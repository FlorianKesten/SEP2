package domain.mediator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseManager
{

   private String url;
   private String user;
   private String pw;
   private Connection connection;
   private String selectLastInsertID = "SELECT LAST_INSERT_ID();";

   // Do NOT change
   private static final String DRIVER = "org.postgresql.Driver";
   private static final String URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=boardgame";
   private static final String USER = "postgres";
   private static final String PASSWORD = "password"; // WRITE THE PASSWORD TO YOUR DATABASE!!!

   /**
    *  Constructor for the DatabaseManager
    */
   public DatabaseManager()
   {
      url = URL;
      user = USER;
      pw = PASSWORD;
      try
      {
         connection = DriverManager.getConnection(url, user, pw);
         System.out.println("DB Connection Ok");
      }
      catch (SQLException e)
      {
         System.out.println("Cant connect to database");
         System.out.println(e);
      }

   }

   /**
    * Checks if the entered username is already present in the database
    * @param username
    * @return
    */
   public boolean existingplayer(String username)
   {
      try
      {
         PreparedStatement stmt = connection
               .prepareStatement("SELECT * FROM player where username = ?");

         stmt.setString(1, username);

         ResultSet rS = stmt.executeQuery();

         return (rS.next());

      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return false;
   }

   /**
    * 
    * Creates a new player in the database with the given username
    * @param username
    */
   public void createPlayer(String username)
   {

      try
      {

         PreparedStatement stmt = connection.prepareStatement(
               "INSERT INTO player ( username, logindate) VALUES (?, current_date)");

         stmt.setString(1, username);

         stmt.executeUpdate();

      }
      catch (SQLException e)
      {
         System.out.println(e);
      }
   }

   /**
    * Returns a list of players
    * @return
    */
   public ArrayList<String> showlist()
   {
      ArrayList<String> answer = new ArrayList<String>();
      try
      {
         PreparedStatement stmt = connection
               .prepareStatement(" Select  username from player");
         ResultSet rs = stmt.executeQuery();
         while (rs.next())
         {

            answer.add(rs.getString(1));
         }

         return answer;
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return answer;

   }
}