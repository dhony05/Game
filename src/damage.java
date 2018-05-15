/**
 * 
 * @author donel
 * This damage class will manage the entries for the damage done animation
 * it will load the images .
 * it as an array of actions that will determine what kind of animation will be displayed
 */
public class damage extends Sprite {

	static String[] action = {"burning"};
	public damage(int x, int y) {
		super(x, y, "dragon_1_", action,6, 11);
		// TODO Auto-generated constructor stub
	}

}
