import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class BrickGenerator {

   public int map[][];
   public int brickWidth;
   public int brickHeight;
   public BrickGenerator(int row, int col) {
      map = new int[row][col];
   
      for(int i = 0; i < map.length; i++) {
         for(int j = 0; j < map[0].length; j++) {
            map[i][j] = 1;
         }
      }// closes first for
   
      brickWidth = 540/col;
      brickHeight = 150/row;
   
   
   } // closes bg

   public void draw(Graphics2D g) {
      for(int i = 0; i < map.length; i++) {
         for(int j = 0; j < map[0].length; j++) {
            if(map[i][j] > 0) {
            
               g.setColor(Color.white);
               g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
            
               g.setStroke(new BasicStroke(3));
               g.setColor(Color.black);
               g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
            
            } // closes if
         }
      } // closes first for
   
   
   } // closes draw
public void setBrickvalue(int value, int row, int col) {
map[row][col] = value;
}

} // closes class