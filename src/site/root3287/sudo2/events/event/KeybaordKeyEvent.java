package site.root3287.sudo2.events.event;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.types.KeyboardKeyEventType;
import site.root3287.sudo2.utils.Input;

public class KeybaordKeyEvent extends Event{
	private Input.Keyboard.State state;
	private int key;
	public KeybaordKeyEvent(int key, Input.Keyboard.State state) {
		super(new KeyboardKeyEventType());
		this.key = key;
		this.state = state;
	}
	public Input.Keyboard.State getState() {
		return state;
	}
	public void setState(Input.Keyboard.State state) {
		this.state = state;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}

}
