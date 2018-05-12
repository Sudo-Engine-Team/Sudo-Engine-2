package site.root3287.sudo2.ui.nineSlice;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.Renderable2D;
import site.root3287.sudo2.engine.render.Render2D;
import site.root3287.sudo2.engine.texture.Texture;

public class NineSlice {
	private Texture texture;
	
	private Renderable2D topLeft;
	private Renderable2D top;
	private Renderable2D topRight;
	
	private Renderable2D left;
	private Renderable2D center;
	private Renderable2D right;
	
	private Renderable2D bottomLeft;
	private Renderable2D bottom;
	private Renderable2D bottomRight;
	
	private Vector2f position = new Vector2f();
	private Vector2f scale = new Vector2f();
	
	public NineSlice(Texture texture, int width, Vector2f offset) {
		
	}
	public NineSlice(Texture texture, int width, int height, Vector2f topLeft, Vector2f top, Vector2f topRight, Vector2f left, Vector2f center, Vector2f right, Vector2f bottomLeft, Vector2f bottom, Vector2f bottomRight) {
		this.topLeft = new Renderable2D(texture, new Vector2f(width, height), topLeft);
		this.top = new Renderable2D(texture, new Vector2f(width, height), top);
		this.topRight = new Renderable2D(texture, new Vector2f(width, height), topRight);
		
		this.left = new Renderable2D(texture, new Vector2f(width, height), left);
		this.center = new Renderable2D(texture, new Vector2f(width, height), center);
		this.right = new Renderable2D(texture, new Vector2f(width, height), right);
		
		this.bottomLeft = new Renderable2D(texture, new Vector2f(width, height), bottomLeft);
		this.bottom = new Renderable2D(texture, new Vector2f(width, height), bottom);
		this.bottomRight = new Renderable2D(texture, new Vector2f(width, height), bottomRight);
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public void render(Render2D render) {
		render.add(topLeft);
		render.add(topRight);
		render.add(left);
		render.add(right);
		render.add(bottomLeft);
		render.add(bottomRight);
		render.add(bottom);
		render.add(top);
		render.add(center);
		render.render();
	}
}
