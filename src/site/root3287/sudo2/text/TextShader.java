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
	int location_isDF;
	int location_width;
	int location_edge;
	int location_borderWidth;
	int location_borderEdge;
	int location_outlineColour;
	
	@Override
	protected void getAllUniformLocations() {
		location_projection = getUniformLocation("projection");
		location_translation = getUniformLocation("translation");
		location_colour = getUniformLocation("colour");
		location_isDF = getUniformLocation("isDF");
		location_width = getUniformLocation("width");
		location_edge = getUniformLocation("edge");
		location_borderWidth = getUniformLocation("borderWidth");
		location_borderEdge = getUniformLocation("borderEdge");
		location_outlineColour = getUniformLocation("outlineColour");
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

	public void loadDistanceField(boolean df, float width, float edge, float borderWidth, float borderEdge, Vector4f outlineColour) {
		if(df){
			loadBoolean(location_isDF, df);
			loadFloat(location_width, width);
			loadFloat(location_edge, edge);
			loadFloat(location_borderWidth, borderWidth);
			loadFloat(location_borderEdge, borderEdge);
			loadVector(location_outlineColour, outlineColour);
		}else{
			loadBoolean(location_isDF, df);
		}
	}

}
