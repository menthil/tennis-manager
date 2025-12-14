package com.escuelait.views.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.escuelait.controllers.Controller;
import com.escuelait.utils.Console;

public abstract class Command {

  private String regex;
  protected Console console;

  Command() {
    this.regex = (this.getName() + " " + this.getRegex()).trim();
    this.console = Console.getInstance();
  }

  protected abstract String getName();

  protected abstract String getRegex();

  boolean is(String prompt) {
    return this.getMatcher("^" + this.getName() + "\\b(.*)", prompt).matches();
  }

  private Matcher getMatcher(String regex, String prompt) {
    return Pattern.compile(regex).matcher(prompt);
  }

  boolean isValid(String prompt) {
    return this.getMatcher(this.regex, prompt).matches();
  }

  abstract String getSyntax();

  public abstract void execute(Controller controller, String prompt);

  protected String getArg(String prompt, int i) {
    return this.getArgs(prompt).get(i);
  }

  private List<String> getArgs(String prompt) {
    assert this.isValid(prompt);
    Matcher matcher = this.getMatcher(this.regex, prompt);
    matcher.matches();
    ArrayList<String> args = new ArrayList<>();
    for (int i = 1; i <= matcher.groupCount(); i++) {
      args.add(matcher.group(i));
    }
    return args;
  }

}
