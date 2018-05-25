package site.root3287.sudo2.ui;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.sun.media.jfxmediaimpl.MediaDisposer.Disposable;

import site.root3287.sudo2.engine.Renderable2D;
import site.root3287.sudo2.engine.interfaces.Updateable;
import site.root3287.sudo2.engine.render.Render2D;
import site.root3287.sudo2.utils.Input;

public abstract class UIElement implements Updateable, Disposable{
	private UIElement parent;
	private ArrayList<UIElement> children;
	private Renderable2D model;
	private boolean isVisable = true;
	private Vector2f position = new Vector2f();
	private Vector2f size = new Vector2f();
	private boolean hover = false;
	
	public void add(UIElement element) {
		this.children.add(element);
		element.parent = this;
	}
	
	public Vector2f getAbsolutePosition() {
		return (this.parent == null)?position:Vector2f.add(parent.position, position, null);
	}
	
	public abstract void onHover();
	public abstract void offHover();
	public abstract void hover();
	public abstract void onClick();
	
	public void mouseUpdate() {
		if(isVisable) {
			Vector3f mouseTemp = Input.Mouse.getMouseProjection(UI.CAMERA, UI.DISPLAY_SIZE);
			Vector2f mousePos = new Vector2f(mouseTemp.x, mouseTemp.y);
			if(UI.mouseInBounds(this, mousePos)) {
				hover();
				if(!hover) {
					hover = true;
					onHover();
				}
				
				if(Input.Mouse.isMousePressed(GLFW.GLFW_MOUSE_BUTTON_1)) {
					onClick();
				}
			}else {
				if(hover) {
					hover = false;
					offHover();
				}
			}
		}
	}
	
	public void setSize(Vector2f size) {this.size = size;}
	public Vector2f getSize() {return this.size;}
	public void visable(boolean visable) {this.isVisable = visable;}
	public boolean isVisable() { return this.isVisable;}
	
	public abstract void render(Render2D render);

	public Renderable2D getModel() {
		return model;
	}

	public void setModel(Renderable2D model) {
		this.model = model;
	}
}
