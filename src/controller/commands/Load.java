package controller.commands;

import java.io.IOException;

import controller.CommandController;
import model.CommandModel;

/**
 * This class represents the load function.
 */
public class Load implements CommandController {
  private final String[] listOfCommands;
  private static final int argumentCount = 3;
  private static final int parentImageIndex = 2;
  private static final int pathImageIndex = 1;

  public Load(String arguments) {
    this.listOfCommands = arguments.split(" ");
  }

  @Override
  public void commandExecution(CommandModel model) throws IllegalArgumentException, IOException {
    this.checkArguments(this.listOfCommands);
    model.loadCommand(this.listOfCommands[pathImageIndex],
            this.listOfCommands[parentImageIndex]);
  }

  @Override
  public void checkArguments(String[] commands) throws IllegalArgumentException {
    if (this.listOfCommands.length != argumentCount) {
      throw new IllegalArgumentException("Incorrect command tokens provided. Expected count: "
              + argumentCount + " arguments, but received "
              + listOfCommands.length + " arguments.");
    }
  }
}
