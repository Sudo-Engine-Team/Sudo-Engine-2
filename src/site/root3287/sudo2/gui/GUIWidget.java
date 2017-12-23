package site.root3287.sudo2.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.texture.AbstractTexture;
import site.root3287.sudo2.text.BitmapFont;

public abstract class GUIWidget {
	private Vector2f position = new Vector2f(), scale = new Vector2f();
	private List<GUIWidget> children;
	private List<BitmapFont> texts = new ArrayList<>();
	private GUIWidget parent;
	private VAO model;
	private AbstractTexture texture;
	private Vector4f bgcolour = new Vector4f(1, 1, 1, 1);

	public Vector4f getBackgroundColour() {
		return bgcolour;
	}

	public void setBackgroundColour(Vector4f bgcolour) {
		this.bgcolour = bgcolour;
	}

	public GUIWidget(){
		position = new Vector2f();
		scale = new Vector2f();
		children = new ArrayList<>();
		parent = null;
		model = null;
		texture = null;
	}
	
	public VAO getModel() {
		return model;
	}
	public void setModel(VAO model) {
		this.model = model;
	}
	public AbstractTexture getTexture() {
		return texture;
	}
	public void setTexture(AbstractTexture texture) {
		this.texture = texture;
	}
	public Vector2f getPosition() {
		if(getParent() != null){
			Vector2f temp = new Vector2f();
			Vector2f.add(getParent().getPosition(), position, temp);
			return temp;
		}
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
	public GUIWidget getParent() {
		return parent;
	}
	public void setParent(GUIWidget parent) {
		this.parent = parent;
	}
	public List<GUIWidget> getChildren(){
		return children;
	}
	public List<GUIWidget> getAllChildren(){
		List<GUIWidget> temp = new ArrayList<>();
		temp.addAll(getChildren());
		for(GUIWidget w : getChildren()){
			temp.add((GUIWidget) w.getAllChildren());
		}
		return temp;
	}
	public void addChildren(GUIWidget w){
		w.setParent(this);
		children.add(w);
	}
	public void addText(BitmapFont text){
		Vector2f tempPos = text.getPosition();
		Vector2f pos = new Vector2f();
		Vector2f.add(getPosition(), tempPos, pos);
		text.setPosition(pos);
		texts.add(text);
	}
	public List<BitmapFont> getText(){
		return texts;
	}
	public List<BitmapFont> getAllTexts(){
		List<BitmapFont> temp = new ArrayList<>();
		for(GUIWidget w : getAllChildren()){
			temp.addAll(w.getText());
		}
		return temp;
	}
}
