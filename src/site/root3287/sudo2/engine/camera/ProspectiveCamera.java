package site.root3287.sudo2.engine.camera;

import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.utils.SudoMaths;

public class ProspectiveCamera extends Camera {
	
	public float width, height, near, far;
	
	@Override
	public void update(float delta) {
		this.viewMatrix = SudoMaths.createViewMatrix(this);
		this.combind = Matrix4f.mul(projectionMatrix, viewMatrix, null);
	}
	
	public void setDimension(float width, float height, float near, float far) {
		this.width = width;
		this.height = height;
		this.near = near;
		this.far = far;
		this.projectionMatrix = SudoMaths.ortho(width, height, near, far);
	}

	@Override
	public void resize(float width, float height) {
		setDimension(width, height, near, far);
	}
}
