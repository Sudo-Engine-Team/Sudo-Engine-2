package site.root3287.sudo2.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.texture.ImageModel;

public abstract class GuiWidget {
	protected final UUID id = UUID.randomUUID();
	protected Vector2f position = new Vector2f();
	protected Vector2f size = new Vector2f();
	protected Vector2f relativePosition = new Vector2f();
	protected Vector2f relativeScale = new Vector2f();
	protected GuiWidget parent;
	protected boolean visable = true;
	protected List<GuiWidget> children = new ArrayList<>();
	protected int level = 0;
	protected boolean autoResize = true;
	protected List<ImageModel> texture = new ArrayList<>();
	
	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	public Vector2f getSize() {
		return size;
	}
	public void setSize(Vector2f size) {
		this.size = size;
	}
	public Vector2f getRelativePosition() {
		return relativePosition;
	}
	public void setRelativePosition(Vector2f relativePosition) {
		this.relativePosition = relativePosition;
	}
	public Vector2f getRelativeScale() {
		return relativeScale;
	}
	public void setRelativeScale(Vector2f relativeScale) {
		this.relativeScale = relativeScale;
	}
	public GuiWidget getParent() {
		return parent;
	}
	public void setParent(GuiWidget parent) {
		this.parent = parent;
	}
	public boolean isVisable() {
		return visable;
	}
	public void setVisable(boolean visable) {
		this.visable = visable;
	}
	public List<GuiWidget> getChildren() {
		return children;
	}
	public void setChildren(List<GuiWidget> children) {
		this.children = children;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public UUID getId() {
		return id;
	}
	
	public void addWidget(GuiWidget w, float relX, float relY, float sizeX, float sizeY) {
		w.setParent(this);
		w.setRelativePosition(new Vector2f(relX, relY));
		w.setRelativeScale(new Vector2f(sizeX, sizeY));
		children.add(w);
	}
	
	public List<ImageModel> getTextures(){
		return texture;
	}
	
	public abstract void update(float delta);
	
}
