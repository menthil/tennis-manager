package com.escuelait.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameTest {

  @Test
  public void when_lack_service_first_time_then_no_point_added_to_rest() {
    Turn turn = new TurnBuilder().build();
    Game game = new GameBuilder().turn(turn).build();
    game.lackService();
    assertEquals(0, game.getPoints(turn.getRestPlayer()));
  }

  @Test
  public void when_two_lack_services_then_one_point_added_to_rest() {
    Turn turn = new TurnBuilder().build();
    Game game = new GameBuilder().turn(turn).build();
    game.lackService();
    game.lackService();
    assertEquals(1, game.getPoints(turn.getRestPlayer()));
  }

  @Test
  public void when_point_service_then_one_point_added_to_service() {
    Turn turn = new TurnBuilder().build();
    Game game = new GameBuilder().turn(turn).build();
    game.addPoint(turn.getServicePlayer());
    assertEquals(1, game.getPoints(turn.getServicePlayer()));
    game.addPoint(turn.getServicePlayer());
    assertEquals(2, game.getPoints(turn.getServicePlayer()));
  }

  @Test
  public void when_point_rest_then_one_point_added_to_rest() {
    Turn turn = new TurnBuilder().build();
    Game game = new GameBuilder().turn(turn).build();
    game.addPoint(turn.getRestPlayer());
    assertEquals(1, game.getPoints(turn.getRestPlayer()));
    game.addPoint(turn.getRestPlayer());
    assertEquals(2, game.getPoints(turn.getRestPlayer()));
  }

  @Test
  public void when_lack_service_point_service_lack_service_then_no_point_added() {
    Turn turn = new TurnBuilder().build();
    Game game = new GameBuilder().turn(turn).build();
    game.lackService();
    game.addPoint(turn.getServicePlayer());
    game.lackService();
    assertEquals(1, game.getPoints(turn.getServicePlayer()));
  }

  @Test
  public void when_lack_service_point_rest_lack_service_then_no_point_added() {
    Turn turn = new TurnBuilder().build();
    Game game = new GameBuilder().turn(turn).build();
    game.lackService();
    game.addPoint(turn.getRestPlayer());
    game.lackService();
    assertEquals(1, game.getPoints(turn.getRestPlayer()));
  }

  @Test
  public void when_service_won_four_points_with_at_least_two_more_points_then_won_the_game() {
    Turn turn = new TurnBuilder().build();
    Player servicePlayer = turn.getServicePlayer();
    Game game = new GameBuilder().turn(turn).advantageService().build();
    assertFalse(game.isFinished());
    assertFalse(game.isWinner(servicePlayer));
    game.addPoint(turn.getServicePlayer());
    assertTrue(game.isFinished());
    assertTrue(game.isWinner(servicePlayer));
  }

  @Test
  public void when_serving_player_wins_game_then_service_changes_to_the_other_player() {
    Turn turn = new TurnBuilder().build();
    Player servicePlayer = turn.getServicePlayer();
    Player restPlayer = turn.getRestPlayer();
    assertNotEquals(servicePlayer, restPlayer);
    Game game = new GameBuilder().turn(turn).serviceWins().build();
    assertTrue(game.isWinner(servicePlayer));
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
    assertTrue(game.isWinner(restPlayer));
    assertNotEquals(turn.getServicePlayer(), servicePlayer);
    assertEquals(turn.getServicePlayer(), restPlayer);
  }

  @Test
  public void when_odd_number_of_points_in_tie_break_then_service_changes_to_the_other_player() {
    Turn turn = new TurnBuilder().build();
    Player servicePlayer = turn.getServicePlayer();
    Player restPlayer = turn.getRestPlayer();
    Game game = new GameBuilder().turn(turn).tieBreakGame().build();
    game.addPoint(servicePlayer);
    assertEquals(turn.getServicePlayer(), restPlayer);
    game.addPoint(restPlayer);
    assertEquals(turn.getServicePlayer(), restPlayer);
    game.addPoint(servicePlayer);
    assertEquals(turn.getServicePlayer(), servicePlayer);
  }

  @Test
  public void when_tie_break_serving_player_need_six_points_to_win() {
    Turn turn = new TurnBuilder().build();
    Player servicePlayer = turn.getServicePlayer();
    Game game = new GameBuilder().turn(turn).tieBreakGame().serviceWins().build();
    assertTrue(game.isWinner(servicePlayer));
  }

  @Test
  public void when_tie_break_resting_player_need_six_points_to_win() {
    Turn turn = new TurnBuilder().build();
    Player restPlayer = turn.getRestPlayer();
    Game game = new GameBuilder().turn(turn).tieBreakGame().restWins().build();
    assertTrue(game.isWinner(restPlayer));
  }

  @Test
  public void when_tie_break_player_needs_difference_of_two_to_win() {
    Turn turn = new TurnBuilder().build();
    Player servicePlayer = turn.getServicePlayer();
    Game game = new GameBuilder()
        .tieBreakGame()
        .turn(turn)
        .service(TieBreakGame.MIN_POINTS_TO_WIN)
        .rest(TieBreakGame.MIN_POINTS_TO_WIN)
        .build();
    assertFalse(game.isFinished());
    game.addPoint(servicePlayer);
    game.addPoint(servicePlayer);
    assertTrue(game.isWinner(servicePlayer));
  }

  @Test
  public void when_tie_break_player_resting_first_serves_next_game_when_odd_number_of_points() {
    Turn turn = new TurnBuilder().build();
    Player servicePlayer = turn.getServicePlayer();
    Player restPlayer = turn.getRestPlayer();
    Game game = new GameBuilder()
        .tieBreakGame()
        .turn(turn)
        .service(TieBreakGame.MIN_POINTS_TO_WIN)
        .rest(1)
        .build();
    assertTrue(game.isWinner(servicePlayer));
    assertEquals(turn.getServicePlayer(), restPlayer);
  }

  @Test
  public void when_tie_break_player_resting_first_serves_next_game_when_even_number_of_points() {
    Turn turn = new TurnBuilder().build();
    Player servicePlayer = turn.getServicePlayer();
    Player restPlayer = turn.getRestPlayer();
    Game game = new GameBuilder()
        .tieBreakGame()
        .turn(turn)
        .service(TieBreakGame.MIN_POINTS_TO_WIN)
        .build();
    assertTrue(game.isWinner(servicePlayer));
    assertEquals(turn.getServicePlayer(), restPlayer);
  }

}
