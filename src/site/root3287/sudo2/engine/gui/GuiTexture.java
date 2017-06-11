package site.root3287.sudo2.engine.gui;

import org.lwjgl.util.vector.Vector2f;

public class GuiTexture {
	public Vector2f position, scale;
	public int texture;
	public float rotation = 0;
	public boolean useProjection = true;
	
	public Vector2f offset = new Vector2f(0, 0);
	public int rows = 1;
	public boolean textureAtlas = true;
	
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	public boolean isUseProjection() {
		return useProjection;
	}
	public void setUseProjection(boolean useProjection) {
		this.useProjection = useProjection;
	}
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
	public boolean isAtlas() {
		return textureAtlas;
	}
	
}
