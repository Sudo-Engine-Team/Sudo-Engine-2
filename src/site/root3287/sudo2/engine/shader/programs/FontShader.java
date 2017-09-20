package site.root3287.sudo2.engine.shader.programs;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformBoolean;
import site.root3287.sudo2.engine.shader.uniforms.UniformFloat;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;
import site.root3287.sudo2.engine.shader.uniforms.UniformVector;

public class FontShader extends Shader{

	private static final String VERTEX_FILE = "/shader/Fonts/fontVertex.glsl";
	private static final String FRAGMENT_FILE = "/shader/Fonts/fontFragment.glsl";
	
	public UniformVector location_colour;
	public UniformMatrix location_translation;
	public UniformBoolean location_distanceField;
	public UniformFloat location_width;
	public UniformFloat location_edge;
	public UniformFloat location_borderWidth;
	public UniformFloat location_borderEdge;
    public UniformMatrix loacation_projection;
     
    public FontShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
 
    @Override
    protected void getAllUniformLocations() {
        location_colour = new UniformVector(programID, "colour");
        location_translation = new UniformMatrix(programID, "translation");
        location_distanceField = new UniformBoolean(programID, "isDistanceField");
        location_width = new UniformFloat(programID,"width");
        location_edge = new UniformFloat(programID,"edge");
        location_borderWidth = new UniformFloat(programID,"borderWidth");
        location_borderEdge = new UniformFloat(programID,"borderEdge");
        loacation_projection =  new UniformMatrix(programID, "projection");
    }
 
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }
     
    public void loadColour(Vector4f colour){
       location_colour.loadVector(colour);
    }
     
    public void loadTranslation(Matrix4f translation){
        location_translation.loadMatrix(translation);
    }
    
    public void isDistanceField(boolean df){
    	location_distanceField.loadBoolean(df);
    }
    
    public void loadDistanceFields(float width, float edge){
    	location_width.loadFloat(width);
    	location_edge.loadFloat(edge);
    }
    
    public void loadBorderFields(float width, float edge){
    	location_borderEdge.loadFloat(edge);
    	location_borderWidth.loadFloat(width);
		
	} 
    public void loadProjection(Matrix4f projection){
    	loacation_projection.loadMatrix(projection);
    }
}
