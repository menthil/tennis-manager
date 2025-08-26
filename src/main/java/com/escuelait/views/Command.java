package com.escuelait.views;

import com.escuelait.controllers.Controller;

abstract class Command {

  protected Controller controller;

  Command(Controller controller) {
    this.controller = controller;
  }

  abstract void execute();

}
