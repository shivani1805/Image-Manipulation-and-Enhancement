package controller.commands;

import java.io.IOException;

import controller.CommandController;
import model.CommandModel;

/**
 * This class represents the histogram creation image manipulation function.
 */
public class Histogram implements CommandController {
  private final String[] listOfCommands;
  private static final int argumentCount = 3;
  private static final int parentImageIndex = 1;
  private static final int newImageIndex = 2;

  public Histogram(String arguments) {
    this.listOfCommands = arguments.split(" ");
  }

  @Override
  public void commandExecution(CommandModel model) throws IllegalArgumentException, IOException {
    this.checkArguments(this.listOfCommands);
    model.histogramCommand(this.listOfCommands[parentImageIndex],
            this.listOfCommands[newImageIndex]);
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