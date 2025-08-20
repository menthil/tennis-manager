package com.escuelait.models;

import java.util.List;

class MatchBuilder {

  Match build() {
    return Match.createThreeSetMatch(1, List.of(PlayerBuilder.createPlayer(), PlayerBuilder.createPlayer()));
  }

}
