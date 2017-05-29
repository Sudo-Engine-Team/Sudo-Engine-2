package site.root3287.sudo2.utils;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.component.functions.TransposeComponent;
import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.entities.Camera;
import site.root3287.sudo2.logger.LogLevel;
import site.root3287.sudo2.logger.Logger;

public class SudoMaths {
	public static Matrix4f createTransformationMatrix(Vector3f translation) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		return matrix;
	}
	public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, float scale){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.x), new Vector3f(1,0,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.y), new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.z), new Vector3f(0,0,1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
		return matrix;
	}
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 0f), matrix, matrix);
		return matrix;
	}
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale, float rotation) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation), new Vector3f(0, 0, 1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 0f), matrix, matrix);
		return matrix;
	}
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector3f rotation, Vector2f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.x), new Vector3f(1,0,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.y), new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.z), new Vector3f(0,0,1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
		return matrix;
	}
	public static Matrix4f createViewMatrix(Camera camera){
		Matrix4f viewMatrix = new Matrix4f();
		float pitch = camera.getComponent(TransposeComponent.class).pitch;
		float yaw = camera.getComponent(TransposeComponent.class).yaw;
		Vector3f position = camera.getComponent(TransposeComponent.class).position;
        viewMatrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(pitch), new Vector3f(1, 0, 0), viewMatrix,
                viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(yaw), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Vector3f cameraPos = position;
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;
	}
	public static Matrix4f createProjectionMatrix(){
		Logger.log(LogLevel.INFO, "Creating projection Matrix");
		Vector2f size = DisplayManager.getCurrentWindowSize();
		float aspectRatio = (float) size.x/ size.y;
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(DisplayManager.FOV/ 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = DisplayManager.FAR_PLANE - DisplayManager.NEAR_PLANE;

		Matrix4f projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((DisplayManager.FAR_PLANE + DisplayManager.NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * DisplayManager.NEAR_PLANE * DisplayManager.FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
		
		return projectionMatrix;
	}
	
	public static Matrix4f createOrthoMatrix(){
		Logger.log("Creating Orthographic Matrix");
		float frustum_length = DisplayManager.FAR_PLANE - DisplayManager.NEAR_PLANE;
		
		Matrix4f orthoMatrix = new Matrix4f();
		orthoMatrix.m00 = (2/DisplayManager.WIDTH);
		orthoMatrix.m11 = (2/DisplayManager.HEIGHT);
		orthoMatrix.m22 = -(2/(frustum_length));
		orthoMatrix.m33 = 1;
		
		//orthoMatrix.m30 = -((right+left)/(right+left));
	//	orthoMatrix.m31 = -((top+bottom)/(top-bottom));
		orthoMatrix.m32 = ((DisplayManager.FAR_PLANE+DisplayManager.NEAR_PLANE) / -(frustum_length));
		return orthoMatrix;
	}
	
	public static float barryCentric(Vector3f p1, Vector3f p2, Vector3f p3, Vector2f pos) {
		float det = (p2.z - p3.z) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.z - p3.z);
		float l1 = ((p2.z - p3.z) * (pos.x - p3.x) + (p3.x - p2.x) * (pos.y - p3.z)) / det;
		float l2 = ((p3.z - p1.z) * (pos.x - p3.x) + (p1.x - p3.x) * (pos.y - p3.z)) / det;
		float l3 = 1.0f - l1 - l2;
		return l1 * p1.y + l2 * p2.y + l3 * p3.y;
	}
	public static float distance3d(Vector3f p1, Vector3f p2){
		return (float) Math.sqrt(Math.pow((p1.x - p2.x ), 2) + Math.pow((p1.y - p2.y), 2) + Math.pow((p1.z - p2.z), 2));
	}
	public static Vector3f maxVector3f(Vector3f x, Vector3f y){
		Vector3f result = new Vector3f();
		result.x = (x.x > y.x)? x.x:y.x;
		result.y = (x.y > y.y)? x.y:y.y;
		result.z = (x.z > y.z)? x.z:y.z;
		return result;
	}
	public static float maxVector3fItem(Vector3f x){
		float maxVal = x.x;
		if(x.x > maxVal){
			maxVal = x.x;
		}
		if(x.y > maxVal){
			maxVal = x.y;
		}
		if(x.z > maxVal){
			maxVal = x.z;
		}
		return maxVal;
	}
	public static float degreesToRadians(float degree){
		return (float) (degree * (Math.PI / 180f));
	}
}
