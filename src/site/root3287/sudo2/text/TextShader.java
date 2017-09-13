package site.root3287.sudo2.text;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.shader.Shader;

public class TextShader extends Shader{

	public TextShader() {
		super("/shader/Text/textVertex.glsl", "/shader/Text/textFragment.glsl");
		// TODO Auto-generated constructor stub
	}

	int location_projection;
	int location_translation;
	int location_colour;
	
	@Override
	protected void getAllUniformLocations() {
		location_projection = getUniformLocation("projection");
		location_translation = getUniformLocation("translation");
		location_colour = getUniformLocation("colour");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "tc");
	}
	
	public void loadTranslation(Matrix4f translation) {
		loadMatrix(location_translation, translation);
	}
	public void loadProjection(Matrix4f p) {
		loadMatrix(location_projection, p);
	}
	
	public void loadColour(Vector4f p) {
		loadVector(location_colour, p);
	}

}
