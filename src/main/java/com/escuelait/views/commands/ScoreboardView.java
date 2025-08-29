package com.escuelait.views.commands;

import com.escuelait.models.Game;
import com.escuelait.models.Score;
import com.escuelait.utils.Console;
import com.escuelait.utils.DateFormatter;

class ScoreboardView {

  private Score score;
  private Console console;

  ScoreboardView(Score score) {
    this.score = score;
    this.console = Console.getInstance();
  }

  void write() {
    this.console.writeln("id:" + this.score.getId());
    this.console.writeln("date:" + new DateFormatter().format(this.score.getCreationDate()));
    for (int i = 0; i < 2; i++) {
      this.console.writeln(this.getPlayerPoints(i));
    }
    this.writeSpecialPoints();
  }

  private String getPlayerPoints(int i) {
    return this.getServiceMark(i) + " " + this.score.getNames()[i]
        + ": " + this.getGamePoints(i) + this.getPlayerSetGames(i);
  }

  private String getServiceMark(int i) {
    return this.score.isMatchFinished() ? "" : this.score.getServices()[i];
  }

  private String getPlayerSetGames(int i) {
    String result = "";
    int[][] setGames = this.score.getSetGames();
    for (int j = 0; j < setGames[i].length; j++) {
      boolean isZeroZero = setGames[0][j] == 0 && setGames[1][j] == 0;
      result += " " + (isZeroZero ? "-" : setGames[i][j]);
    }
    return result;
  }

  private String getGamePoints(int i) {
    if (this.score.isGameFinished()) {
      return "0";
    }
    if (this.score.isTieBreak()) {
      return this.score.getGamePoints()[i] + "";
    }
    if (this.score.getGamePoints()[i] < Game.MIN_POINTS_TO_WIN) {
      return new String[] { "0", "15", "30", "40" }[this.score.getGamePoints()[i]];
    }
    if (this.score.getGamePoints()[i] > this.score.getGamePoints()[(i + 1) % 2]) {
      return "AD";
    }
    return "40";
  }

  private void writeSpecialPoints() {
    if (this.score.isGameFinished()) {
      this.console.write("Game ball!!! ");
      if (this.score.isTieBreak() && !this.score.isMatchFinished()) {
        this.console.write("Tie break!!! ");
      }
    }
    if (this.score.isSetFinished()) {
      this.console.write("Set ball!!! ");
    }
    if (this.score.isMatchFinished()) {
      this.console.write("Match finished!!! ");
    }
    this.console.writeln();
  }

}
