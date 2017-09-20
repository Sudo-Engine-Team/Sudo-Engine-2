package site.root3287.sudo2.engine.shader.uniforms;

import org.lwjgl.opengl.GL20;

import site.root3287.sudo2.engine.shader.UniformVarible;

public class UniformBoolean extends UniformVarible{

	public UniformBoolean(int program, String name) {
		super(program, name);
		// TODO Auto-generated constructor stub
	}
	
	public void loadBoolean(boolean value) {
		GL20.glUniform1f(location, (value)?1f:0f);
	}

}
