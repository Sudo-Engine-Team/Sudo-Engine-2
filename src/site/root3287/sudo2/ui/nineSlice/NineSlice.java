package site.root3287.sudo2.ui.nineSlice;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.Renderable2D;
import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.interfaces.Disposable;
import site.root3287.sudo2.engine.render.Render2D;
import site.root3287.sudo2.engine.texture.Texture;

public class NineSlice implements Disposable{
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
	private VAO model;
	
	public NineSlice(Texture texture, int width, Vector2f offset) {
		
	}
	public NineSlice(Texture texture, int width, int height, Vector2f topLeft, Vector2f top, Vector2f topRight, Vector2f left, Vector2f center, Vector2f right, Vector2f bottomLeft, Vector2f bottom, Vector2f bottomRight) {
		this.topLeft = new Renderable2D(texture.clone(), new Vector2f(width, height), topLeft);
		this.top = new Renderable2D(texture.clone(), new Vector2f(width, height), top);
		this.topRight = new Renderable2D(texture.clone(), new Vector2f(width, height), topRight);
		
		this.left = new Renderable2D(texture.clone(), new Vector2f(width, height), left);
		this.center = new Renderable2D(texture.clone(), new Vector2f(width, height), center);
		this.right = new Renderable2D(texture.clone(), new Vector2f(width, height), right);
		
		this.bottomLeft = new Renderable2D(texture.clone(), new Vector2f(width, height), bottomLeft);
		this.bottom = new Renderable2D(texture.clone(), new Vector2f(width, height), bottom);
		this.bottomRight = new Renderable2D(texture.clone(), new Vector2f(width, height), bottomRight);
		
		setPosition(new Vector2f());
		setScale(new Vector2f(50,50));
		
	}
	
	public void render(Render2D render) {

		render.add(center);
		render.add(topLeft);
		render.add(topRight);
		render.add(bottomLeft);
		render.add(bottomRight);
		
		render.add(top);
		render.add(bottom);
		render.add(left);
		render.add(right);
		render.render();
	}
	
	public Texture getTexture() {return this.texture;}
	public Vector2f getPosition() {return this.position;}
	public Vector2f getScale() {return this.scale;}
	
	public void setPosition(Vector2f position) {
		this.position = position;
		this.center.setPosition(position);
		
		float sep = 0.2f;
		this.top.setPosition(new Vector2f(position.x, position.y+sep));
		this.bottom.setPosition(new Vector2f(position.x, position.y-sep));
		this.left.setPosition(new Vector2f(position.x-sep, position.y));
		this.right.setPosition(new Vector2f(position.x+sep, position.y));
		this.topRight.setPosition(new Vector2f(position.x+sep, position.y+sep));
		this.topLeft.setPosition(new Vector2f(position.x-sep, position.y+sep));
		this.bottomLeft.setPosition(new Vector2f(position.x-sep, position.y-sep));
		this.bottomRight.setPosition(new Vector2f(position.x+sep, position.y-sep));
	}
	public void setScale(Vector2f scale) {
		this.scale = scale;
		this.center.setScale(scale);
		this.top.setScale(new Vector2f(scale.x, this.top.getScale().y));
		this.bottom.setScale(new Vector2f(scale.x, this.bottom.getScale().y));
		this.left.setScale(new Vector2f(this.left.getScale().x, scale.y));
		this.right.setScale(new Vector2f(this.right.getScale().x, scale.y));
	}
	public void setModel(VAO model) {
		this.model = model;
		this.topLeft.setModel(model);
		this.top.setModel(model);
		this.topRight.setModel(model);
		this.left.setModel(model);
		this.center.setModel(model);
		this.right.setModel(model);
		this.bottomLeft.setModel(model);
		this.bottom.setModel(model);
		this.bottomRight.setModel(model);
	}
	@Override
	public void dispose() {
		model.dispose();
	}
}
