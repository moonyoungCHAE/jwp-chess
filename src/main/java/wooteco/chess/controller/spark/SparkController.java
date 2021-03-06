package wooteco.chess.controller.spark;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import wooteco.chess.domain.position.Position;
import wooteco.chess.dto.GameDto;
import wooteco.chess.dto.PlayerDto;
import wooteco.chess.service.BoardService;
import wooteco.chess.service.PlayerService;

public class SparkController {
	private final Gson gson = new GsonBuilder().create();
	private final BoardService boardService = new BoardService();
	private final PlayerService playerService = new PlayerService();

	public void route() {
		Spark.staticFileLocation("/templates");
		post("/createNewGame", this::createNewGame);
		get("/startGame/:id", this::startGame);
		get("/loadBoard/:id", this::loadBoard);
		post("/move/:id", this::move);
	}

	private String createNewGame(Request request, Response response) {
		try {
			int roomId = createPlayers(request);
			boardService.create(roomId);
			HashMap<String, Object> model = new HashMap<>();
			model.put("id", roomId);
			return gson.toJson(model);
		} catch (Exception e) {
			response.status(500);
			return gson.toJson(e.getMessage());
		}
	}

	private int createPlayers(Request request) throws SQLException {
		Map<String, String> params = new HashMap<>();
		params = gson.fromJson(request.body(), params.getClass());
		int player1Id = playerService.create(
			new PlayerDto(params.get("player1Name"), params.get("player1Password"), "white"));
		int player2Id = playerService.create(
			new PlayerDto(params.get("player2Name"), params.get("player2Password"), "black"));
		return boardService.createRoom(player1Id, player2Id);
	}

	private String startGame(Request request, Response response) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", request.params(":id"));
		return render(map, "play.hbs");
	}

	private String loadBoard(Request request, Response response) {
		try {
			int id = Integer.parseInt(request.params(":id"));
			return gson.toJson(boardService.load(id));
		} catch (Exception e) {
			response.status(500);
			return gson.toJson(e.getMessage());
		}
	}

	private String move(Request request, Response response) {
		try {
			int id = Integer.parseInt(request.params(":id"));
			Map<String, Integer> map = gson.fromJson(request.body(), new TypeToken<Map<String, Integer>>() {
			}.getType());
			Position source = Position.of(map.get("sourceX"), map.get("sourceY"));
			Position target = Position.of(map.get("targetX"), map.get("targetY"));
			GameDto dto = boardService.move(id, source, target);
			return gson.toJson(dto);
		} catch (Exception e) {
			response.status(500);
			return gson.toJson(e.getMessage());
		}
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}

