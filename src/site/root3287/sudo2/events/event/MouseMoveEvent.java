package site.root3287.sudo2.events.event;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.types.MouseMoveEventType;

public class MouseMoveEvent extends Event{
	private float x,y,dx,dy;
	public MouseMoveEvent(float x, float y, float dx, float dy){
		super(new MouseMoveEventType());
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getDx() {
		return dx;
	}
	public void setDx(float dx) {
		this.dx = dx;
	}
	public float getDy() {
		return dy;
	}
	public void setDy(float dy) {
		this.dy = dy;
	}
}
