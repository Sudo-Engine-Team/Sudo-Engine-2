package site.root3287.sudo2.entities;

import site.root3287.sudo2.component.functions.TransposeComponent;

public class Camera extends Entity{

	public Camera() {
		addComponent(new TransposeComponent());
	}
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
