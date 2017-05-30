package site.root3287.sudo2.entities;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.component.functions.ModelComponet;
import site.root3287.sudo2.component.functions.TransposeComponent;
import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.ModelTexture;
import site.root3287.sudo2.engine.TexturedModel;

public class CubeEntity extends Entity{

	public CubeEntity() {
		float[] vertices = {			
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
				
		};
		float[] textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0	
		};
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22
		};
		float[] normals = {
			1,1,1,
			1,1,1,
			1,1,1,
			1,1,1,
			
			1,1,1,
			1,1,1,
			1,1,1,
			1,1,1,
			
			1,1,1,
			1,1,1,
			1,1,1,
			1,1,1,
			
			1,1,1,
			1,1,1,
			1,1,1,
			1,1,1,
			
			1,1,1,
			1,1,1,
			1,1,1,
			1,1,1,
			
			1,1,1,
			1,1,1,
			1,1,1,
			1,1,1,
		};
		TransposeComponent tc = new TransposeComponent();
		tc.position = new Vector3f(0, 0, 0);
		tc.rotation = new Vector3f(0, 0, 0);
		addComponent(tc);
		ModelComponet modelComponet = new ModelComponet();
		modelComponet.model = new TexturedModel(Loader.getInstance().loadToVAO(vertices, textureCoords, normals, indices), new ModelTexture(Loader.getInstance().loadTexture("res/image/white.png")));
		addComponent(modelComponet);
	}
	
	@Override
	public void update(float delta) {
		
	}

}
