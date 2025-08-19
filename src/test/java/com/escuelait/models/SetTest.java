package com.escuelait.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SetTest {

  @Test
  public void when_set_starts_result_is_zero_to_zero() {
    Set set = new SetBuilder().build();
    assertEquals(0, set.getFirstPlayerResult());
    assertEquals(0, set.getSecondPlayerResult());
  }

  @Test
  public void when_first_player_wins_a_game_result_is_one_to_zero() {
    Set set = new SetBuilder().firstPlayerGames(1).build();
    assertEquals(1, set.getFirstPlayerResult());
    assertEquals(0, set.getSecondPlayerResult());
  }

  @Test
  public void when_second_player_wins_a_game_result_is_zero_to_one() {
    Set set = new SetBuilder().secondPlayerGames(1).build();
    assertEquals(0, set.getFirstPlayerResult());
    assertEquals(1, set.getSecondPlayerResult());
  }

  @Test
  public void when_player_gets_at_leats_six_games_with_two_more_games_than_other_player_wins_set() {
    Set set = new SetBuilder().firstPlayerGames(5).secondPlayerGames(1).build();
    assertFalse(set.isFinished());
    assertEquals(5, set.getFirstPlayerResult());
    assertEquals(1, set.getSecondPlayerResult());
    set = new SetBuilder().firstPlayerGames(6).secondPlayerGames(4).build();
    assertTrue(set.isFinished());
    assertEquals(6, set.getFirstPlayerResult());
    assertEquals(4, set.getSecondPlayerResult());
    set = new SetBuilder().firstPlayerGames(5).secondPlayerGames(7).build();
    assertTrue(set.isFinished());
    assertEquals(5, set.getFirstPlayerResult());
    assertEquals(7, set.getSecondPlayerResult());
  }

  @Test
  public void when_players_draw_at_six_games_only_need_tie_break_game_to_win() {
    Set set = new SetBuilder().firstPlayerGames(7).secondPlayerGames(6).build();
    assertEquals(TieBreakGame.MIN_POINTS_TO_WIN, set.getMinPointsToWinGame());
    assertTrue(set.isFinished());
    set = new SetBuilder().firstPlayerGames(6).secondPlayerGames(7).build();
    assertEquals(TieBreakGame.MIN_POINTS_TO_WIN, set.getMinPointsToWinGame());
    assertTrue(set.isFinished());
  }

}
