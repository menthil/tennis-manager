package com.escuelait.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {
  CREATE_REFEREE("createReferee", "name:(.+);password:(.+)", "createReferee name:<name>;password:<password>"),
  LOGIN("login", "name:(.+);password:(.+)", "login name:<name>;password:<password>"),
  EXIT("exit", "", "exit"),
  HELP("help", "", "help"),
  CREATE_PLAYER("createPlayer", "name:(.+)", "createPlayer name:<name>"),
  READ_PLAYERS("readPlayers", "", "readPlayers"),
  CREATE_MATCH("createMatch", "sets:(.+);ids:(\\d+),(\\d+)", "createMatch sets:<3|5>;ids:<id1>,<id2>"),
  READ_PLAYER("readPlayer:(\\d+)", "", "readPlayer:<id>"),
  READ_MATCH("readMatch:(\\d+)", "", "readMatch:<id>"),
  LOGOUT("logout", "", "logout"),
  LACK_SERVICE("lackService", "", "lackService"),
  POINT_SERVICE("pointService", "", "pointService"),
  POINT_REST("pointRest", "", "pointRest"),
  FINISH_MATCH("finishMatch", "", "finishMatch"),
  ;

  private String name;
  private String regex;
  private String syntax;

  private Command(String name, String regex, String syntax) {
    this.name = name;
    this.regex = (this.name + " " + regex).trim();
    this.syntax = syntax;
  }

  boolean is(String prompt) {
    return this.getMatcher("^" + this.name + "\\b(.*)", prompt).matches();
  }

  private Matcher getMatcher(String regex, String prompt) {
    assert prompt != null;
    return Pattern.compile(regex).matcher(prompt);
  }

  boolean isValid(String prompt) {
    return this.getMatcher(prompt).matches();
  }

  private Matcher getMatcher(String prompt) {
    return this.getMatcher(this.regex, prompt);
  }

  public List<String> getArgs(String prompt) {
    assert this.isValid(prompt);
    Matcher matcher = this.getMatcher(prompt);
    matcher.matches();
    ArrayList<String> args = new ArrayList<>();
    for (int i = 1; i <= matcher.groupCount(); i++) {
      args.add(matcher.group(i));
    }
    return args;
  }

  public String getSyntax() {
    return this.syntax;
  }

}
