package wooteco.chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wooteco.chess.domain.Team;
import wooteco.chess.domain.Turn;
import wooteco.chess.domain.piece.king.King;
import wooteco.chess.domain.piece.pawn.Pawn;
import wooteco.chess.domain.piece.queen.Queen;
import wooteco.chess.domain.position.Position;

class PieceTest {
	@Test
	@DisplayName("자기 턴인 경우 정상적으로 True를 반환합니다.")
	void isTurn() {
		King instance = King.of(Team.WHITE, Position.of("a1"));
		assertThat(instance.isTurn(new Turn(Team.WHITE))).isTrue();
	}

	@Test
	@DisplayName("자신의 턴이 아닌 경우 정상적으로 False를 반환합니다.")
	void isNotTurn() {
		King instance = King.of(Team.WHITE, Position.of("a1"));
		assertThat(instance.isTurn(new Turn(Team.BLACK))).isFalse();
	}

	@Test
	@DisplayName("자신이 가지고 있는 Team을 정상반환합니다.")
	void getTeam() {
		King instance = King.of(Team.WHITE, Position.of("a1"));
		assertThat(instance.getTeam()).isEqualTo(Team.WHITE);
	}

	@Test
	@DisplayName("킹인지 아닌지를 판단하는 메소드로 킹인 경우 True를 반환합니다.")
	void isKing() {
		King instance = King.of(Team.WHITE, Position.of("a1"));
		assertThat(instance.isKing()).isTrue();
	}

	@Test
	@DisplayName("킹이 아닌 것으로 isKing을 호출하는 경우 False를 반환합니다.")
	void isNotKing() {
		Queen instance = Queen.of(Team.WHITE, Position.of("a1"));
		assertThat(instance.isKing()).isFalse();
	}

	@Test
	@DisplayName("폰인 경우에만 isPawn이 True를 반환합니다.")
	void isPawnTest() {
		Pawn pawn = Pawn.of(Team.WHITE, Position.of("a1"));
		King king = King.of(Team.WHITE, Position.of("a1"));
		assertThat(pawn.isPawn()).isTrue();
		assertThat(king.isPawn()).isFalse();
	}
}