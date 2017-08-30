package site.root3287.sudo2.shape;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.Loader;

public class CircleShape extends Shape{
	public CircleShape() {
		this.scale = new Vector2f(100, 100);
		CircleGenerator g = new CircleGenerator();
		g.generateCircle(1f, 360);
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
		setModel(Loader.getInstance().loadToVAO(pos, ind));
	}
	
	public class CircleGenerator{
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
			
			System.out.println(pos);
			System.out.println(ind);
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
		//position.x +=10*d;
		Vector4f colour = this.colour;
		if(colour.x > 0 && colour.z == 0){
		    colour.x--;
		    colour.y++;
		  }
		  if(colour.y > 0 && colour.x == 0){
			  colour.y--;
			  colour.z++;
		  }
		  if(colour.z > 0 && colour.y == 0){
		    colour.x++;
		    colour.z--;
		  }
		  this.colour = colour;
	}

}
