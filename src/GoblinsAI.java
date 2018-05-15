


public class GoblinsAI extends Rect
{
   public GoblinsAI(double x, double y, int A)
   {
      super(x, y, A, A, A);
   }

   public void shoot(Rect arrow, Rect dragon)
   {
      double ux = Lookup.cos[A];
      double uy = Lookup.sin[A];

      double nx = -uy;
      double ny = ux;

      double vx = dragon.x - x;
      double vy = dragon.y - y;

      double d = vx*nx + vy*ny;


      if((d < 500) && (d > -5))
      {
         super.shoote(arrow);
     }
   }


   public void moveTowards(Rect rect)
   {
      turnTowards(rect);

      double ux = Lookup.cos[A];
      double uy = Lookup.sin[A];

      double nx = -uy;
      double ny = ux;

      double vx = rect.x - x;
      double vy = rect.y - y;

      double d = vx*ux + vy*uy;


     // if ( d > 250)

       //  this.moveForwardBy(4);
   }



   public void turnTowards(Rect rect)
   {
      double ux = Lookup.cos[A];
      double uy = Lookup.sin[A];

      double nx = -uy;
      double ny = ux;

      double vx = rect.x - x;
      double vy = rect.y - y;

      double d = vx*nx + vy*ny;

      if(d > 5)
      {
          A += 3;
          if (A >= 360)  A -= 360;
      }
      if(d < -5)
      {
         A -= 3;
         if(A < 0)  A += 360;
      }

   }

}