package controller.commands;

import java.io.IOException;

import controller.CommandController;
import model.CommandModel;

/**
 * This class represents the color correct image manipulation function.
 */
public class ColorCorrect implements CommandController {
  private final String[] listOfCommands;
  private static final int argumentCountSplit = 5;
  private static final int argumentCountNonSplit = 3;
  private static final int parentImageIndex = 1;
  private static final int newImageIndex = 2;
  private static final int splitKey = 3;
  private static final int percentValue = 4;

  public ColorCorrect(String arguments) {
    this.listOfCommands = arguments.split(" ");
  }

  @Override
  public void commandExecution(CommandModel model) throws IllegalArgumentException, IOException {
    this.checkArguments(this.listOfCommands);
    if (listOfCommands.length == argumentCountSplit) {
      if (this.listOfCommands[splitKey].equalsIgnoreCase("split")) {
        if (this.listOfCommands[percentValue] != null) {
          model.colorCorrectCommand(this.listOfCommands[parentImageIndex],
                  this.listOfCommands[newImageIndex],
                  Double.parseDouble(this.listOfCommands[percentValue]));
        } else {
          throw new IllegalArgumentException("Percent value is missing");
        }
      } else {
        throw new IllegalArgumentException("Invalid command provided. Expected keyword : split");
      }
    } else if (listOfCommands.length == argumentCountNonSplit) {
      model.colorCorrectCommand(this.listOfCommands[parentImageIndex],
              this.listOfCommands[newImageIndex], 100);
    }
  }

  @Override
  public void checkArguments(String[] commands) throws IllegalArgumentException {
    if (this.listOfCommands.length != argumentCountSplit
            && this.listOfCommands.length != argumentCountNonSplit) {
      throw new IllegalArgumentException("Incorrect command tokens provided. Expected count: "
              + argumentCountSplit + " or " + argumentCountNonSplit + " arguments, but received "
              + listOfCommands.length + " arguments.");
    }
  }

}
