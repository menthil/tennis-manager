package com.escuelait.models;

class TurnBuilder {

  Turn build() {
    return Turn.firstPlayerServes(new PlayerBuilder().build());
  }

}
