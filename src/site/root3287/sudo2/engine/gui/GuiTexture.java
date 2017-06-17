package site.root3287.sudo2.engine.gui;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

public class GuiTexture {
	
	
	public Vector2f position, scale;
	public int texture;
	public float rotation = 0;
	public boolean useProjection = true;
	
	public Vector2f offset = new Vector2f(0, 0);
	public int rows = 1;
	public boolean textureAtlas = true;
	
	public boolean isColoured = false;
	public Vector4f colour = new Vector4f(0, 0, 0, 0);
	
	public GuiTexture(int texture, Vector2f position, Vector2f scale) {
		super();
		this.position = position;
		this.scale = scale;
		this.texture = texture;
	}
	
	public GuiTexture(Vector2f position, Vector2f scale, Vector4f colour) {
		super();
		this.position = position;
		this.scale = scale;
		this.colour = colour;
		this.isColoured = true;
	}
	
	public boolean isColoured() {
		return isColoured;
	}

	public void setColoured(boolean isColoured) {
		this.isColoured = isColoured;
	}

	public Vector4f getColour() {
		return colour;
	}

	public void setColour(Vector4f colour) {
		this.colour = colour;
	}
	
	public GuiTexture(Vector2f position, Vector2f scale) {
		super();
		this.position = position;
		this.scale = scale;
	}
	
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
	public GuiTexture copy(){
		GuiTexture texture = new GuiTexture(this.texture, this.position, this.scale);
		texture.offset = this.offset;
		texture.rotation = this.rotation;
		texture.rows = this.rows;
		texture.textureAtlas = this.textureAtlas;
		texture.useProjection = this.useProjection;
		return texture;
	}
}
