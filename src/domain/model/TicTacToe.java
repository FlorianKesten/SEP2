package domain.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class TicTacToe extends Observable implements Board {

	private PlayingPiece[][] board;
	private boolean X;
	private	ArrayList<Observer> players;
	private int	count;

	
	/**
	 * Constructor for TicTacToe
	 */
	public TicTacToe() {
		initializeBoard();
	}
	
	/**
	 * Creates a board
	 */
	private void initializeBoard()
	{
		board = new PlayingPiece[3][3];
		X = true;
		count=0;
		players = new ArrayList<Observer>();
		for (int i = 0; i < 3; i++)
	      {
	         for (int j = 0; j < 3; j++)
	         {
	            board[i][j] = new Mark(new int[]{i,j},"e");
	         }
	      }
	}
	
   /* (non-Javadoc)
    * @see domain.model.Board#addPlayer(java.util.Observer)
    */
   public boolean addPlayer(Observer p)
   {
	   addObserver(p);
	   players.add(p);
	   return players.size() == 2;
   }
	
   /* (non-Javadoc)
    * @see domain.model.Board#interact(java.lang.String)
    */
   @Override
   public void interact(String mark)
   {	
	   String[] coordinates;
	   coordinates = mark.split(";");
	   System.out.println(coordinates[0]);
	   if (X) {
			board[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])] = new Mark(new int[] {Integer.parseInt(coordinates[0]),Integer.parseInt(coordinates[1])}, "X");
			count++;
			if(testWin("X"))
			{
				mark = mark + ";X;notify;win:p1";
				initializeBoard();
				count=0;
			}
			if(count == 9)
			{
				mark = mark + ";X;notify;draw";
				initializeBoard();
				count=0;
			}
			mark = mark + ";X;cont";
			X = false;
			
		}
		else {
			board[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])] = new Mark(new int[] {Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]) }, "O");
			count++;
			if(testWin("O"))
			{
				mark = mark + ";O;notify;win:p1";
				initializeBoard();
				count=0;
			}
			if(count == 9)
			{
				mark = mark + ";O;notify;draw";
				initializeBoard();
				count=0;
			}
			mark = mark + ";O;cont";
			X = true;
		}
	   
	   super.setChanged();
	   super.notifyObservers(mark);
	   
	   
   }
   
   /**
    * Return true if a type of mark reached the conditions necessary to win
    * @param type
    * @return
    */
   public boolean  testWin(String type){
	   try
	   { 
	   for(int i=0;i<3;i++){
	         if(board[i][0].getType().equals(type)&&board[i][1].getType().equals(type)
	               &&board[i][2].getType().equals(type)){
	            return true;
	         }
	         if(board[0][i].getType().equals(type)&&board[1][i].getType().equals(type)
	               &&board[2][i].getType().equals(type)){
	            return true;
	         }
	      }
	      if(board[0][0].getType().equals(type)&&board[1][1].getType().equals(type)
	               &&board[2][2].getType().equals(type)){
	            return true;
	         }
	      if(board[0][2].getType().equals(type)&&board[1][1].getType().equals(type)
	            &&board[2][0].getType().equals(type)){
	         return true;
	      }
	      
	   }catch(NullPointerException e)
	   {
		   // nothing
	   }
	   return false;
	   }
   
   /* (non-Javadoc)
    * @see java.util.Observable#addObserver(java.util.Observer)
    */
   public void addObserver(Observer o)
	{
	   super.addObserver(o);
	}
	
}

