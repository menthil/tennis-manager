package com.escuelait.models;

import static org.junit.Assert.assertEquals;

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

}
