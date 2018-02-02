package site.root3287.sudo2.display;

public interface Screen {
	void init();
	void update(float delta);
	void render();
	void resize(float width, float height, float pixelWidth, float pixelHeight, float scaleWidth, float scaleHeight);
	void destory();
}
