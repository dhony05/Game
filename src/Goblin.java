
/**
 * 
 * @author donel
 * This class manages the entries for goblins 
 * It contains an array of actions according to what type of animation is loaded
 */
public class Goblin extends Sprite{

	 static String[] action = {"run", "attack","walk"};

	   public Goblin(int x, int y)
	   {
	      super(x, y, "goblin_2_", action, 6, 9 );
	      System.out.println(y);
	   }
	   
}
