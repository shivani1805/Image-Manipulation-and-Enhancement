package model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This interface represents the image storage and manipulation functionality for all commands.
 */
public interface CommandModel {

  /**
   * Fetches the image from the image map for applying blur, and stores it back.
   *
   * @param parentKey    the key of the image to be fetched
   * @param newImageKey  the key that the new image has to be stored with
   * @param percentValue the percentage to split the image by
   */
  void blurCommand(String parentKey, String newImageKey, double percentValue);

  /**
   * Fetches the image from the image map for applying luma, and stores it back.
   *
   * @param parentKey    the key of the image to be fetched
   * @param newImageKey  the key that the new image has to be stored with
   * @param percentValue the percentage to split the image by
   */
  void lumaCommand(String parentKey, String newImageKey, double percentValue);

  /**
   * Loads the image.
   *
   * @param imagePath the path of the image to be loaded
   * @param imageKey  the key that the new image has to be stored with
   * @throws FileNotFoundException if the image is not found at that path.
   */
  void loadCommand(String imagePath, String imageKey) throws FileNotFoundException;

  /**
   * Saves the image.
   *
   * @param imagePath the path of the image to be saved at
   * @param imageKey  the key that the image has to be searched with
   * @param imageType the extension of the image
   * @throws IOException if there are discrepancies
   */
  void saveCommand(String imagePath, String imageKey, String imageType) throws IOException;

  /**
   * Fetches the image from the image map for applying value, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  void valueCommand(String parentKey, String newImageKey);

  /**
   * Fetches the image from the image map for applying intensity, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  void intensityCommand(String parentKey, String newImageKey);

  /**
   * Fetches the image from the image map for applying horizontal flip, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  void horizontalCommand(String parentKey, String newImageKey);

  /**
   * Fetches the image from the image map for applying vertical flip, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  void verticalCommand(String parentKey, String newImageKey);

  /**
   * Fetches the image from the image map for applying sharpen, and stores it back.
   *
   * @param parentKey    the key of the image to be fetched
   * @param newImageKey  the key that the new image has to be stored with
   * @param percentValue the percentage to split the image by
   */
  void sharpenCommand(String parentKey, String newImageKey, double percentValue);

  /**
   * Fetches the image from the image map for applying sepia, and stores it back.
   *
   * @param parentKey    the key of the image to be fetched
   * @param newImageKey  the key that the new image has to be stored with
   * @param percentValue the percentage to split the image by
   */
  void sepiaCommand(String parentKey, String newImageKey, double percentValue);

  /**
   * Fetches the image from the image map for applying red component, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  void redComponentCommand(String parentKey, String newImageKey);

  /**
   * Fetches the image from the image map for applying green component, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  void greenComponentCommand(String parentKey, String newImageKey);

  /**
   * Fetches the image from the image map for applying blue component, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  void blueComponentCommand(String parentKey, String newImageKey);

  /**
   * Fetches the image from the image map for applying red component, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   * @param value       the value to be brightened by
   */
  void brightenCommand(String parentKey, String newImageKey, int value);

  /**
   * Fetches the images from the image map for applying combine, and stores it back.
   *
   * @param newImageKey   the key that the new image has to be stored with
   * @param redImageKey   the key of the red image to be fetched
   * @param greenImageKey the key of the green image to be fetched
   * @param blueImageKey  the key of the blue image to be fetched
   */
  void rgbCombineCommand(String newImageKey, String redImageKey, String greenImageKey,
                         String blueImageKey);

  /**
   * Fetches the image from the image map for applying split, and stores all parts back.
   *
   * @param parentKey     the key that the target image has to be searched with
   * @param redImageKey   the key of the red image to be stored
   * @param greenImageKey the key of the green image to be stored
   * @param blueImageKey  the key of the blue image to be stored
   */
  void rgbSplitCommand(String parentKey, String redImageKey, String greenImageKey,
                       String blueImageKey);

  /**
   * Fetches the images from the image map for creating histogram, and stores it back.
   *
   * @param newImageKey the key that the new image has to be stored with
   * @param parentKey   the key that the target image has to be searched with
   */
  void histogramCommand(String parentKey, String newImageKey);

  /**
   * Fetches the image from the image map for applying color correct, and stores it back.
   *
   * @param parentKey    the key of the image to be fetched
   * @param newImageKey  the key that the new image has to be stored with
   * @param percentValue the percentage to split the image by
   */
  void colorCorrectCommand(String parentKey, String newImageKey, double percentValue);

  /**
   * Fetches the image from the image map for applying levels adjustment, and stores it back.
   *
   * @param parentKey    the key of the image to be fetched
   * @param newImageKey  the key that the new image has to be stored with
   * @param percentValue the percentage to split the image by
   * @param b            black values
   * @param m            mid values
   * @param w            white values
   */
  void levelsAdjustCommand(String parentKey, String newImageKey, int b,
                           int m, int w, double percentValue);

  /**
   * Fetches the image from the image map for applying compress, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   * @param percentage  the percentage to compress the image by
   */
  void compressCommand(String parentKey, String newImageKey, int percentage);
}
