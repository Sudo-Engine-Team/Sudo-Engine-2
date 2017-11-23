package site.root3287.sudo2.engine.shader.programs;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.shader.Shader;
import site.root3287.sudo2.engine.shader.uniforms.UniformMatrix;
import site.root3287.sudo2.engine.shader.uniforms.UniformVector;
import site.root3287.sudo2.utils.SudoMaths;

public class ShapeShader extends Shader {
	
	public ShapeShader() {
		super("/shader/shape/shapeVertex.glsl", "/shader/shape/shapeFragment.glsl");
	}
	
	UniformMatrix location_prospectiveMatrix, trans;
	UniformVector location_colour;
	
	@Override
	protected void getAllUniformLocations() {
		location_prospectiveMatrix = new UniformMatrix(programID, "pmatrix");
		trans = new UniformMatrix(programID, "trans");
		location_colour = new UniformVector(programID, "col");
	}
	
	public void loadProspectiveMatrix(Matrix4f m){
		location_prospectiveMatrix.loadMatrix(m);
	}
	
	public void loadTrasform(Vector2f p, Vector2f s){
		Matrix4f trans = SudoMaths.createTransformationMatrix(p, s);
		this.trans.loadMatrix(trans);
	}
	
	public void loadColour(Vector4f colour){
		location_colour.loadVector(colour);
	}
	
	@Override
	protected void bindAttributes() {
		bindAttribute(0, "positions");
	}

}
