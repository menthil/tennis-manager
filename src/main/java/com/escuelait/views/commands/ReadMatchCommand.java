package com.escuelait.views.commands;

import java.util.List;
import java.util.Optional;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.ManageController;
import com.escuelait.models.Match;
import com.escuelait.models.Player;
import com.escuelait.utils.DateFormatter;

class ReadMatchCommand extends Command {

  @Override
  protected String getName() {
    return "readMatch:(\\d+)";
  }

  @Override
  protected String getRegex() {
    return "";
  }

  @Override
  String getSyntax() {
    return "readMatch:<id>";
  }

  @Override
  void execute(Controller controller, String prompt) {
    int id = Integer.parseInt(this.getArg(prompt, 0));
    ManageController manageController = (ManageController) controller;
    Optional<Match> match = manageController.getMatch(id);
    if (match.isPresent()) {
      this.write(match.get());
    } else {
      this.console.writeln("El id de la partida no existe: " + id);
    }
  }

  private void write(Match match) {
    this.console.writeln(this.getDate(match) + this.getSets(match));
    this.writePlayers(match);
  }

  private String getDate(Match match) {
    return "date:" + new DateFormatter().format(match.getCreationDate()) + "; ";
  }

  private String getSets(Match match) {
    return "sets:" + match.getNumberOfSets();
  }

  private void writePlayers(Match match) {
    for (Player player : match.getPlayers()) {
      this.console.writeln(
          "name:" + player.name() + "; id:" + player.id() + "; sets:" + this.getGames(match.getSetGames(player)));
    }
  }

  private String getGames(List<Integer> gamesPerSet) {
    String result = "";
    for (int games : gamesPerSet) {
      result += " " + games;
    }
    return result.trim();
  }

}
