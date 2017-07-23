package site.root3287.sudo2.gui.event;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.gui.GuiButton;
import site.root3287.sudo2.gui.event.type.GuiButtonClickEventType;

public class GuiButtonClickEvent extends Event{
	private GuiButton button;
	public GuiButtonClickEvent(GuiButton button) {
		super(new GuiButtonClickEventType());
		this.setButton(button);
	}
	public GuiButton getButton() {
		return button;
	}
	public void setButton(GuiButton button) {
		this.button = button;
	}

}
