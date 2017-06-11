package site.root3287.sudo2.component.functions;

import java.util.UUID;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.component.EntityComponent;
import site.root3287.sudo2.entities.Entity;
import site.root3287.sudo2.utils.Input;

public class FirstPersonProspectivePlayerControls extends EntityComponent {
	public boolean isGrabbed = true, isMouseGrabbedRequest = false, canFly = false, gravity = true, isInAir = false,
			canDoubleJump = false;
	//private final float GRAVITY = (float) (-30f), JUMP = 10, CAMERA_HEIGHT = 3.5f;
	private Vector3f position; /* rotation, */// velocity;
	private float pauseCooldown = 0;
	private UUID id;
	public float sensitivity = 0.1f, pitch, yaw, distance = 20f, dy = 0, flySpeed = 0.25f;
	///private int direction;

	public FirstPersonProspectivePlayerControls(UUID id) {
		this.id = id;
	}

	public void update(float delta) {
		this.position = Entity.getComponent(this.id, TransposeComponent.class).position;
		this.pitch = Entity.getComponent(this.id, TransposeComponent.class).pitch;
		this.yaw = Entity.getComponent(this.id, TransposeComponent.class).yaw;
		//this.direction = Entity.getComponent(this.id, TransposeComponent.class).direction;
		//this.velocity = Entity.getComponent(this.id, TransposeComponent.class).velocity;

		if (pauseCooldown < 0) {
			this.pauseCooldown = 0;
		}
		if (this.pauseCooldown <= 5) {
			this.pauseCooldown += delta;
		}

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
		  
		  System.out.println(flySpeed);
		 

		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
			position.x += finalDistance * (float) Math.sin(Math.toRadians(yaw));
			position.z -= finalDistance * (float) Math.cos(Math.toRadians(yaw));
		}
		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_S)) {
			position.x -= finalDistance * (float) Math.sin(Math.toRadians(yaw));
			position.z += finalDistance * (float) Math.cos(Math.toRadians(yaw));
		}
		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_A)) {
			position.x -= finalDistance * (float) Math.sin(Math.toRadians(yaw + 90));
			position.z += finalDistance * (float) Math.cos(Math.toRadians(yaw + 90));
		}
		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
			position.x -= finalDistance * (float) Math.sin(Math.toRadians(yaw - 90));
			position.z += finalDistance * (float) Math.cos(Math.toRadians(yaw - 90));
		}

		if (Input.Keyboard.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			if (pauseCooldown >= 0.75) {
				this.isMouseGrabbedRequest = true;
				pauseCooldown = 0;
			}
		}

		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
			position.y += finalDistance;
		}

		if (Input.Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			position.y -= finalDistance;
		}

		Entity.getComponent(this.id, TransposeComponent.class).position = this.position;
		Entity.getComponent(this.id, TransposeComponent.class).pitch = this.pitch;
		Entity.getComponent(this.id, TransposeComponent.class).yaw = this.yaw;
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
