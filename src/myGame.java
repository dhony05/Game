
import java.applet.Applet;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;


public class myGame extends Applet implements KeyListener, Runnable
{
	Image    off_screen;
	Graphics off_g;
	
	Random random = new Random() ;
	

	/**
	 * Created Rectangles objects from Rect class to manage the collision detection
	 */
	Rect skyRect = new Rect(0,10,2000,20,45);
	Rect dragonRect = new Rect (105,570,195,105,45);
	Rect groundRect = new Rect (0,718,2000,240, 45);
	Rect backBackGround = new Rect(0,10,10,1000, 45);
	Rect middleBacground = new Rect(1000,10,10,1000,45);
	Rect underGroundRect = new Rect (0,918,2000,240, 45);
	Rect frontLineRect = new Rect(1900,10,10,1000,45); 
	


	Dragon dragonites;
	damage[] DAMAGEDONE = new damage[1000];
	Rect damageRECT [] = new Rect[DAMAGEDONE.length];
	
	ArrayList<Fire> fireList = new ArrayList<>();
	ArrayList<Rect> fireRectList = new ArrayList<>();
	
	
	
	//Fire[] fire = new Fire[20];
	//Rect[] fireRect = new Rect[20];
	aditionalObject house = new aditionalObject(1000, 600);
	aditionalObject[] houses = new aditionalObject[100];
	Rect[] housesRect = new Rect[houses.length];
	
	
	
	
	
	
	

	Goblin[] goblines = new Goblin[10];
	Rect [] goblinsRect = new Rect[goblines.length];

	
	ArrayList<ImageLayer>  backgroundList = new ArrayList<>();
	


	ImageLayer background0 = new ImageLayer("Layer_0000_9.png",0,0,10);
	ImageLayer background1 = new ImageLayer("Layer_0001_8.png",0,200,10);
	ImageLayer background2 = new ImageLayer("Layer_0002_7.png",0,0,10);
	ImageLayer background3 = new ImageLayer("Layer_0003_6.png",0,0,10);
	ImageLayer background4 = new ImageLayer("Layer_0004_Lights.png",0,0,100);
	ImageLayer background5 = new ImageLayer("Layer_0005_5.png",0,0,10);
	ImageLayer background6 = new ImageLayer("Layer_0006_4.png",0,0,10);
	ImageLayer background7 = new ImageLayer("Layer_0007_Lights.png",0,0,100);
	ImageLayer background8 = new ImageLayer("Layer_0008_3.png",0,0,10);
	ImageLayer background9 = new ImageLayer("Layer_0009_2.png",0,0,10);
	ImageLayer background11 = new ImageLayer("houses.gif",0,0,10);
	ImageLayer background10 = new ImageLayer("Layer_0010_1.png",0,0,10);

	//to otimize this create an array



	Thread t;

	boolean sp_pressed = false;
	boolean lt_Pressed = false;
	boolean rt_Pressed = false;
	boolean up_Pressed = false;
	boolean dn_Pressed = false;


	boolean overlap = false;
	

	int mx;
	int my;


	public void init()
	{
		resize(2000,950);
		off_screen = this.createImage(2000, 1200);
		off_g      = off_screen.getGraphics();


		
		/**
		 * Setting the original position for the objects 
		 *
		 * 
		 */
		
		dragonites = new Dragon(75, 500);
//////////////////////////////
		for (int i = 0; i<100; i++){
			Fire fires = new Fire(800,0);
			Rect fireRects = new Rect(-800,20,30,50, 45);
			fireList.add(fires);
			fireRectList.add(fireRects);
			
		}
		/////////////////////////////////////////////
		
		
		for (int i = 0; i < DAMAGEDONE.length; i++){
			
			DAMAGEDONE[i] = new damage(1000+50 *i, 700);
			damageRECT[i] = new Rect(1000+50 *i, 700, 25,25,45);
			
		}
		//////////////////////////////////////////////
		// create a random variable to track the houses position 
		
		for (int i = 0; i< houses.length; i++){
			int currentRand = random.nextInt(800)+250;
			houses[i] = new aditionalObject(1100 +currentRand*i, 700);
			 
			housesRect[i] = new Rect(1100 + currentRand*i, 700,125,130,90);
			
		}
		//////////////////////////////////////////////////////
		for (int i = 0; i < goblines.length; i++){
			goblines[i] = new Goblin(1000+40*i, 670);
			goblinsRect[i] = new Rect(goblines[i].x+20,goblines[i].y+20,25,30,45);
		}
		///////////////////////////////////////////////////
		



		requestFocus();
		addKeyListener(this);



		t = new Thread(this);

		t.start();
	}
	int  current = 0;

