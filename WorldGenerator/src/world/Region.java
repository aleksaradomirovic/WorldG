package world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Region {
	byte height, temp;
	int x, y;
	
	void update() {
		if(height > 0) {
			temp = (byte) (64-height);
		} else {
			temp = 64;
		}
		temp = (byte)(temp - Math.abs(y-64));
	}
	
	void draw(Graphics g) {
		update();
		
		if(Window.viewmode == 0) {
			if(height >= 0)
				g.setColor(new Color(height*2,125,0));
			else
				g.setColor(new Color(0,0,256+(height*2)));
		} else if(Window.viewmode == 1) {
			if(temp >= 0)
				g.setColor(new Color(255,255-temp*2,255-temp*2));
			else
				g.setColor(new Color(256+temp*2,256+temp*2,255));
		} else if(Window.viewmode == 2) {
			if(height < 0) {
				g.setColor(new Color(0,50,200));
			} else {
				g.setColor(new Color(0,200,0));
				if(temp < 20) {
					g.setColor(new Color(0,100,0));
				} if(temp < 0) {
					g.setColor(new Color(255,255,255));
				}
			}
		} else if(Window.viewmode == 3) {
			if(height < 0) {
				g.setColor(new Color(0,50,200));
			} else {
				g.setColor(new Color(0,200,0));
				if(height > 20) {
					g.setColor(new Color(100,100,100));
				} if(height > 40) {
					g.setColor(new Color(255,255,255));
				}
			}
		}
		
		
		g.fillRect(x*5, y*5, 5, 5);
	}
	
	public Region(int x, int y) {
		this.x = x;
		this.y = y;
		
		height = (byte) (Window.rng.nextInt(256)-128);
	}
	
	void smooth() {
		ArrayList<Region> ref = World.surrounding(x, y);
		
		int i = 0, h = 0;
		for(Region e : ref) {
			i++;
			h+=e.height;
		}
		height = (byte) (h/i);
	}
}
