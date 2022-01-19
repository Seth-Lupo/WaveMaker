package energywaves;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Main extends PApplet {

	public static final int WIDTH = 600, HEIGHT = 600;
	public static final float FRAMERATE = 60;
	public static final boolean SPACIAL = true;
	public static double STEPS = 4;
	public static double ANGLE = 1;
	public static Mesh mesh = new Mesh();
	
	public static void main(String[] args) {
		PApplet.main("energywaves.Main");
	}
	
	public void settings() {
		System.out.println("---SETTINGS---");
		if (SPACIAL) size(WIDTH, HEIGHT, this.P3D);
		else size(WIDTH, HEIGHT);
	}
	
	public void setup() {
		System.out.println("---SETUP---");
		
		frameRate(FRAMERATE);
		
		System.out.println("---DRAW---");
	}
	
	public void draw() {
		background(255);
		mesh.draw(this);
		
	}
	
	public void mouseClicked(MouseEvent e) {
		if (!SPACIAL) mesh.grid[e.getX()][e.getY()].h = 300;
	}
	
	public void mouseDragged(MouseEvent e) {
		if (!SPACIAL) mesh.grid[e.getX()][e.getY()].h = 300;
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKey()=='w') {
			ANGLE += 0.1;
			System.out.println(ANGLE);
		}
		if (e.getKey()=='s') ANGLE -= 0.1;
	}
	
}