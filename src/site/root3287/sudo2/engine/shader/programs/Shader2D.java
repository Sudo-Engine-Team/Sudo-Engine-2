package site.root3287.sudo2.engine.shader.programs;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;
import site.root3287.sudo2.engine.shader.uniforms.UniformVector;

public class Shader2D extends Shader{

	public UniformMatrix proj;
	public UniformMatrix trans;
	public UniformVector colour;
	public UniformMatrix view;
	
	public Shader2D() {
		super("/shader/shader2D/shader.glsl");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		proj = new UniformMatrix(programID, "proj");
		trans = new UniformMatrix(programID, "trans");
		colour = new UniformVector(programID, "colour");
		view = new UniformMatrix(programID, "view");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "pos");
	}

}
