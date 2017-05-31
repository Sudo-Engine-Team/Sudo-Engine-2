package site.root3287.sudo2.entities;

import site.root3287.sudo2.component.functions.TransposeComponent;

public class ProspectiveCamera extends Camera {
	@Override
	public void update(float delta) {
		getComponent(TransposeComponent.class).position.z += 0.01f;
	}
}
