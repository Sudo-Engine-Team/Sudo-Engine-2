package site.root3287.sudo2.engine.shader.programs;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.shader.Shader;

public class FontShader extends Shader{

	private static final String VERTEX_FILE = "/shader/Fonts/fontVertex.glsl";
	private static final String FRAGMENT_FILE = "/shader/Fonts/fontFragment.glsl";
	
	private int location_colour;
    private int location_translation;
    private int location_distanceField;
    private int location_width;
    private int location_edge;
    private int location_borderWidth;
    private int location_borderEdge;
    private int loacation_projection;
     
    public FontShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
 
    @Override
    protected void getAllUniformLocations() {
        location_colour = super.getUniformLocation("colour");
        location_translation = super.getUniformLocation("translation");
        location_distanceField = super.getUniformLocation("isDistanceField");
        location_width = super.getUniformLocation("width");
        location_edge = super.getUniformLocation("edge");
        location_borderWidth = super.getUniformLocation("borderWidth");
        location_borderEdge = super.getUniformLocation("borderEdge");
        loacation_projection = super.getUniformLocation("projection");
    }
 
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }
     
    public void loadColour(Vector4f colour){
        super.loadVector(location_colour, colour);
    }
     
    public void loadTranslation(Matrix4f translation){
        super.loadMatrix(location_translation, translation);
    }
    
    public void isDistanceField(boolean df){
    	super.loadBoolean(location_distanceField, df);
    }
    
    public void loadDistanceFields(float width, float edge){
    	super.loadFloat(location_width, width);
    	super.loadFloat(location_edge, edge);
    }
    
    public void loadBorderFields(float width, float edge){
    	super.loadFloat(location_borderEdge, edge);
    	super.loadFloat(location_borderWidth, width);
		
	} 
    public void loadProjection(Matrix4f projection){
    	super.loadMatrix(loacation_projection, projection);
    }
}
