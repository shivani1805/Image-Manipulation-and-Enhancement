package mock;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JComboBox;

import view.ImeView;

/**
 * This class represents the view GUI execution.
 */
public class MockView extends JFrame implements ImeView {
  private StringBuilder log;
  private String abc = "sample text";
  private String number = "3";
  int num = Integer.parseInt(number);
  private BufferedImage bufferedImage =
          new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

  private JComboBox<String> comboBox = new JComboBox<>();
  private String caption;

  /**
   * Constructs the GUI.
   *
   * @param caption the string caption to be provided
   * @param log     a stringbuilder instance for logging
   */
  public MockView(String caption, StringBuilder log) {
    this.caption = caption;
    this.log = log;
    log.append(caption);
  }

  public String returnString() {
    return caption;
  }

  public void loadImage() {
    log.append("Image loaded \n");
  }

  public void displayHistogramImage(BufferedImage histogram) {
    log.append("Histogram set to false \n");
  }

  public void setHistogramLabelFalse() {
    log.append("Histogram set to false \n");
  }


  public void loadImageIconToBuffered(BufferedImage bImage) {
    log.append("Image icon converted to buffered image \n");
  }


  public void loadPreviewImage(BufferedImage bImage) {
    log.append("Preview image loaded\n");
  }


  public void splitPreview(BufferedImage previewImage, String operation) {
    log.append("Split preview loaded\n");

  }


  public void preview(BufferedImage previewImage, String operation) {
    log.append("Preview succesfully loaded \n");
  }


  public void levelAdjustPreview(BufferedImage previewImage, String operation) {
    log.append("Levels adjust preview loaded\n");

  }

  public void setListeners(ActionListener actionListener) {
    log.append("Listeners set correctly \n");
  }

  public JComboBox<String> getComboBox() {
    log.append("Combobox is fetched \n");
    return comboBox;
  }


  public String getPercentText() {
    log.append("Percent fetched \n");
    return abc;
  }


  public void setSelectedOptionLabelText(String text) {
    log.append("Selected option label has been set \n");
  }


  public BufferedImage returnImage() {
    log.append("Switch case entered \n");
    return bufferedImage;
  }


  public void numericFieldError() {
    log.append("Numerical field error \n");
  }


  public void levelsAdjustError() {
    log.append("Levels adjust error \n");
  }


  public void previewFrameDispose() {
    log.append("Apply reached \n");
  }


  public String getPopUpTextValue() {
    log.append("Pop up text value reached \n");
    return abc;
  }


  public void saveImage() {
    log.append("Image saved. \n");
  }


  public String getBlackValue() {
    log.append("Black value reached \n");
    return number;
  }


  public String getWhiteValue() {
    log.append("White value reached \n");
    return number;
  }


  public String getMidValue() {
    log.append("Mid value reached \n");
    return number;
  }
}