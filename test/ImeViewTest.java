import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ViewControllerImpl;
import mock.MockView;

import static org.junit.Assert.assertEquals;

import controller.ViewController;
import view.ImeView;

/**
 * A JUnit test class for the ImeView.
 */
public class ImeViewTest {

  private ImeView mock;
  private ViewController controller;


  @Test
  public void constructorTest() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    assertEquals("This is the test \n", log.toString());
  }

  @Test
  public void testLoad() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Load Image");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
            + "\nHistogram set to false \nImage loaded \nSwitch case entered "
            + "\nHistogram set to false \n", log.toString());
  }

  @Test
  public void testComboBox() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Drop down");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
            + "\nCombobox is fetched \nImage loaded \nSwitch case entered "
            + "\nHistogram set to false \n", log.toString());
  }

  @Test
  public void testBlur() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Split-Blur");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
            + "\nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
            + "\nNumerical field error \n", log.toString());

  }

  @Test
  public void testSepia() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Split-Sepia");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
            + "\nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
            + "\nNumerical field error \n", log.toString());

  }

  @Test
  public void testLuma() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Split-Luma");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
                    + "\nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
                    + "\nNumerical field error \n",
            log.toString());
  }

  @Test
  public void testSharpen() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Split-Sharpen");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
            + "\nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
            + "\nNumerical field error \n", log.toString());
  }

  @Test
  public void testColorCorrect() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Split-Color-Correct");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
                    + "\nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
                    + "\nNumerical field error \n",
            log.toString());
  }

  @Test
  public void testLevelsAdjust() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Split-Levels-Adjust");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
            + "\nBlack value reached \nBlack value reached \nMid value reached \nWhite value "
            + "reached \nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
            + "\nNumerical field error \n", log.toString());
  }

  @Test
  public void testRedComponent() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Red Component");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
            + "\nBlack value reached \nBlack value reached \nMid value reached \nWhite value "
            + "reached \nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
            + "\nNumerical field error \n", log.toString());
  }

  @Test
  public void testGreenComponent() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Red Component");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
            + "\nBlack value reached \nBlack value reached \nMid value reached \nWhite value "
            + "reached \nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
            + "\nNumerical field error \n", log.toString());
  }

  @Test
  public void testBlueComponent() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Red Component");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
            + "\nBlack value reached \nBlack value reached \nMid value reached \nWhite value "
            + "reached \nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
            + "\nNumerical field error \n", log.toString());
  }

  @Test
  public void testHorizontalFlip() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Horizontal Flip");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
                    + "\nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
                    + "\nNumerical field error \n",
            log.toString());
  }

  @Test
  public void testVerticalFlip() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Vertical Flip");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
                    + "\nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
                    + "\nNumerical field error \n",
            log.toString());
  }

  @Test
  public void testCompress() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Compress");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
                    + "\nSwitch case entered \nPercent fetched \nPercent fetched \nPercent fetched "
                    + "\nNumerical field error \n",
            log.toString());
  }

  @Test
  public void testApply() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Apply");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
            + "\nPercent fetched \nPercent fetched \nPercent fetched \n"
            + "Numerical field error \n", log.toString());
  }

  @Test
  public void testSave() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Save");
    controller.actionPerformed(mockEvent);
    assertEquals("This is the test \nListeners set correctly \nListeners set correctly "
            + "\nImage saved. \n", log.toString());

  }

  @Test
  public void testExit() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "Exit Button");
    controller.actionPerformed(mockEvent);
    assertEquals("GUI exited", log.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalid() {
    StringBuilder log = new StringBuilder();
    mock = new MockView("This is the test \n", log);
    controller = new ViewControllerImpl(mock);
    ActionListener dummyListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Dummy action performed: " + e.getActionCommand());
      }
    };
    mock.setListeners(dummyListener);
    ActionEvent mockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            "xyz");
    controller.actionPerformed(mockEvent);
  }

}