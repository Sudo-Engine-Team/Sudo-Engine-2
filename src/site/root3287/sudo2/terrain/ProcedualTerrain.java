package site.root3287.sudo2.terrain;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.texture.AbstractTexture;
import site.root3287.sudo2.utils.SudoMaths.Perlin;

public class ProcedualTerrain extends Terrain{
	private long seed;
	private Perlin generator;
	public ProcedualTerrain(int x , int z, int size, int lod) {
		this.x = x*size;
		this.y = z*size;
		this.lod = lod;
		this.size = size;
		generator = new Perlin();
		this.setSeed(generator.getSeed());
		TerrainGenerator g = new TerrainGenerator(this.x, this.y, generator);
		g.generate(size, lod);
		this.model = Loader.getInstance().loadToVAO(g.position, g.texture, g.normals, g.ind);
		this.texture = new AbstractTexture(Loader.getInstance().loadTexture("res/image/white.png"));
	}
	public ProcedualTerrain(int x , int z, int size, int lod, long seed) {
		this.x = x*size;
		this.y = z*size;
		this.lod = lod;
		this.size = size;
		this.setSeed(seed);
		generator = new Perlin(seed);
		TerrainGenerator g = new TerrainGenerator(this.x, this.y, generator);
		g.generate(size, lod);
		this.model = Loader.getInstance().loadToVAO(g.position, g.texture, g.normals, g.ind);
		this.texture = new AbstractTexture(Loader.getInstance().loadTexture("res/image/white.png"));
	}
	
	public long getSeed() {
		return seed;
	}
	public void setSeed(long seed) {
		this.seed = seed;
	}

	private static class TerrainGenerator{
		float[] position;
		float[] texture;
		float[] normals;
		int[] ind;
		Perlin generator;
		float x, y;
		public TerrainGenerator(float x, float y, Perlin generator) {
			this.generator = generator;
			this.x = x;
			this.y =y;
		}
		
		public void generate(int size, int lod){
			int count = lod * lod;
	        position = new float[count * 3];
	        normals = new float[count * 3];
	        texture = new float[count*2];
	        ind = new int[6*(lod-1)*(lod-1)];
	        int vertexPointer = 0;
	        for(int i=0;i<lod;i++){
	            for(int j=0;j<lod;j++){
	            	position[vertexPointer*3] = (float)j/((float)lod - 1) * size;
	            	position[vertexPointer*3+1] = getHeight(i,j);
	            	position[vertexPointer*3+2] = (float)i/((float)lod - 1) * size;
	            	
	            	Vector3f normal = getNormal(i, j);
	                normals[vertexPointer*3] = normal.x;
	                normals[vertexPointer*3+1] = normal.y;
	                normals[vertexPointer*3+2] = normal.z;
	                texture[vertexPointer*2] = (float)j/((float)lod - 1);
	                texture[vertexPointer*2+1] = (float)i/((float)lod - 1);
	                vertexPointer++;
	            }
	        }
	        int pointer = 0;
	        for(int gz=0;gz<lod-1;gz++){
	            for(int gx=0;gx<lod-1;gx++){
	                int topLeft = (gz*lod)+gx;
	                int topRight = topLeft + 1;
	                int bottomLeft = ((gz+1)*lod)+gx;
	                int bottomRight = bottomLeft + 1;
	                ind[pointer++] = topLeft;
	                ind[pointer++] = bottomLeft;
	                ind[pointer++] = topRight;
	                ind[pointer++] = topRight;
	                ind[pointer++] = bottomLeft;
	                ind[pointer++] = bottomRight;
	            }
	        }
		}
		
		private float getHeight(float x, float y){
			generator.setX(this.x);
			generator.setY(this.y);
			return generator.getPerlin(x, y);
		}
		
		private Vector3f getNormal(float x, float y){
			float h0 = getHeight(x-1, y); // x-1 z
			float h1 = getHeight(x+1, y); // x+1 z
			float h3 = getHeight(x, y-1); // x z-1
			float h4 = getHeight(x, y+1); // x z+1
			return (Vector3f) new Vector3f(h0-h1, 2, h3-h4).normalise();
		}
	}
}
