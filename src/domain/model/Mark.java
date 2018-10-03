package domain.model;

public class Mark implements PlayingPiece
{

   private int[] coordinates;
   private String type;

   /**
    * @param coordinates
    * @param type
    */
   public Mark(int[] coordinates, String type)
   {
      this.coordinates = coordinates;
      this.type = type;
   }

   /* (non-Javadoc)
    * @see domain.model.PlayingPiece#getCoordinates()
    */
   public int[] getCoordinates()
   {
      return coordinates;
   }

   /* (non-Javadoc)
    * @see domain.model.PlayingPiece#getType()
    */
   public String getType()
   {
      return type;
   }
}