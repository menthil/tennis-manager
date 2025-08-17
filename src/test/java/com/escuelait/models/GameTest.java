package com.escuelait.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameTest {

  @Test
  public void when_lack_service_first_time_then_no_point_added_to_rest() {
    Game game = Game.normalGame();
    game.lackService();
    assertEquals(0, game.getRestPoints());
  }

  @Test
  public void when_two_lack_services_then_one_point_added_to_rest() {
    Game game = Game.normalGame();
    game.lackService();
    game.lackService();
    assertEquals(1, game.getRestPoints());
  }

  @Test
  public void when_point_service_then_one_point_added_to_service() {
    Game game = Game.normalGame();
    game.pointService();
    assertEquals(1, game.getServicePoints());
    game.pointService();
    assertEquals(2, game.getServicePoints());
  }

  @Test
  public void when_point_rest_then_one_point_added_to_rest() {
    Game game = Game.normalGame();
    game.pointRest();
    assertEquals(1, game.getRestPoints());
    game.pointRest();
    assertEquals(2, game.getRestPoints());
  }

  @Test
  public void when_lack_service_point_service_lack_service_then_no_point_added() {
    Game game = Game.normalGame();
    game.lackService();
    game.pointService();
    game.lackService();
    assertEquals(1, game.getServicePoints());
  }

  @Test
  public void when_lack_service_point_rest_lack_service_then_no_point_added() {
    Game game = Game.normalGame();
    game.lackService();
    game.pointRest();
    game.lackService();
    assertEquals(1, game.getRestPoints());
  }

  @Test
  public void when_service_won_four_points_with_at_least_two_more_points_then_won_the_game() {
    Game game = Game.normalGame();
    game.pointService();
    game.pointService();
    game.pointService();
    game.pointRest();
    game.pointRest();
    game.pointRest();
    game.pointService();
    assertFalse(game.isGameFinished());
    assertFalse(game.isServiceWinner());
    game.pointService();
    assertTrue(game.isGameFinished());
    assertTrue(game.isServiceWinner());
  }

}
