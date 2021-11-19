package object;

public class PlayThread extends Thread {

	private Game game;
	
	public PlayThread(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		this.game.launch();
	}
}
