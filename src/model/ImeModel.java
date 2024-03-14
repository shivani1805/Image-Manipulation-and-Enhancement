package model;

import java.util.List;

/**
 * This interface represents the image manipulation functions.
 */
public interface ImeModel extends NewImeModel {

  /**
   * Performs the vertical flip function.
   */
  int[][] flipVertical();

  /**
   * Performs the horizontal flip function.
   */
  int[][] flipHorizontal();

  /**
   * Performs the brighten/ darken function.
   *
   * @param brightFactor the degree to brighten/ darken by
   */
  int[][] brighten(int brightFactor);

  /**
   * Performs the split function.
   */
  List<int[][]> split();

  /**
   * Performs the combine function.
   */
  int[][] combineChannel();

  /**
   * Performs the blur function.
   */
  int[][] blur(double percentValue);

  /**
   * Performs the sharpen function.
   */
  int[][] sharpen(double percentValue);

  /**
   * Performs the sepia filter application function.
   */
  int[][] sepia(double percentValue);

  /**
   * Performs the value filter application function.
   */
  int[][] value();

  /**
   * Performs the intensity filter application function.
   */
  int[][] intensity();

  /**
   * Performs the luma filter application function.
   */
  int[][] luma(double percentValue);

  /**
   * Performs the red component extraction function.
   */
  int[][] rComponent();

  /**
   * Performs the green component extraction function.
   */
  int[][] gComponent();

  /**
   * Performs the blue component extraction function.
   */
  int[][] bComponent();

}
