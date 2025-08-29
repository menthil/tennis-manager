package com.escuelait.views.commands;

import java.util.HashMap;

import com.escuelait.models.Game;
import com.escuelait.models.Match;
import com.escuelait.models.Player;
import com.escuelait.utils.Console;
import com.escuelait.utils.DateFormatter;

class ScoreboardView {

  private Match match;
  private Console console;

  ScoreboardView(Match match) {
    this.match = match;
    this.console = Console.getInstance();
  }

  void write() {
    this.console.writeln("id:" + this.match.getId());
    this.console.writeln("date:" + new DateFormatter().format(this.match.getCreationDate()));
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
    String result = "";
    HashMap<Player, int[]> setGames = this.getSetGames();
    for (int j = 0; j < this.match.getNumberOfSets(); j++) {
      boolean isZeroZero = setGames.get(player)[j] == 0 && setGames.get(this.match.getOther(player))[j] == 0;
      result += " " + (isZeroZero ? "-" : setGames.get(player)[j]);
    }
    return result;
  }

  private HashMap<Player, int[]> getSetGames() {
    HashMap<Player, int[]> setGames = new HashMap<>();
    for (Player player : this.match.getPlayers()) {
      setGames.put(player, new int[this.match.getNumberOfSets()]);
      for (int j = 0; j < this.match.getSetGames(player).size(); j++) {
        setGames.get(player)[j] = this.match.getSetGames(player).get(j);
      }
    }
    return setGames;
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
