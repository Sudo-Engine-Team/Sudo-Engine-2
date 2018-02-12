package site.root3287.sudo2.UI;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.events.event.KeyboardPressedEvent;
import site.root3287.sudo2.text.BitmapFontFile;
import site.root3287.sudo2.utils.Input;

public abstract class UITextbox extends UIButton {
	private boolean isActive = false;
	private boolean shouldStayOn = false;
	private Vector4f activeColour = new Vector4f();
	private Vector4f deactiveColour = new Vector4f();
	private BitmapFontFile file; 
	private String text ="";
	
	public UITextbox(BitmapFontFile file){
		super();
		setScale(new Vector2f(400, 50));
		UIBlinker b = new UIBlinker(this);
		getChildren().add(b);
		this.file = file;
		UIText t = new UIText("", file) {
		};
		t.setScale(new Vector2f(200,200));
		add(t);
		
		Input.Keyboard.addKeyPressedListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				boolean change = true;
				if(!(e instanceof KeyboardPressedEvent) || !isActive){
					return false;
				}
				KeyboardPressedEvent e1 = (KeyboardPressedEvent)e;
				System.out.println(e1.getKey());
				if(e1.getKey() >= 340 || e1.getKey() >= 290)
					return false;
				if(e1.getKey() == 256){
					isActive = false;
					setColour(deactiveColour);
					getChildren().get(0).setVisable(false);
					return false;
				}
				
				if(e1.getKey() == 259){
					if(text.length()-1 > -1){
						text = text.substring(0, text.length()-1);
						((UIBlinker)getChildren().get(0)).advance(-10);
					}else{
						change = false;
					}
				}else if(e1.getKey() == 257){
					//text = text+"\n";
					return false;
				}else{
					text = text + Input.Keyboard.glfwToChar(e1.getKey());
					((UIBlinker)getChildren().get(0)).advance(10);
				}
				if(change)
					t.setText(text);
				return true;
			}
		});
	}
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		super.update(delta);
		if(Input.Mouse.isMousePressed(GLFW.GLFW_MOUSE_BUTTON_1) && isActive && !shouldStayOn){
			isActive = false;
			setColour(deactiveColour);
			getChildren().get(0).setVisable(false);
			deactivate();
		}
		shouldStayOn = false;
	}
	
	@Override
	public void onClick() {
		isActive = true;
		shouldStayOn = true;
		setColour(activeColour);
		getChildren().get(0).setVisable(true);
		activate();
	}
	
	public boolean isActive(){
		return this.isActive;
	}
	
	public Vector4f getActiveColour() {
		return activeColour;
	}

	public void setActiveColour(Vector4f activeColour) {
		this.activeColour = activeColour;
	}

	public Vector4f getDeactiveColour() {
		return deactiveColour;
	}

	public void setDeactiveColour(Vector4f deactiveColour) {
		this.deactiveColour = deactiveColour;
	}
	
	public abstract void deactivate();
	public abstract void activate();
	
	public BitmapFontFile getBitmapFontFile(){
		return file;
	}

	private static class UIBlinker extends UIWidget{
		private float time;
		public UIBlinker(UIWidget parent) {
			super();
			this.setParent(parent);
			time = 0;
			setColour(new Vector4f(0, 0, 0, 1));
			Vector2f pScale = getParent().getScale();
			setScale(new Vector2f(pScale.x/100, pScale.y-30));
			this.setVisable(false);
		}
		
		@Override
		public void update(float delta) {
			// TODO Auto-generated method stub
			super.update(delta);
			if(time > 0.75 && ((UITextbox)getParent()).isActive){
				this.setVisable(isVisable()?false:true);
				time = 0;
			}
			time+=delta;
		}
		
		public void advance(float d){
			Vector2f pos = getPosition();
			setPosition(new Vector2f(pos.x+d, pos.y));
		}
	}
}