	public void run()
	{

		while(true)
		{
			
			if(fireRectList.get(current).overlaps(houses[current])){
				System.out.println("is firing");
			}
			//			for (int i= 0;i <goblinsRect.length;i++){
			//				goblinsRect[i].(shell[current],dragonRect);
			//			}
			//		     
			//			 current++;
			//	         if(current == shell.length)  current = 0;
			//
			//      for(int i = 0; i < shell.length; i++){
			//
			//   shell[i].move();
			//     }
			//int currentGoblin = 0;
			for (int i = 0 ; i< goblines.length-1;i++){

				goblinsRect[i].moveLeftBy(1);
				goblines[i].moveLeftBy(1);
				
					
			if((goblinsRect[i].overlaps(middleBacground))||(goblinsRect[i].overlaps(goblinsRect[i+1]))){

				goblines[i].moveRightBy(1); goblinsRect[i].moveRightBy(1);
				goblines[i].isShooting = true;
			    goblines[i].AttingGoblin(1);
				//System.out.println("is overlaping");

			} 
			}

			for(int i=0; i<fireList.size(); i++)
			{	fireList.get(i).move();
				fireRectList.get(i).move();
			
				//fire[i].move();
				//fireRect[i].move(); 
			}
			for(int i =0; i < fireList.size(); i++)
			if(fireRectList.get(i).overlaps(houses[i])){
				fireRectList.remove(i);
				fireList.remove(i);
				houses[i].inHouseDestroyed();
				System.out.println("is working");
//			if(fireRect[current].overlaps(groundRect)){
//					System.out.println("is overlaping");
//				}	
//			
			
			
//				

			}
			

			//shell.move();
			//if (shell.overlaps(soldier))  soldier.moving = false;
			/*if(sp_pressed)
			{
				tank.shoot(shell);
			}
			if(lt_Pressed)
			{
				tank.rotateLeftBy(6);
			}
			if(rt_Pressed)
			{
				tank.rotateRightBy(6);
			}
			if(up_Pressed)
			{
				tank.moveForwardBy(5);
			}
			if(dn_Pressed)
			{
				tank.moveBackwardBy(5);
			}*/

			

			//overlap = dragonRect.overlaps(groundRect);
			

			if(up_Pressed){   dragonites.moveUpBy(2); dragonRect.moveUpBy(2);   }
			if(dn_Pressed){   dragonites.moveDownBy(2); dragonRect.moveDownBy(2);  }
			if(lt_Pressed) 
			{
				dragonites.moveLeftBy(2); dragonRect.moveLeftBy(2);


			}


			if(rt_Pressed) {
				dragonites.moveRightBy(2); dragonRect.moveRightBy(2);


				//				for(int i = 0; i< goblines.length; i++)
				//					goblines[i].moveRightBy(2);
			
			}
			/**
			 * Manages the attack 
			 */
			if (sp_pressed){
				dragonites.Atack(2);
				dragonites.shoot(fireList.get(current));
				sp_pressed = false;
				
			
			
				fireList.get(current).move();
				//fire[current].move();
				fireRectList.get(current).setVelocity(fireList.get(current).vx, fireList.get(current).vy); // FINALLY SYNCRONICED RECT WITH FIRE ANIMATION
				fireRectList.get(current).setLocation(fireList.get(current).x + 30+ 150 * fireList.get(current).unit_vx, fireList.get(current).y + 20+ 220 * fireList.get(current).unit_vy);
				
				if(fireRectList.get(current).overlaps(houses[current])){
					System.out.println("is firing");
				}
				System.out.println("current: " +current);//.shoot(fire[1]);
				
				current++;
				if(current == fireList.size())  
					current = 0;
			
				
			
			}

			if(dragonRect.overlaps(groundRect)){
				dragonites.moveUpBy(2); dragonRect.moveUpBy(2);

			}
			if(dragonRect.overlaps(backBackGround)){
				dragonites.moveRightBy(2); dragonRect.moveRightBy(2);

			}
			if(dragonRect.overlaps(middleBacground)){
				dragonites.moveLeftBy(2);dragonRect.moveLeftBy(2);
				background10.moveLeftBy(20);
				background9.moveLeftBy(20);
				background8.moveLeftBy(20);
				background7.moveLeftBy(20);
				background6.moveLeftBy(20);
				background5.moveLeftBy(20);
				background4.moveLeftBy(20);
				background3.moveLeftBy(20);
				background2.moveLeftBy(20);
				background1.moveLeftBy(20);
				background11.moveLeftBy(20);
				
				for ( int i = 0; i<houses.length;i++){
					
					houses[i].moveLeftBy(2);
					housesRect[i].moveLeftBy(2);
				}
				
			}

			if(dragonRect.overlaps(skyRect)){
				dragonites.moveDownBy(2);dragonRect.moveDownBy(2);
			}
			
			


			//		


			repaint();

			try
			{
				t.sleep(15);
			}
			catch(Exception x){}
		}

	}

