import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Font;

public class Game extends JPanel implements KeyListener, ActionListener{
   private boolean play = false;
   private int score = 0;

   private int totalBricks = 21;

   private Timer timer;
   private int delay = 8;

   private int playerX = 310;

   private int ballpositionX = 120;
   private int ballpositionY = 350;
   private int balldirectionX = -1;
   private int balldirectionY = -2;

  private BrickGenerator map;
   
   public Game() {
      map = new BrickGenerator(3, 7);
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      timer = new Timer(delay, this);
      timer.start();
   
   }

   public void paint(Graphics g) {
   
   // background
      g.setColor(Color.black);
      g.fillRect(1, 1, 692, 5932);
   
   // map 
      map.draw((Graphics2D)g);
      
    // scores 
    g.setColor(Color.white);
    g.setFont(new Font("serif", Font.BOLD, 25));
    g.drawString(""+score, 590, 30);
   
   
   // borders
      g.setColor(Color.yellow);
      g.fillRect(0, 0, 3, 592);
      g.fillRect(0, 0, 692, 3);
      g.fillRect(691, 0, 3, 592);
   
   // paddle
      g.setColor(Color.blue);
      g.fillRect(playerX, 550, 100, 8);
   
   // ball
      g.setColor(Color.pink);
      g.fillOval(ballpositionX, ballpositionY, 20, 20);
      
      if(totalBricks==0){
			play=false;
			g.setColor(Color.yellow);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("YOU WON "+score, 190, 300);
		}
      
      if(ballpositionY > 570) {
      play = false;
      balldirectionX = 0;
      balldirectionY = 0;
      g.setColor(Color.RED);
      
      g.setFont(new Font("serif", Font.BOLD, 25));
      g.drawString("GAME OVER, score: " + score, 190, 300);
      
       g.setFont(new Font("serif", Font.BOLD, 20));
       g.drawString("Press Enter To Restart", 230, 350);
}
   
      g.dispose();
   }


   public void actionPerformed(ActionEvent e) {
    timer.start();
    if(play) {
         if(new Rectangle(ballpositionX, ballpositionY, 20, 20). intersects(new Rectangle(playerX, 550, 100, 8))) {
            balldirectionY = -balldirectionY;
         }
      
       A:  for(int i = 0; i< map.map.length; i++) {
         
            for(int j = 0; j < map.map[0].length; j++) {
     
               if(map.map[i][j] > 0) {
                  int brickX = j * map.brickWidth + 80;
                  int brickY = i * map.brickHeight + 50;
                  int brickWidth = map.brickWidth;
                  int brickHeight = map.brickHeight;
                
                  Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                  Rectangle ballRect = new Rectangle(ballpositionX, ballpositionY, 20, 20);
                  Rectangle brickRect = rect;
                
                  if(ballRect.intersects(brickRect)) {
                     map.setBrickvalue(0,i,j);
                     totalBricks--;
                     score += 5;
                  
                     if(ballpositionX + 19 <= brickRect.x || ballpositionX + 1 >= brickRect.x + brickRect.width) {
                        balldirectionX = -balldirectionX;
                     } else {
                        balldirectionY = -balldirectionY;
                     }
                     break A;
                  } // closes last if
               } // closes first if
            } // closes for
            
           
         } // closes first for
      
         ballpositionX += balldirectionX;
         ballpositionY += balldirectionY;
      
         if(ballpositionX < 0) {
            balldirectionX = -balldirectionX;
         }
         if(ballpositionY < 0) {
            balldirectionY = -balldirectionY;
         }
         if(ballpositionX > 670) {
            balldirectionX = -balldirectionX;
         
         }
      } // closes play if
   
   
   
      repaint();
   }

   public void keyTyped(KeyEvent e) {
   }
   public void keyPressed(KeyEvent ke) {
      if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
         if(playerX >= 600) {
            playerX = 600;
         } else {
            moveRight(); 
         }
      } 
      if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
         if(playerX < 10) {
            playerX = 10;
         } else {
            moveLeft(); 
         }
      } // end left if
      
      if(ke.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballpositionX = 120;
                ballpositionY = 350;
                balldirectionX = -1;
                balldirectionY = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new BrickGenerator(3,7);

                repaint();
            }
      }
      
   } // end keypressed
   public void keyReleased(KeyEvent e) {
   }

   public void moveRight() {
      play = true;
      playerX+=20;
   } // closes right
   public void moveLeft() {
      play = true;
      playerX-=20;
   }


} // closes class