package site.root3287.sudo2.engine.camera;

import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.utils.SudoMaths;

public class OrthographicCamera extends Camera{

	public OrthographicCamera() {
		this.projectionMatrix = SudoMaths.ortho();
		this.viewMatrix = SudoMaths.createViewMatrix(this);
		Matrix4f.mul(projectionMatrix, viewMatrix, combind);
	}
	
	@Override
	public void update(float delta) {
		Matrix4f.mul(projectionMatrix, viewMatrix, combind);
	}
	
}
