package site.root3287.sudo2.engine.model;

import site.root3287.sudo2.engine.Loader;

@Deprecated
public class Model {
	
	private int vaoID;
	private int vertexCount;
	
	public Model(float[] position, float[] textureCoords, float[] normals, int[] indices) {
		Model m = Loader.getInstance().loadToVAO(position, textureCoords, normals, indices);
		this.vaoID = m.vaoID;
		this.vertexCount = m.vertexCount;
	}
	public Model(float[] position, float[] textureCoords, int[] indices) {
		Model m = Loader.getInstance().loadToVAO(position, textureCoords, indices);
		this.vaoID = m.vaoID;
		this.vertexCount = m.vertexCount;
	}
	public Model(float[] position, int[] indices) {
		Model m = Loader.getInstance().loadToVAO(position, indices);
		this.vaoID = m.vaoID;
		this.vertexCount = m.vertexCount;
	}
	public Model(float[] position) {
		Model m = Loader.getInstance().loadToVAO(position);
		this.vaoID = m.vaoID;
		this.vertexCount = m.vertexCount;
	}
	
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
