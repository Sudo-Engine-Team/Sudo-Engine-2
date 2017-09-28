package site.root3287.sudo2.engine.shader.programs;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformBoolean;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;
import site.root3287.sudo2.engine.shader.uniforms.UniformVector;

public class Shader2D extends Shader{

	public UniformMatrix proj;
	public UniformMatrix trans;
	public UniformBoolean isOverrideColour; 
	public UniformVector overrideColour;
	public UniformMatrix tcTrans;
	public Shader2D() {
		super("/shader/Shader2D/vertex.glsl", "/shader/Shader2D/fragment.glsl");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		proj = new UniformMatrix(programID, "proj");
		trans = new UniformMatrix(programID, "trans");
		isOverrideColour = new UniformBoolean(programID, "isOverideColour");
		overrideColour = new UniformVector(programID, "overrideColour");
		tcTrans = new UniformMatrix(programID, "tcTrans");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "tc");
	}

}
