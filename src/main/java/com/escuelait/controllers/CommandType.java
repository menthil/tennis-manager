package com.escuelait.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandType {
  CREATE_REFEREE("createReferee", "name:(.+);password:(.+)", "createReferee name:<name>;password:<password>"),
  LOGIN("login", "name:(.+);password:(.+)", "login name:<name>;password:<password>"),
  EXIT("exit", "", "exit"),
  HELP("help", "", "help"),
  CREATE_PLAYER("createPlayer", "name:(.+)", "createPlayer name:<name>"),
  READ_PLAYERS("readPlayers", "", "readPlayers"),
  CREATE_MATCH("createMatch", "sets:(.+);ids:(\\d+),(\\d+)", "createMatch sets:<3|5>;ids:<id1>,<id2>"),
  READ_PLAYER("readPlayer:\\d+", "", "readPlayer:<id>"),
  READ_MATCH("readMatch:\\d+", "", "readMatch:<id>"),
  LOGOUT("logout", "", "logout"),
  LACK_SERVICE("lackService", "", "lackService"),
  POINT_SERVICE("pointService", "", "pointService"),
  POINT_REST("pointRest", "", "pointRest"),
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
    return this.parameters;
  }

}
