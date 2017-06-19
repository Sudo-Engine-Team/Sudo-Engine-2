package site.root3287.sudo2.engine.camera;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.utils.Input;

public class ThirdPersonCamera extends ProspectiveCamera{
	private Vector3f pointToFollow;
	private float distance = 50;
	private float angle = 0;
	
	public ThirdPersonCamera() {
		setPointToFollow(new Vector3f());
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		//Calculate Zoom
		double dWheel = Input.Mouse.getDWheel();
		distance -= dWheel;
		
		//Calculate Pitch
		if(Input.Mouse.isMouseDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)){
			this.pitch -= Input.Mouse.getDY();
		}
		
		//calculate rotation
		if(Input.Mouse.isMouseDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
			this.angle -= Input.Mouse.getDX();
		}
		
		float theta = 0 + angle;
		float xOffset = (float) (calcululateHDistance() * Math.sin(Math.toRadians(theta)));
		float yOffset = (float) (calcululateHDistance() * Math.cos(Math.toRadians(theta)));
		this.position.x -= xOffset;
		this.position.y -= yOffset;
		this.position.y = distance + calcululateVDistance();
		
		this.yaw = 180 - (0 + angle);
		
		System.out.println(yaw);
	}
	
	public float calcululateHDistance(){
		return (float) (distance*Math.cos(Math.toRadians(this.pitch)));
	}
	public float calcululateVDistance(){
		return (float) (distance*Math.sin(Math.toRadians(this.pitch)));
	}

	public Vector3f getPointToFollow() {
		return pointToFollow;
	}

	public void setPointToFollow(Vector3f pointToFollow) {
		this.pointToFollow = pointToFollow;
	}
}
