package site.root3287.sudo2.UI;

public class UIUtils {
	private static UIUtils _instance;
	private UIUtils() {
		
	}
	public static UIUtils getInstance() {
		if(_instance == null) {
			_instance = new UIUtils();
		}
		return _instance;
	}
}
