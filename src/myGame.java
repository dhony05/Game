
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

	boolean isDestroyed = false;
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



	Fire fires = new Fire(-800,0);
	Rect fireRects = new Rect(-800,20,50,50, 45);
	aditionalObject house = new aditionalObject(1000, 600);
	aditionalObject[] houses = new aditionalObject[100];
	Rect[] housesRect = new Rect[houses.length];





	int damagePoints = 0;
	int housesDestroyed = 0;
	int goblinskilled = 0;

	ArrayList<Goblin>  goblinList = new ArrayList<>();
	ArrayList<Rect>  goblinRectList = new ArrayList<>();




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
			Fire fires = new Fire(-800,0);
			Rect fireRects = new Rect(-800,20,50,50, 45);
			fireList.add(fires);
			fireRectList.add(fireRects);

		}
		/////////////////////////////////////////////


		for (int i = 0; i < DAMAGEDONE.length; i++){
			int currentRand = random.nextInt(70) + 50;
			DAMAGEDONE[i] = new damage(-1000+currentRand *i, 700);
			damageRECT[i] = new Rect(-1000+currentRand *i, 700, 25,25,45);

		}
		//////////////////////////////////////////////
		// create a random variable to track the houses position 

		for (int i = 0; i< houses.length; i++){
			int currentRand = random.nextInt(800)+250;

			houses[i] = new aditionalObject(1100 +currentRand*i, 700);
			housesRect[i] = new Rect(1100 + currentRand*i, 700,125,130,90);

		}
		//////////////////////////////////////////////////////
		for (int i = 0; i < 100; i++){
			int currentRand = random.nextInt(100)+40;
			Goblin goblins = new Goblin(1000+currentRand*i, 670);
			goblinList.add(goblins);
			Rect goblinsRecting = new Rect(goblinList.get(i).x+20,goblinList.get(i).y+20,25,30,45);

			goblinRectList.add(goblinsRecting);


		}



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

///////////////////////////////Colition detection between fires and houses
			for(int i = 0; i< fireRectList.size(); i++){
				for(int j = 0; j< houses.length; j++){
					if(fireRectList.get(i).overlaps(houses[j])&& !houses[j].isFlaming){
						damagePoints = 5 + damagePoints; 
						housesDestroyed ++;																	//////////////////////////////////// REALLY A F... NESTED LOOP
						fireRectList.remove(i);
						fireList.remove(i);
						houses[j].inHouseDestroyed();// changing house animation 
						fireList.add(fires);
						fireRectList.add(fireRects);


						System.out.println("House done");

					}
				}
			}
