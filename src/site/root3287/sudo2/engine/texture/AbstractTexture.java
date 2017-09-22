package site.root3287.sudo2.engine.texture;

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
	 * @param width 		- Source Image Width
	 * @param height 		- Source Image Height
	 */
	public AbstractTexture(int id, int width, int height) {
		this(id, 0,0,width, height, width, height);
	}
	
	/**
	 * 
	 * @param id 		- OpenGL TextureID
	 * @param x  		- Offset X in pixels to copy
	 * @param y	 		- Offset Y in pixels to copy
	 * @param width		- The width of the sprite size
	 * @param height 	- The height of the sprite size;
	 */
	public AbstractTexture(int id, int x, int y, int width, int height){
		this(id, x, y, width, height, 512, 512);
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
	public AbstractTexture(int id, int x, int y, int width, int height, int srcWidth, int srcHeight) {
		this.textureID = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.srcWidth = srcWidth;
		this.srcHeight = srcHeight;
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
}
