package wooteco.chess.domain;

import wooteco.chess.domain.state.Score;

public class Status {
	private final Score white;
	private final Score black;

	public Status(Score white, Score black) {
		this.white = white;
		this.black = black;
	}

	public Team winner() {
		if (white.isBiggerThan(black)) {
			return Team.WHITE;
		}
		if (black.isBiggerThan(white)) {
			return Team.BLACK;
		}
		return Team.NONE;
	}

	public double getWhite() {
		return white.getScore();
	}

	public double getBlack() {
		return black.getScore();
	}
}
