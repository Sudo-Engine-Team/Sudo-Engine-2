package site.root3287.sudo2.engine.camera;

import org.lwjgl.glfw.GLFW;

import site.root3287.sudo2.utils.Input;

public class FirstPersonCamera extends ProspectiveCamera{
	public boolean isGrabbed = true, isMouseGrabbedRequest = false, canFly = false, gravity = true, isInAir = false,
			canDoubleJump = false;
	//private final float GRAVITY = (float) (-30f), JUMP = 10, CAMERA_HEIGHT = 3.5f;
	public float sensitivity = 0.1f, distance = 20f, dy = 0, flySpeed = 0.25f;
	
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		if (isGrabbed) {
			this.pitch -= Input.Mouse.getDY() * sensitivity;
			this.yaw -= Input.Mouse.getDX() * sensitivity;
			
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
		}

	//	this.direction = ((int) (this.yaw / 90));

		float finalDistance = Math.abs(this.distance * delta * flySpeed);

		
		  if(Input.Mouse.getDWheel() != 0){ flySpeed += Input.Mouse.getDWheel()*delta;
		  if(flySpeed < 0){ flySpeed = 0.00000001f; } }
		  

		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
			position.x += finalDistance * (float) Math.sin(Math.toRadians(this.yaw));
			position.z -= finalDistance * (float) Math.cos(Math.toRadians(this.yaw));
		}
		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_S)) {
			position.x -= finalDistance * (float) Math.sin(Math.toRadians(this.yaw));
			position.z += finalDistance * (float) Math.cos(Math.toRadians(this.yaw));
		}
		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_A)) {
			position.x -= finalDistance * (float) Math.sin(Math.toRadians(this.yaw + 90));
			position.z += finalDistance * (float) Math.cos(Math.toRadians(this.yaw + 90));
		}
		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
			position.x -= finalDistance * (float) Math.sin(Math.toRadians(this.yaw - 90));
			position.z += finalDistance * (float) Math.cos(Math.toRadians(this.yaw - 90));
		}

		if (Input.Keyboard.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
				this.isMouseGrabbedRequest = true;
		}

		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
			position.y += finalDistance;
		}

		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			position.y -= finalDistance;
		}

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
