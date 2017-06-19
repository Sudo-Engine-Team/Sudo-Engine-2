package site.root3287.sudo2.engine.frustum;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.engine.physics.collision.AABB;

public class Frustum {
	private static int NEAR = 0, FAR = 1, TOP =2, BOTTOM=3, LEFT=4, RIGHT=5;
	private FrustumPlane[] planes = new FrustumPlane[6];
	
	public Frustum(Matrix4f combined) {
		for(int i = 0; i < 6; i++){
			planes[i] = new FrustumPlane();
		}
		createPlanes(combined);
	}

	public void update(Matrix4f combined){
		createPlanes(combined);
	}
	private void createPlanes(Matrix4f combined){
		planes[LEFT].normal.x = combined.m03 + combined.m00;
		planes[LEFT].normal.y = combined.m13 + combined.m10;
		planes[LEFT].normal.z = combined.m23 + combined.m20;
		planes[LEFT].distance = combined.m33 + combined.m30;
		
		planes[RIGHT].normal.x = combined.m03 - combined.m00;
		planes[RIGHT].normal.y = combined.m13 - combined.m10;
		planes[RIGHT].normal.z = combined.m23 - combined.m20;
		planes[RIGHT].distance = combined.m33 - combined.m30;
		
		planes[BOTTOM].normal.x = combined.m03 + combined.m01;
		planes[BOTTOM].normal.y = combined.m13 + combined.m11;
		planes[BOTTOM].normal.z = combined.m23 + combined.m21;
		planes[BOTTOM].distance = combined.m33 + combined.m31;
		
		planes[TOP].normal.x = combined.m03 - combined.m01;
		planes[TOP].normal.y = combined.m13 - combined.m11;
		planes[TOP].normal.z = combined.m23 - combined.m21;
		planes[TOP].distance = combined.m33 - combined.m31;
		
		planes[NEAR].normal.x = combined.m03 + combined.m02;
		planes[NEAR].normal.y = combined.m13 + combined.m12;
		planes[NEAR].normal.z = combined.m23 + combined.m22;
		planes[NEAR].distance = combined.m33 + combined.m32;
		
		planes[FAR].normal.x = combined.m03 - combined.m02;
		planes[FAR].normal.y = combined.m13 - combined.m12;
		planes[FAR].normal.z = combined.m23 - combined.m22;
		planes[FAR].distance = combined.m33 - combined.m32;
		for(FrustumPlane p : planes){
			float length = 1/p.normal.length();
			p.normal.normalise();
			p.distance *= length;
		}
	}
	
	public boolean isPointInFrustum(Vector3f point){
		for(FrustumPlane p :planes){
			if(p.distanceToPoint(point) < 0){
				return false;
			}
		}
		return true;
	}
	
	public boolean isAABBinFrustum(AABB box){
		 boolean result = true;

		    for (FrustumPlane plane : planes){
		        if (plane.distanceToPoint(box.getVP(plane.normal)) < 0){
		            return false;
		        }else if (plane.distanceToPoint(box.getVN(plane.normal)) < 0){
		            result = true;
		        }
		    }

		return result;
	}
}
