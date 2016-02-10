package com.themagzuz.elements;

import java.awt.event.KeyEvent;

import com.themagzuz.core.Input;
import com.themagzuz.core.Renderer;
import com.themagzuz.core.gfx.Image;

import test.Game;

public class Player {

	public float x = 0, y = 0;

	private Game game;
	private Renderer renderer;

	private Image image = new Image("/Test.png");

	private boolean left;

	private int bulletId;

	private int offset;

	public Player(boolean shootsLeft, Game game, Renderer r) {
		this.game = game;
		this.left = shootsLeft;
		if (left) {
			bulletId = 1;
			x = 304;
		} else
			bulletId = 0;
		renderer = r;
		if (left) {
			offset = -16;
		} else {
			offset = 16;
		}
	}

	public void update() {
		renderer = game.getRenderer();
		if (!left) {
			if (Input.isKey(KeyEvent.VK_D) && (x + 1 <= 304)) {
				x += 0.2;
			}
			if (Input.isKey(KeyEvent.VK_A) && (x - 1 >= 0)) {
				x -= 0.2;
			}
			if (Input.isKey(KeyEvent.VK_W) && (y - 1 >= 0)) {
				y -= 0.2;
			}
			if (Input.isKey(KeyEvent.VK_S) && (y + 1 <= 224)) {
				y += 0.2;
			}
			if (Input.isKeyPressed(KeyEvent.VK_SPACE) && game.getBullets()[bulletId] == null) {
				shoot((int) x + offset, (int) y);
			}
		}
	}
	
	public void AIControl(float x, float y){
		
	}

	public void shoot(int x, int y) {
		if (game.getBullets()[bulletId] != null)
			return;
		game.getBullets()[bulletId] = new Bullet(x, y, renderer, game, left, bulletId);
		game.getBullets()[bulletId].move();

	}

	public void render(Renderer r) {
		r.drawImage(image, (int) x, (int) y);
		if (game.getBullets()[bulletId] != null) {
			game.getBullets()[bulletId].render();
		}
	}

}
