package controller;

import java.io.IOException;

import model.CommandModel;

/**
 * This interface represents the CLI implementation functionality of the code.
 */
public interface FileController extends CommandController {

  /**
   * Checks the arguments provided.
   *
   * @param model    the arguments provided via scanner object
   * @param filepath path of the command file
   * @throws IllegalArgumentException if there is any discrepancy in the argumements
   * @throws IOException              if there is any input output discrepancy
   */
  void commandExecution(CommandModel model, String filepath)
          throws IllegalArgumentException, IOException;
}
