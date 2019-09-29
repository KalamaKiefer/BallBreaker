import javax.swing.JFrame;

public class BallBreaker {

   public static void main(String [] args) {
   
      JFrame b = new JFrame();
      Game gameplay = new Game();
      b.setBounds(10, 10, 700, 600);
      b.setTitle("Ball Breaker");
      b.setResizable(false);
      b.setVisible(true);
      b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      b.add(gameplay);
   
   
   }
}