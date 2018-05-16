import java.awt.Graphics;
import java.util.Random;

public class Fire extends Sprite 
{
	
	
   static String[] action = {"fire"};
   double unit_vx;
   double unit_vy;
   Random rnd = new Random();
   int delay;
   
   public Fire(int x, int y)
   {
      super(x, y, "dragon_1_", action, 6, 9);
 

   }
   public void shoot(Fire  fires) // this shoot method will display the fire  on the screen 
   {

	  unit_vx = Math.cos(10*Math.PI/180);
      unit_vy = Math.sin(20*Math.PI/180);
      int r = rnd.nextInt() % 5;
      
      double vx = 10 * unit_vx;
      double vy = .1 * unit_vy;
      
      fires.setLocation(x + (150+ r) * unit_vx, y + (220 + r) * unit_vy) ;
      fires.setVelocity(vx, vy);
      fires.Firing(FIRE);
   

      }
      
   
   }

   

