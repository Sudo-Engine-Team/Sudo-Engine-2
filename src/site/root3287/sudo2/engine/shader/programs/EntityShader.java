package site.root3287.sudo2.engine.shader.programs;

import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.component.functions.ColourComponent;
import site.root3287.sudo2.component.functions.TransposeComponent;
import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformBoolean;
import site.root3287.sudo2.engine.shader.uniforms.UniformFloat;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;
import site.root3287.sudo2.engine.shader.uniforms.UniformVector;
import site.root3287.sudo2.entities.Light;

public class EntityShader extends Shader{

	public UniformMatrix location_transformationMatrix;
	public UniformMatrix location_projectionMatrix;
	public UniformMatrix location_viewMatrix;
	public UniformVector[] location_lightPosition;
	public UniformVector[] location_lightColour;
	public UniformFloat location_shineDamper;
	public UniformFloat location_reflectivity;
	public UniformBoolean location_useFakeLight;
	public UniformFloat location_fogDensity;
	public UniformFloat location_fogGradient;
	public UniformVector location_skyColour;
	public UniformBoolean location_useTextureAtlas;
	public UniformFloat location_textureAtlasRows;
	public UniformVector location_textureAtlasOffset;
    
    private static final int MAX_LIGHT = 4;
	
	public EntityShader() {
		super("/shader/Entity/vertexShader.glsl", "/shader/Entity/fragmentShader.glsl");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = new UniformMatrix(programID, "transformationMatrix");
        location_projectionMatrix = new UniformMatrix(programID, "projectionMatrix");
        location_viewMatrix = new UniformMatrix(programID, "viewMatrix");
        location_shineDamper = new UniformFloat(programID, "shineDamper");
        location_reflectivity = new UniformFloat(programID, "reflectivity");
        location_useFakeLight = new UniformBoolean(programID, "useFakeLight");
        location_fogDensity = new UniformFloat(programID, "fogDensity");
        location_fogGradient = new UniformFloat(programID, "fogGradient");
        location_skyColour = new UniformVector(programID, "skyColour");
        
        location_lightPosition = new UniformVector[MAX_LIGHT];
        location_lightColour = new UniformVector[MAX_LIGHT];
        for(int i=0; i<MAX_LIGHT; i++){
        	location_lightPosition[i] = new UniformVector(programID, "lightPosition["+i+"]");
        	location_lightColour[i] = new UniformVector(programID, "lightColour["+i+"]");
        }
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
        super.bindAttribute(2, "normal");
	}
	
	
	public void loadShineVariables(float damper,float reflectivity){
        location_shineDamper.loadFloat(damper);
        location_reflectivity.loadFloat(reflectivity);
    }
     
    public void loadTransformationMatrix(Matrix4f matrix){
       location_transformationMatrix.loadMatrix(matrix);
    }
     
    public void loadLight(List<Light> light){
    	for(int i =0; i<light.size(); i++){
    		if(i < MAX_LIGHT){
    			location_lightPosition[i].loadVector(light.get(i).getComponent(TransposeComponent.class).position);
            	location_lightColour[i].loadVector(light.get(i).getComponent(ColourComponent.class).colour);
    		}else{
    			location_lightPosition[i].loadVector(new Vector3f(0, 0, 0));
            	location_lightColour[i].loadVector(new Vector4f(0, 0, 0, 0));
    		}
    	}
    }
     
    public void loadViewMatrix(Matrix4f viewMatrix){
       location_viewMatrix.loadMatrix(viewMatrix);
    }
     
    public void loadProjectionMatrix(Matrix4f projection){
        location_projectionMatrix.loadMatrix(projection);
    }
    
    public void loadFakeLight(boolean fakeLight){
    	location_useFakeLight.loadBoolean(fakeLight);
    }
    public void setFog(float fogDensity, float fogGradient){
    	location_fogDensity.loadFloat(fogDensity);
    	location_fogGradient.loadFloat(fogGradient);
    }
    public void loadSkyColour(float r, float g, float b){
    	location_skyColour.loadVector(new Vector3f(r,g,b));
    }
    public void useTextureAtlas(boolean useAtlas, int rows, Vector2f offset){
		location_useTextureAtlas.loadBoolean(useAtlas);
		if(useAtlas && rows > 0 && offset !=null){
			location_textureAtlasRows.loadFloat( rows);
			location_textureAtlasOffset.loadVector( offset);
		}
	}
}
