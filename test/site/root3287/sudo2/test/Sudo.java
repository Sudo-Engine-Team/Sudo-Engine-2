package site.root3287.sudo2.test;

import org.lwjgl.util.vector.Vector2f;

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
import site.root3287.sudo2.ui.UIElement;

public class Sudo implements Screen{
	
	public static void main(String[] args) {
		Game game = new Game("test");
		game.setScreen(new Sudo(game));
		game.run();
	}
	
	Render2D render;
	Game game;
	OrthographicCamera c;
	
	Renderable2D obj;
	
	UIElement element;
	
	public Sudo(Game game) {
		this.game = game;
	}

	@Override
	public void init() {
		this.c = new OrthographicCamera();
		this.c.setDimension(game.getWidth(), game.getHeight(), game.getNearPlane(), game.getFarPlane());
		UI.DISPLAY_SIZE = new Vector2f(game.getWidth(), game.getHeight());
		UI.CAMERA = c;
		
		obj = new Renderable2D();
		
		VAO model = new VAO();
		IBO ibo = new IBO();
		ibo.setData(new int[] {
				0,1,2,2,1,3
		});
		VBO pos = new VBO();
		pos.setData(new float[] {-1,1,0,-1,-1,0,1,1,0,1,-1,0});
		model.addIBO(ibo);
		model.addVBO(0, 3, pos);
		
		obj.setModel(model);
		obj.setScale(new Vector2f(200,100));
		
		render = new Render2D();
		
		element = new UIElement() {
			
			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void update(float delta) {
				mouseUpdate();
			}
			
			@Override
			public void onHover() {
				System.out.println("On Hovering");
			}
			
			@Override
			public void onClick() {
				System.out.println("Click");
			}
			
			@Override
			public void offHover() {
				System.out.println("Off Hovering");
				
			}
			
			@Override
			public void hover() {
				System.out.println("Hovering");
			}

			@Override
			public void render(Render2D render) {
				
			}
		};
		
		element.setSize(new Vector2f(200,100));
	}

	@Override
	public void update(float delta) {
		c.update(delta);
		element.update(delta);
		render.setCamera(c);
	}

	@Override
	public void render() {
		RenderUtils.clear(game.getBackgroundColour());
		render.render(obj);
	}

	@Override
	public void resize(float width, float height, float pixelWidth, float pixelHeight, float scaleWidth,
			float scaleHeight) {
		UI.DISPLAY_SIZE = new Vector2f(width, height);
		
	}

	@Override
	public void destory() {
		render.dispose();
		obj.getModel().dispose();
	}

}
