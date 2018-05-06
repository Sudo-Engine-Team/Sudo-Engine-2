package site.root3287.sudo2.engine.render;

import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.engine.shader.Shader;

public abstract class Renderer {
	public Shader shader;
	public Matrix4f projection;
	public abstract void dispose();
}
