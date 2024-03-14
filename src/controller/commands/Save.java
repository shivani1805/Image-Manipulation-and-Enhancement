package controller.commands;

import java.io.IOException;

import controller.CommandController;
import model.CommandModel;

/**
 * This class represents the save function.
 */
public class Save implements CommandController {

  private static final int argumentCount = 3;
  private static final int pathImageIndex = 1;
  private static final int destImageIndex = 2;
  private final String[] listOfCommands;
  private String imageType;

  /**
   * This represents the save constructor and takes in input
   * arguments from the input controller. The function parses
   * the command and checks for the image format.
   */
  public Save(String inputArguments) {
    this.listOfCommands = inputArguments.split(" ");
    this.imageType = this.listOfCommands[pathImageIndex].split("[.]")[1];

  }

  @Override
  public void commandExecution(CommandModel model) throws IllegalArgumentException, IOException {
    this.checkArguments(this.listOfCommands);
    model.saveCommand(this.listOfCommands[pathImageIndex],
            this.listOfCommands[destImageIndex],
            this.imageType);
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
