package com.escuelait.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MatchTest {

  private List<Player> players;
  private Match match;
  private int firstPlayerSets;
  private int secondPlayerSets;

  @Before
  public void before() {
    this.players = new PlayerBuilder().build();
    this.match = new MatchBuilder().players(this.players).build();
    this.firstPlayerSets = 0;
    this.secondPlayerSets = 0;
  }

  @Test
  public void when_one_lack_service_then_score_doesnt_change() {
    this.match.lackService();
    assertEquals(0, this.match.getGamePoints(this.getFirstPlayer()));
    assertEquals(0, this.match.getGamePoints(this.getSecondPlayer()));
  }

  @Test
  public void when_add_point_to_service_service_player_gets_one_point() {
    this.match.addPointService();
    assertEquals(1, this.match.getGamePoints(this.getServicePlayer()));
    assertEquals(0, this.match.getGamePoints(this.getRestPlayer()));
  }

  @Test
  public void when_add_point_to_rest_rest_player_gets_one_point() {
    this.match.addPointRest();
    assertEquals(0, this.match.getGamePoints(this.getServicePlayer()));
    assertEquals(1, this.match.getGamePoints(this.getRestPlayer()));
  }

  @Test
  public void when_first_player_wins_a_game_then_game_numbers_increases_in_one() {
    this.winGame(this.getFirstPlayer());
    assertTrue(this.match.isGameFinished());
    assertEquals(1, this.match.getSetGames(this.getFirstPlayer()).get(0).intValue());
  }

  @Test
  public void when_first_player_wins_a_game_and_a_player_wins_a_point_then_game_is_not_finished() {
    this.winGame(this.getFirstPlayer());
    this.match.addPointRest();
    assertFalse(this.match.isGameFinished());
  }

  @Test
  public void when_second_player_wins_a_game_then_game_numbers_increases_in_one() {
    this.winGame(this.getSecondPlayer());
    assertTrue(this.match.isGameFinished());
    assertEquals(Integer.valueOf(1), this.match.getSetGames(this.getSecondPlayer()).get(0));
  }

  @Test
  public void when_second_player_wins_a_game_and_a_player_wins_a_point_then_game_is_not_finished() {
    this.winGame(this.getSecondPlayer());
    this.match.addPointRest();
    assertFalse(this.match.isGameFinished());
  }

  @Test
  public void when_first_player_wins_a_set_then_set_numbers_increases_in_one() {
    assertEquals(1, this.match.getSetGames(this.getFirstPlayer()).size());
    this.firstPlayerSets = 1;
    this.alternateSetsWinning();
    assertEquals(2, this.match.getSetGames(this.getFirstPlayer()).size());
    assertTrue(this.match.isSetFinished());
  }

  @Test
  public void when_first_player_wins_a_set_and_a_player_wins_a_point_then_set_is_not_finished() {
    this.firstPlayerSets = 1;
    this.alternateSetsWinning();
    this.match.addPointService();
    assertFalse(this.match.isSetFinished());
  }

  @Test
  public void when_second_player_wins_a_set_then_set_numbers_increases_in_one() {
    assertEquals(1, this.match.getSetGames(this.getSecondPlayer()).size());
    this.secondPlayerSets = 1;
    this.alternateSetsWinning();
    assertEquals(2, this.match.getSetGames(this.getSecondPlayer()).size());
    assertTrue(this.match.isSetFinished());
  }

  @Test
  public void when_second_player_wins_a_set_and_a_player_wins_a_point_then_set_is_not_finished() {
    this.secondPlayerSets = 1;
    this.alternateSetsWinning();
    this.match.addPointService();
    assertFalse(this.match.isSetFinished());
  }

  @Test
  public void when_first_player_wins_two_sets_in_a_three_set_match_then_wins_match() {
    this.firstPlayerSets = 2;
    this.secondPlayerSets = 1;
    this.alternateSetsWinning();
    assertTrue(this.match.isFinished());
    assertTrue(this.match.isWinner(this.getFirstPlayer()));
  }

  @Test
  public void when_second_player_wins_two_sets_in_a_three_set_match_then_wins_match() {
    this.firstPlayerSets = 1;
    this.secondPlayerSets = 2;
    this.alternateSetsWinning();
    assertTrue(this.match.isFinished());
    assertTrue(this.match.isWinner(this.getSecondPlayer()));
  }

  @Test
  public void when_first_player_wins_three_sets_in_a_five_set_match_then_wins_match() {
    this.firstPlayerSets = 3;
    this.secondPlayerSets = 1;
    this.match = new MatchBuilder().players(this.players).fiveSetsMatch().build();
    this.alternateSetsWinning();
    assertTrue(this.match.isFinished());
    assertTrue(this.match.isWinner(this.getFirstPlayer()));
  }

  @Test
  public void when_second_player_wins_three_sets_in_a_five_set_match_then_wins_match() {
    this.firstPlayerSets = 0;
    this.secondPlayerSets = 3;
    this.match = new MatchBuilder().players(this.players).fiveSetsMatch().build();
    this.alternateSetsWinning();
    assertTrue(this.match.isFinished());
    assertTrue(this.match.isWinner(this.getSecondPlayer()));
  }

  private Player getFirstPlayer() {
    return this.players.get(0);
  }

  private Player getSecondPlayer() {
    return this.players.get(1);
  }

  private Player getServicePlayer() {
    return this.match.isServing(this.getFirstPlayer())
        ? this.getFirstPlayer()
        : this.getSecondPlayer();
  }

  private Player getRestPlayer() {
    return !this.match.isServing(this.getFirstPlayer())
        ? this.getFirstPlayer()
        : this.getSecondPlayer();
  }

  private void winGame(Player player) {
    if (this.match.isServing(player)) {
      this.winGameServing();
    } else {
      this.winGameResting();
    }
  }

  private void winGameServing() {
    for (int j = 0; j < Game.MIN_POINTS_TO_WIN; j++) {
      this.match.addPointService();
    }
  }

  private void winGameResting() {
    for (int j = 0; j < Game.MIN_POINTS_TO_WIN; j++) {
      this.match.addPointRest();
    }
  }

  private void alternateSetsWinning() {
    for (int setNumber = 0; setNumber < this.firstPlayerSets + this.secondPlayerSets; setNumber++) {
      if (setNumber % 2 == 0) {
        if (setNumber / 2 < firstPlayerSets) {
          this.playerWinsSet(this.getFirstPlayer());
        } else {
          this.playerWinsSet(this.getSecondPlayer());
        }
      } else {
        if (setNumber / 2 < secondPlayerSets) {
          this.playerWinsSet(this.getSecondPlayer());
        } else {
          this.playerWinsSet(this.getFirstPlayer());
        }
      }
    }
  }

  private void playerWinsSet(Player player) {
    for (int i = 0; i < Set.MIN_GAMES_TO_WIN; i++) {
      this.winGame(player);
    }
  }

}
