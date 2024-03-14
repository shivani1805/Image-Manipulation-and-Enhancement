package controller.commands;

import java.io.IOException;

import controller.CommandController;
import model.CommandModel;

/**
 * This class represents the levels image manipulation function.
 */
public class LevelsAdjust implements CommandController {
  private final String[] listOfCommands;

  private static final int argumentCountSplit = 8;
  private static final int argumentCountNonSplit = 6;
  private static final int parentImageIndex = 4;
  private static final int newImageIndex = 5;
  private static final int b = 1;
  private static final int m = 2;
  private static final int w = 3;

  private static final int splitKey = 6;
  private static final int percentValue = 7;

  public LevelsAdjust(String arguments) {
    this.listOfCommands = arguments.split(" ");
  }

  @Override
  public void commandExecution(CommandModel model) throws IllegalArgumentException, IOException {
    this.checkArguments(this.listOfCommands);
    if (listOfCommands.length == argumentCountSplit) {
      if (this.listOfCommands[splitKey].equalsIgnoreCase("split")) {
        if (this.listOfCommands[percentValue] != null) {
          model.levelsAdjustCommand(this.listOfCommands[parentImageIndex],
                  this.listOfCommands[newImageIndex], Integer.parseInt(this.listOfCommands[b]),
                  Integer.parseInt(this.listOfCommands[m]),
                  Integer.parseInt(this.listOfCommands[w]),
                  Double.parseDouble(this.listOfCommands[percentValue]));
        } else {
          throw new IllegalArgumentException("Percent value is missing");
        }
      } else {
        throw new IllegalArgumentException("Invalid command provided. Expected keyword : split");
      }
    } else if (listOfCommands.length == argumentCountNonSplit) {
      model.levelsAdjustCommand(this.listOfCommands[parentImageIndex],
              this.listOfCommands[newImageIndex], Integer.parseInt(this.listOfCommands[b]),
              Integer.parseInt(this.listOfCommands[m]),
              Integer.parseInt(this.listOfCommands[w]), 100);
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
