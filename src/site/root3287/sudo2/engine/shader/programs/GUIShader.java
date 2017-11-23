package site.root3287.sudo2.engine.shader.programs;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;
import site.root3287.sudo2.engine.shader.uniforms.UniformVector;

public class GUIShader extends Shader{

	public UniformMatrix proj, trans;
	public UniformVector bgColour;
	
	public GUIShader() {
		super("/shader/Gui/shader.glsl");
	}

	@Override
	protected void getAllUniformLocations() {
		proj = new UniformMatrix(programID, "proj");
		trans = new UniformMatrix(programID, "trans");
		bgColour = new UniformVector(programID, "bgColour");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "pos");
	}
	
}
