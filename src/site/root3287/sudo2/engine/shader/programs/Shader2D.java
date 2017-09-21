package site.root3287.sudo2.engine.shader.programs;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;

public class Shader2D extends Shader{

	UniformMatrix proj;
	UniformMatrix trans;
	
	public Shader2D() {
		super("/shader/shader2D/vertex.glsl", "/shader/shader2D/fragment.glsl");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void bindAttributes() {
		// TODO Auto-generated method stub
		
	}

}
