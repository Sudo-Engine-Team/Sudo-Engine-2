package site.root3287.sudo2.engine.texture;

import org.lwjgl.util.vector.Vector2f;

public abstract class AbstractTexture {
	protected int width, height;
	protected int textureID;
	protected boolean textureAtlas = false;
	protected int rows = 1;
	protected boolean hasTranspancy = true;
	protected Vector2f offset;
	
	public AbstractTexture(int id) {
		this.textureID = id;
	}
	
	public AbstractTexture(int id, int width, int height) {
		this.textureID = id;
		this.width = width; 
		this.height = height;
	}
	
	public int getTextureID() {
		return this.textureID;
	}
	public void setTextureID(int id) {
		this.textureID = id;
	}
	public boolean isTextureAtlas() {
		return textureAtlas;
	}
	protected void setTextureAtlas(boolean textureAtlas) {
		this.textureAtlas = textureAtlas;
	}
	public int getRows() {
		return rows;
	}
	protected void setRows(int rows) {
		this.rows = rows;
	}
	public Vector2f getOffset() {
		return offset;
	}
	protected void setOffset(Vector2f offset) {
		this.offset = offset;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
