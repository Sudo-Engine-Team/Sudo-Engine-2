package site.root3287.sudo2.engine.texture;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.Loader;

public class ImageModel {
	private AbstractTexture texture;
	private Vector2f position = new Vector2f(0,0), scale = new Vector2f(1f,1f), offset = new Vector2f(), textureSize = new Vector2f();
	private float rotation = 0;
	private ImageModel() {
		// TODO Auto-generated constructor stub
	}
	public ImageModel(String file, float width, float height,float xOffset, float yOffset, float texX, float texY) {
		this.texture = new AbstractTexture(Loader.getInstance().loadTexture(file));
		this.offset = new Vector2f(xOffset, yOffset);
		this.setTextureSize(new Vector2f(texture.width/(texX/2),texture.height/(texY/2)));
		this.scale = new Vector2f(width, height);
	}
	public ImageModel(TextureAtlas atlas, float width, float height){
		this.texture = atlas;
		this.textureSize = new Vector2f(atlas.getWidth(), atlas.getHeight());
		this.offset = new Vector2f(atlas.x, atlas.y);
		this.scale = new Vector2f(width, height);
	}
	
	public Vector2f getOffset() {
		return offset;
	}
	public void setOffset(Vector2f offset) {
		this.offset = offset;
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

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public AbstractTexture getTexture() {
		return texture;
	}

	public Vector2f getTextureSize() {
		return textureSize;
	}

	public void setTextureSize(Vector2f textureSize) {
		this.textureSize = textureSize;
	}
	
	@Override
	public ImageModel clone() throws CloneNotSupportedException {
		ImageModel temp = new ImageModel();
		temp.offset = this.offset;
		temp.position = this.position;
		temp.rotation = this.rotation;
		temp.scale = this.scale;
		temp.texture = this.texture;
		temp.textureSize = this.textureSize;
		return temp;
	}
}
