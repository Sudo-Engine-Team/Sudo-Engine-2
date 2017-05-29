package site.root3287.sudo2;

import site.root3287.sudo2.display.GameLauncher;
import site.root3287.sudo2.logger.LogLevel;
import site.root3287.sudo2.logger.Logger;

public class SudoEngine {
	public static void main(String[] args){
		Logger.log(LogLevel.INFO, "Loading LWJGL natives for "+System.getProperty("os.name"));
		Logger.log(LogLevel.INFO, "System.getProperty('os.name') == " + System.getProperty("os.name"));
		Logger.log(LogLevel.INFO, "System.getProperty('os.version') == " + System.getProperty("os.version"));
		Logger.log(LogLevel.INFO, "System.getProperty('os.arch') == " + System.getProperty("os.arch"));
		Logger.log(LogLevel.INFO, "System.getProperty('java.version') == " + System.getProperty("java.version"));
		Logger.log(LogLevel.INFO, "System.getProperty('java.vendor') == " + System.getProperty("java.vendor"));
		Logger.log(LogLevel.INFO, "System.getProperty('sun.arch.data.model') == " + System.getProperty("sun.arch.data.model"));
		new GameLauncher();
	}
}
