package com.escuelait.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandType {
  CREATE_REFEREE("createReferee", "name:(.+);password:(.+)", "name:<name>;password:<password>"),
  LOGIN("login", "name:(.+);password:(.+)", "name:<name>;password:<password>"),
  EXIT("exit", "", ""),
  HELP("help", "", "");
  ;

  private String name;
  private String regex;
  private String parameters;

  private CommandType(String name, String regex, String parameters) {
    this.name = name;
    this.regex = (this.name + " " + regex).trim();
    this.parameters = parameters;
  }

  boolean is(String commandString) {
    return this.getMatcher("^" + this.name + "\\b(.*)", commandString).matches();
  }

  private Matcher getMatcher(String regex, String commandString) {
    assert commandString != null;
    return Pattern.compile(regex).matcher(commandString);
  }

  boolean isValid(String commandString) {
    return this.getMatcher(commandString).matches();
  }

  private Matcher getMatcher(String commandString) {
    return this.getMatcher(this.regex, commandString);
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

  public String getCommand() {
    return (this.name + " " + this.parameters).trim();
  }

}
