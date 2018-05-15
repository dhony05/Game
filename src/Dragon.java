public class Dragon extends Sprite
{
	//Rect fireRect = new Rect(0,0, 30, 30, 45);
   static String[] action = {"fly","attack","burning"};
   double unit_vx;
   double unit_vy;
   int current = 0;

   public Dragon(int x, int y)
   {
      super(x, y, "dragon_1_", action, 6, 5);
  
   }
   
   public void shoot(Fire...fires) // this method will display the fire  on the screen 
   {
	   
	  unit_vx = Math.cos(10*Math.PI/180);
      unit_vy = Math.sin(20*Math.PI/180);
      
      double vx = 10 * unit_vx;
      double vy = .1 * unit_vy;
      
     // for(int i = 0; i< fires.length;i++){
      if(current == fires.length) current =0;
      fires[current].setLocation(x + 150 * unit_vx, y + 220 * unit_vy) ;
      fires[current].setVelocity(vx, vy);
     // fire[i].Firing(FIRE);
      isShooting = false;
      // attach the fire to the dragon 
      }
      
     // moving = false; 
   }
 



   

