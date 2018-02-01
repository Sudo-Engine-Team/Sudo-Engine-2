package site.root3287.sudo2.UI;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.interfaces.Updateable;
import site.root3287.sudo2.text.BitmapFont;

public abstract class UIText extends UIWidget implements Updateable{
	private BitmapFont bmFont;
	
	public UIText(){
		super();
	}
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		super.update(delta);
		this.bmFont.setPosition(this.getAbsolutePosition());
	}

	public BitmapFont getBitmapFont() {
		return bmFont;
	}

	public void setBitmapFont(BitmapFont file) {
		this.bmFont = file;
	}
	
	@Override
	public void setPosition(Vector2f position) {
		// TODO Auto-generated method stub
		super.setPosition(position);
	}
	
	@Override
	public void setScale(Vector2f scale) {
		// TODO Auto-generated method stub
		super.setScale(scale);
		
		this.bmFont.setScale(scale);
	}
	
	public void setText(String text){
		this.bmFont.setText(text);
	}
	
	@Override
	public void setColour(Vector4f colour) {
		// TODO Auto-generated method stub
		super.setColour(colour);
		bmFont.setColour(colour);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		bmFont.getModel().dispose();
	}
}
