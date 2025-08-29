package com.escuelait.models;

public class State {

  private StateValue stateValue;
  private Match match;

  public State() {
    this.stateValue = StateValue.INITIAL;
    this.match = null;
  }

  public StateValue getValue() {
    return this.stateValue;
  }

  public void exit() {
    this.stateValue = StateValue.EXIT;
  }

  public void started() {
    this.stateValue = StateValue.STARTED;
    this.match = null;
  }

  public void logged() {
    this.stateValue = StateValue.LOGGED;
    this.match = null;
  }

  public void matchStarted(Match match) {
    assert match != null;
    this.stateValue = StateValue.MATCH_STARTED;
    this.match = match;
  }

  public void lackService() {
    assert this.isMatchStarted();
    this.match.lackService();
  }

  public boolean isFinished() {
    assert this.isMatchStarted();
    return this.match.isFinished();
  }

  public void addPointService() {
    assert this.isMatchStarted();
    this.match.addPointService();
  }

  public void addPointRest() {
    assert this.isMatchStarted();
    this.match.addPointRest();
  }

  public Match getMatch() {
    assert this.isMatchStarted();
    return this.match;
  }

  public boolean isMatchStarted() {
    return this.stateValue == StateValue.MATCH_STARTED;
  }

}
