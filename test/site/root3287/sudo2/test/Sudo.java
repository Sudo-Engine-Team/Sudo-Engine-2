package site.root3287.sudo2.test;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.display.Game;
import site.root3287.sudo2.display.Screen;
import site.root3287.sudo2.engine.IBO;
import site.root3287.sudo2.engine.Renderable2D;
import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.VBO;
import site.root3287.sudo2.engine.camera.OrthographicCamera;
import site.root3287.sudo2.engine.render.Render2D;
import site.root3287.sudo2.engine.render.RenderUtils;
import site.root3287.sudo2.ui.UI;
import site.root3287.sudo2.ui.elements.UIPanel;
import site.root3287.sudo2.ui.elements.UIRoot;

public class Sudo implements Screen{
	
	public static void main(String[] args) {
		Game game = new Game("test");
		game.setScreen(new Sudo(game));
		game.run();
	}
	
	Render2D render;
	Game game;
	OrthographicCamera c;
	
	VAO model;
	
	UIRoot root;
	
	public Sudo(Game game) {
		this.game = game;
	}

	@Override
	public void init() {
		this.c = new OrthographicCamera();
		this.c.setDimension(game.getWidth(), game.getHeight(), game.getNearPlane(), game.getFarPlane());
		UI.DISPLAY_SIZE = new Vector2f(game.getWidth(), game.getHeight());
		UI.CAMERA = c;
		
		model = new VAO();
		IBO ibo = new IBO();
		ibo.setData(new int[] {
				0,1,2,2,1,3
		});
		VBO pos = new VBO();
		pos.setData(new float[] {-1,1,0,-1,-1,0,1,1,0,1,-1,0});
		//VBO tc = new VBO();
		//tc.setData(new float[] {0,0,0,1,1,0,1,1});
		model.addIBO(ibo);
		model.addVBO(0, 3, pos);
		//model.addVBO(1, 2, tc);
		
		this.root = new UIRoot();
		
		UIPanel panel1 = new UIPanel();
		panel1.setModel(new Renderable2D(model, new Vector4f(255, 0, 0, 255)));
		panel1.setPosition(new Vector2f(-200f,0));
		
		UIPanel panel1a = new UIPanel();
		panel1a.setModel(new Renderable2D(model, new Vector4f(255, 255, 0, 255)));
		panel1a.setSize(new Vector2f(90, 90));
		panel1.add(panel1a);
		
		UIPanel panel2 = new UIPanel();
		panel2.setModel(new Renderable2D(model, new Vector4f(0, 0, 255, 255)));
		panel2.setPosition(new Vector2f(200, 0));
		
		this.root.add(panel1);
		this.root.add(panel2);
		
		render = new Render2D();
	}

	@Override
	public void update(float delta) {
		c.update(delta);
		render.setCamera(c);
		this.root.update(delta);
		System.out.println(delta);
	}

	@Override
	public void render() {
		RenderUtils.clear(game.getBackgroundColour());
		root.render(render);
	}

	@Override
	public void resize(float width, float height, float pixelWidth, float pixelHeight, float scaleWidth,
			float scaleHeight) {
		UI.DISPLAY_SIZE = new Vector2f(width, height);
		
	}

	@Override
	public void destory() {
		render.dispose();
		model.dispose();
	}

}
