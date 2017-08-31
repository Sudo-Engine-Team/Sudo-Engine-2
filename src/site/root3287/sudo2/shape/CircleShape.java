package site.root3287.sudo2.shape;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

public class CircleShape extends Shape{
	float radius;
	int lod;
	public CircleShape(float radius, int lod) {
		this.radius = radius;
		this.lod = lod;
		this.scale = new Vector2f(100, 100);
		regenerate();
	}
	
	public void regenerate(){
		CircleGenerator g = new CircleGenerator();
		g.generateCircle(radius, lod);
		float[] pos = new float[g.pos.size()];
		int i= 0;
		for(float p : g.pos){
			pos[i++] = p;
		}
		i=0;
		int[] ind = new int[g.ind.size()];
		for(int x : g.ind){
			ind[i++] = x;
		}
		setModel(pos, ind);
	}
	
	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public int getLod() {
		return lod;
	}

	public void setLod(int lod) {
		this.lod = lod;
	}

	private class CircleGenerator{
		public List<Float> pos = new ArrayList<>();
		public List<Integer> ind = new ArrayList<>();
		public void generateCircle(float r, int lod){
			pos.add(0f); pos.add(0f); pos.add(0f);
			for(int i = 0; i < lod; i++){
				pos.add((float)(r*Math.sin(i*(2*Math.PI / lod))));
				pos.add((float)(r*Math.cos(i*(2*Math.PI / lod))));
				pos.add(0f);
			}
			for(int i = 0; i < pos.size()/3; i++){
				getIndices(i, pos.size()/3);
			}
			ind.add(0);
			ind.add((int)(pos.size()/3f)-1);
			ind.add(1);
		}
		private void getIndices(int start, int end){
			if(start+2 <= end){
				ind.add(0);
				ind.add(start+1);
				ind.add(start+2);
			}
		}
	}

	@Override
	public void update(float d) {
	}

}
