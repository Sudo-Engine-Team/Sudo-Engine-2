package site.root3287.sudo2.UI;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.VAO;

public abstract class UIWidget {
	private VAO vao;
	private Vector2f position, scale;
	private UIWidget parent;
	private List<UIWidget> children;
	
	
	public UIWidget(){
		position = new Vector2f();
		scale = new Vector2f(100,100);
		parent = null;
		children = new ArrayList<>();
	}
	
	public void add(UIWidget w){
		w.setParent(this);
		children.add(w);
	}
	
	public void setParent(UIWidget w){
		this.parent = w;
	}
	
	public UIWidget getParent(){
		return this.parent;
	}
	
	public VAO getVAO() {
		return vao;
	}
	public void setVAO(VAO vao) {
		this.vao = vao;
	}
	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	public Vector2f getAbsolutePosition() {
		return (parent !=null)? Vector2f.add(parent.getAbsolutePosition(), position, null): position;
	}
	public Vector2f getScale() {
		return scale;
	}
	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
	
	public abstract void update(float delta);
}
