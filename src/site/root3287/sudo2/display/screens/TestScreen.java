package site.root3287.sudo2.display.screens;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.display.Screen;
import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.render.Render;
import site.root3287.sudo2.entities.Camera;
import site.root3287.sudo2.entities.CubeEntity;
import site.root3287.sudo2.entities.Light;
import site.root3287.sudo2.entities.ProspectiveCamera;
import site.root3287.sudo2.utils.Input;

public class TestScreen implements Screen {

	private Render render;
	private CubeEntity cube;
	private Light light;
	private Camera camera;
	
	@Override
	public void init() {
		this.camera = new ProspectiveCamera();
		this.render = new Render();
		cube = new CubeEntity();
		this.light = new Light(new Vector3f(0,10,0), new Vector4f(1, 1, 1, 1));
	}

	@Override
	public void update() {
		//Input.Mouse.setMousePosition(DisplayManager.WIDTH-DisplayManager.WIDTH/2, DisplayManager.HEIGHT-DisplayManager.HEIGHT/2);
		System.out.println("DX: "+Input.Mouse.getDX()+"\n"+"DY: "+Input.Mouse.getDY()+"\n");
		this.camera.update(1);
		this.render.updateCamera(camera);
		this.render.addEntity(cube);
		this.render.addLight(this.light);
	}
	
	@Override
	public void render() {
		this.render.render();
	}

	@Override
	public void destory() {
		this.render.dispose();
		Loader.getInstance().destory();
	}
}
