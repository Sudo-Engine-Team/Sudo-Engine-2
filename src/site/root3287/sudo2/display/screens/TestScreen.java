package site.root3287.sudo2.display.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.component.functions.AABBComponent;
import site.root3287.sudo2.component.functions.TransposeComponent;
import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.display.Screen;
import site.root3287.sudo2.engine.FBO;
import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.camera.Camera;
import site.root3287.sudo2.engine.camera.FirstPersonCamera;
import site.root3287.sudo2.engine.camera.OrthographicCamera;
import site.root3287.sudo2.engine.frustum.Frustum;
import site.root3287.sudo2.engine.gui.GuiTexture;
import site.root3287.sudo2.engine.gui.components.GuiPanel;
import site.root3287.sudo2.engine.render.Render;
import site.root3287.sudo2.entities.CubeOBJEntity;
import site.root3287.sudo2.entities.Entity;
import site.root3287.sudo2.entities.Light;
import site.root3287.sudo2.utils.Input;

public class TestScreen implements Screen {

	private Render render;
	private Light light;
	private Camera prospectiveCamera;
	private Camera orthographicCamera;
	private Frustum frustum;
	private List<Entity> allEntity = new ArrayList<>();
	private List<GuiTexture> allTexture = new ArrayList<>();
	private GuiPanel panel;
	
	private int fbo1, fboTexture, fboDepth;
	
	@Override
	public void init() {
		
		this.prospectiveCamera = new FirstPersonCamera();
		this.orthographicCamera = new OrthographicCamera();
		this.render = new Render(prospectiveCamera);
		this.render.setProjectionMatrix(prospectiveCamera.getProjectionMatrix());
		this.render.setOrthographicMatrix(orthographicCamera.getProjectionMatrix());
		this.render.init();
		this.light = new Light(new Vector3f(10000,10000,10000), new Vector4f(0.5f, 0.5f, 0.75f, 0));
		Input.Mouse.setGrabbed(true);
		
		for (int i=0; i<200; i++){
			CubeOBJEntity cube = new CubeOBJEntity();
			cube.getComponent(TransposeComponent.class).position = new Vector3f(new Random().nextFloat() * 100, new Random().nextFloat() *100*-1, new Random().nextFloat() *100);
			cube.getComponent(TransposeComponent.class).scale = 2;
			allEntity.add(cube);
		}
		
		GuiTexture crosshairX = new GuiTexture(Loader.getInstance().loadTexture("res/image/ui-grey-1.png"), new Vector2f(0f, 0f), new Vector2f(5f, 0.5f));
		GuiTexture crosshairY = new GuiTexture(Loader.getInstance().loadTexture("res/image/ui-grey-1.png"), new Vector2f(0f, 0f), new Vector2f(0.5f, 5f));
		GuiTexture inventoryBar = new GuiTexture(Loader.getInstance().loadTexture("res/image/ui-grey-1.png"), new Vector2f(0f, -DisplayManager.HEIGHT/2.25f), new Vector2f(DisplayManager.WIDTH/4f, 25f));
		panel = new GuiPanel("res/image/GUIAtlas.png", new Vector2f(512-128, 0), new Vector2f(32, 128), new Vector2f(3, 0), new Vector2f(4, 0), new Vector2f(5, 0)) {
			
			@Override
			public void whileHovering() {
				
			}
			
			@Override
			public void show() {
				
			}
			
			@Override
			public void onLeave() {
				
			}
			
			@Override
			public void onHover() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onClick() {
				System.out.println("Clicked");
			}
			
			@Override
			public void hide() {
			
			}
		};
		allTexture.addAll(panel.getNinePatch().getNinePatch());
		allTexture.add(crosshairX);
		allTexture.add(crosshairY);
		allTexture.add(inventoryBar);
		
		
		fbo1 = FBO.createFBO();
		fboTexture = FBO.createFBOTexture((int)DisplayManager.WIDTH, (int)DisplayManager.HEIGHT);
		fboDepth = FBO.createFBODepthTexture((int)DisplayManager.WIDTH, (int)DisplayManager.HEIGHT);
		FBO.unbindFBO();
		//allTexture.add(new GuiTexture(fboTexture, new Vector2f(0, 0), new  Vector2f(DisplayManager.WIDTH, DisplayManager.HEIGHT)));
		
		//allTexture.add(guiTop);
		frustum  = new Frustum(prospectiveCamera.getCombind());
	}

	@Override
	public void update() {
		panel.update((float) DisplayManager.getDelta());
		for(Entity e:allEntity){
			e.update((float) DisplayManager.getDelta());
		}
		this.prospectiveCamera.update((float) DisplayManager.getDelta());
		
		if(Input.Keyboard.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)){
			Input.Mouse.setGrabbed((Input.Mouse.isGrabbed())?false:true);
		}
		
		frustum.update(prospectiveCamera.getCombind());
	}
	
	@Override
	public void render() {
		this.render.updateCamera(prospectiveCamera);
		
		/*FBO.bindFBO(fbo1, (int)DisplayManager.WIDTH, (int)DisplayManager.HEIGHT);
		for(Entity e : allEntity){
			if(e.hasComponent(AABBComponent.class) && frustum.isAABBinFrustum(e.getComponent(AABBComponent.class).aabbBox)){
				this.render.addEntity(e);
			}
		}
		//this.render.render();
		FBO.unbindFBO();*/
		
		for(Entity e : allEntity){
			if(e.hasComponent(AABBComponent.class) && frustum.isAABBinFrustum(e.getComponent(AABBComponent.class).aabbBox)){
				this.render.addEntity(e);
			}
		}
		for(GuiTexture t:allTexture){
			render.addGui(t);
		}
		this.render.addLight(this.light);
		this.render.render();
	}
	
	@Override
	public void destory() {
		this.render.dispose();
		Loader.getInstance().destory();
		FBO.dispose();
	}
}
