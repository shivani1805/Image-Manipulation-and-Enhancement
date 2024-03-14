package model;

/**
 * This interface represents the newly added image manipulation functions.
 */
public interface NewImeModel {

  /**
   * Performs the blue color correction extraction function.
   */
  int[][] colorCorrectImage(double percentValue);

  /**
   * Performs the histogram creation function.
   */
  int[][] histogram();

  /**
   * Performs the levels adjustment function.
   *
   * @param b            black values
   * @param m            mid values
   * @param w            white values
   * @param percentValue the percent to split by
   */
  int[][] levelsAdjustment(int b, int m, int w, double percentValue);

  /**
   * Performs the compress function.
   *
   * @param imageArr   the image array
   * @param percentage the percent to compress
   */
  int[][] compress(int[][] imageArr, int percentage);
}
