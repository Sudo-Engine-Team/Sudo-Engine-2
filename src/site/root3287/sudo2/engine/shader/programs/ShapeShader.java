package site.root3287.sudo2.engine.shader.programs;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;
import site.root3287.sudo2.engine.shader.uniforms.UniformVector;

public class ShapeShader extends Shader {
	
	public ShapeShader() {
		super("/shader/shape/shapeVertex.glsl", "/shader/shape/shapeFragment.glsl");
	}
	
	UniformMatrix location_prospectiveMatrix;
	UniformVector location_pos;
	UniformVector location_scl;
	UniformVector location_colour;
	
	@Override
	protected void getAllUniformLocations() {
		location_prospectiveMatrix = new UniformMatrix(programID, "pmatrix");
		location_pos = new UniformVector(programID, "pos");
		location_scl =new UniformVector(programID, "scl");
		location_colour = new UniformVector(programID, "col");
	}
	
	public void loadProspectiveMatrix(Matrix4f m){
		location_prospectiveMatrix.loadMatrix(m);
	}
	
	public void loadTrasform(Vector2f p, Vector2f s){
		location_pos.loadVector(p);
		location_scl.loadVector(s);
	}
	
	public void loadColour(Vector4f colour){
		location_colour.loadVector(colour);
	}
	
	@Override
	protected void bindAttributes() {
		bindAttribute(0, "positions");
	}

}
