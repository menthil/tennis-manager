package com.escuelait.views;

import com.escuelait.controllers.Controller;

interface Command {

  void execute(Controller controller);

}
