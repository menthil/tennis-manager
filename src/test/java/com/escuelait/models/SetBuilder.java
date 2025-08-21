package com.escuelait.models;

class SetBuilder {

  Set build() {
    return Set.start(new TurnBuilder().build());
  }

}
