package site.root3287.sudo2;

import site.root3287.sudo2.display.DisplayManager;
import site.root3287.sudo2.display.GameLauncher;
import site.root3287.sudo2.display.screens.TestScreen;

public class SudoEngine {
	public static void main(String[] args){
		DisplayManager.SCREEN = new TestScreen();
		new GameLauncher();
	}
}
