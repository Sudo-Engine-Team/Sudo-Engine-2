package site.root3287.sudo2.engine.shader.uniforms;

import org.lwjgl.opengl.GL20;

import site.root3287.sudo2.engine.shader.UniformVarible;

public class UniformFloat extends UniformVarible{

	public UniformFloat(int program, String name) {
		super(program, name);
		// TODO Auto-generated constructor stub
	}
	public void loadFloat(float f) {
		GL20.glUniform1f(this.location, f);
	}
}
