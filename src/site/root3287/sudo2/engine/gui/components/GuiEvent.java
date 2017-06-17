package site.root3287.sudo2.engine.gui.components;

public interface GuiEvent {
	void onClick();
	void onHover();
	void onLeave();
	void show();
	void hide();
	void whileHovering();
	void update(float delta);
}
