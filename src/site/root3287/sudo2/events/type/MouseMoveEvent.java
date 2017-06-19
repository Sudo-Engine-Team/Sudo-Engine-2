package site.root3287.sudo2.events.type;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.EventType;

public class MouseMoveEvent extends Event {
	private float x, y;
	private boolean buttonDown;
	
	public MouseMoveEvent(float x, float y, boolean buttonDown) {
		super(EventType.MOUSE_MOVE);
		this.x = x;
		this.y = y;
		this.buttonDown = buttonDown;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public boolean wasButtonDown(){
		return buttonDown;
	}
}
