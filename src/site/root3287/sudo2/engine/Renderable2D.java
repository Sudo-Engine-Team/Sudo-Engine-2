package site.root3287.sudo2.engine;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.texture.Texture;

public class Renderable2D {
	private Vector2f position = new Vector2f(0, 0);
	private Vector2f scale = new Vector2f(100, 100);
	private Vector3f rotation = new Vector3f(0, 0, 0);
	
	private VAO model;
	
	private Texture image;
	private Vector4f colour = new Vector4f(0,0,0,1);
	
	public Renderable2D() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean hasImage() {
		if(image == null) return false;
		return true;
	}

	public Vector4f getColour() {
		return colour;
	}

	public void setColour(Vector4f colour) {
		this.colour = colour;
	}

	public Texture getImage() {
		return image;
	}

	public void setImage(Texture image) {
		this.image = image;
	}
	
	public VAO getModel() {
		return this.model;
	}
	
	public void setModel(VAO model) {
		this.model = model;
	}

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

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

}
