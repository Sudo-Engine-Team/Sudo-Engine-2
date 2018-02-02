package site.root3287.sudo2.UI;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.interfaces.Disposable;
import site.root3287.sudo2.engine.interfaces.Updateable;

public abstract class UIWidget implements Updateable, Disposable{
	private VAO vao;
	private Vector2f position, scale;
	private UIWidget parent;
	private List<UIWidget> children;
	private boolean visable = true;
	private Vector4f colour = new Vector4f(1, 1, 1, 1);
	
	
	public UIWidget(){
		UIUtils.getInstance(); // Initialize the UIUtils, if it's haven't it already been...
		setVAO(UIUtils.getInstance().getVAO());
		position = new Vector2f();
		scale = new Vector2f(100,100);
		parent = null;
		children = new ArrayList<>();
	}
	
	public void add(UIWidget w){
		w.setParent(this);
		children.add(w);
	}
	public List<UIWidget> getChildren(){
		return children;
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
	
	public boolean isVisable() {
		return visable;
	}

	public void setVisable(boolean visable) {
		this.visable = visable;
	}
	
	public void setColour(Vector4f colour){
		this.colour = colour;
	}
	
	public Vector4f getColour(){
		return this.colour;
	}

	public void update(float delta){
		for(UIWidget child : children)
			child.update(delta);
	}
	
	public void rescale(float scaleX, float scaleY){
		Vector2f orginal = getScale();
		this.scale = new Vector2f(orginal.x*scaleX, orginal.y*scaleY);
		for(UIWidget child : children){
			child.rescale(scaleX, scaleY);
		}
	}
	
	public void dispose(){
		UIUtils.getInstance().dispose();
		for(UIWidget child : children){
			child.dispose();
		}
	}
}
