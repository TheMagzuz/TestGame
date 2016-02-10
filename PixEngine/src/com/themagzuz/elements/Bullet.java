package com.themagzuz.elements;

import com.themagzuz.core.GameContainer;
import com.themagzuz.core.Renderer;
import com.themagzuz.core.gfx.Image;

import test.Game;

public class Bullet {

	private int x, y;

	private Renderer r;
	private Image image = new Image("/Bullet.png");;
	private Game game;

	private int id;

	private boolean goingLeft;

	public Bullet(int x, int y, Renderer r, Game game, boolean goingLeft, int id) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.game = game;
		this.goingLeft = goingLeft;
		this.id = id;
		if (game.getBullets()[id] != null) {
			return;
		} else {
			game.getBullets()[id] = this;
		}
	}

	public void move() {
		if (!goingLeft) {
			x++;
		} else {
			x--;
		}
		if (x <= 0 || x >= 290){
			game.getBullets()[id] = null;
		}

	}

	public void render() {
		move();
		if (r != null)
			r.drawImage(image, x, y);
	}

	public boolean getGoingLeft() {
		return goingLeft;
	}

}
