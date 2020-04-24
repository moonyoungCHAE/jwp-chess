package wooteco.chess.domain.state;

import wooteco.chess.domain.Board;
import wooteco.chess.domain.Team;
import wooteco.chess.domain.piece.Piece;
import wooteco.chess.domain.piece.bishop.Bishop;
import wooteco.chess.domain.piece.blank.Blank;
import wooteco.chess.domain.piece.king.King;
import wooteco.chess.domain.piece.knight.Knight;
import wooteco.chess.domain.piece.pawn.Pawn;
import wooteco.chess.domain.piece.queen.Queen;
import wooteco.chess.domain.piece.rook.Rook;
import wooteco.chess.domain.position.Position;

import java.util.TreeMap;

public class BoardRepository {
	private static final String BLACK_PAWN = "2";
	private static final String WHITE_PAWN = "7";

	public static Board create() {
		TreeMap<Position, Piece> initialBoard = new TreeMap<>();
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				initialBoard.put(Position.of(i, j), new Blank(Position.of(i, j)));
			}
		}
		for (char i = 'a'; i <= 'h'; i++) {
			initialBoard.put(Position.of(i + BLACK_PAWN),
				Pawn.of(Team.WHITE, Position.of((char)i + BLACK_PAWN)));
			initialBoard.put(Position.of((char)i + WHITE_PAWN),
				Pawn.of(Team.BLACK, Position.of((char)i + WHITE_PAWN)));
		}
		initialBoard.put(Position.of("a1"), Rook.of(Team.WHITE, Position.of("a1")));
		initialBoard.put(Position.of("b1"), Knight.of(Team.WHITE, Position.of("b1")));
		initialBoard.put(Position.of("c1"), Bishop.of(Team.WHITE, Position.of("c1")));
		initialBoard.put(Position.of("d1"), Queen.of(Team.WHITE, Position.of("d1")));
		initialBoard.put(Position.of("e1"), King.of(Team.WHITE, Position.of("e1")));
		initialBoard.put(Position.of("f1"), Bishop.of(Team.WHITE, Position.of("f1")));
		initialBoard.put(Position.of("g1"), Knight.of(Team.WHITE, Position.of("g1")));
		initialBoard.put(Position.of("h1"), Rook.of(Team.WHITE, Position.of("h1")));

		initialBoard.put(Position.of("a8"), Rook.of(Team.BLACK, Position.of("a8")));
		initialBoard.put(Position.of("b8"), Knight.of(Team.BLACK, Position.of("b8")));
		initialBoard.put(Position.of("c8"), Bishop.of(Team.BLACK, Position.of("c8")));
		initialBoard.put(Position.of("d8"), Queen.of(Team.BLACK, Position.of("d8")));
		initialBoard.put(Position.of("e8"), King.of(Team.BLACK, Position.of("e8")));
		initialBoard.put(Position.of("f8"), Bishop.of(Team.BLACK, Position.of("f8")));
		initialBoard.put(Position.of("g8"), Knight.of(Team.BLACK, Position.of("g8")));
		initialBoard.put(Position.of("h8"), Rook.of(Team.BLACK, Position.of("h8")));

		return new Board(initialBoard);
	}
}
