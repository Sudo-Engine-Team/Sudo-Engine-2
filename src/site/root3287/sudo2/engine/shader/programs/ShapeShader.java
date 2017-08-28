package site.root3287.sudo2.engine.shader.programs;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.shader.Shader;

public class ShapeShader extends Shader {
	
	public ShapeShader() {
		super("/shader/shape/shapeVertex.glsl", "/shader/shape/shapeFragment.glsl");
	}
	
	int location_prospectiveMatrix;
	int location_pos;
	int location_scl;
	int location_colour;
	
	@Override
	protected void getAllUniformLocations() {
		location_prospectiveMatrix = getUniformLocation("pmatrix");
		location_pos = getUniformLocation("pos");
		location_scl = getUniformLocation("scl");
		location_colour = getUniformLocation("col");
	}
	
	public void loadProspectiveMatrix(Matrix4f m){
		loadMatrix(location_prospectiveMatrix, m);
	}
	
	public void loadTrasform(Vector2f p, Vector2f s){
		loadVector(location_pos, p);
		loadVector(location_scl, s);
	}
	
	public void loadColour(Vector4f colour){
		loadVector(location_colour, colour);
	}
	
	@Override
	protected void bindAttributes() {
		bindAttribute(0, "positions");
	}

}
