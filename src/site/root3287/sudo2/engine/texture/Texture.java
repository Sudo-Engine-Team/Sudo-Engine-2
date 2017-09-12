package site.root3287.sudo2.engine.texture;

import org.lwjgl.util.vector.Vector2f;

public class Texture extends AbstractTexture {
	public Texture(int id, int width, int height) {
		super(id, width, height);
		this.rows = 0;
		this.textureAtlas = false;
		this.offset = new Vector2f();
	}

	public Texture(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
}
