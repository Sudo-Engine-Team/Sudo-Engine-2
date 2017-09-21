package site.root3287.sudo2.engine.texture;

public class AbstractTexture {
	protected int srcWidth, srcHeight;
	protected int textureID;
	protected boolean hasTranspancy = true;
	protected int x, y, width,height;
	
	public AbstractTexture(int id) {
		this(id, 512, 512);
	}
	
	public AbstractTexture(int id, int width, int height) {
		this(id, 0,0,width, height, width, height);
	}
	
	public AbstractTexture(int id, int x, int y, int width, int height){
		this(id, x, y, width, height, width, height);
	}
	
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
