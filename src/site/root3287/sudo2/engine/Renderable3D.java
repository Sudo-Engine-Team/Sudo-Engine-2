package site.root3287.sudo2.engine;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.engine.texture.Texture;

public class Renderable3D {
	private Vector3f scale;
	private Vector3f position;
	
	private VAO vao;
	private Texture texture;
	public Renderable3D() {
		// TODO Auto-generated constructor stub
	}
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public Vector3f getScale() {
		return scale;
	}
	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	public VAO getModel() {
		return vao;
	}
	public void setModel(VAO vao) {
		this.vao = vao;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

}
