

import java.awt.*;
import java.util.Random;

public class Tank extends PolygonModel
{
  Random rnd = new Random();

  public Tank(double x, double y, int A)
  {
      super(x, y, A);
  }

  public void shoot(Rect shell)
  {
     double unit_vx = Math.cos(A*Math.PI/180);
     double unit_vy = Math.sin(A*Math.PI/180);

     int r = rnd.nextInt() % 5;

     shell.setLocation(x + (30 + r) * unit_vx, y + (30 + r) * unit_vy) ;

     double vx = 10 * unit_vx;
     double vy = 10 * unit_vy;

     shell.setVelocity(vx, vy);
  }


  public int[][] struct_x()
  {
     final int[][] struct_x =
     {
        {35, 35, -35, -35},
        {5, 5, -5, -5},
        {30, 30, 5, 5},
        {27, 27, -27, -27},
        {27, 27, -27, -27},
     };

     return struct_x;
  }

  public int[][] struct_y()
  {
     final int[][] struct_y =
     {
        {-25, 25, 25, -25},
        {-12, 12, 12, -12},
        {-3, 3, 3, -3},
        {25, 32, 32, 25},
        {-25, -32, -32, -25}
     };

     return struct_y;
  }
}







