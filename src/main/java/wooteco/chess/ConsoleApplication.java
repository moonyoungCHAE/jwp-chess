package wooteco.chess;

import wooteco.chess.controller.ConsoleController;
import wooteco.chess.domain.ChessGame;
import wooteco.chess.domain.state.Ready;

public class ConsoleApplication {
	public static void main(String[] args) {
		ConsoleController controller = new ConsoleController(new ChessGame(new Ready()));
		controller.run();
	}
}
