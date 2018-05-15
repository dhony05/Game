import java.awt.*;
import java.util.Random;

public class Rect
{
   double x;
   double y;

   int w;
   int A;
   int h;

   double vx = 0;
   double vy = 0;
   
   Random rnd = new Random() ;
   static final double g = .1;
   

   double ax = 0;
   double ay = g;

   boolean held = false;

   public Rect(double x, double y, int w, int h, int A)
   {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
      this.A = A;
   }

   public void setLocation(double x, double y)
   {
      this.x = x;
      this.y = y;
   }

   public void shoot(Rect[] arrow,Rect dragon)
   {
      double ux = Lookup.cos[A];
      double uy = Lookup.sin[A];

      double nx = -uy;
      double ny = ux;

      double vx = dragon.x - x;
      double vy = dragon.y - y;

      double d = vx*nx + vy*ny;


      if((d < 5) && (d > -5))
      {
         shoot(arrow, dragon);
      }
   }


   public void grab()
   {
      held = true;
   }

   public void drop()
   {
      held = false;
   }

   public boolean overlaps(Rect r)
   {
      return (x      < r.x + r.w )   &&
             (x + w  > r.x       )   &&
             (y      < r.y + r.h )   &&
             (y + h  > r.y       );
   }

   public boolean contains(int mx, int my)
   {
      return (mx > x) && (mx < x+w) && (my > y) && (my < y+h);
   }


   public void draw(Graphics g)
   {
      g.drawRect((int)x, (int)y, w, h);
   }

   public void setVelocity(double vx, double vy)
   {
      this.vx = vx;
      this.vy = vy;
   }
   public void setAccelation(double ax, double ay)
   {
      this.ax = ax;
      this.ay = ay;
   }

   public void move()
   {
	  vx += ax;
	  vy += ay;
	  
      x += vx;
      y += vy;
   }


   public void moveBy(double dx, double dy)
   {
	  
      x += dx;
      y += dy;
   }
   

   public void resizeBy(int dw, int dh)
   {
       w += dw;
       h += dh;
   }
   
   public void moveUpBy(int dy)
   {
      y -= dy;
      
     
   }
   public void moveDownBy(int dy)
   {
      y += dy;

   
   }
   public void moveLeftBy(int dx)
   {
      x -= dx;

     
   }
   public void moveRightBy(int dx)
   {
      x += dx;

     
   }
   
	    
	  public void shoote(Rect shell) {
	 double unit_vx = Math.cos(A*Math.PI/180);
     double unit_vy = Math.sin(A*Math.PI/180);

     int r = rnd.nextInt() % 5;

     shell.setLocation(x + (30 + r) * unit_vx, y + (30 + r) * unit_vy) ;

     double vx = 10 * unit_vx;
     double vy = 10 * unit_vy;

     shell.setVelocity(vx, vy);
  }


	// TODO Auto-generated method stub
	
}


