package site.root3287.sudo2.terrain;

import java.util.List;

import site.root3287.sudo2.engine.Loader;

public class FlatTerrain extends Terrain{

	public FlatTerrain(int x , int z, int size, int lod) {
		this.x = x*size;
		this.y = z*size;
		this.lod = lod;
		this.size = size;
		FlatTerrainGenerator g = new FlatTerrainGenerator();
		g.generate(size, lod);
		//this.model = Loader.getInstance().loadToVAO()
	}
	
	private static class FlatTerrainGenerator{
		List<Float> position;
		List<Float> texture;
		List<Float> normals;
		List<Integer> ind;
		
		public void generate(int size, int lod){
			int vertexPointer = 0;
			for(int x = 0; x < lod; x++){
				for(int z = 0; z < lod; z++){
					position.set(vertexPointer*3, (float) x / ((float) lod - 1) * size);
					position.set(vertexPointer*3+1, 0f);
					position.set(vertexPointer*3+2, (float) z / ((float) lod - 1) * size);

					//Vector3f normal = calculateNormal(j, i, image);

					
					normals.set(vertexPointer*3, 0f);
					normals.set(vertexPointer*3+1, 1f);
					normals.set(vertexPointer*3+2, 0f);
					texture.set(vertexPointer*2, (float) x / ((float) lod - 1));

					texture.set(vertexPointer*2, (float) z / ((float) lod - 1));

					vertexPointer++;
				}
			}
			int pointer = 0;
			for(int gx = 0; gx<lod; gx++){
				for(int gz = 0; gz<lod; gz++){
					int topLeft = (gz * size) + gx;

					int topRight = topLeft + 1;

					int bottomLeft = ((gz + 1) * size) + gx;

					int bottomRight = bottomLeft + 1;

					ind.set(pointer++, topLeft);

					ind.set(pointer++, bottomLeft);

					ind.set(pointer++, topRight);

					ind.set(pointer++, topRight);

					ind.set(pointer++,bottomLeft);

					ind.set(pointer++, bottomRight);
				}
			}
		}
	}
}
