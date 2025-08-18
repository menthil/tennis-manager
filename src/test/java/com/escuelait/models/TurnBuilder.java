package com.escuelait.models;

import java.util.List;

class TurnBuilder {

  static Turn create() {
    return Turn.startMatch(List.of(PlayerBuilder.createPlayer(), PlayerBuilder.createPlayer()));
  }

}
