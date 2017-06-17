package site.root3287.sudo2.engine.gui.components;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.utils.Input;

public abstract class GUIComponent implements GuiEvent{
	protected Vector2f position, scale;
	
	protected boolean isHidden = false, isHovering = false, isClicked = false;

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
	
	@Override
	public void update(float delta) {
		if(!isHidden && !Input.Mouse.isGrabbed()){
			Vector2f mouseCoordinates = Input.Mouse.getTranslatedMouseCorrds(-DisplayManager.WIDTH/2, -DisplayManager.HEIGHT/2);
			if (position.y + scale.y > -mouseCoordinates.y && position.y - scale.y < -mouseCoordinates.y && position.x + scale.x > mouseCoordinates.x && position.x - scale.x < mouseCoordinates.x) {
				whileHovering();
				if(!isHovering){
					isHovering = true;
					onHover();
				}
				if(Input.Mouse.isMouseDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
					if(!isClicked){
						onClick();
						isClicked = true;
					}
				}else{
					isClicked = false;
				}
			}else{
				if(isHovering){
					isHovering = false;
					onLeave();
				}
			}
		}
	}
}
