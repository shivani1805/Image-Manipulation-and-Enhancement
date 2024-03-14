package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This interface represents the image reading, saving and buffered image to 2D pixel conversion
 * capabilities.
 */
public interface ImeController {

  /**
   * Reads the buffered image.
   *
   * @param path the path of the image.
   */
  BufferedImage readImage(String path);

  /**
   * Converts the buffered image to a 2D pixel array.
   *
   * @param image the buffered image
   */
  int[][] convertImageToPixel(BufferedImage image);

  /**
   * Converts the 2D array of pixels back to a buffered image.
   *
   * @param image the 2D array of pixels
   */
  BufferedImage convertPixelToImage(int[][] image);

  /**
   * Saves the image to the provided path.
   *
   * @param image     the image to be saved
   * @param path      the path the image has to be saved at
   * @param extension the extension of image to be saved
   */
  void saveImage(BufferedImage image, String path, String extension) throws IOException;
}
