package site.root3287.sudo2.shape;

import java.util.ArrayList;
import java.util.List;

import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.VBO;

public abstract class SquareShape extends Shape{

	public SquareShape() {
		SquareGenerator g = new SquareGenerator();
		this.model = new VAO();
		VBO pos = new VBO();
		pos.setData(g.getPos());
		VBO ind = new VBO(true);
		ind.setData(g.getInd());
		this.model.addVBO(ind);
		this.model.addVBO(0, 3, pos);
	}
	
	private static class SquareGenerator{
		private List<Float> pos = new ArrayList<>();
		private List<Integer> ind = new ArrayList<>();
		
		public SquareGenerator(){
			addPos(-1f, 1f);
			addPos(-1f,-1f);
			addPos(1f, 1f);
			addPos(1f, -1f);
			ind.add(0);
			ind.add(1);
			ind.add(2);
			ind.add(2);
			ind.add(1);
			ind.add(3);
		}
		
		public void addPos(float x, float y){
			pos.add(x);
			pos.add(y);
			pos.add(0f);
		}
		
		public float[] getPos(){
			float[] pos = new float[this.pos.size()];
			for(int i = 0; i<this.pos.size(); i++){
				pos[i] = this.pos.get(i);
			}
			return pos;
		}
		public int[] getInd(){
			int[] pos = new int[this.ind.size()];
			for(int i = 0; i<this.ind.size(); i++){
				pos[i] = this.ind.get(i);
			}
			return pos;
		}
	}

	@Override
	public abstract void update(float delta);
}
