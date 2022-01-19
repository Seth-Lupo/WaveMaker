package energywaves;

public class Cell {
	
	public double h, velH, accH;
	public boolean wall = false;
	
	public Cell() {
		
		
	}
		
	public void advance(double dt) {
		
		if (!wall) {
			velH += accH*dt;
			h += velH*dt;
			velH*=0.9999;
		}
		
	}
	
	public void raise() {
		wall = true;
		h = 0;
	}
	
	public void excite(double height) {
		if (!wall) h = height;
	}
	
	
	
	public String toString() {
		return "Height: " + h + ", Velocity: " + velH +  ", Acceleration: " + accH;
	}
	

}
