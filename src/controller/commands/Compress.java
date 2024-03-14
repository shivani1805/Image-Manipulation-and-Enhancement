package controller.commands;

import java.io.IOException;

import controller.CommandController;
import model.CommandModel;

/**
 * This class represents the color correct image manipulation function.
 */
public class Compress implements CommandController {
  private final String[] listOfCommands;
  private static final int argumentCount = 4;
  private static final int parentImageIndex = 2;
  private static final int newImageIndex = 3;
  private static final int percentage = 1;

  public Compress(String arguments) {
    this.listOfCommands = arguments.split(" ");
  }

  @Override
  public void commandExecution(CommandModel model) throws IllegalArgumentException, IOException {
    this.checkArguments(this.listOfCommands);
    model.compressCommand(this.listOfCommands[parentImageIndex],
            this.listOfCommands[newImageIndex], Integer.parseInt(this.listOfCommands[percentage]));
  }

  @Override
  public void checkArguments(String[] commands) throws IllegalArgumentException {
    if (this.listOfCommands.length != argumentCount) {
      throw new IllegalArgumentException("Incorrect command tokens provided. Expected count: "
              + argumentCount + " arguments, but received "
              + listOfCommands.length + " arguments.");
    }
    if (Integer.parseInt(this.listOfCommands[percentage]) < 0
            || Integer.parseInt(this.listOfCommands[percentage]) > 100) {
      throw new IllegalArgumentException("The percentage values are not between 0 and 100.");
    }
  }
}
