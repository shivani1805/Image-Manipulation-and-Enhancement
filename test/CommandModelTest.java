
import org.junit.Test;


import model.CommandModel;
import model.CommandModelImpl;

/**
 * A JUnit test class for the CommandModel.
 */
public class CommandModelTest {

  private CommandModel model;


  @Test(expected = IllegalArgumentException.class)
  public void testBlurPercentLessZero() {
    model = new CommandModelImpl();
    model.blurCommand("test", "test2", -20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLBlurPercentGreater100() {
    model = new CommandModelImpl();
    model.blurCommand("test", "test2", 107);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSharpenPercentLessZero() {
    model = new CommandModelImpl();
    model.sharpenCommand("test", "test2", -20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSharpenPercentGreater100() {
    model = new CommandModelImpl();
    model.sharpenCommand("test", "test2", 107);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSepiaPercentLessZero() {
    model = new CommandModelImpl();
    model.sepiaCommand("test", "test2", -20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSepiaPercentGreater100() {
    model = new CommandModelImpl();
    model.sepiaCommand("test", "test2", 107);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorCorrectPercentLessZero() {
    model = new CommandModelImpl();
    model.colorCorrectCommand("test", "test2", -20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorCorrectPercentGreater100() {
    model = new CommandModelImpl();
    model.colorCorrectCommand("test", "test2", 107);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLevelsAdjustPercentLessZero() {
    model = new CommandModelImpl();
    model.levelsAdjustCommand("test", "test2", 10, 20, 30, -20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLevelsAdjustPercentGreater100() {
    model = new CommandModelImpl();
    model.levelsAdjustCommand("test", "test2", 10, 20, 30, 190);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testBMWValues() {
    model = new CommandModelImpl();
    model.levelsAdjustCommand("test", "test2", -1, 20, 30, 190);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBMWValues2() {
    model = new CommandModelImpl();
    model.levelsAdjustCommand("test", "test2", 10, -2, 30, 190);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBMWValues3() {
    model = new CommandModelImpl();
    model.levelsAdjustCommand("test", "test2", 10, 20, -2, 190);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBMWValues4() {
    model = new CommandModelImpl();
    model.levelsAdjustCommand("test", "test2", 290, 20, 30, 190);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBMWValues5() {
    model = new CommandModelImpl();
    model.levelsAdjustCommand("test", "test2", 10, 290, 30, 190);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBMWValues6() {
    model = new CommandModelImpl();
    model.levelsAdjustCommand("test", "test2", 10, 20, 300, 190);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBMWValues7() {
    model = new CommandModelImpl();
    model.levelsAdjustCommand("test", "test2", 230, 20, 30, 190);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBMWValues8() {
    model = new CommandModelImpl();
    model.levelsAdjustCommand("test", "test2", 10, 200, 30, 190);
  }


}
