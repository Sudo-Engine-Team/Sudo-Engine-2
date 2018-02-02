package site.root3287.sudo2.engine.camera;

import org.json.JSONObject;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class Camera{
	
	protected Matrix4f projectionMatrix;
	protected Matrix4f viewMatrix;
	protected Matrix4f combind;
	
	protected Vector3f position;
	protected float pitch, yaw;
	
	public Camera() {
		position = new Vector3f();
	}
	
	public abstract void resize(float width, float height);
	public abstract void update(float delta);

	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public void setProjectionMatrix(Matrix4f projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
	}

	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}

	public void setViewMatrix(Matrix4f viewMatrix) {
		this.viewMatrix = viewMatrix;
	}

	public Matrix4f getCombind() {
		return combind;
	}

	public void setCombind(Matrix4f combind) {
		this.combind = combind;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	@Override
	public String toString() {
		JSONObject obj = new JSONObject();
		obj.append("position", getPosition());
		obj.append("pitch", getPitch());
		obj.append("yaw", getYaw());
		//obj.append("view", getViewMatrix());
		return obj.toString();
	}
}
