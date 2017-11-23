package site.root3287.sudo2.engine.shader.programs;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformBoolean;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;
import site.root3287.sudo2.engine.shader.uniforms.UniformVector;

public class TextShader extends Shader{

	public TextShader() {
		super("/shader/Text/textVertex.glsl", "/shader/Text/textFragment.glsl");
		// TODO Auto-generated constructor stub
	}

	public UniformMatrix location_projection;
	public UniformMatrix location_translation;
	public UniformVector location_colour;
	public UniformBoolean location_isDF;
	
	@Override
	protected void getAllUniformLocations() {
		location_projection = new UniformMatrix(programID, "projection");
		location_translation =new UniformMatrix(programID, "translation");
		location_colour = new UniformVector(programID, "colour");
		location_isDF = new UniformBoolean(programID, "isDF");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "tc");
	}
	
	public void loadTranslation(Matrix4f translation) {
		location_translation.loadMatrix(translation);
	}
	public void loadProjection(Matrix4f p) {
		location_projection.loadMatrix(p);
	}
	
	public void loadColour(Vector4f p) {
		location_colour.loadVector(p);
	}

}
