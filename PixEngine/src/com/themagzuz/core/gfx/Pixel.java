package com.themagzuz.core.gfx;

public class Pixel {


	public static float getAlpha(int c){
		return (0xff & (c >> 24)) / 255f;
	}
	
	public static float getRed(int c){
		return (0xff & (c >> 16)) / 255f;
	}
	public static float getGreen(int c){
		return (0xff & (c >> 8)) /255f;
	} 
	public static float getBlue(int c){
		return (0xff & (c)) / 255f;
	}
	
	public static int getColor (float a, float r, float g, float b){
		return (int)(((a + 0.5)*255f)) << 24;
	}
	
}
