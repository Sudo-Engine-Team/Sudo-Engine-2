package site.root3287.sudo2.engine.shader.programs;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformBoolean;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;
import site.root3287.sudo2.engine.shader.uniforms.UniformVector;

public class TerrainShader extends Shader{

	public UniformMatrix proj, view, trans;
	public UniformBoolean forceColour;
	public UniformVector colour;
	
	public UniformVector lightPosition;
	public UniformVector lightColour;
	public TerrainShader() {
		super("/shader/Terrain/vertex.glsl", "/shader/Terrain/fragment.glsl");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		proj = new UniformMatrix(programID, "proj");
		view = new UniformMatrix(programID, "view");
		trans = new UniformMatrix(programID, "trans");
		forceColour = new UniformBoolean(programID, "forceColour");
		colour = new UniformVector(programID, "fcolour");
		
		lightColour = new UniformVector(programID, "lightColour");
		lightPosition = new UniformVector(programID, "lightPosition");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "tc");
		bindAttribute(2, "norm");
	}

}
