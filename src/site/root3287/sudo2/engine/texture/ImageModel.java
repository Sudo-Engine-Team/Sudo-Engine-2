package site.root3287.sudo2.engine.texture;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.model.Model;
import site.root3287.sudo2.utils.SudoUtils;

public class ImageModel {
	private Model model;
	private AbstractTexture texture;
	private Vector2f position = new Vector2f(0,0), scale = new Vector2f(1f,1f), offset = new Vector2f(), textureSize = new Vector2f();
	private float rotation = 0;
	private ImageModel() {
		// TODO Auto-generated constructor stub
	}
	public ImageModel(String file, float width, float height,float xOffset, float yOffset, float texX, float texY) {
		this.offset = new Vector2f(xOffset, yOffset);
		this.setTextureSize(new Vector2f(texX*2,texY*2));
		this.texture = new AbstractTexture(Loader.getInstance().loadTexture(file));
		ImageGenerator gen = new  ImageGenerator(width, height);
		model = Loader.getInstance().loadToVAO(SudoUtils.toFloatArray(gen.pos), SudoUtils.toFloatArray(gen.tc), SudoUtils.toIntegerArray(gen.ind));
	}
	public ImageModel(TextureAtlas atlas, float width, float height){
		this.texture = atlas;
		this.textureSize = new Vector2f(atlas.getWidth()*2, atlas.getHeight()*2);
		this.offset = new Vector2f(atlas.x, atlas.y);
		ImageGenerator gen = new  ImageGenerator(width, height);
		model = Loader.getInstance().loadToVAO(SudoUtils.toFloatArray(gen.pos), SudoUtils.toFloatArray(gen.tc), SudoUtils.toIntegerArray(gen.ind));
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

	public Model getModel() {
		return model;
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

	private static class ImageGenerator{
		List<Float> pos = new ArrayList<>();
		List<Integer> ind = new ArrayList<>();
		List<Float> tc = new ArrayList<>();
		
		public ImageGenerator(float width, float height) {
			pos.add(-width/2);
			pos.add(height/2f);
			pos.add(0f);
			
			pos.add(width/2f);
			pos.add(height/2f);
			pos.add(0f);
			
			pos.add(-width/2f);
			pos.add(-height/2f);
			pos.add(0f);
			
			pos.add(width/2f);
			pos.add(-height/2f);
			pos.add(0f);
			
			ind.add(0);
			ind.add(1);
			ind.add(2);
			ind.add(1);
			ind.add(2);
			ind.add(3);
			
			tc.add(0f);
			tc.add(0f);
			
			tc.add(1f);
			tc.add(0f);
			
			tc.add(0f);
			tc.add(1f);
			
			tc.add(1f);
			tc.add(1f);
		}
	}
	
	@Override
	public ImageModel clone() throws CloneNotSupportedException {
		ImageModel temp = new ImageModel();
		temp.model = this.model;
		temp.offset = this.offset;
		temp.position = this.position;
		temp.rotation = this.rotation;
		temp.scale = this.scale;
		temp.texture = this.texture;
		temp.textureSize = this.textureSize;
		return temp;
	}
}
