package site.root3287.sudo2.engine.camera;

import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.utils.SudoMaths;

public class ProspectiveCamera extends Camera {
	
	public ProspectiveCamera() {
		this.projectionMatrix = SudoMaths.createProjectionMatrix();
		this.viewMatrix = SudoMaths.createViewMatrix(this);
		this.combind = Matrix4f.mul(projectionMatrix, viewMatrix, null);
	}
	
	@Override
	public void update(float delta) {
		this.viewMatrix = SudoMaths.createViewMatrix(this);
		this.combind = Matrix4f.mul(projectionMatrix, viewMatrix, null);
	}

	@Override
	public void resize(float width, float height) {
		this.projectionMatrix = SudoMaths.createProjectionMatrix();
	}
}
