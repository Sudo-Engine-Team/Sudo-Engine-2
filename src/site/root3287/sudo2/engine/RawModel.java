package site.root3287.sudo2.engine;

public class RawModel {
	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vaoID, int vetexCount){
		this.vaoID = vaoID;
		this.vertexCount = vetexCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
}
