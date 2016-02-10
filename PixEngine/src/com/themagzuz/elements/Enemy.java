package com.themagzuz.elements;

import java.util.Random;

import com.themagzuz.core.Renderer;

public class Enemy {

	private Player player;
	
	private Random random = new Random();
	
	public boolean started = false;
	
	public Enemy(Player p){
		this.player = p;
	}
	
	public void update(){
		player.shoot((int)player.x,(int) player.y);
		player.update();
	}
	public void render(Renderer r){
		player.render(r);
	}
	
}
