package site.root3287.sudo2.shape;

import java.util.ArrayList;
import java.util.List;

import site.root3287.sudo2.engine.Loader;

public class SquareShape extends Shape{

	@Override
	public void update(float delta) {}
	
	public SquareShape() {
		SquareGenerator g = new SquareGenerator();
		this.model = Loader.getInstance().loadToVAO(g.getPos(), g.getInd());
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
}
