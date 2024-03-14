package controller;

import java.io.IOException;

import model.CommandModel;

/**
 * This interface represents the execution and argument check for each command.
 */
public interface CommandController {

  /**
   * Executes the command.
   *
   * @param model object of the command model
   * @throws IllegalArgumentException if there is any discrepancy in the command
   * @throws IOException              if there is any input output discrepancy
   */
  void commandExecution(CommandModel model) throws IllegalArgumentException, IOException;

  /**
   * Checks the arguments provided.
   *
   * @param commands the arguments provided via scanner object
   * @throws IllegalArgumentException if there is any discrepancy in the argumements
   */
  void checkArguments(String[] commands) throws IllegalArgumentException;
}
