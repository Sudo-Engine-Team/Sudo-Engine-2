package site.root3287.sudo2.engine.gui;

import org.lwjgl.util.vector.Vector2f;

public class GuiTexture {
	public Vector2f position, scale;
	public int texture;
	
	public GuiTexture(int texture, Vector2f position, Vector2f scale) {
		super();
		this.position = position;
		this.scale = scale;
		this.texture = texture;
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
	public int getTexture() {
		return texture;
	}
	public void setTexture(int texture) {
		this.texture = texture;
	}
	
}
