package site.root3287.sudo2.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.model.Model;
import site.root3287.sudo2.engine.texture.AbstractTexture;

public abstract class GuiWidget {
	protected GuiWidget parent = null;
	protected final UUID id = UUID.randomUUID();
	protected Vector2f position = new Vector2f(), size = new Vector2f(), fixedSize = new Vector2f();
	protected boolean visable = true, enabled = true, focused = false;
	protected String tooltip = "";
	protected float fontSize = 1;
	protected List<GuiWidget> childern = new ArrayList<>();
	protected GuiLayout layout;
	protected GuiTheme theme;
	protected float rotation = 0;
	protected Model model;
	
	public GuiWidget getParent() {
		return parent;
	}
	public void setParent(GuiWidget parent) {
		this.parent = parent;
	}
	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	public GuiTheme getTheme() {
		return theme;
	}
	public void setTheme(GuiTheme theme) {
		this.theme = theme;
	}
	public Vector2f absolutePosition() {
		Vector2f pos = new Vector2f();
		if(parent != null){
        	Vector2f.add(parent.absolutePosition(), position, pos);
        }else{
        	pos = this.position;
        }
		return pos;
	}
	public Vector2f getSize() {
		return size;
	}
	public void setSize(Vector2f size) {
		this.size = size;
	}
	public float getWidth(){
		return size.x;
	}
	public void setWidth(float w){
		size.x = w;
	}
	public float getHeight(){
		return size.y;
	}
	public void setHeight(float h){
		size.y = h;
	}
	public Vector2f getFixedSize() {
		return fixedSize;
	}
	public void setFixedSize(Vector2f fixedSize) {
		this.fixedSize = fixedSize;
	}
	public float getFixedWidth(){
		return fixedSize.x;
	}
	public void setFixedWidth(float w){
		fixedSize.x = w;
	}
	public float getFixedHeight(){
		return fixedSize.y;
	}
	public void setFixedHeight(float h){
		fixedSize.y = h;
	}
	public boolean isVisable() {
		return visable;
	}
	public void setVisable(boolean visable) {
		this.visable = visable;
	}
	
	public boolean visableRecusive(){
		boolean visible = true;
        GuiWidget widget = this;
        while (widget != null) {
            visible = widget.visable;
            widget = widget.getParent();
        }
        return visible;
	}
	
	public List<GuiWidget> children(){
		return childern;
	}
	
	public int childCount(){
		return this.childern.size();
	}
	
	public void addChild(GuiWidget w){
		this.childern.add(w);
	}
	
	public void removeChild(int w){
		this.childern.remove(w);
	}
	
	public void removeChild(GuiWidget w){
		this.childern.remove(w);
	}
	
	public GuiWidget childAt(int i){
		return childern.get(i);
	}
	
	//TODO: childIndex(GuiWidget)
	public int childIndex(GuiWidget w){
		return 0;
	}
	public UUID getID() {
		return id;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isFocused() {
		return focused;
	}
	public void setFocused(boolean focused) {
		this.focused = focused;
	}
	//TODO requestFocus()
	public void requestFocus(){
		
	}
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public float getFontSize() {
		return fontSize;
	}
	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
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
	
	public abstract void update(float delta);
	public abstract AbstractTexture getTexture();
	public abstract List<AbstractTexture> getTextures();
}
