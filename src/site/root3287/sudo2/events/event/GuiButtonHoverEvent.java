package site.root3287.sudo2.events.event;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.gui.GuiButton;
import site.root3287.sudo2.gui.event.type.GuiButtonHoverEventType;

public class GuiButtonHoverEvent extends Event{
	private GuiButton button;
	public GuiButtonHoverEvent(GuiButton b) {
		super(new GuiButtonHoverEventType());
		this.setButton(b);
	}
	public GuiButton getButton() {
		return button;
	}
	public void setButton(GuiButton button) {
		this.button = button;
	}
}
