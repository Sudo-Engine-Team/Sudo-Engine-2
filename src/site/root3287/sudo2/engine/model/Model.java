package site.root3287.sudo2.engine.model;

public class Model {
	
	private int vaoID;
	private int vertexCount;
	
	public Model(int vaoID, int vc) {
		this.vaoID = vaoID;
		this.vertexCount = vc;
	}
	
	public int getVaoID() {
		return vaoID;
	}
	public void setVaoID(int vaoID) {
		this.vaoID = vaoID;
	}
	public int getVertexCount() {
		return vertexCount;
	}
	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}
}
