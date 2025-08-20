package com.escuelait.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatchTest {

  @Test
  public void when_one_lack_service_service_player_stays_at_zero_points() {
    Match match = new MatchBuilder().build();
    match.lackService();
    assertEquals(0, match.getFirstPlayerGamePoints());
    assertEquals(0, match.getSecondPlayerGamePoints());
  }

  @Test
  public void when_add_point_to_service_service_player_gets_one_point() {
    Match match = new MatchBuilder().build();
    match.addPointService();
    assertEquals(1, match.getFirstPlayerGamePoints());
    assertEquals(0, match.getSecondPlayerGamePoints());
  }

  @Test
  public void when_add_point_to_rest_rest_player_gets_one_point() {
    Match match = new MatchBuilder().build();
    match.addPointRest();
    assertEquals(0, match.getFirstPlayerGamePoints());
    assertEquals(1, match.getSecondPlayerGamePoints());
  }

  @Test
  public void when_first_set_ends_a_second_set_starts() {
    Match match = new MatchBuilder().build();
    this.firstPlayerWinsFirstSet(match);
    assertArrayEquals(new int[] { Set.MIN_GAMES_TO_WIN, 0, 0 }, match.getFisrstPlayerSetGames());
  }

  private void firstPlayerWinsFirstSet(Match match) {
    for (int i = 0; i < Set.MIN_GAMES_TO_WIN; i++) {
      if (i % 2 == 0) {
        this.winGameServing(match);
      } else {
        this.winGameResting(match);
      }
    }
  }

  private void winGameServing(Match match) {
    for (int j = 0; j < Game.MIN_POINTS_TO_WIN; j++) {
      match.addPointService();
    }
  }

  private void winGameResting(Match match) {
    for (int j = 0; j < Game.MIN_POINTS_TO_WIN; j++) {
      match.addPointRest();
    }
  }

}
