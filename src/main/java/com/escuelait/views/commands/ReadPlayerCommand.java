package com.escuelait.views.commands;

import java.util.List;
import java.util.Optional;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.ManageController;
import com.escuelait.models.Match;
import com.escuelait.models.Player;
import com.escuelait.utils.DateFormatter;

class ReadPlayerCommand extends Command {

  @Override
  protected String getName() {
    return "readPlayer:(\\d+)";
  }

  @Override
  protected String getRegex() {
    return "";
  }

  @Override
  String getSyntax() {
    return "readPlayer:<id>";
  }

  @Override
  void execute(Controller controller, String prompt) {
    int id = Integer.parseInt(this.getArg(prompt, 0));
    ManageController manageController = (ManageController) controller;
    Optional<Player> player = manageController.getPlayer(id);
    if (player.isPresent()) {
      this.write(player.get(), manageController.getMatchesByPlayerId(id));
    } else {
      this.console.writeln("El id del jugador no existe: " + id);
    }
  }

  private void write(Player player, List<Match> matches) {
    this.console.writeln("name:" + player.name() + "; id:" + player.id());
    for (Match match : matches) {
      this.write(match, player);
    }
    String winRate = matches.isEmpty() ? "-" : String.format("%.0f%%", this.calculateWinrate(matches, player));
    this.console.writeln("winner:" + winRate);
  }

  private void write(Match match, Player player) {
    this.console.writeln(this.getMatchId(match)
        + this.getDate(match)
        + this.getName(match, player)
        + this.getSets(match, player)
        + this.isWinner(match, player));
  }

  private String getMatchId(Match match) {
    return "match id:" + match.getId() + "; ";
  }

  private String getDate(Match match) {
    return "date:" + new DateFormatter().format(match.getCreationDate()) + "; ";
  }

  private String getName(Match match, Player player) {
    return "name:" + match.getOther(player).name() + "; ";
  }

  private String getSets(Match match, Player player) {
    return "sets:" + match.getSetsWon(player) + "/" + match.getSetsWon(match.getOther(player)) + "; ";
  }

  private String isWinner(Match match, Player player) {
    return match.isWinner(player) ? "winner" : "looser";
  }

  private float calculateWinrate(List<Match> matches, Player player) {
    return (float) this.calculateMatchesWon(matches, player) * 100 / matches.size();
  }

  private int calculateMatchesWon(List<Match> matches, Player player) {
    int result = 0;
    for (Match match : matches) {
      result += match.isWinner(player) ? 1 : 0;
    }
    return result;
  }

}
