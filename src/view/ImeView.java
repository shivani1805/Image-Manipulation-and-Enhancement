package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;


/**
 * This interface represents the view GUI execution.
 */
public interface ImeView {

  /**
   * Sets the action listeners.
   *
   * @param clicks the action event
   */
  void setListeners(ActionListener clicks);

  /**
   * Finds errors in the entered values.
   */
  void numericFieldError();

  /**
   * Loads the image.
   */
  void loadImage();

  /**
   * Returns the combobox.
   */
  JComboBox<String> getComboBox();

  /**
   * Sets the combo box option label text.
   *
   * @param text the text to be set
   */
  void setSelectedOptionLabelText(String text);

  /**
   * Returns the buffered image.
   */
  BufferedImage returnImage();

  /**
   * Converts image icon to buffered image.
   *
   * @param bImage the image to be converted
   */
  void loadImageIconToBuffered(BufferedImage bImage);

  /**
   * Loads the preview image.
   *
   * @param bImage the image to be loaded
   */
  void loadPreviewImage(BufferedImage bImage);

  /**
   * Loads the preview frame.
   */
  void previewFrameDispose();

  /**
   * Shows the pop-up text value.
   */
  String getPopUpTextValue();

  /**
   * Saves the image.
   */
  void saveImage();

  /**
   * Performs the preview function.
   *
   * @param previewImage the buffered image to be previewed.
   * @param operation    the operation to be performed.
   */
  void preview(BufferedImage previewImage, String operation);

  /**
   * Performs the split preview function.
   *
   * @param previewImage the buffered image to be previewed.
   * @param operation    the operation to be performed.
   */
  void splitPreview(BufferedImage previewImage, String operation);

  /**
   * Performs the preview function.
   *
   * @param previewImage the buffered image to be previewed.
   * @param operation    the operation to be performed.
   */
  void levelAdjustPreview(BufferedImage previewImage, String operation);

  /**
   * Gets the percent text.
   */
  String getPercentText();

  /**
   * Gets black value.
   */
  String getBlackValue();

  /**
   * Gets white value.
   */
  String getWhiteValue();

  /**
   * Gets mid value.
   */
  String getMidValue();

  /**
   * Error message for levels adjust.
   */
  void levelsAdjustError();

  /**
   * Displays the histogram.
   *
   * @param histogram the image that histogram has to be made for.
   */
  void displayHistogramImage(BufferedImage histogram);

  /**
   * Sets histogram label to false.
   */
  void setHistogramLabelFalse();


}
