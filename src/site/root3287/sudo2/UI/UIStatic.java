package site.root3287.sudo2.UI;

import site.root3287.sudo2.text.BitmapFont;

public abstract class UIStatic extends UIWidget {
	private BitmapFont text;

	public BitmapFont getText() {
		return text;
	}

	public void setText(BitmapFont text) {
		this.text = text;
	}
}
