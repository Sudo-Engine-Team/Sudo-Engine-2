package site.root3287.sudo2.shape;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.IBO;
import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.VBO;

public abstract class Shape {
	protected Vector2f position = new Vector2f(0,0), scale = new Vector2f(1f,1f);
	protected VAO model;
	protected Vector4f colour = new Vector4f(1, 1, 1, 1);
	
	public Shape(){
		this(new Vector2f(), new Vector2f(), new Vector4f(1,0.5f,0.5f,1));
	}
	
	public Shape(Vector2f pos, Vector2f scale){
		this(pos, scale, new Vector4f(1,0.5f,0.5f,1));
	}
	
	public Shape(Vector2f pos, Vector2f scale, Vector4f colour){
		this.position = pos;
		this.scale = scale;
	}
	
	public abstract void update(float delta);
	
	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	public Vector2f getScale() {
		return scale;
	}
	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
	public Vector4f getColour() {
		return colour;
	}
	public void setColour(Vector4f colour) {
		this.colour = colour;
	}
	public VAO getModel() {
		return model;
	}
	public void setModel(float[] position, int[] ind) {
		if(this.model != null){
			model.dispose();
		}
		this.model = new VAO();
		VBO pos = new VBO();
		pos.setData(position);
		IBO indVBO = new IBO();
		indVBO.setData(ind);
		this.model.addVBO(0,3,pos);
		this.model.addIBO(indVBO);
	}
}
