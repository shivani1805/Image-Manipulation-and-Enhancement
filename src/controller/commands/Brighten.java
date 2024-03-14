package controller.commands;

import java.io.IOException;

import controller.CommandController;
import model.CommandModel;

/**
 * This class represents the brighten component extraction image manipulation function.
 */
public class Brighten implements CommandController {
  private final String[] listOfCommands;
  private static final int argumentCount = 4;
  private static final int parentImageIndex = 2;
  private static final int newImageIndex = 3;
  private static final int brightenFactor = 1;

  public Brighten(String arguments) {
    this.listOfCommands = arguments.split(" ");
  }

  @Override
  public void commandExecution(CommandModel model) throws IllegalArgumentException, IOException {
    this.checkArguments(this.listOfCommands);
    model.brightenCommand(this.listOfCommands[parentImageIndex],
            this.listOfCommands[newImageIndex],
            Integer.parseInt(this.listOfCommands[brightenFactor]));
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
