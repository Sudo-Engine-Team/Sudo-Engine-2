package site.root3287.sudo2.engine.texture;

import org.lwjgl.util.vector.Vector2f;

public class TextureAtlas extends AbstractTexture{

	public TextureAtlas(int id) {
		super(id);
		this.textureAtlas = true;
		this.offset = new Vector2f();
		this.rows = 1;
	}

	public void setRows(int rows) {
		super.setRows(rows);
	}
	public void setOffset(Vector2f offset) {
		super.setOffset(offset);
	}
}
