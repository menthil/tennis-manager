package com.escuelait.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

  private Turn turn;
  private Game game;

  @Before
  public void before() {
    this.turn = new TurnBuilder().build();
    this.game = new GameBuilder().turn(this.turn).build();
  }

  @Test
  public void when_lack_service_first_time_then_no_point_added_to_rest() {
    this.game.lackService();
    assertEquals(0, this.game.getPoints(this.turn.getRestPlayer()));
  }

  @Test
  public void when_lack_service_first_time_then_isLackService_is_true() {
    this.game.lackService();
    assertTrue(this.game.isLackService());
  }

  @Test
  public void when_two_lack_services_then_one_point_added_to_rest() {
    this.game.lackService();
    this.game.lackService();
    assertEquals(1, this.game.getPoints(this.turn.getRestPlayer()));
  }

  @Test
  public void when_two_lack_services_then_isLackService_is_false() {
    this.game.lackService();
    this.game.lackService();
    assertFalse(this.game.isLackService());
  }

  @Test
  public void when_first_player_wins_a_point_then_one_point_added_to_first_player() {
    this.game.addPoint(this.turn.getFirstPlayer());
    assertEquals(1, this.game.getPoints(this.turn.getFirstPlayer()));
    this.game.addPoint(this.turn.getFirstPlayer());
    assertEquals(2, this.game.getPoints(this.turn.getFirstPlayer()));
  }

  @Test
  public void when_second_player_wins_a_pont_then_one_point_added_to_second_player() {
    this.game.addPoint(this.turn.getSecondPlayer());
    assertEquals(1, this.game.getPoints(this.turn.getSecondPlayer()));
    this.game.addPoint(this.turn.getSecondPlayer());
    assertEquals(2, this.game.getPoints(this.turn.getSecondPlayer()));
  }

  @Test
  public void when_lack_service_and_first_player_wins_point_and_lack_service_then_no_point_added_to_first_player() {
    this.game.lackService();
    this.game.addPoint(this.turn.getFirstPlayer());
    this.game.lackService();
    assertEquals(1, this.game.getPoints(this.turn.getFirstPlayer()));
  }

  @Test
  public void when_lack_service_and_second_player_wins_point_abd_lack_service_then_no_point_added_to_second_player() {
    this.game.lackService();
    this.game.addPoint(this.turn.getSecondPlayer());
    this.game.lackService();
    assertEquals(1, this.game.getPoints(this.turn.getSecondPlayer()));
  }

  @Test
  public void when_a_player_won_four_points_with_at_least_two_more_points_then_won_the_game() {
    Player firstPlayer = this.turn.getFirstPlayer();
    this.alternatePoints(Game.MIN_POINTS_TO_WIN + 1, Game.MIN_POINTS_TO_WIN);
    assertFalse(this.game.isFinished());
    assertFalse(this.game.isWinner(firstPlayer));
    this.game.addPoint(firstPlayer);
    assertTrue(this.game.isFinished());
    assertTrue(this.game.isWinner(firstPlayer));
  }

  @Test
  public void when_first_player_wins_game_then_service_changes_to_the_other_player() {
    Player servicePlayer = this.turn.getServicePlayer();
    this.alternatePoints(Game.MIN_POINTS_TO_WIN, 0);
    assertNotEquals(this.turn.getServicePlayer(), servicePlayer);
  }

  @Test
  public void when_second_player_wins_game_then_service_changes_to_the_other_player() {
    Player servicePlayer = this.turn.getServicePlayer();
    this.alternatePoints(0, Game.MIN_POINTS_TO_WIN);
    assertNotEquals(this.turn.getServicePlayer(), servicePlayer);
  }

  @Test
  public void when_odd_number_of_points_in_tie_break_then_service_changes_to_the_other_player() {
    Player restPlayer = this.turn.getRestPlayer();
    Player servicePlayer = this.turn.getServicePlayer();
    this.game = new GameBuilder().turn(this.turn).tieBreakGame().build();
    this.game.addPoint(servicePlayer);
    assertEquals(this.turn.getServicePlayer(), restPlayer);
    this.game.addPoint(restPlayer);
    assertEquals(this.turn.getServicePlayer(), restPlayer);
    this.game.addPoint(servicePlayer);
    assertEquals(this.turn.getServicePlayer(), servicePlayer);
  }

  @Test
  public void when_tie_break_first_player_need_six_points_to_win() {
    Player firstPlayer = this.turn.getFirstPlayer();
    this.game = new GameBuilder().turn(this.turn).tieBreakGame().build();
    this.alternatePoints(TieBreakGame.MIN_POINTS_TO_WIN, 0);
    assertTrue(this.game.isWinner(firstPlayer));
  }

  @Test
  public void when_tie_break_second_player_need_six_points_to_win() {
    Player secondPlayer = this.turn.getSecondPlayer();
    this.game = new GameBuilder().turn(this.turn).tieBreakGame().build();
    this.alternatePoints(0, TieBreakGame.MIN_POINTS_TO_WIN);
    assertTrue(this.game.isWinner(secondPlayer));
  }

  @Test
  public void when_tie_break_first_player_needs_difference_of_two_to_win() {
    Player firstPlayer = this.turn.getFirstPlayer();
    this.game = new GameBuilder().turn(this.turn).tieBreakGame().build();
    this.alternatePoints(TieBreakGame.MIN_POINTS_TO_WIN, TieBreakGame.MIN_POINTS_TO_WIN);
    assertFalse(this.game.isFinished());
    this.game.addPoint(firstPlayer);
    this.game.addPoint(firstPlayer);
    assertTrue(this.game.isWinner(firstPlayer));
  }

  @Test
  public void when_tie_break_player_resting_first_serves_next_game_when_odd_number_of_points() {
    Player servicePlayer = this.turn.getServicePlayer();
    Player restPlayer = this.turn.getRestPlayer();
    this.game = new GameBuilder().turn(this.turn).tieBreakGame().build();
    this.alternatePoints(TieBreakGame.MIN_POINTS_TO_WIN, 1);
    assertTrue(this.game.isWinner(servicePlayer));
    assertEquals(this.turn.getServicePlayer(), restPlayer);
  }

  @Test
  public void when_tie_break_player_resting_first_serves_next_game_when_even_number_of_points() {
    Player servicePlayer = this.turn.getServicePlayer();
    Player restPlayer = this.turn.getRestPlayer();
    this.game = new GameBuilder().turn(this.turn).tieBreakGame().build();
    this.alternatePoints(TieBreakGame.MIN_POINTS_TO_WIN, 0);
    assertTrue(this.game.isWinner(servicePlayer));
    assertEquals(this.turn.getServicePlayer(), restPlayer);
  }

  private void alternatePoints(int firstPlayerPoints, int secondPlayerPoints) {
    Player firstPlayer = this.turn.getFirstPlayer();
    Player secondPlayer = this.turn.getSecondPlayer();
    for (int i = 0; i < Math.max(firstPlayerPoints, secondPlayerPoints); i++) {
      if (i < firstPlayerPoints) {
        this.game.addPoint(firstPlayer);
      }
      if (i < secondPlayerPoints) {
        this.game.addPoint(secondPlayer);
      }
    }
  }

}
