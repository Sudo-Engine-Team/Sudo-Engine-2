package site.root3287.sudo2.events.event;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.types.MouseClickEventType;
import site.root3287.sudo2.utils.Input;

public class MouseClickEvent extends Event {
	private float x, y;
	private int key;
	private Input.Mouse.State state;
	public MouseClickEvent(float x, float y, int key, Input.Mouse.State state) {
		super(new MouseClickEventType());
		this.x = x;
		this.y = y;
		this.key = key;
		this.state = state;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
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
	public Input.Mouse.State getState() {
		return state;
	}
	public void setState(Input.Mouse.State state) {
		this.state = state;
	}

}
