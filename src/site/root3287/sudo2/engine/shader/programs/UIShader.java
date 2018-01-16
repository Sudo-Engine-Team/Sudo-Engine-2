package site.root3287.sudo2.engine.shader.programs;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;
import site.root3287.sudo2.engine.shader.uniforms.UniformVector;

public class UIShader extends Shader {
	
	public UniformMatrix proj, trans;
	public UniformVector colour;

	public UIShader() {
		super("/shader/UI/shader.glsl");
	}

	@Override
	protected void getAllUniformLocations() {
		proj = new UniformMatrix(programID, "proj");
		trans = new UniformMatrix(programID, "trans");
		colour = new UniformVector(programID, "colour");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "tc");
	}

}
