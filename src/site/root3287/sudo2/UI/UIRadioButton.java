package site.root3287.sudo2.UI;

import java.util.ArrayList;
import java.util.List;

public abstract class UIRadioButton extends UIToggleButton {
	
	private List<UIRadioButton> buttons = new ArrayList<>();
	
	public UIRadioButton(){
		super();
	}

	@Override
	public void onClick() {
		super.onClick();
		for(UIRadioButton b : buttons){
			if(b.isToggled())
				b.setToggled(false);
		}
	}
	
	public void addButton(UIRadioButton b){
		if(buttons.contains(b) || b == this)
			return;
		buttons.add(b);
		for(UIRadioButton c : buttons){
			c.addButton(b);
		}
		b.addButton(this);
	}
}
