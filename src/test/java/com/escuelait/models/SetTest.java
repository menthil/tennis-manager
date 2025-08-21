package com.escuelait.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SetTest {

  private SetBuilder setBuilder;
  private Set set;

  @Before
  public void before() {
    this.setBuilder = new SetBuilder();
    this.set = this.setBuilder.build();
  }

  @Test
  public void when_set_starts_result_is_zero_to_zero() {
    assertEquals(0, this.set.getGamesWon(this.setBuilder.getFirstPlayer()));
    assertEquals(0, this.set.getGamesWon(this.setBuilder.getSecondPlayer()));
  }

  @Test
  public void when_first_player_wins_a_game_result_is_one_to_zero() {
    this.firstPlayerWinsGame(0);
    assertEquals(1, this.set.getGamesWon(this.setBuilder.getFirstPlayer()));
    assertEquals(0, this.set.getGamesWon(this.setBuilder.getSecondPlayer()));
  }

  @Test
  public void when_second_player_wins_a_game_result_is_zero_to_one() {
    this.secondPlayerWinsGame(0);
    assertEquals(0, this.set.getGamesWon(this.setBuilder.getFirstPlayer()));
    assertEquals(1, this.set.getGamesWon(this.setBuilder.getSecondPlayer()));
  }

  @Test
  public void when_player_dont_wins_at_least_six_games_then_set_is_not_finished() {
    this.alternateGamesWinning(5, 1);
    assertFalse(this.set.isFinished());
    assertEquals(5, this.set.getGamesWon(this.setBuilder.getFirstPlayer()));
    assertEquals(1, this.set.getGamesWon(this.setBuilder.getSecondPlayer()));
  }

  @Test
  public void when_first_player_wins_six_games_with_two_more_games_than_other_player_then_wins_set() {
    this.alternateGamesWinning(6, 4);
    assertTrue(this.set.isFinished());
    assertEquals(6, this.set.getGamesWon(this.setBuilder.getFirstPlayer()));
    assertEquals(4, this.set.getGamesWon(this.setBuilder.getSecondPlayer()));
  }

  @Test
  public void when_second_player_wins_six_games_with_two_more_games_than_other_player_then_wins_set() {
    this.alternateGamesWinning(5, 7);
    assertTrue(this.set.isFinished());
    assertEquals(5, this.set.getGamesWon(this.setBuilder.getFirstPlayer()));
    assertEquals(7, this.set.getGamesWon(this.setBuilder.getSecondPlayer()));
  }

  @Test
  public void when_players_draw_at_six_games_and_first_player_wins_next_game_then_first_player_wins() {
    this.alternateGamesWinning(7, 6);
    assertEquals(TieBreakGame.MIN_POINTS_TO_WIN, this.set.getMinPointsToWinGame());
    assertTrue(this.set.isFinished());
  }

  @Test
  public void when_players_draw_at_six_games_and_second_player_wins_next_game_then_second_player_wins() {
    this.alternateGamesWinning(6, 7);
    assertEquals(TieBreakGame.MIN_POINTS_TO_WIN, this.set.getMinPointsToWinGame());
    assertTrue(this.set.isFinished());
  }

  private void alternateGamesWinning(int firstPlayerGames, int secondPlayerGames) {
    for (int gameNumber = 0; gameNumber < firstPlayerGames + secondPlayerGames; gameNumber++) {
      if (gameNumber % 2 == 0) {
        if (gameNumber / 2 < firstPlayerGames) {
          this.firstPlayerWinsGame(gameNumber);
        } else {
          this.secondPlayerWinsGame(gameNumber);
        }
      } else {
        if (gameNumber / 2 < secondPlayerGames) {
          this.secondPlayerWinsGame(gameNumber);
        } else {
          this.firstPlayerWinsGame(gameNumber);
        }
      }
    }
  }

  private void firstPlayerWinsGame(int gameNumber) {
    int minPointsToWinGame = this.set.getMinPointsToWinGame();
    for (int i = 0; i < minPointsToWinGame; i++) {
      if (gameNumber % 2 == 0) {
        this.set.addPointService();
      } else {
        this.set.addPointRest();
      }
    }
  }

  private void secondPlayerWinsGame(int gameNumber) {
    int minPointsToWinGame = this.set.getMinPointsToWinGame();
    for (int i = 0; i < minPointsToWinGame; i++) {
      if (gameNumber % 2 == 0) {
        this.set.addPointRest();
      } else {
        this.set.addPointService();
      }
    }
  }

}
