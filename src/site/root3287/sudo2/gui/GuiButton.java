package site.root3287.sudo2.gui;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.events.event.GuiButtonHoverEvent;
import site.root3287.sudo2.events.event.MouseClickEvent;
import site.root3287.sudo2.events.event.MouseMoveEvent;
import site.root3287.sudo2.gui.event.GuiButtonClickEvent;
import site.root3287.sudo2.gui.event.type.GuiButtonClickEventType;
import site.root3287.sudo2.gui.event.type.GuiButtonHoverEventType;
import site.root3287.sudo2.utils.Input;

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
	
	private Listener clickListener;
	private Listener hoverListener;
	
	public static EventDispatcher clickDispatcher = new EventDispatcher(new GuiButtonClickEventType());
	public static EventDispatcher hoverDispatcher = new EventDispatcher(new GuiButtonHoverEventType());
	
	private GuiButton _instance = null;
	
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
		_instance = this;
		
		Input.Mouse.addClickListener(new Listener() {
			@Override
			public boolean onEvent(Event e) {
				Vector2f mouse = Input.Mouse.getTranslatedMouseCorrds(-DisplayManager.WIDTH/2, -DisplayManager.HEIGHT/2);
				if(e instanceof MouseClickEvent){
					if(((MouseClickEvent) e).getState() == Input.Mouse.State.MOUSE_PRESS){
						if(absolutePosition().y + size.y + texture.getTextureSize() > -mouse.y && absolutePosition().y - size.y - texture.getTextureSize()< -mouse.y){
							if(absolutePosition().x + size.x + texture.getTextureSize() > mouse.x && absolutePosition().x - size.x - texture.getTextureSize() < mouse.x){
								clickDispatcher.execute(new GuiButtonClickEvent(_instance));
							}else{
								System.out.println("Too far on x");
							}
						}else{
							System.out.println("too far on y");
							System.out.println("Button bounds: "+(absolutePosition().y - size.y - texture.getTextureSize())+" - "+ (absolutePosition().y + size.y + texture.getTextureSize()));
							Vector2f mouseI = new Vector2f();
							mouse.negate(mouseI);
							System.out.println("Mouse Bounds: "+mouseI);
						}
					}
					return true;
				}
				return false;
			}
		});
		
		Input.Mouse.addMoveListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				Vector2f mouse = Input.Mouse.getTranslatedMouseCorrds(-DisplayManager.WIDTH/2, -DisplayManager.HEIGHT/2);
				if(e instanceof MouseMoveEvent){
						if(absolutePosition().y + size.y + texture.getTextureSize() > -mouse.y && absolutePosition().y - size.y - texture.getTextureSize()< -mouse.y){
							if(absolutePosition().x + size.x + texture.getTextureSize() > mouse.x && absolutePosition().x - size.x - texture.getTextureSize() < mouse.x){
								hoverDispatcher.execute(new GuiButtonHoverEvent(_instance));
							}else{
								
							}
						}else{
							
						}
				}
				return false;
			}
		});
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
}
