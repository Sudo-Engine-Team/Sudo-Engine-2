package site.root3287.sudo2.engine.shader.programs;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;

public class Shader2DImage extends Shader {
	
	public UniformMatrix proj;
	public UniformMatrix imageTrans;
	public UniformMatrix trans;
	public UniformMatrix view;

	public Shader2DImage() {
		super("/shader/shader2D/shaderImage.glsl");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		proj = new UniformMatrix(programID, "proj");
		imageTrans = new UniformMatrix(programID, "imageTrans");
		trans = new UniformMatrix(programID, "trans");
		view = new UniformMatrix(programID, "view");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "tc");
	}

}
