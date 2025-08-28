package com.escuelait.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SetTest {

  private Turn turn;
  private Set set;
  private int firstPlayerGames;
  private int secondPlayerGames;

  @Before
  public void before() {
    this.turn = new TurnBuilder().build();
    this.set = new SetBuilder().turn(this.turn).build();
    this.firstPlayerGames = 0;
    this.secondPlayerGames = 0;
  }

  @Test
  public void when_set_starts_result_is_zero_to_zero() {
    this.firstPlayerGames = 0;
    this.secondPlayerGames = 0;
    assertEquals(this.firstPlayerGames, this.set.getGamesWon(this.turn.getFirstPlayer()));
    assertEquals(this.secondPlayerGames, this.set.getGamesWon(this.turn.getSecondPlayer()));
  }

  @Test
  public void when_first_player_wins_a_game_result_is_one_to_zero() {
    this.firstPlayerGames = 1;
    this.secondPlayerGames = 0;
    this.alternateGamesWinning();
    assertEquals(this.firstPlayerGames, this.set.getGamesWon(this.turn.getFirstPlayer()));
    assertEquals(this.secondPlayerGames, this.set.getGamesWon(this.turn.getSecondPlayer()));
  }

  @Test
  public void when_second_player_wins_a_game_result_is_zero_to_one() {
    this.firstPlayerGames = 0;
    this.secondPlayerGames = 1;
    this.alternateGamesWinning();
    assertEquals(this.firstPlayerGames, this.set.getGamesWon(this.turn.getFirstPlayer()));
    assertEquals(this.secondPlayerGames, this.set.getGamesWon(this.turn.getSecondPlayer()));
  }

  @Test
  public void when_player_dont_wins_at_least_six_games_then_set_is_not_finished() {
    this.firstPlayerGames = Set.MIN_GAMES_TO_WIN - 1;
    this.secondPlayerGames = 1;
    this.alternateGamesWinning();
    assertFalse(this.set.isFinished());
  }

  @Test
  public void when_players_draw_at_six_games_then_tie_break_starts() {
    this.firstPlayerGames = Set.MIN_GAMES_TO_WIN;
    this.secondPlayerGames = Set.MIN_GAMES_TO_WIN - 1;
    this.alternateGamesWinning();
    assertFalse(this.set.isTieBreak());
    this.playerWinsGame(this.turn.getSecondPlayer());
    assertTrue(this.set.isTieBreak());
  }

  @Test
  public void when_first_player_wins_six_games_with_two_more_games_than_other_player_then_wins_set() {
    this.firstPlayerGames = Set.MIN_GAMES_TO_WIN;
    this.secondPlayerGames = Set.MIN_GAMES_TO_WIN - 2;
    this.alternateGamesWinning();
    assertTrue(this.set.isFinished());
    assertTrue(this.set.isWinner(this.turn.getFirstPlayer()));
  }

  @Test
  public void when_second_player_wins_six_games_with_two_more_games_than_other_player_then_wins_set() {
    this.firstPlayerGames = Set.MIN_GAMES_TO_WIN - 1;
    this.secondPlayerGames = Set.MIN_GAMES_TO_WIN + 1;
    this.alternateGamesWinning();
    assertTrue(this.set.isFinished());
    assertTrue(this.set.isWinner(this.turn.getSecondPlayer()));
  }

  @Test
  public void when_player_wins_game_with_lackService_then_game_is_finished() {
    Player player = this.turn.getRestPlayer();
    for (int i = 0; i < Game.MIN_POINTS_TO_WIN - 1; i++) {
      this.set.addPoint(player);
    }
    this.set.lackService();
    this.set.lackService();
    assertTrue(this.set.isGameFinished());
  }

  @Test
  public void when_players_draw_at_six_games_and_first_player_wins_next_game_then_first_player_wins() {
    this.firstPlayerGames = Set.MIN_GAMES_TO_WIN + 1;
    this.secondPlayerGames = Set.MIN_GAMES_TO_WIN;
    this.alternateGamesWinning();
    assertTrue(this.set.isFinished());
    assertTrue(this.set.isWinner(this.turn.getFirstPlayer()));
  }

  @Test
  public void when_players_draw_at_six_games_and_second_player_wins_next_game_then_second_player_wins() {
    this.firstPlayerGames = Set.MIN_GAMES_TO_WIN;
    this.secondPlayerGames = Set.MIN_GAMES_TO_WIN + 1;
    this.alternateGamesWinning();
    assertTrue(this.set.isFinished());
    assertTrue(this.set.isWinner(this.turn.getSecondPlayer()));
  }

  private void alternateGamesWinning() {
    for (int gameNumber = 0; gameNumber < this.firstPlayerGames + this.secondPlayerGames; gameNumber++) {
      if (gameNumber % 2 == 0) {
        if (gameNumber / 2 < firstPlayerGames) {
          this.playerWinsGame(this.turn.getFirstPlayer());
        } else {
          this.playerWinsGame(this.turn.getSecondPlayer());
        }
      } else {
        if (gameNumber / 2 < secondPlayerGames) {
          this.playerWinsGame(this.turn.getSecondPlayer());
        } else {
          this.playerWinsGame(this.turn.getFirstPlayer());
        }
      }
    }
  }

  private void playerWinsGame(Player player) {
    int games = this.set.getGamesWon(player);
    do {
      this.set.addPoint(player);
    } while (games == this.set.getGamesWon(player));
  }

}
