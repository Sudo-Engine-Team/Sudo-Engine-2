package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.engine.shader.programs.EntityShader;
import site.root3287.sudo2.entities.Light;
import site.root3287.sudo2.logger.LogLevel;
import site.root3287.sudo2.logger.Logger;
import site.root3287.sudo2.utils.SudoMaths;

public class Render {
	private Matrix4f projectionMatrix;
	private Matrix4f orthographicMatrix;
	
	private List<Light> lights = new ArrayList<>();
	
	//Start the shader
	//Init render varible.
	EntityShader entityShader = new EntityShader();
	EntityRender entityRender;
	
	public Render() {
		enableCulling();
		this.projectionMatrix = SudoMaths.createProjectionMatrix();
		this.orthographicMatrix = SudoMaths.createOrthoMatrix();
		this.entityRender = new EntityRender(entityShader, projectionMatrix);
	}
	
	public void prepare() {
		Logger.log(LogLevel.DEBUG_RENDER, "Preparing render");
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(DisplayManager.BACKGROUND_COLOUR.x, DisplayManager.BACKGROUND_COLOUR.y, DisplayManager.BACKGROUND_COLOUR.z, DisplayManager.BACKGROUND_COLOUR.w);
	}
	
	public void render(){
		prepare();
	}
	
	public void addLight(Light l){
		lights.add(l);
	}

	public void dispose() {
		//Dispose all the shaders...
		this.entityShader.dispose();
	}
	
	public static void enableCulling(){
		Logger.log(LogLevel.DEBUG_RENDER, "Enabling Culling");
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	public static void disableCulling(){
		Logger.log(LogLevel.DEBUG_RENDER, "Disabling Culling");
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public Matrix4f getOrthographicMatrix(){
		return this.orthographicMatrix;
	}
}
