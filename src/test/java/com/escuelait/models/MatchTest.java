package com.escuelait.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class MatchTest {

  @Test
  public void when_one_lack_service_service_player_stays_zero_points() {
    Match match = Match.createThreeSetMatch(1, List.of(PlayerBuilder.createPlayer(), PlayerBuilder.createPlayer()));
    match.lackService();
    assertEquals(0, match.getFirstPlayerGamePoints());
    assertEquals(0, match.getSecondPlayerGamePoints());
  }

  @Test
  public void when_add_point_to_service_service_player_gets_one_point() {
    Match match = Match.createThreeSetMatch(1, List.of(PlayerBuilder.createPlayer(), PlayerBuilder.createPlayer()));
    match.addPointService();
    assertEquals(1, match.getFirstPlayerGamePoints());
    assertEquals(0, match.getSecondPlayerGamePoints());
  }

  @Test
  public void when_add_point_to_rest_rest_player_gets_one_point() {
    Match match = Match.createThreeSetMatch(1, List.of(PlayerBuilder.createPlayer(), PlayerBuilder.createPlayer()));
    match.addPointRest();
    assertEquals(0, match.getFirstPlayerGamePoints());
    assertEquals(1, match.getSecondPlayerGamePoints());
  }

  @Test
  public void when_first_set_ends_a_second_set_starts() {
    Match match = Match.createThreeSetMatch(1, List.of(PlayerBuilder.createPlayer(), PlayerBuilder.createPlayer()));
    for (int i = 0; i < 6 * 4; i++) {
      if (i / 4 % 2 == 0) {
        match.addPointService();
      } else {
        match.addPointRest();
      }
    }
    assertArrayEquals(new int[] { 6, 0 }, match.getFisrstPlayerSetGames());
  }

}
