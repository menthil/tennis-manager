package com.escuelait.models;

class SetBuilder {

  private Turn turn;

  Set build() {
    return Set.start(this.turn);
  }

  SetBuilder turn(Turn turn) {
    this.turn = turn;
    return this;
  }

}
