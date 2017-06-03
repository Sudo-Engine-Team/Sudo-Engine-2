package site.root3287.sudo2.engine.shader.programs;

import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.engine.shader.Shader;

public class Shader2D extends Shader{

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	
	public Shader2D() {
		super("res/shader/gui/guiVertex.glsl", "res/shader/gui/guiFragment.glsl");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformation");
		location_projectionMatrix = super.getUniformLocation("projection");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
	}

	public void loadTransformation(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjection(Matrix4f matrix){
		super.loadMatrix(location_projectionMatrix, matrix);
	}
}
