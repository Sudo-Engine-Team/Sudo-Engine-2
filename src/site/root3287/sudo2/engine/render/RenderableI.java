package site.root3287.sudo2.engine.render;

public interface RenderableI {
	<T extends Iterable<T>> void render(T t);
	void dispose();
}
