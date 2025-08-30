package com.escuelait.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.escuelait.models.Match;
import com.escuelait.models.Player;
import com.escuelait.models.State;
import com.escuelait.repositories.MatchRepository;
import com.escuelait.repositories.PlayerRepository;

public class ManageController extends Controller {

  private static final int MIN_LENGTH = 4;
  private PlayerRepository playerRepository;
  private MatchRepository matchRepository;

  ManageController(State state, PlayerRepository playerRepository, MatchRepository matchRepository) {
    super(state);
    this.playerRepository = playerRepository;
    this.matchRepository = matchRepository;
  }

  @Override
  public void accept(ControllerVisitor visitor) {
    assert visitor != null;
    visitor.visit(this);
  }

  @Override
  public List<CommandType> getAvailableCommandTypes() {
    return List.of(
        CommandType.CREATE_PLAYER,
        CommandType.READ_PLAYERS,
        CommandType.CREATE_MATCH,
        CommandType.READ_PLAYER,
        CommandType.READ_MATCH,
        CommandType.LOGOUT,
        CommandType.HELP,
        CommandType.EXIT);
  }

  public List<String> getCreatePlayerErrors(String name) {
    assert name != null;
    if (name.length() < ManageController.MIN_LENGTH) {
      return List.of("La longitud del nombre debe ser al menos " + ManageController.MIN_LENGTH);
    }
    if (this.playerRepository.findByName(name).isPresent()) {
      return List.of("El jugador ya existe");
    }
    return List.of();
  }

  public void createPlayer(String name) {
    assert this.getCreatePlayerErrors(name).isEmpty();
    this.playerRepository.create(name);
  }

  public List<Player> getPlayers() {
    return this.playerRepository.findAll();
  }

  public List<String> getCreateMatchErrors(int numberOfSets, int[] ids) {
    assert ids != null && ids.length == 2;
    if (!Match.VALID_NUMBER_OF_SETS.contains(numberOfSets)) {
      List<String> validNumberOfSets = Match.VALID_NUMBER_OF_SETS.stream().map(i -> i.toString()).toList();
      return List.of("Número de sets no válido. Opciones válidas: " + String.join(", ", validNumberOfSets));
    }
    for (int id : ids) {
      if (this.getPlayer(id).isEmpty()) {
        return List.of("El id del jugador no existe: " + id);
      }
    }
    if (ids[0] == ids[1]) {
      return List.of("Un jugador no puede jugar contra sí mismo");
    }
    return List.of();
  }

  public void createMatch(int numberOfSets, int[] ids) {
    assert this.getCreateMatchErrors(numberOfSets, ids).isEmpty();
    Match match = this.matchRepository.create(numberOfSets, this.getPlayers(ids));
    this.state.matchStarted(match);
  }

  private List<Player> getPlayers(int[] ids) {
    ArrayList<Player> players = new ArrayList<>();
    for (int id : ids) {
      players.add(this.getPlayer(id).get());
    }
    return players;
  }

  public Optional<Player> getPlayer(int id) {
    return this.playerRepository.findById(id);
  }

  public List<Match> getMatchesByPlayerId(int id) {
    Optional<Player> player = this.playerRepository.findById(id);
    assert player.isPresent();
    return this.matchRepository.findByPlayer(player.get());
  }

  public Optional<Match> getMatch(int id) {
    return this.matchRepository.findById(id);
  }

}
