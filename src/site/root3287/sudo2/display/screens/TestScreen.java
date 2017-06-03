package site.root3287.sudo2.display.screens;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.display.Screen;
import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.render.Render;
import site.root3287.sudo2.entities.Camera;
import site.root3287.sudo2.entities.CubeEntity;
import site.root3287.sudo2.entities.CubeOBJEntity;
import site.root3287.sudo2.entities.Light;
import site.root3287.sudo2.entities.ProspectiveCamera;
import site.root3287.sudo2.utils.Input;

public class TestScreen implements Screen {

	private Render render;
	private CubeOBJEntity cube;
	private Light light;
	private Camera camera;
	
	@Override
	public void init() {
		this.camera = new ProspectiveCamera();
		this.render = new Render();
		cube = new CubeOBJEntity();
		this.light = new Light(new Vector3f(0,10,0), new Vector4f(1, 1, 1, 1));
		Input.Mouse.setGrabbed(true);
	}

	@Override
	public void update() {
		this.camera.update((float) DisplayManager.getDelta());
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
