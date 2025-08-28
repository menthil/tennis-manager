package com.escuelait.models;

public class State {

  private StateValue stateValue;
  private Match match;

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

  public void logged() {
    this.stateValue = StateValue.LOGGED;
  }

  public void matchStarted() {
    this.stateValue = StateValue.MATCH_STARTED;
  }

  public void setMatch(Match match) {
    assert match != null;
    this.match = match;
  }

  public void lackService() {
    assert this.match != null;
    this.match.lackService();
  }

  public Score getScore() {
    assert this.match != null;
    return this.match.getScore();
  }

  public void addPointService() {
    assert this.match != null;
    this.match.addPointService();
  }

  public void addPointRest() {
    assert this.match != null;
    this.match.addPointRest();
  }

}
