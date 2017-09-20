package site.root3287.sudo2.engine.shader;

import org.lwjgl.opengl.GL20;

public abstract class UniformVarible {
	protected String name;
	protected int location;
	
	public UniformVarible(int program, String name) {
		this.setName(name);
		this.location = GL20.glGetUniformLocation(program, name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
