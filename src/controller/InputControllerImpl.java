package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.Blur;
import controller.commands.ColorCorrect;
import controller.commands.Compress;
import controller.commands.Histogram;
import controller.commands.Horizontal;
import controller.commands.Intensity;
import controller.commands.LevelsAdjust;
import controller.commands.Load;
import controller.commands.RedComponent;
import controller.commands.GreenComponent;
import controller.commands.BlueComponent;
import controller.commands.Brighten;
import controller.commands.RgbCombine;
import controller.commands.Luma;
import controller.commands.RgbSplit;
import controller.commands.Run;
import controller.commands.Save;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.Value;
import controller.commands.Vertical;
import model.CommandModel;

/**
 * This class represents the command detection and execution capabilities.
 */
public class InputControllerImpl implements InputController {

  private CommandModel model = null;
  private String[] args;
  protected Map<String, Function<Scanner, CommandController>> imeCommands;
  protected Map<String, Function<Scanner, FileController>> fileCommands;

  /**
   * Sets up the hashmap of commands.
   */
  private void setupMap() {
    imeCommands = new HashMap<>();
    imeCommands.put("blur", s -> new Blur(s.nextLine()));
    imeCommands.put("load", s -> new Load(s.nextLine()));
    imeCommands.put("save", s -> new Save(s.nextLine()));
    imeCommands.put("luma", s -> new Luma(s.nextLine()));
    imeCommands.put("value-component", s -> new Value(s.nextLine()));
    imeCommands.put("intensity-component", s -> new Intensity(s.nextLine()));
    imeCommands.put("horizontal-flip", s -> new Horizontal(s.nextLine()));
    imeCommands.put("vertical-flip", s -> new Vertical(s.nextLine()));
    imeCommands.put("sharpen", s -> new Sharpen(s.nextLine()));
    imeCommands.put("sepia", s -> new Sepia(s.nextLine()));
    imeCommands.put("red-component", s -> new RedComponent(s.nextLine()));
    imeCommands.put("green-component", s -> new GreenComponent(s.nextLine()));
    imeCommands.put("blue-component", s -> new BlueComponent(s.nextLine()));
    imeCommands.put("brighten", s -> new Brighten(s.nextLine()));
    imeCommands.put("rgb-combine", s -> new RgbCombine(s.nextLine()));
    imeCommands.put("rgb-split", s -> new RgbSplit(s.nextLine()));
    imeCommands.put("histogram", s -> new Histogram(s.nextLine()));
    imeCommands.put("color-correct", s -> new ColorCorrect(s.nextLine()));
    imeCommands.put("levels-adjust", s -> new LevelsAdjust(s.nextLine()));
    imeCommands.put("compress", s -> new Compress(s.nextLine()));
    imeCommands.put("run", s -> new Run(s.nextLine()));
  }

  /**
   * Executes up the hashmap of commands.
   */
  private void setupFileMap() {
    fileCommands = new HashMap<>();
    fileCommands.put("run", s -> new Run(s.nextLine()));
  }

  /**
   * Constructs an object of the input controller imp file.
   *
   * @param model an object of the CommandModel class
   */
  public InputControllerImpl(CommandModel model) {
    this.model = model;
    this.setupMap();
  }

  /**
   * Constructs an object of the input controller imp file.
   *
   * @param model an object of the CommandModel class
   * @param args  an array of the command line arguments
   */
  public InputControllerImpl(CommandModel model, String[] args) {
    if (args.length != 0) {

      if (!args[0].equals("-file") && !args[0].equals("-text")) {
        throw new IllegalArgumentException("Expected keyword : \"-file\" or \"-text\" "
                + "but received : " + args[0]);
      }
      if (args[0].equals("-file") && args.length == 1) {
        throw new IllegalArgumentException("Expected filename missing");
      }
      if (args[0].equals("-text") && args.length != 1) {
        throw new IllegalArgumentException("Invalid command/extra arguments received");
      }

      if (args[0].equals("-file")) {
        this.model = model;
        this.setupFileMap();
        this.args = args;
      } else {
        this.model = model;
        this.setupMap();
        this.args = args;

      }
    }
  }

  /**
   * Takes input from the command line interface via scanner object.
   *
   * @throws IOException when incorrect input provided
   */
  public void inputProcessing() throws IOException {
    if (this.args != null) {
      if (this.args[0].equals("-file")) {
        this.commandFileExecution(new Scanner(args[1]), args[1]);
      } else if (this.args[0].equals("-text")) {
        System.out.print("Enter a command: ");
        Scanner scanner = new Scanner(System.in);
        this.commandExecution(scanner);
      }

    }

  }

  /**
   * Excutes a file of commands.
   *
   * @param scan     the scanner object with arguments
   * @param filePath the path of the file
   * @throws IOException when incorrect input provided
   */
  public void commandFileExecution(Scanner scan, String filePath) throws IOException {

    FileController cObj;
    try {
      Function<Scanner, FileController> cmd =
              fileCommands.getOrDefault("run", null);
      if (cmd == null) {
        throw new IllegalArgumentException();
      } else {
        cObj = cmd.apply(scan);
        cObj.commandExecution(this.model, filePath);
      }
    } catch (IllegalArgumentException e) {
      System.out.print(e.getMessage() + '\n');
    }
  }

  /**
   * Applies the corresponding action from the hashmap with the command being executed.
   *
   * @param scan the scanner object
   * @throws IOException when incorrect input file provided
   */
  public void commandExecution(Scanner scan) throws IOException {
    String command;
    CommandController cObj;
    try {
      while (scan.hasNextLine()) {
        if (!scan.hasNext()) {
          break;
        }
        String inputCommand = scan.next();
        if (inputCommand.startsWith("#")) {
          scan.nextLine();
          continue;
        }
        if (inputCommand.equalsIgnoreCase("q") || inputCommand.equalsIgnoreCase("quit")) {
          return;
        }
        command = inputCommand.split(" ")[0];

        Function<Scanner, CommandController> cmd =
                imeCommands.getOrDefault(command, null);
        if (cmd == null) {
          throw new IllegalArgumentException();
        } else {
          cObj = cmd.apply(scan);
          cObj.commandExecution(this.model);
          System.out.print("Command executed : " + command + '\n');
        }
      }
    } catch (IOException | IllegalArgumentException e) {
      System.out.print(e.getMessage() + '\n');
    }
  }

}

