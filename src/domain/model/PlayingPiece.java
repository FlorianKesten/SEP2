package domain.model;

public interface PlayingPiece
{
   /**
    * @return
    */
   public int[] getCoordinates();

   /**
    * returns the coordinates of a playing piece
    * @return
    */
   public String getType();
}