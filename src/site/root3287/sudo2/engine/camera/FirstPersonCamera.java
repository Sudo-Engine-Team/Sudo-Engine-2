package site.root3287.sudo2.engine.camera;

import org.lwjgl.glfw.GLFW;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.events.event.KeyboardDownEvent;
import site.root3287.sudo2.events.event.MouseMoveEvent;
import site.root3287.sudo2.utils.Input;

public class FirstPersonCamera extends ProspectiveCamera{
	public boolean isGrabbed = true, isMouseGrabbedRequest = false, canFly = false, gravity = true, isInAir = false,
			canDoubleJump = false;
	//private final float GRAVITY = (float) (-30f), JUMP = 10, CAMERA_HEIGHT = 3.5f;
	public float sensitivity = 0.1f, distance = 20f, dy = 0, flySpeed = 0.25f*0.25f, finalDistance;
	
	public FirstPersonCamera() {
		finalDistance = Math.abs(this.distance * flySpeed);

		Input.Keyboard.addKeyDownListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(e instanceof KeyboardDownEvent){
					if(((KeyboardDownEvent) e).getKey() == GLFW.GLFW_KEY_W){
						position.x += finalDistance * (float) Math.sin(Math.toRadians(yaw));
						position.z -= finalDistance * (float) Math.cos(Math.toRadians(yaw));
						return true;
					}
				}
				return false;
			}
		});
		Input.Keyboard.addKeyDownListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(e instanceof KeyboardDownEvent){
					if(((KeyboardDownEvent) e).getKey() == GLFW.GLFW_KEY_S){
						position.x -= finalDistance * (float) Math.sin(Math.toRadians(yaw));
						position.z += finalDistance * (float) Math.cos(Math.toRadians(yaw));
						return true;
					}
				}
				return false;
			}
		});
		
		Input.Keyboard.addKeyDownListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(e instanceof KeyboardDownEvent){
					if(((KeyboardDownEvent) e).getKey() == GLFW.GLFW_KEY_A){
						position.x -= finalDistance * (float) Math.sin(Math.toRadians(yaw + 90));
						position.z += finalDistance * (float) Math.cos(Math.toRadians(yaw + 90));
						return true;
					}
				}
				return false;
			}
		});
		
		Input.Keyboard.addKeyDownListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(e instanceof KeyboardDownEvent){
					if(((KeyboardDownEvent) e).getKey() == GLFW.GLFW_KEY_D){
						position.x -= finalDistance * (float) Math.sin(Math.toRadians(yaw - 90));
						position.z += finalDistance * (float) Math.cos(Math.toRadians(yaw - 90));
						return true;
					}
				}
				return false;
			}
		});

		Input.Keyboard.addKeyDownListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(e instanceof KeyboardDownEvent){
					if(((KeyboardDownEvent) e).getKey() == GLFW.GLFW_KEY_SPACE){
						position.y += finalDistance;
						return true;
					}
				}
				return false;
			}
		});
		
		Input.Keyboard.addKeyDownListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(e instanceof KeyboardDownEvent){
					if(((KeyboardDownEvent) e).getKey() == GLFW.GLFW_KEY_LEFT_SHIFT){
						position.y -= finalDistance;
						return true;
					}
				}
				return false;
			}
		});
		
		Input.Mouse.addMoveListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(e instanceof MouseMoveEvent){
					if (Input.Mouse.isGrabbed()) {
						pitch -= ((MouseMoveEvent) e).getDy() * sensitivity;
						yaw -= ((MouseMoveEvent) e).getDx() * sensitivity;
					}
				}
				return false;
			}
		});
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		finalDistance = Math.abs(this.distance * flySpeed * delta);

	//	this.direction = ((int) (this.yaw / 90));
		
		if (this.pitch > 90) {
			this.pitch = 90;
		} else if (this.pitch < -90) {
			this.pitch = -90;
		}

		if (this.yaw > 360) {
			this.yaw = 0;
		} else if (this.yaw < 0) {
			this.yaw = 360 - Math.abs(this.yaw);
		}
		
		  if(Input.Mouse.getDWheel() != 0){ flySpeed += Input.Mouse.getDWheel();
		  if(flySpeed < 0){ flySpeed = 0.00000001f; } }

		if (isMouseGrabbedRequest) {
			isMouseGrabbedRequest = false;
			if (isGrabbed) {
				this.isGrabbed = false;
			} else {
				this.isGrabbed = true;
			}
			// GLFW.glfwSetInputMode(DisplayManager.WINDOW, GLFW.GLFW_CURSOR,
			// (this.isGrabbed)?GLFW.GLFW_CURSOR_HIDDEN:GLFW.GLFW_CURSOR_NORMAL);
		}
	}
}
