package com.escuelait.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameTest {

  @Test
  public void when_lack_service_first_time_then_no_point_added_to_rest() {
    Game game = new GameBuilder().build();
    game.lackService();
    assertEquals(0, game.getRestPoints());
  }

  @Test
  public void when_two_lack_services_then_one_point_added_to_rest() {
    Game game = new GameBuilder().build();
    game.lackService();
    game.lackService();
    assertEquals(1, game.getRestPoints());
  }

  @Test
  public void when_point_service_then_one_point_added_to_service() {
    Game game = new GameBuilder().build();
    game.addPointService();
    assertEquals(1, game.getServicePoints());
    game.addPointService();
    assertEquals(2, game.getServicePoints());
  }

  @Test
  public void when_point_rest_then_one_point_added_to_rest() {
    Game game = new GameBuilder().build();
    game.addPointRest();
    assertEquals(1, game.getRestPoints());
    game.addPointRest();
    assertEquals(2, game.getRestPoints());
  }

  @Test
  public void when_lack_service_point_service_lack_service_then_no_point_added() {
    Game game = new GameBuilder().build();
    game.lackService();
    game.addPointService();
    game.lackService();
    assertEquals(1, game.getServicePoints());
  }

  @Test
  public void when_lack_service_point_rest_lack_service_then_no_point_added() {
    Game game = new GameBuilder().build();
    game.lackService();
    game.addPointRest();
    game.lackService();
    assertEquals(1, game.getRestPoints());
  }

  @Test
  public void when_service_won_four_points_with_at_least_two_more_points_then_won_the_game() {
    Game game = new GameBuilder().advantageService().build();
    assertFalse(game.isGameFinished());
    assertFalse(game.isServiceWinner());
    game.addPointService();
    assertTrue(game.isGameFinished());
    assertTrue(game.isServiceWinner());
  }

  @Test
  public void when_serving_player_wins_game_then_service_changes_to_the_other_player() {
    Turn turn = new TurnBuilder().build();
    Player servicePlayer = turn.getServicePlayer();
    Player restPlayer = turn.getRestPlayer();
    assertNotEquals(servicePlayer, restPlayer);
    Game game = new GameBuilder().turn(turn).serviceWins().build();
    assertTrue(game.isServiceWinner());
    assertNotEquals(turn.getServicePlayer(), servicePlayer);
    assertEquals(turn.getServicePlayer(), restPlayer);
  }

  @Test
  public void when_rest_player_wins_game_then_service_changes_to_the_other_player() {
    Turn turn = new TurnBuilder().build();
    Player servicePlayer = turn.getServicePlayer();
    Player restPlayer = turn.getRestPlayer();
    assertNotEquals(servicePlayer, restPlayer);
    Game game = new GameBuilder().turn(turn).restWins().build();
    assertTrue(game.isRestWinner());
    assertNotEquals(turn.getServicePlayer(), servicePlayer);
    assertEquals(turn.getServicePlayer(), restPlayer);
  }

  @Test
  public void when_odd_number_of_points_in_tie_break_then_service_changes_to_the_other_player() {
    Turn turn = new TurnBuilder().build();
    Player servicePlayer = turn.getServicePlayer();
    Player restPlayer = turn.getRestPlayer();
    Game game = new GameBuilder().turn(turn).tieBreakGame().build();
    game.addPointService();
    assertEquals(turn.getServicePlayer(), restPlayer);
    game.addPointRest();
    assertEquals(turn.getServicePlayer(), restPlayer);
    game.addPointService();
    assertEquals(turn.getServicePlayer(), servicePlayer);
  }

}
