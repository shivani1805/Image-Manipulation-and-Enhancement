package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class represents the image reading, saving and buffered image to 2D pixel conversion
 * capabilities.
 */
public class ImeControllerImpl implements ImeController {

  /**
   * Reads the image.
   *
   * @param path path to read from
   */
  @Override
  public BufferedImage readImage(String path) {
    BufferedImage image;
    {
      try {
        image = ImageIO.read(new File(path));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    return image;
  }

  /**
   * Converts buffered image to 2D array of pixels.
   *
   * @param image Buffered image
   */
  @Override
  public int[][] convertImageToPixel(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][] pixels = new int[width][height];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        pixels[x][y] = image.getRGB(x, y);
      }
    }
    return pixels;
  }

  /**
   * Converts 2D array of pixels to buffered image.
   *
   * @param image 2D array of pixels
   */
  @Override
  public BufferedImage convertPixelToImage(int[][] image) {
    int arrayHeight = image[0].length;
    int arrayWidth = image.length;
    BufferedImage bufferedImage = new BufferedImage(arrayWidth, arrayHeight,
            BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < arrayHeight; i++) {
      for (int j = 0; j < arrayWidth; j++) {
        bufferedImage.setRGB(j, i, image[j][i]);
      }
    }
    return bufferedImage;
  }

  /**
   * Performs the save functionality for saving images in ppm, jpg and png formats.
   *
   * @param image     2D array of pixels of our buffered image
   * @param path      the path to save the image at
   * @param extension the extension of the image to save with
   */
  @Override
  public void saveImage(BufferedImage image, String path, String extension) throws IOException {
    if (!extension.equals("ppm")) {
      try {
        File outputImage = new File(path);
        ImageIO.write(image, extension, outputImage);
      } catch (IOException e) {
        throw new IOException("error while saving image");
      }
    } else {
      try {
        int width = image.getWidth();
        int height = image.getHeight();
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        writer.write("P3\n");
        writer.write(width + " " + height + "\n");
        writer.write("255\n");

        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int rgb = image.getRGB(x, y);
            int red = (rgb >> 16) & 0xFF;
            int green = (rgb >> 8) & 0xFF;
            int blue = rgb & 0xFF;

            writer.write(red + " " + green + " " + blue + " ");
          }
          writer.write("\n");
        }

        writer.close();
        fileWriter.close();
      } catch (IOException e) {
        throw new IOException("error while saving image");
      }
    }
  }
}