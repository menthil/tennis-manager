package com.escuelait.views.commands;

import com.escuelait.models.Game;
import com.escuelait.models.Match;
import com.escuelait.models.Player;
import com.escuelait.utils.Console;

class ScoreboardView {

  private Match match;
  private Console console;

  ScoreboardView(Match match) {
    assert match != null;
    this.match = match;
    this.console = Console.getInstance();
  }

  void write() {
    for (Player player : this.match.getPlayers()) {
      this.console.writeln(this.getPlayerPoints(player));
    }
    this.writeSpecialPoints();
  }

  private String getPlayerPoints(Player player) {
    return this.getServiceMark(player) + " " + player.name()
        + ": " + this.getGamePoints(player) + this.getPlayerSetGames(player);
  }

  private String getServiceMark(Player player) {
    if (this.match.isFinished()) {
      return "";
    }
    if (this.match.isServing(player)) {
      if (this.match.isLackService()) {
        return "+";
      } else {
        return "*";
      }
    }
    return " ";
  }

  private String getGamePoints(Player player) {
    if (this.match.isGameFinished()) {
      return "0";
    }
    if (this.match.isTieBreak()) {
      return this.match.getGamePoints(player) + "";
    }
    if (this.match.getGamePoints(player) < Game.MIN_POINTS_TO_WIN) {
      return new String[] { "0", "15", "30", "40" }[this.match.getGamePoints(player)];
    }
    if (this.match.getGamePoints(player) > this.match.getGamePoints(this.match.getOther(player))) {
      return "AD";
    }
    return "40";
  }

  private String getPlayerSetGames(Player player) {
    return this.getPlayedSetGames(player) + this.getNonPlayedSetsGames(player);
  }

  private String getPlayedSetGames(Player player) {
    String result = "";
    for (int i = 0; i < this.match.getSetGames(player).size(); i++) {
      result += " ";
      if (this.isZeroZero(player, i)) {
        result += "-";
      } else {
        result += this.match.getSetGames(player).get(i);
      }
    }
    return result;
  }

  private boolean isZeroZero(Player player, int i) {
    return this.match.getSetGames(player).get(i) == 0
        && this.match.getSetGames(this.match.getOther(player)).get(i) == 0;
  }

  private String getNonPlayedSetsGames(Player player) {
    String result = "";
    for (int i = this.match.getSetGames(player).size(); i < this.match.getNumberOfSets(); i++) {
      result += " -";
    }
    return result;
  }

  private void writeSpecialPoints() {
    if (this.match.isGameFinished()) {
      this.console.write("Game ball!!! ");
      if (this.match.isTieBreak() && !this.match.isFinished()) {
        this.console.write("Tie break!!! ");
      }
    }
    if (this.match.isSetFinished()) {
      this.console.write("Set ball!!! ");
    }
    if (this.match.isFinished()) {
      this.console.write("Match finished!!! ");
    }
    this.console.writeln();
  }

}
