package site.root3287.sudo2.terrain;

import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.texture.AbstractTexture;
import site.root3287.sudo2.engine.texture.Texture;

public class Terrain {
	protected int x, y, size, lod;
	protected VAO model;
	protected Vector4f colour;
	protected boolean forceColour;
	protected AbstractTexture texture;
	
	public VAO getModel() {
		return model;
	}
	public void setModel(VAO model) {
		this.model = model;
	}
	protected Texture Texture;
	public AbstractTexture getTexture() {
		return texture;
	}
	public void setTexture(AbstractTexture t){
		this.texture = t;
	}
	public float getX() {
		// TODO Auto-generated method stub
		return x;
	}
	public float getZ() {
		// TODO Auto-generated method stub
		return y;
	}
	public Vector4f getColour() {
		return colour;
	}
	public void setColour(Vector4f colour) {
		this.colour = colour;
		setForceColour(true);
	}
	public boolean isForceColour() {
		return forceColour;
	}
	public void setForceColour(boolean forceColour) {
		this.forceColour = forceColour;
	}
}
