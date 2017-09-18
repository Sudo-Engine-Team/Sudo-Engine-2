package site.root3287.sudo2.gui;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.texture.AbstractTexture;
import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.gui.event.type.GuiButtonClickEventType;
import site.root3287.sudo2.gui.event.type.GuiButtonHoverEventType;

public class GuiButton extends GuiWidget{
	public static enum ButtonType{
		NORMAL,
		RADIO,
		TOGGLE,
		POPUP,
	}
	
	private String caption;
	private boolean pushed;
	private ButtonType type;
	private Vector4f textColour;
	private Vector4f backgroundColour;
	private AbstractTexture texture;
	
	private Listener clickListener;
	private Listener hoverListener;
	
	public static EventDispatcher clickDispatcher = new EventDispatcher(new GuiButtonClickEventType());
	public static EventDispatcher hoverDispatcher = new EventDispatcher(new GuiButtonHoverEventType());
	
	public GuiButton(GuiWidget parent) {
		this(parent, null, null, ButtonType.NORMAL);
	}
	
	public GuiButton(GuiWidget parent, ButtonType type){
		this(parent, null, null, type);
	}
	
	public GuiButton(GuiWidget parent, Vector2f position, Vector2f scale, ButtonType type) {
		this.parent = parent;
		this.position = position;
		this.size = scale;
		this.type = type;
		this.parent.addChild(this);
	}
	
	public static enum IconPosition {
        LEFT,
        LEFTCENTERED,
        RIGHTCENTERED,
        RIGHT
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Vector4f getBackgroundColour() {
		return backgroundColour;
	}

	public void setBackgroundColour(Vector4f backgroundColour) {
		this.backgroundColour = backgroundColour;
	}

	public Vector4f getTextColour() {
		return textColour;
	}

	public void setTextColour(Vector4f textColour) {
		this.textColour = textColour;
	}

	public ButtonType getType() {
		return type;
	}

	public void setType(ButtonType type) {
		this.type = type;
	}

	public boolean isPushed() {
		return pushed;
	}

	public void setPushed(boolean pushed) {
		this.pushed = pushed;
	}

	@Override
	public void update(float delta) {
		
	}
	
	public void addClickListener(Listener l){
		if(clickDispatcher.hasListener(this.clickListener)){
			clickDispatcher.removeListener(this.clickListener);
		}
		this.clickListener = l;
		clickDispatcher.addListener(this.clickListener);
	}

	public void addHoverListener(Listener l){
		if(hoverDispatcher.hasListener(this.hoverListener)){
			hoverDispatcher.removeListener(this.hoverListener);
		}
		this.hoverListener = l;
		hoverDispatcher.addListener(this.hoverListener);
	}

	@Override
	public AbstractTexture getTexture() {
		return null;
	}

	@Override
	public List<AbstractTexture> getTextures() {
		return null;
	}
}
