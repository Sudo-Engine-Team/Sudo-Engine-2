package site.root3287.sudo2.ui.elements;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.render.Render2D;
import site.root3287.sudo2.ui.UIElement;

public class UIPanel extends UIElement{
	private Vector2f padding;
	private float roundingRadius = 0;
	
	@Override
	public void update(float delta) {
		model.setPosition(getAbsolutePosition());
		for(UIElement element : this.children) {
			element.update(delta);
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHover() {
		System.out.println("Hovering");
	}

	@Override
	public void offHover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Render2D render) {
		if(this.isVisable)
			render.add(model);
		
		for(UIElement elements : children) {
			elements.render(render);
		}
	}

	public Vector2f getPadding() {
		return padding;
	}

	public void setPadding(Vector2f padding) {
		this.padding = padding;
	}

	public float getRoundingRadius() {
		return roundingRadius;
	}

	public void setRoundingRadius(float roundingRadius) {
		this.roundingRadius = roundingRadius;
	}
}
