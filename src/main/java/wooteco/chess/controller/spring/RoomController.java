package wooteco.chess.controller.spring;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wooteco.chess.dto.PlayerDto;
import wooteco.chess.dto.RoomDto;
import wooteco.chess.dto.req.PlayersDto;
import wooteco.chess.service.BoardService;
import wooteco.chess.service.PlayerService;

@Controller
@RequestMapping("/room")
public class RoomController {
	private final PlayerService playerService;
	private final BoardService boardService;

	public RoomController(PlayerService playerService, BoardService boardService) {
		this.playerService = playerService;
		this.boardService = boardService;
	}

	@GetMapping
	public String createNewGame() {
		return "createNewGame";
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Object> createNewGame(@RequestBody PlayersDto playersDto) {
		try {
			int roomId = createRoom(playersDto);
			boardService.create(roomId);
			return ResponseEntity
				.status(200)
				.body(new RoomDto(roomId, playersDto.getPlayer1Name(), playersDto.getPlayer2Password()));
		} catch (Exception e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public String start(@PathVariable int id, Model model) {
		model.addAttribute("id", id);
		return "play";
	}

	private int createRoom(PlayersDto playersDto) throws SQLException {
		int player1Id = playerService.create(
			new PlayerDto(playersDto.getPlayer1Name(), playersDto.getPlayer1Password(), "white"));
		int player2Id = playerService.create(
			new PlayerDto(playersDto.getPlayer2Name(), playersDto.getPlayer2Password(), "black"));
		return boardService.createRoom(player1Id, player2Id);
	}
}
