package com.escuelait.views.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.escuelait.controllers.Controller;
import com.escuelait.utils.Console;

public abstract class Command {

  protected Controller controller;
  protected String prompt;
  private String regex;
  protected Console console;

  Command(Controller controller, String prompt) {
    this.controller = controller;
    this.prompt = prompt;
    this.regex = (this.getName() + " " + this.getRegex()).trim();
    this.console = Console.getInstance();
  }

  protected abstract String getName();

  protected abstract String getRegex();

  boolean is() {
    return this.getMatcher("^" + this.getName() + "\\b(.*)").matches();
  }

  private Matcher getMatcher(String regex) {
    return Pattern.compile(regex).matcher(this.prompt);
  }

  boolean isValid() {
    return this.getMatcher(this.regex).matches();
  }

  abstract String getSyntax();

  public abstract void execute();

  List<String> getArgs() {
    assert this.isValid();
    Matcher matcher = this.getMatcher(this.regex);
    matcher.matches();
    ArrayList<String> args = new ArrayList<>();
    for (int i = 1; i <= matcher.groupCount(); i++) {
      args.add(matcher.group(i));
    }
    return args;
  }

}
