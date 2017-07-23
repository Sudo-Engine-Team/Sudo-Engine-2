package site.root3287.sudo2.events.event;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.types.WindowResizeEventType;

public class WindowResizeEvent extends Event{

	public float width, height;
	public WindowResizeEvent(float width, float height) {
		super(new WindowResizeEventType());
	}

}
