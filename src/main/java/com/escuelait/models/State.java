package com.escuelait.models;

public class State {

  private StateValue stateValue;

  public State() {
    this.stateValue = StateValue.INITIAL;
  }

  public StateValue getValue() {
    return this.stateValue;
  }

  public void exit() {
    this.stateValue = StateValue.EXIT;
  }

  public void started() {
    this.stateValue = StateValue.STARTED;
  }

}
