package site.root3287.sudo2.net.server;

public class Command {
	private Runnable run;
	private String[] args;
	public Command(String[] args, Runnable run) {
		
	}
	public String[] getArgs() {
		return args;
	}
	public void setArgs(String[] args) {
		this.args = args;
	}
	public Runnable getRun() {
		return run;
	}
	public void setRun(Runnable run) {
		this.run = run;
	}
}
