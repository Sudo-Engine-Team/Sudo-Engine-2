package site.root3287.sudo2.ui.elements;

import site.root3287.sudo2.engine.render.Render2D;
import site.root3287.sudo2.ui.UIElement;

public class UIRoot extends UIElement {
	
	public UIRoot() {
		this.root = this;
		this.parent = null;
	}
	
	@Override
	public void update(float delta) {
		for(UIElement element : children) {
			element.update(delta);
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHover() {
		// TODO Auto-generated method stub

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
		for(UIElement element : this.children) {
			//Queue all children to render.
			element.render(render);
		}
		//Render all children.
		render.render();
	}
	
	@Override
	public void add(UIElement element) {
		// TODO Auto-generated method stub
		super.add(element);
		element.setParent(null);
	}

}
