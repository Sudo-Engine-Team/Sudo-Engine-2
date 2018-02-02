package site.root3287.sudo2.UI;

import org.lwjgl.glfw.GLFW;

import site.root3287.sudo2.utils.Input;

public abstract class UIButton extends UIWidget{
	private boolean hover = false;
	public UIButton() {
		super();
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		if(UIUtils.getInstance().mouseInBounds(this, UIUtils.getInstance().getMousePosition())){
			//TODO: Tell the other UIWidgets that I've been handeled.
			hover();
			if(!hover){
				hover = true;
				onHover();
			}
			
			if(Input.Mouse.isMousePressed(GLFW.GLFW_MOUSE_BUTTON_1)){
				//TODO: Tell the other UIWidgets that I've been handeled.
				onClick();
			}
		}else{
			if(hover){
				hover = false;
				offHover();
			}
		}
		
	}
	
	public abstract void onHover();
	public abstract void offHover();
	public abstract void onClick();
	public abstract void hover();
	
}
