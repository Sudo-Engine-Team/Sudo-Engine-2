package site.root3287.sudo2.engine.shader.uniforms;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.shader.UniformVarible;

public class UniformVector extends UniformVarible{

	public UniformVector(int program, String name) {
		super(program, name);
	}
	
	public void loadVector(Vector2f vector) {
		GL20.glUniform2f(this.location ,vector.x,vector.y);
	}
	
	public void loadVector(Vector3f vector) {
		 GL20.glUniform3f(this.location,vector.x,vector.y, vector.z);
	}
	
	public void loadVector(Vector4f vector) {
		 GL20.glUniform4f(this.location,vector.x,vector.y, vector.z, vector.w);
	}

}
