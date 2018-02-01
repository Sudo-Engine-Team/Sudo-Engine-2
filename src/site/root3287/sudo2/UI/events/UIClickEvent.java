package site.root3287.sudo2.UI.events;

import site.root3287.sudo2.UI.events.type.UIClickEventType;
import site.root3287.sudo2.events.Event;

public class UIClickEvent extends Event {

	public float x;
	public float y;
	public int button;
	
	public UIClickEvent() {
		super(new UIClickEventType());
	}

}