///////////////////////////////////////////////////Colition detection between fires and goblins
			for(int i = 0; i< fireRectList.size(); i++){

				for(int j = 0; j< goblinList.size(); j++){

					if(fireRectList.get(i).overlaps(goblinRectList.get(j))){

						damagePoints = 2 + damagePoints;
						goblinskilled ++;
						/////////////////OMGGGGGGGGGGGGGGGGGG!!!!!!!!!!!!!
						fireRectList.remove(i);
						fireList.remove(i);
						goblinList.remove(j);
						goblinRectList.remove(j);
						if(fireRectList.size() == 10){
							for(int k = 0; j<20; k++){
								fireList.add(fires);
								fireRectList.add(fireRects);
							}
						}

						int currentRand = random.nextInt(100)+40;
						Goblin goblins = new Goblin(-5000+currentRand*i, 670);
						Rect goblinsRecting = new Rect(goblinList.get(i).x+20-1000,goblinList.get(i).y+20-1000,25,30,45);
						goblinList.add(goblins);
						goblinRectList.add(goblinsRecting);


						System.out.println("goblin  gone");

					}
				}
			}

			for(int i=0; i<fireList.size(); i++){
				if(fireRectList.get(i).overlaps(underGroundRect)){
					fireList.remove(i);
					fireRectList.remove(i);
					if(fireList.size() == 10){
						for(int j = 0; j<20; j++){
							fireList.add(fires);
							fireRectList.add(fireRects);
						}
					}



				}

			}
			for (int i = 0 ; i< goblinList.size()-1;i++){

				goblinRectList.get(i).moveRightBy(1);
				goblinList.get(i).WalkingGobling(1);;

			} 	
			//			for (int i = 0 ; i< goblines.length-1;i++)
			//			if((goblinsRect[i].overlaps(middleBacground))){
			//
			//				goblines[i].WalkingGobling(1); goblinsRect[i].moveRightBy(1);
			//goblines[i].isShooting = true;
			// goblines[i].AttingGoblin(1);
			//System.out.println("is overlaping");


			//}

			for(int i=0; i<fireList.size(); i++)
			{
				fireList.get(i).move();
				fireRectList.get(i).move();

			}



			//System.out.println("is working");




			if(up_Pressed){   dragonites.moveUpBy(2); dragonRect.moveUpBy(2);   }
			if(dn_Pressed){   dragonites.moveDownBy(2); dragonRect.moveDownBy(2);  }
			if(lt_Pressed) 
			{
				dragonites.moveLeftBy(2); dragonRect.moveLeftBy(2);


			}


			if(rt_Pressed) {
				dragonites.moveRightBy(2); dragonRect.moveRightBy(2);


			}
			/**
			 * Manages the attack 
			 */


			/////////////////////////////////////////////////////////////////////////////////////
			if (sp_pressed){

				if(current == fireList.size())  
					current = 0;
				dragonites.Atack(2);
				if(fireList.size() ==5) fireList.add(fires);
				dragonites.shoot(fireList.get(current));
				sp_pressed = false;



				fireList.get(current).move();
				fireRectList.get(current).setVelocity(fireList.get(current).vx, fireList.get(current).vy); // FINALLY SYNCRONICED RECT WITH FIRE ANIMATION
				fireRectList.get(current).setLocation(fireList.get(current).x + 30+ 150 * fireList.get(current).unit_vx, fireList.get(current).y + 20+ 220 * fireList.get(current).unit_vy);

				System.out.println("current: " +current);

				current++;
				if(current == fireList.size())  
					current = 0;



			}
			////////////////////////////////////////////////////////////////////////////////////////////////////

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
					damageRECT[i].moveLeftBy(2);
					DAMAGEDONE[i].moveLeftBy(2);
					goblinRectList.get(i).moveRightBy(-2);
					goblinList.get(i).WalkingGobling(-2);;

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



		for(int i =0; i<goblinList.size();i++){
			goblinList.get(i).draw(g);
			//goblinRectList.get(i).draw(g);


		}

		for (int i = 0; i < fireList.size(); i++){
			fireList.get(i).draw(g);
			//fireRectList.get(i).draw(g);


		}

		for (int i = 0; i< DAMAGEDONE.length;i++){
			//damageRECT[i].draw(g);
			DAMAGEDONE[i].draw(g);

		}
		for ( int i = 0; i<houses.length;i++){
			houses[i].draw(g);
			//housesRect[i].draw(g);

		}

		dragonites.draw(g);




		/**
		 * Drawing Rectangles for the sprites collision detection
		 */



		//for ( int i = 0; i<houses.length;i++){
			//housesRect[i].draw(g);
		//}
		
		
		
		
		g.setColor(Color.GREEN);
		g.setFont(new Font("TimesRoman",Font.PLAIN,50));
		g.drawString("DAMAGE POINTS: " + damagePoints, 100, 100);

		g.setColor(Color.YELLOW);
		g.setFont(new Font("TimesRoman",Font.PLAIN,25));
		g.drawString("Houses burned: " + housesDestroyed, 100, 120);

		g.setColor(Color.YELLOW);
		g.setFont(new Font("TimesRoman",Font.PLAIN,25));
		g.drawString("Goblins Killed: " + goblinskilled, 100, 140);

		if(dragonites.isShooting){
			g.setColor(Color.YELLOW);
			g.setFont(new Font("TimesRoman",Font.PLAIN,75));
			g.drawString("DRACARYS!! " , 300, 220);


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
