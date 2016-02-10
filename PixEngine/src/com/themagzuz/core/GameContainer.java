package com.themagzuz.core;


public class GameContainer implements Runnable{

	private Thread thread;
	
	private AbstractGame game;
	private static AbstractGame _game;
	private Window window;
	private Renderer renderer;
	private Input input;
	
	private int width = 320, height = 240;
	private float scale = 2;
	private String title = "PixEngine";
	
	private boolean isRunning = false;
	
	private double frameCap = 1/60.0;	
	
	public static GameContainer main;
	
	public GameContainer(AbstractGame game){
		this.game = game;
		GameContainer._game = game;
		GameContainer.main = this;
	}
	public void start(){
		if (isRunning) return;
		
		window = new Window(this);
		renderer = new Renderer(this);
		input = new Input(this);
		
		// Initialize engine components
		
		thread = new Thread(this);
		thread.run();
	}
	public void stop(){
		
		if (!isRunning) return;
		isRunning = false;
		
	}
	public void run(){
		isRunning = true;
		
		
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unProccesedTime = 0;
		double frameTime = 0;
		int frames = 0;
		
		while (isRunning){
				boolean render = false;
			
				firstTime = System.nanoTime() / 100000000.0;
				passedTime = firstTime - lastTime;
				lastTime = firstTime;
				
				unProccesedTime += passedTime;
				frameTime += passedTime;
				while (unProccesedTime >= frameCap){
					game.update(this, (float) frameCap);
					input.update();
					
					unProccesedTime -= frameCap;
					render = true;
					if (frameTime >= 1){
						frameTime = 0;
						System.out.println(frames);
						frames= 0;
					}
				}
				if (render){
					renderer.clear();
					
					game.render(this, renderer);
					window.update();
					frames++;
				} else {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}
		cleanUp();
	}
	private void cleanUp(){
		window.cleanup();
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Window getWindow() {
		return window;
	}
	public Renderer getRenderer() {
		return renderer;
	}
	public static AbstractGame getGame(){
		return _game;
	}
	
}
