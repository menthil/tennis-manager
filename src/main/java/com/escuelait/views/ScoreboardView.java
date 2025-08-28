package com.escuelait.views;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.escuelait.models.Score;
import com.escuelait.utils.Console;

class ScoreboardView {

  private Score score;
  private Console console;

  ScoreboardView(Score score) {
    this.score = score;
    this.console = Console.getInstance();
  }

  void write() {
    this.console.writeln("id:" + this.score.getId());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.of("ES"));
    this.console.writeln("date:" + this.score.getCreationDate().format(formatter));
    int[][] setGames = this.score.getSetGames();
    String playersPoints[] = new String[setGames.length];
    for (int i = 0; i < playersPoints.length; i++) {
      playersPoints[i] = this.score.getServices()[i];
      playersPoints[i] += " " + this.score.getNames()[i] + ":";
      playersPoints[i] += " " + this.score.getGamePoints()[i];
    }
    for (int j = 0; j < setGames[0].length; j++) {
      boolean isZeroZero = setGames[0][j] == 0 && setGames[1][j] == 0;
      for (int i = 0; i < playersPoints.length; i++) {
        playersPoints[i] += " " + (isZeroZero ? "-" : setGames[i][j]);
      }
    }
    for (String playerPoints : playersPoints) {
      this.console.writeln(playerPoints);
    }
  }

}
