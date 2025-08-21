package com.escuelait.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MatchTest {

  private MatchBuilder matchBuilder;
  private Match match;

  @Before
  public void before() {
    this.matchBuilder = new MatchBuilder();
    this.match = this.matchBuilder.build();
  }

  @Test
  public void when_one_lack_service_service_player_stays_at_zero_points() {
    this.match.lackService();
    assertEquals(0, this.match.getGamePoints(this.matchBuilder.getFirstPlayer()));
    assertEquals(0, this.match.getGamePoints(this.matchBuilder.getSecondPlayer()));
  }

  @Test
  public void when_add_point_to_service_service_player_gets_one_point() {
    this.match.addPointService();
    assertEquals(1, this.match.getGamePoints(this.matchBuilder.getFirstPlayer()));
    assertEquals(0, this.match.getGamePoints(this.matchBuilder.getSecondPlayer()));
  }

  @Test
  public void when_add_point_to_rest_rest_player_gets_one_point() {
    this.match.addPointRest();
    assertEquals(0, this.match.getGamePoints(this.matchBuilder.getFirstPlayer()));
    assertEquals(1, this.match.getGamePoints(this.matchBuilder.getSecondPlayer()));
  }

  @Test
  public void when_first_set_ends_a_second_set_starts() {
    this.firstPlayerWinsFirstSet();
    assertArrayEquals(new int[] { Set.MIN_GAMES_TO_WIN, 0, 0 }, this.match.getFisrstPlayerSetGames());
  }

  private void firstPlayerWinsFirstSet() {
    for (int i = 0; i < Set.MIN_GAMES_TO_WIN; i++) {
      if (i % 2 == 0) {
        this.winGameServing();
      } else {
        this.winGameResting();
      }
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

}
