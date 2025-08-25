package com.escuelait.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandType {
  CREATE_REFEREE("createReferee", "name:(.{4,});password:(.{4,})"),
  LOGIN("login", "name:(.{4,});password:(.{4,})"),
  EXIT("exit", ""),
  ;

  private String name;
  private String regex;

  private CommandType(String name, String parameters) {
    this.name = name;
    this.regex = (this.name + " " + parameters).trim();
  }

  boolean isValid(String commandString) {
    assert commandString != null;
    return this.getMatcher(commandString).matches();
  }

  private Matcher getMatcher(String commandString) {
    return Pattern.compile(this.regex).matcher(commandString);
  }

  public List<String> getArgs(String commandString) {
    assert this.isValid(commandString);
    Matcher matcher = this.getMatcher(commandString);
    matcher.matches();
    ArrayList<String> args = new ArrayList<>();
    for (int i = 1; i <= matcher.groupCount(); i++) {
      args.add(matcher.group(i));
    }
    return args;
  }

}
