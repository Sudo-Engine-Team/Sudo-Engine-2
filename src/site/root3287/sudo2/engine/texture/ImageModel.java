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
	private Vector2f position = new Vector2f(), scale = new Vector2f();
	private float rotation = 0;
	public ImageModel(String file){
		this(file, 0,0);
	}
	public ImageModel(String file, float xOffset, float yOffset){
		this.texture = new AbstractTexture(Loader.getInstance().loadTexture(file));
		ImageGenerator gen = new  ImageGenerator(texture.getWidth(), texture.getHeight(), xOffset, yOffset, texture.getWidth(), texture.getHeight());
		model = Loader.getInstance().loadToVAO(SudoUtils.toFloatArray(gen.pos), SudoUtils.toFloatArray(gen.tc), SudoUtils.toIntegerArray(gen.ind));
	}
	public ImageModel(String file, float xOffset, float yOffset, float width, float height) {
		this.texture = new AbstractTexture(Loader.getInstance().loadTexture(file));
		ImageGenerator gen = new  ImageGenerator(texture.getWidth(), texture.getHeight(), xOffset, yOffset, width, height);
		model = Loader.getInstance().loadToVAO(SudoUtils.toFloatArray(gen.pos), SudoUtils.toFloatArray(gen.tc), SudoUtils.toIntegerArray(gen.ind));
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

	private static class ImageGenerator{
		List<Float> pos = new ArrayList<>();
		List<Integer> ind = new ArrayList<>();
		List<Float> tc = new ArrayList<>();
		
		public ImageGenerator(float srcWidth, float srcHeight, float xOffset, float yOffset, float width, float height) {
			pos.add(0f);
			pos.add(0f);
			pos.add(0f);
			
			pos.add(0+width);
			pos.add(0f);
			pos.add(0f);
			
			pos.add(0f);
			pos.add(0+height);
			pos.add(0f);
			
			pos.add(0+width);
			pos.add(0+height);
			pos.add(0f);
			
			ind.add(0);
			ind.add(1);
			ind.add(2);
			ind.add(2);
			ind.add(1);
			ind.add(3);
			
			tc.add(xOffset/srcWidth);
			tc.add(yOffset/srcHeight);
			
			tc.add(xOffset+srcWidth/srcWidth);
			tc.add(yOffset/srcHeight);
			
			tc.add(xOffset/srcWidth);
			tc.add(yOffset+srcHeight/srcHeight);
			
			tc.add(xOffset+srcWidth/srcWidth);
			tc.add(yOffset+srcHeight/srcHeight);
		}
	}
}
