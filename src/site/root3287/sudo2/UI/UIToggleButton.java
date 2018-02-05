package site.root3287.sudo2.UI;

import org.lwjgl.util.vector.Vector4f;

public abstract class UIToggleButton extends UIButton {
	
	private Vector4f toggleColour = this.getColour();
	private Vector4f toggleOffColour = this.getColour();
	private boolean toggled = false;
	
	public UIToggleButton(){
		super();
	}
	
	public UIToggleButton(Vector4f offToggle, Vector4f onToggle) {
		super();
		setColour(offToggle);
		setToggleOffColour(offToggle);
		setToggleColour(onToggle);
	}
	
	@Override
	public void onClick() {
		if(toggled){
			toggled = false;
			offToggle();
			setColour(toggleOffColour);
		}else{
			toggled = true;
			onToggle();
			setColour(toggleColour);
		}
	}
	
	public abstract void onToggle();
	public abstract void offToggle();
	
	public Vector4f getToggleOffColour() {
		return toggleOffColour;
	}

	public void setToggleOffColour(Vector4f toggleOffColour) {
		this.toggleOffColour = toggleOffColour;
	}

	public Vector4f getToggleColour() {
		return toggleColour;
	}

	public void setToggleColour(Vector4f toggleColour) {
		this.toggleColour = toggleColour;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
		if(toggled){
			setColour(toggleColour);
			onToggle();
		}else{
			setColour(toggleOffColour);
			offToggle();
		}
	}
}
