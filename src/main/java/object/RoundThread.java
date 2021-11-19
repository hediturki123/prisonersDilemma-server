package object;

public class RoundThread extends Thread{

	private Round round;
	private Game game;
	
	public RoundThread(Round round, Game game) {
		this.round = round;
		this.game = game;
	}
	
	@Override
	public void run() {
		this.round.playRound(game);
	}
}
