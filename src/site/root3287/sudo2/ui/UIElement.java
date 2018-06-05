package site.root3287.sudo2.ui;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.Renderable2D;
import site.root3287.sudo2.engine.interfaces.Disposable;
import site.root3287.sudo2.engine.interfaces.Updateable;
import site.root3287.sudo2.engine.render.Render2D;

public abstract class UIElement implements Updateable, Disposable{
	protected UIElement parent;
	protected ArrayList<UIElement> children = new ArrayList<>();
	protected Renderable2D model;
	protected boolean isVisable = true;
	protected Vector2f position = new Vector2f();
	protected Vector2f size = new Vector2f(100,100);
	protected boolean hover = false;
	protected UIElement root;
	
	public void add(UIElement element) {
		element.parent = this;
		element.root = this.root;
		this.children.add(element);
	}
	
	public abstract void onHover();
	public abstract void offHover();
	public abstract void hover();
	public abstract void onClick();
	public abstract void render(Render2D render);
	
	public void setSize(Vector2f size) {this.size = size; if(model != null) this.model.setScale(size);}
	public Vector2f getSize() {return this.size;}
	public void visable(boolean visable) {this.isVisable = visable;}
	public boolean isVisable() { return this.isVisable;}
	public Renderable2D getModel() { return model;}
	public void setModel(Renderable2D model) { this.model = model;}
	public UIElement getRoot() { return this.root;}
	protected void setRoot(UIElement root) { this.root = root;}
	public ArrayList<UIElement> getChildren() { return this.children;}
	public void setPosition(Vector2f pos) { this.position = pos; }
	public Vector2f getPosition() { return this.position;}
	public Vector2f getAbsolutePosition() { return (this.parent == null)?position:Vector2f.add(parent.position, position, null);}
	public UIElement getParent() {return parent;}
	public void setParent(UIElement element) { this.parent = element;}
}
