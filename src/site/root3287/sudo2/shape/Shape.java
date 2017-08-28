package site.root3287.sudo2.shape;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.RawModel;

public abstract class Shape {
	protected Vector2f position = new Vector2f(10, 100), scale = new Vector2f(50f,50f);
	protected RawModel model;
	protected Vector4f colour = new Vector4f(1, 0, 0, 1);
	
	public abstract void update(float d);
	
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
	public RawModel getModel() {
		return model;
	}
	public void setModel(RawModel model) {
		this.model = model;
	}
}
