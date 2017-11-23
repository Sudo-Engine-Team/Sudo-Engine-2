package site.root3287.sudo2.engine.texture;

import org.lwjgl.opengl.GL11;

import site.root3287.sudo2.engine.Loader;

public class AbstractTexture {
	protected int srcWidth, srcHeight;
	protected int textureID;
	protected boolean hasTranspancy = true;
	protected int x, y, width,height;

	/**
	 * Constructor
	 * @param id - the OpenGL TextureID
	 */
	public AbstractTexture(int id) {
		this(id, 512, 512);
	}
	
	/**
	 * Constructor 
	 * Load in one big texture
	 * @param id 			- OpenGL TextureID
	 * @param srcWidth 		- Source Image Width
	 * @param srcHeight 		- Source Image Height
	 */
	public AbstractTexture(int id, int srcWidth, int srcHeight) {
		this(id, 0,0,srcWidth, srcHeight, srcWidth, srcHeight);
	}
	
	/**
	 * Constructor Base
	 * @param id		- OpenGL TextureID
	 * @param x			- X Offset in pixels
	 * @param y			- Y Offset in pixels
	 * @param width		- Width of the sprite (in pixels)
	 * @param height	- Height of the sprite (in pixels);
	 * @param srcWidth
	 * @param srcHeight
	 */
	public AbstractTexture(int id, int srcWidth, int srcHeight, int x, int y, int width, int height) {
		this.textureID = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.srcWidth = srcWidth;
		this.srcHeight = srcHeight;
	}
	
	public AbstractTexture(String file){
		AbstractTexture temp = Loader.getInstance().textureObject(file);
		this.hasTranspancy= temp.hasTranspancy;
		this.height = temp.height;
		this.srcHeight = temp.srcHeight;
		this.srcWidth = temp.srcWidth;
		this.textureID = temp.textureID;
		this.width = this.srcWidth;
		this.height = this.srcHeight;
		this.x = temp.x;
		this.y = temp.y;
	}
	
	public int getTextureID() {
		return this.textureID;
	}
	public void setTextureID(int id) {
		this.textureID = id;
	}
	public int getSourceWidth() {
		return srcWidth;
	}
	public int getSourceHeight() {
		return srcHeight;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setTexturePrametersi(int pram1, int pram2) {
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, pram1, pram2);
	}
}
