package controller.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import controller.FileController;
import controller.InputController;
import controller.InputControllerImpl;
import model.CommandModel;
import model.CommandModelImpl;

/**
 * This class represents the run function.
 */
public class Run implements FileController {
  private final String[] listOfCommands;
  private static final int argumentCount = 2;
  private static final int filePath = 1;

  CommandModel model = new CommandModelImpl();
  InputController obj = new InputControllerImpl(model);

  public Run(String arguments) {
    this.listOfCommands = arguments.split(" ");
  }

  @Override
  public void commandExecution(CommandModel model) throws IllegalArgumentException, IOException {
    this.checkArguments(this.listOfCommands);
    File file = new File(listOfCommands[filePath]);
    try {

      Scanner scan = new Scanner(file);
      obj.commandExecution(scan);
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + listOfCommands[filePath]);
      e.printStackTrace();
    }
  }

  @Override
  public void commandExecution(CommandModel model, String filePath)
          throws IllegalArgumentException {
    File file = new File(filePath);
    try {

      Scanner scan = new Scanner(file);
      obj.commandExecution(scan);
    } catch (IOException e) {
      System.err.println("File not found: " + filePath);
      e.printStackTrace();
    }

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
