package site.root3287.sudo2.entities;

import site.root3287.sudo2.component.functions.FirstPersonProspectivePlayerControls;

public class ProspectiveCamera extends Camera {
	
	public ProspectiveCamera() {
		addComponent(new FirstPersonProspectivePlayerControls(this.id));
	}
	
	@Override
	public void update(float delta) {
		getComponent(FirstPersonProspectivePlayerControls.class).update(delta);
	}
}
