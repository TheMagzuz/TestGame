package test;

import com.themagzuz.core.AbstractGame;
import com.themagzuz.core.GameContainer;
import com.themagzuz.core.Renderer;
import com.themagzuz.core.gfx.Image;
import com.themagzuz.elements.Bullet;
import com.themagzuz.elements.Enemy;
import com.themagzuz.elements.Player;

public class Game extends AbstractGame {

	private Image image = new Image("/Bullet.png");
	private int p1x = 0;
	private int p1y = 0;

	private int p2x = 110;
	private int p2y = 0;

	private Renderer renderer;

	private Bullet[] bullets;

	private Player[] players = new Player[1];

	private Enemy enemy; 
	
	private boolean init = false;

	public static void main(String args[]) {
		GameContainer gc = new GameContainer(new Game());
		gc.start();
	}

	private void init() {
		bullets = new Bullet[2];
		init = true;
		players[0] = new Player(false, this, renderer);
		enemy = new Enemy(new Player(true, this, renderer));
		enemy.started = true;
	}

	@Override
	public void update(GameContainer gc, float dt) {

		if (!init)
			init();
		/*
		 * if (Input.isKey(KeyEvent.VK_D) && (p1x+1 <= 256)){ p1x++; } if
		 * (Input.isKey(KeyEvent.VK_A) && (p1x-1 >= 0)){ p1x--; } if
		 * (Input.isKey(KeyEvent.VK_W) && (p1y-1 >= 0)){ p1y--; } if
		 * (Input.isKey(KeyEvent.VK_S) && (p1y+1 <= 176)){ p1y++; } if
		 * (Input.isKeyPressed(KeyEvent.VK_SPACE)&& bullets[0] == null){
		 * shoot(p1x+63, p1y+23, false); }
		 */

		for (Player p : players) {
			p.update();
		}
		enemy.update();

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		/*
		 * //if (p1x < gc.getWidth() || p1x > 0 || y < gc.getHeight() || y > 0)
		 * r.drawImage(image, p1x, p1y); //Sp1ystem.out.println("p1x: " + p1x +
		 * " Y: " + y); if (bullets[0] != null){ bullets[0].move(); } renderer =
		 * r;
		 */
		renderer = r;
		for (Player p : players) {
			p.render(r);
		}
		enemy.render(r);
	}

	public Bullet[] getBullets() {
		return bullets;
	}
	
	public Renderer getRenderer(){
		return renderer;
	}

}