	public void update(Graphics g)
	{
		off_g.clearRect(0, 0, 1000, 700);

		paint(off_g);

		g.drawImage(off_screen, 0, 0, null);
	}


	public void paint(Graphics g)
	{



		background10.draw(g);
		background9.draw(g);
		background8.draw(g);
		background7.draw(g);
		background6.draw(g);
		background5.draw(g);
		background4.draw(g);
		background3.draw(g);
		background2.draw(g);
		background1.draw(g);
		background11.draw(g);




		//house.draw(g);



		for(int i =0; i<goblines.length;i++){
			goblines[i].draw(g);
			goblinsRect[i].draw(g);

		}

		for (int i = 0; i < fireList.size(); i++){
			fireList.get(i).draw(g);
			fireRectList.get(i).draw(g);
			

		}
		
		for (int i = 0; i< DAMAGEDONE.length;i++){
			
			DAMAGEDONE[i].draw(g);
			damageRECT[i].draw(g);
			
		}
		for ( int i = 0; i<houses.length;i++){
			houses[i].draw(g);
			housesRect[i].draw(g);
		}
		
		dragonites.draw(g);
		/**
		 * Drawing Rectangles for the sprites collision detection
		 */
		dragonRect.draw(g);
		groundRect.draw(g);
		backBackGround.draw(g);
		middleBacground.draw(g);
		skyRect.draw(g);
		underGroundRect.draw(g);
		frontLineRect.draw(g);
		
		
		



		if (dragonRect.overlaps(groundRect))  {
			g.drawString("overlap", 0, 500);
			System.out.println("overlaping");
		}

		else  {
			g.drawString("-------", mx, my);
		}

	}

	


	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();

		if(code == e.VK_LEFT)     lt_Pressed = true;
		if(code == e.VK_RIGHT)    rt_Pressed = true;
		if(code == e.VK_UP)       up_Pressed = true;
		if(code == e.VK_DOWN)     dn_Pressed = true;

		if(code == e.VK_SPACE)    sp_pressed = true;


	}

	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();

		if(code == e.VK_LEFT)     lt_Pressed = false;
		if(code == e.VK_RIGHT)    rt_Pressed = false;
		if(code == e.VK_UP)       up_Pressed = false;
		if(code == e.VK_DOWN)     dn_Pressed = false;

		if(code == e.VK_SPACE)    sp_pressed = false;
	}

	public void keyTyped(KeyEvent e) { 
		
	}


}
