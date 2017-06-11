package site.root3287.sudo2.engine;

import org.lwjgl.util.vector.Vector2f;

public class TexturedModel {
	private RawModel rawModle;
	private ModelTexture texture;
	
	private boolean textureAtlas = false;
	private Vector2f offset = new Vector2f(0, 0);
	
	public TexturedModel(RawModel model, ModelTexture texture){
		this.rawModle = model;
		this.texture = texture;
	}

	public RawModel getRawModel() {
		return rawModle;
	}

	public ModelTexture getTexture() {
		return texture;
	}

	public Vector2f getOffset() {
		return offset;
	}

	public void setOffset(Vector2f offset) {
		this.offset = offset;
	}

	public boolean isTextureAtlas() {
		return textureAtlas;
	}

	public void hasTextureAtlas(boolean textureAtlas) {
		this.textureAtlas = textureAtlas;
	}
}
