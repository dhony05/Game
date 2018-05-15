import java.awt.*;
import java.awt.Graphics;

public class Sprite extends Rect
{
   Animation[] animation;
   

   boolean selected = false;


   boolean moving = false;
   boolean isShooting = false;
   boolean isFlaming = false;

   static final int LEFT   = 0;
   static final int RIGHT  = 0;
   static final int UP     = 0;
   static final int DOWN   = 0;
   static final int ATTACK = 1;
   static final int FIRE = 0;
   static final int WALKing = 0;
   static final int ATTACKing = 1;
   static final int DAMAGEDONE = 2;
   static final int NORMAL = 0;
   static final int DESTROYED = 1;

   int pose = LEFT;
   int gPose = RIGHT;
   
   public Sprite(int x, int y, String file, String[] action, int count, int duration)
   {
      super(x, y, 30, 50,45);
      
     

      animation = new Animation[action.length];

      for(int i = 0; i < action.length; i++)

         animation[i] = new Animation(file + action[i]+"_00", count, duration);
   }

   public void moveUpBy(int dy)
   {
      y -= dy;
      
      moving = true;

      pose = UP;
   }
   public void moveDownBy(int dy)
   {
      y += dy;

      moving = true;

      pose = DOWN;
   }
   public void moveLeftBy(int dx)
   {
      x -= dx;

      moving = true;

     // pose = LEFT;
   }
   
   public void moveRightBy(int dx)
   {
      x += dx;

      moving = true;

      pose = RIGHT;
   }
   public void Atack(int dx){
	  // moving = true;
	   isShooting = true;
	   pose = ATTACK;
	   
   }
   public void Firing(int dx){
	   moving = true;
	   isShooting= false;
	   pose = FIRE;
	   
   }
   public void showDamage(int dx){
	   moving = true;
	   gPose = DAMAGEDONE;
			   
   }
   
  public void inHouse(){
	   moving = true;
	   pose = NORMAL;
	   isFlaming =false;
   }
   
   public void inHouseDestroyed(){
	   moving = true;
	   pose = DESTROYED;
	   isFlaming = true;
   }

   public void AttingGoblin(int dx){
	   moving = false;
	   gPose = ATTACKing;
   }
   public void WalkingGobling(int dx){
	   x -= dx;
	   moving = true;
	   gPose = WALKing;
   }
   public void draw(Graphics g)
   {
	  
      if(moving )
         g.drawImage(animation[pose].nextImage(), (int)x, (int)y, null);
      //else if (isShooting)
    	//  g.drawImage(animation[ATTACK].stillImage(), (int)x, (int)y, null);
      		
      else
         g.drawImage(animation[pose].stillImage(), (int)x, (int)y, null);

         moving = true;
      

      g.setColor(Color.red);
      if (selected) g.drawLine((int)x+8, (int)y+h, (int)x+w-5, (int)y+h);
      g.setColor(Color.black);

   }
   
}