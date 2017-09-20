package site.root3287.sudo2.engine.shader.programs;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformBoolean;
import site.root3287.sudo2.engine.shader.uniforms.UniformFloat;
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
	public UniformFloat location_width;
	public UniformFloat location_edge;
	public UniformFloat location_borderWidth;
	public UniformFloat location_borderEdge;
	public UniformVector location_outlineColour;
	
	@Override
	protected void getAllUniformLocations() {
		location_projection = new UniformMatrix(programID, "projection");
		location_translation =new UniformMatrix(programID, "translation");
		location_colour = new UniformVector(programID, "colour");
		location_isDF = new UniformBoolean(programID, "isDF");
		location_width = new UniformFloat(programID, "width");
		location_edge =new UniformFloat(programID, "edge");
		location_borderWidth = new UniformFloat(programID, "borderWidth");
		location_borderEdge = new UniformFloat(programID, "borderEdge");
		location_outlineColour = new UniformVector(programID, 	"outlineColour");
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

	public void loadDistanceField(boolean df, float width, float edge, float borderWidth, float borderEdge, Vector4f outlineColour) {
		location_isDF.loadBoolean(df);
		if(df){
			location_isDF.loadBoolean(df);
			location_width.loadFloat(width);
			location_edge.loadFloat(edge);
			location_borderWidth.loadFloat(borderWidth);
			location_borderEdge.loadFloat(borderEdge);
			location_outlineColour.loadVector(outlineColour);
		}
	}

}
