package controller;

import java.io.IOException;
import java.util.Scanner;

/**
 * This interface represents the command detection and execution capabilities.
 */
public interface InputController {

  /**
   * Takes input from the command line interface via scanner object.
   *
   * @throws IOException when incorrect input provided
   */
  void inputProcessing() throws IOException;

  /**
   * Applies the corresponding action from the hashmap with the command being executed.
   *
   * @param scan the scanner object
   * @throws IOException when incorrect input file provided
   */
  void commandExecution(Scanner scan) throws IOException;
}
