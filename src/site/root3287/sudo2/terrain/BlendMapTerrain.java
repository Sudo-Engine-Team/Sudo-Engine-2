package site.root3287.sudo2.terrain;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.texture.AbstractTexture;

public class BlendMapTerrain extends Terrain {
	public BlendMapTerrain(int x , int z, int size, int lod) {
		this.x = x*size;
		this.y = z*size;
		this.lod = lod;
		this.size = size;
		FlatTerrainGenerator g = new FlatTerrainGenerator();
		g.generate(size, lod);
		this.model = Loader.getInstance().loadToVAO(g.position, g.texture, g.normals, g.ind);
		this.texture = new AbstractTexture(Loader.getInstance().loadTexture("res/image/white.png"));
	}
	
	private static class FlatTerrainGenerator{
		float[] position;
		float[] texture;
		float[] normals;
		int[] ind;
		
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
	            	position[vertexPointer*3+1] = 0;
	            	position[vertexPointer*3+2] = (float)i/((float)lod - 1) * size;
	                normals[vertexPointer*3] = 0;
	                normals[vertexPointer*3+1] = 1;
	                normals[vertexPointer*3+2] = 0;
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
	}
}