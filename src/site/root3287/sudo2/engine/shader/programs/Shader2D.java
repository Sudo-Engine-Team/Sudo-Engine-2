package site.root3287.sudo2.engine.shader.programs;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.shader.Shader;

public class Shader2D extends Shader{

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_useProjectionMatrix;
	private int location_useTextureAtlas;
	private int location_textureAtlasRows;
	private int location_textureAtlasOffset;
	private int location_colouredQuad;
	private int location_quadColour;
	
	public Shader2D() {
		super("res/shader/gui/guiVertex.glsl", "res/shader/gui/guiFragment.glsl");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformation");
		location_projectionMatrix = super.getUniformLocation("projection");
		location_useProjectionMatrix = super.getUniformLocation("useProjection");
		location_useTextureAtlas = super.getUniformLocation("useTextureAtlas");
		location_textureAtlasRows = super.getUniformLocation("textureAtlasRows");
		location_textureAtlasOffset = super.getUniformLocation("textureAtlasOffset");
		location_colouredQuad = super.getUniformLocation("isColouredQuad");
		location_quadColour = super.getUniformLocation("quadColour");
	}

	@Override
	protected void bindAttributes() {
		bindAttribute(0, "position");
	}

	public void loadTransformation(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjection(Matrix4f matrix){
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void useProjection(boolean matrix){
		super.loadBoolean(location_useProjectionMatrix, matrix);
	}
	
	public void useTextureAtlas(boolean useAtlas, int rows, Vector2f offset){
		if(!useAtlas){
			return;
		}
		super.loadBoolean(location_useTextureAtlas, useAtlas);
		if(useAtlas && rows > 0 && offset !=null){
			super.loadFloat(location_textureAtlasRows, rows);
			super.loadVector(location_textureAtlasOffset, offset);
		}
	}
	
	public void colouredQuad(boolean colouredQuad, Vector4f colour){
		if(!colouredQuad){
			super.loadBoolean(location_colouredQuad, colouredQuad);
			super.loadVector(location_colouredQuad, new Vector4f(0, 0, 0, 0));
			return;
		}
		super.loadBoolean(location_colouredQuad, colouredQuad);
		super.loadVector(location_quadColour, colour);
	}
}
