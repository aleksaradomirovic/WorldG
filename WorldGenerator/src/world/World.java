package world;

import java.awt.Graphics;
import java.util.ArrayList;

public class World {
	static Region[][] terrain = new Region[256][128];
	
	final int smoothing = 7;
	
	void draw(Graphics g) {
		for(int x = 0; x < 256; x++) {
			for(int y = 0; y < 128; y++) {
				terrain[x][y].draw(g);
			}
		}
	}
	
	void generate() {
		for(int x = 0; x < 256; x++) {
			for(int y = 0; y < 128; y++) {
				terrain[x][y] = new Region(x,y);
			}
		}
	}
	
	void smooth() {
		for(int x = 0; x < 256; x++) {
			for(int y = 0; y < 128; y++) {
				terrain[x][y].smooth();
			}
		}
	}
	
	public World() {
		generate();
		for(int i = 0; i < smoothing; i++) {
			smooth();
		}
		for(int x = 0; x < 256; x++) {
			for(int y = 0; y < 128; y++) {
				terrain[x][y].height*=2;
			}
		}
	}
	
	static ArrayList<Region> surrounding(int x, int y) {
		ArrayList<Region> r = new ArrayList<>();
		if(x > 0) {
			r.add(terrain[x-1][y]);
		}
		if(x < 255) {
			r.add(terrain[x+1][y]);
		}
		if(y > 0) {
			r.add(terrain[x][y-1]);
		}
		if(y < 127) {
			r.add(terrain[x][y+1]);
		}
		return r;
	}
}
