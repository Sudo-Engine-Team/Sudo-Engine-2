package site.root3287.sudo2.engine.physics.collision;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.engine.physics.IntersectData;
import site.root3287.sudo2.utils.SudoMaths;

public class BoundingSphere {
	private Vector3f center;
	private float radius;
	
	public BoundingSphere(Vector3f center, float radius){
		this.center = center;
		this.radius = radius;
	}
	
	public IntersectData isIntersecting(BoundingSphere other){
		float radiusDistace = this.radius + other.radius;
		float centerDistance = SudoMaths.distance3d(other.center, this.center);
		float distance = centerDistance - radiusDistace;
		
		return new IntersectData(centerDistance < radiusDistace, distance);
	}
	
	public Vector3f getCenter() {
		return center;
	}
	public void setCenter(Vector3f point) {
		this.center = point;
	}
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
}
