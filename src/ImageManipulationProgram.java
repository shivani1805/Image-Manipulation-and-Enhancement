import java.io.IOException;

import view.ImeView;
import view.ImeViewImpl;
import controller.InputController;
import controller.InputControllerImpl;
import controller.ViewController;
import controller.ViewControllerImpl;
import model.CommandModel;
import model.CommandModelImpl;

/**
 * This class represents the main function of our program that takes in user input.
 */
public class ImageManipulationProgram {

  /**
   * The main function of our program that calls the controller after interpreting user input.
   */
  public static void main(String[] args) throws IOException {

    CommandModel model = new CommandModelImpl();

    if (args.length != 0) {
      InputController obj = new InputControllerImpl(model, args);
      obj.inputProcessing();
    } else {
      ImeView view = new ImeViewImpl("Image Manipulation");
      ViewController obj = new ViewControllerImpl(view);
    }

  }
}
