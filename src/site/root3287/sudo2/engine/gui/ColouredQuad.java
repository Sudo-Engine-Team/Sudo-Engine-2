package site.root3287.sudo2.engine.gui;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

public class ColouredQuad extends GuiTexture{
	public Vector4f colour = new Vector4f();
	public Vector2f position = new Vector2f();
	public Vector2f size = new Vector2f();
	
	public ColouredQuad(Vector4f colour, Vector2f position, Vector2f size) {
		super(position, size);
		this.colour = colour;
	}
	public Vector4f getColour() {
		return colour;
	}
	public void setColour(Vector4f colour) {
		this.colour = colour;
	}
	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	public Vector2f getSize() {
		return size;
	}
	public void setSize(Vector2f size) {
		this.size = size;
	}
}
