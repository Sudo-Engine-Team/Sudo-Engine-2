package site.root3287.sudo2.engine.shader.uniforms;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.engine.shader.UniformVarible;

public class UniformMatrix extends UniformVarible{

	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public UniformMatrix(int program, String name) {
		super(program, name);
	}
	
	public void loadMatrix(Matrix4f matrix) {
		matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4fv(this.location, false, matrixBuffer);
	}

}
