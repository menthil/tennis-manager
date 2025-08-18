package com.escuelait.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SetTest {

  @Test
  public void when_set_starts_result_is_zero_to_zero() {
    Set set = Set.startSet(null);
    assertEquals(0, set.getFirstPlayerResult());
    assertEquals(0, set.getSecondPlayerResult());
  }

  @Test
  public void when_first_player_wins_a_game_result_is_one_to_zero() {
    Set set = Set.startSet(new TurnBuilder().build());
    set.addPointService();
    set.addPointService();
    set.addPointService();
    set.addPointService();
    assertEquals(1, set.getFirstPlayerResult());
    assertEquals(0, set.getSecondPlayerResult());
  }

}
