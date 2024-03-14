package controller.commands;

import java.io.IOException;

import controller.CommandController;
import model.CommandModel;

/**
 * This class represents the rgb combine image manipulation function.
 */
public class RgbCombine implements CommandController {
  private final String[] listOfCommands;
  private static final int argumentCount = 5;
  private static final int finalImageName = 1;
  private static final int redImageIndex = 2;
  private static final int greenImageIndex = 3;
  private static final int blueImageIndex = 4;

  public RgbCombine(String arguments) {
    this.listOfCommands = arguments.split(" ");
  }

  @Override
  public void commandExecution(CommandModel model) throws IllegalArgumentException, IOException {
    this.checkArguments(this.listOfCommands);
    model.rgbCombineCommand(this.listOfCommands[finalImageName],
            this.listOfCommands[redImageIndex], this.listOfCommands[greenImageIndex],
            this.listOfCommands[blueImageIndex]);
  }

  @Override
  public void checkArguments(String[] commands) throws IllegalArgumentException {
    if (this.listOfCommands.length != argumentCount) {
      throw new IllegalArgumentException("Incorrect command tokens provided. Expected count: "
              + argumentCount + " arguments, but received " + listOfCommands.length
              + " arguments.");
    }
  }

}
