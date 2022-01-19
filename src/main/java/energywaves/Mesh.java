package energywaves;

import processing.core.PApplet;

public class Mesh {
	
	public static final int S = Main.SPACIAL ? 2 : 1;
	public static final int WIDTH = Main.WIDTH/S, HEIGHT = Main.HEIGHT/S;
	public Cell[][] grid = new Cell[WIDTH][HEIGHT];
	
	public Mesh() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				grid[i][j] = new Cell();
			}
		}
		
//		exciteLine(0, 1, 1, 0, 10);
//		raiseConic(0, 0.05, 0.05, 0, 0.15, 0.15, 1);
//		raiseConic(1, 0.95, 0.95, 1, 0.85, 0.85, 1);
		
		
		excitePoint(0.5, 0.1, 30);
		raiseConic(0, 0, 1, 0, 0.5, 0.1, 1.15);
		
		
//		exciteCircle(0.2, 0.5, 0.2, 5);

		
		
		
	}
	
	public void raisePoint(double x, double y) {
		grid[(int)(x*WIDTH)][(int)(y*HEIGHT)].raise();
	}
	
	public void excitePoint(double x, double y, double height) {
		grid[(int)(x*WIDTH)][(int)(y*HEIGHT)].excite(height);
	}
	
	public void raiseCircle(double x, double y, double r) {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if (approx(dist((double) i/WIDTH, (double) j/HEIGHT, x, y), r, 0.009)) {
					grid[i][j].raise();
				}
			}
		}
	}
	
	public void exciteCircle(double x, double y, double r, double height) {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if (approx(dist((double) i/WIDTH, (double) j/HEIGHT, x, y), r, 0.009)) {
					grid[i][j].excite(height);
				}
			}
		}
	}
	
	
	public void raiseLine(double x1, double y1, double x2, double y2) {
		double compX = x2-x1, compY = y2-y1, currCompX = 0, currCompY = 0;
		while (currCompX < compX) {
			try {
				grid[(int)(WIDTH*(x1+currCompX))][(int)(HEIGHT*(y1+currCompY))].raise();
			} catch (Exception e) {}
			currCompX += 0.001*compX;
			currCompY += 0.001*compY;
		}	
	}
	
	public void exciteLine(double x1, double y1, double x2, double y2, double height) {
		double compX = x2-x1, compY = y2-y1, currCompX = 0, currCompY = 0;
		while (currCompX < compX) {
			try {
				grid[(int)(WIDTH*(x1+currCompX))][(int)(HEIGHT*(y1+currCompY))].excite(height);
			} catch (Exception e) {}
			currCompX += 0.001*compX;
			currCompY += 0.001*compY;
		}	
	}
	
	public void raiseConic(double x1, double y1, double x2, double y2, double xf, double yf, double ratio) {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				System.out.println(i/WIDTH);
				double dist1 = dist(x1, y1, x2, y2, (double) i/WIDTH,  (double) j/HEIGHT);
				double dist2 = dist(xf, yf, (double) i/WIDTH, (double) j/HEIGHT)/ratio;
				if (approx(dist1, dist2, 0.009)) {
					grid[i][j].raise();
				}
			}
		}
	}
	
	public boolean approx(double a, double b, double e) {
		return (a >= b-e) && (a <= b+e);
	}
	
	public double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
	
	public double dist(double x1, double y1, double x2, double y2, double x3, double y3) {
		double cross = Math.abs((x2-x1)*(y3-y1)-(y2-y1)*(x3-x1));
		double den = dist(x1, y1, x2, y2);
		return cross/den;
	}
	
	
	
	public void draw(PApplet p) {
		
		for (int i = 0; i < Main.STEPS; i++) advance(1/Main.STEPS);
		
		
		if (Main.SPACIAL) {
			
			
			
			p.translate(0, Main.HEIGHT/2);
			p.rotateX((float)(Main.ANGLE));
			p.translate(0, -Main.HEIGHT/2);
			float scaleH = 5;
			
//			p.directionalLight(241, 119, 32, 0, 1, -1);
//			p.directionalLight(0, 167, 225, 0, -1, 1);
			p.directionalLight(100, 100, 100, 0, -1, 1);
			p.directionalLight(255, 255, 255, 0, 1, -1);
			p.ambientLight(55, 55, 55);
			
			p.noStroke();
			p.fill(60, 70, 245);
			
			
			p.beginShape(p.TRIANGLES);
			for (int i = 0; i < WIDTH-1; i++) {
				for (int j = 0; j < HEIGHT-1; j++) {
					p.vertex((float) i*S, (float) j*S, (float) (scaleH*grid[i][j].h)); 
					p.vertex((float) (i+1)*S, (float) j*S, (float) (scaleH*grid[i+1][j].h));
					p.vertex((float) i*S, (float) (j+1)*S, (float) (scaleH*grid[i][j+1].h));
					
					p.vertex((float) (i+1)*S, (float) (j+1)*S, (float) (scaleH*grid[i+1][j+1].h)); 
					p.vertex((float) (i+1)*S, (float) j*S, (float) (scaleH*grid[i+1][j].h));
					p.vertex((float) i*S, (float) (j+1)*S, (float) (scaleH*grid[i][j+1].h));
					
				}
			}
			p.endShape();
			
			
			for (int i = 0; i < WIDTH-1; i++) {
				for (int j = 0; j < HEIGHT-1; j++) {
					
					p.fill(212, 175, 55);
					int wallHeight = 30;
					
					if (grid[i][j].wall) {
						p.beginShape(p.QUADS);
						
						p.vertex((float) i*S-S/2, (float) j*S-S/2, (float) -wallHeight); 
						p.vertex((float) i*S+S/2, (float) j*S-S/2, (float) -wallHeight); 
						p.vertex((float) i*S+S/2, (float) j*S-S/2, (float) wallHeight); 
						p.vertex((float) i*S-S/2, (float) j*S-S/2, (float) wallHeight); 
						
						p.vertex((float) i*S+S/2, (float) j*S-S/2, (float) -wallHeight); 
						p.vertex((float) i*S+S/2, (float) j*S+S/2, (float) -wallHeight); 
						p.vertex((float) i*S+S/2, (float) j*S+S/2, (float) wallHeight); 
						p.vertex((float) i*S+S/2, (float) j*S-S/2, (float) wallHeight); 
						
						p.vertex((float) i*S+S/2, (float) j*S+S/2, (float) -wallHeight); 
						p.vertex((float) i*S-S/2, (float) j*S+S/2, (float) -wallHeight); 
						p.vertex((float) i*S-S/2, (float) j*S+S/2, (float) wallHeight); 
						p.vertex((float) i*S+S/2, (float) j*S+S/2, (float) wallHeight); 
						
						p.vertex((float) i*S-S/2, (float) j*S+S/2, (float) -wallHeight); 
						p.vertex((float) i*S-S/2, (float) j*S-S/2, (float) -wallHeight); 
						p.vertex((float) i*S-S/2, (float) j*S-S/2, (float) wallHeight); 
						p.vertex((float) i*S-S/2, (float) j*S+S/2, (float) wallHeight);
						
						p.vertex((float) i*S-S/2, (float) j*S-S/2, (float) wallHeight); 
						p.vertex((float) i*S+S/2, (float) j*S-S/2, (float) wallHeight); 
						p.vertex((float) i*S+S/2, (float) j*S+S/2, (float) wallHeight); 
						p.vertex((float) i*S-S/2, (float) j*S+S/2, (float) wallHeight);
						
						
						
						
						
						p.endShape();
					}
				}
			}
			
			
			
		
		} else {
			p.loadPixels();
			for (int i = 0; i < WIDTH; i++) {
				for (int j = 0; j < HEIGHT; j++) {
					if (grid[i][j].wall) p.pixels[j*WIDTH+i] = p.color(212, 175, 55);
					else p.pixels[j*WIDTH+i] = p.color(bound(200 + 100*grid[i][j].h));
				}
			}
			p.updatePixels();
		}
		
		
		
		
		
	}
	
	public static int bound(double value) {
		return (int) Math.max(Math.min(value, 255), 0);
	}
	
	public void advance(double dt) {
		
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				grid[i][j].accH = (Main.SPACIAL ? 0.3 : 2) * (top(i,j).h + bottom(i,j).h + left(i,j).h + right(i,j).h - 4*grid[i][j].h);
			}
		}
		
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				grid[i][j].advance(dt);
			}
		}	
		
	}
	
	
	public Cell top(int i, int j) {
		try {
			return grid[i][j-1];
		} catch (IndexOutOfBoundsException e) {
			return new Cell();
		}
	}
	
	public Cell bottom(int i, int j) {
		try {
			return grid[i][j+1];
		} catch (IndexOutOfBoundsException e) {
			return new Cell();
		}
	}
	
	public Cell left(int i, int j) {
		try {
			return grid[i-1][j];
		} catch (IndexOutOfBoundsException e) {
			return new Cell();
		}
	}
	
	public Cell right(int i, int j) {
		try {
			return grid[i+1][j];
		} catch (IndexOutOfBoundsException e) {
			return new Cell();
		}
	}
	
}
