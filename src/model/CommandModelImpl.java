package model;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.NoSuchElementException;

import controller.ImeController;
import controller.ImeControllerImpl;

/**
 * This class represents the image storage and manipulation functionality for all commands.
 */
public class CommandModelImpl implements CommandModel {
  private final ImageMap imageCache;
  private ImeModel obj;
  private ImeController obj1 = new ImeControllerImpl();
  private int[][] imageArr;
  private int[][] newImageArr;

  /**
   * Constructs the CommandModelImpl object and
   * initialises the image Map.
   */
  public CommandModelImpl() {
    this.imageCache = ImageMapImpl.getMap();
  }

  /**
   * Fetches the image from the image map for applying blur, and stores it back.
   *
   * @param parentKey    the key of the image to be fetched
   * @param newImageKey  the key that the new image has to be stored with
   * @param percentValue the percentage to split the image by
   */
  @Override
  public void blurCommand(String parentKey, String newImageKey, double percentValue) {
    if (percentValue < 0 || percentValue > 100) {
      throw new IllegalArgumentException("Invalid percent value");
    }
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.blur(percentValue);
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying luma, and stores it back.
   *
   * @param parentKey    the key of the image to be fetched
   * @param newImageKey  the key that the new image has to be stored with
   * @param percentValue the percentage to split the image by
   */
  @Override
  public void lumaCommand(String parentKey, String newImageKey, double percentValue) {
    if (percentValue < 0 || percentValue > 100) {
      throw new IllegalArgumentException("Invalid percent value");
    }
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.luma(percentValue);
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying value, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  @Override
  public void valueCommand(String parentKey, String newImageKey) {
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.value();
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying intensity, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  @Override
  public void intensityCommand(String parentKey, String newImageKey) {
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.intensity();
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying horizontal flip, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  @Override
  public void horizontalCommand(String parentKey, String newImageKey) {
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.flipHorizontal();
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying vertical flip, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  @Override
  public void verticalCommand(String parentKey, String newImageKey) {
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.flipVertical();
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying sharpen, and stores it back.
   *
   * @param parentKey    the key of the image to be fetched
   * @param newImageKey  the key that the new image has to be stored with
   * @param percentValue the percentage to split the image by
   */
  @Override
  public void sharpenCommand(String parentKey, String newImageKey, double percentValue) {
    if (percentValue < 0 || percentValue > 100) {
      throw new IllegalArgumentException("Invalid percent value");
    }
    if (this.imageCache.searchKey(parentKey)) {

      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.sharpen(percentValue);
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying sepia, and stores it back.
   *
   * @param parentKey    the key of the image to be fetched
   * @param newImageKey  the key that the new image has to be stored with
   * @param percentValue the percentage to split the image by
   */
  @Override
  public void sepiaCommand(String parentKey, String newImageKey, double percentValue) {
    if (percentValue < 0 || percentValue > 100) {
      throw new IllegalArgumentException("Invalid percent value");
    }
    if (this.imageCache.searchKey(parentKey)) {

      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.sepia(percentValue);
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying red component, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  @Override
  public void redComponentCommand(String parentKey, String newImageKey) {
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.rComponent();
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying green component, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  @Override
  public void greenComponentCommand(String parentKey, String newImageKey) {
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.gComponent();
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying blue component, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   */
  @Override
  public void blueComponentCommand(String parentKey, String newImageKey) {
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.bComponent();
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying red component, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   * @param value       the value to be brightened by
   */
  @Override
  public void brightenCommand(String parentKey, String newImageKey, int value) {
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.brighten(value);
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the images from the image map for applying combine, and stores it back.
   *
   * @param newImageKey   the key that the new image has to be stored with
   * @param redImageKey   the key of the red image to be fetched
   * @param greenImageKey the key of the green image to be fetched
   * @param blueImageKey  the key of the blue image to be fetched
   */
  public void rgbCombineCommand(String newImageKey, String redImageKey, String greenImageKey,
                                String blueImageKey) {
    int[][] redImageArr;
    int[][] greenImageArr;
    int[][] blueImageArr;
    if ((this.imageCache.searchKey(redImageKey)) && (this.imageCache.searchKey(greenImageKey))
            && (this.imageCache.searchKey(blueImageKey))) {
      redImageArr = this.imageCache.fetchImage(redImageKey);
      greenImageArr = this.imageCache.fetchImage(greenImageKey);
      blueImageArr = this.imageCache.fetchImage(blueImageKey);
      obj = new ImeModelImpl(redImageArr.length, redImageArr[0].length, deepCopy(redImageArr),
              deepCopy(greenImageArr), deepCopy(blueImageArr));
      newImageArr = obj.combineChannel();
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key not present");
    }
  }

  /**
   * Fetches the image from the image map for applying split, and stores all parts back.
   *
   * @param parentKey     the key that the target image has to be searched with
   * @param redImageKey   the key of the red image to be stored
   * @param greenImageKey the key of the green image to be stored
   * @param blueImageKey  the key of the blue image to be stored
   */
  @Override
  public void rgbSplitCommand(String parentKey, String redImageKey, String greenImageKey,
                              String blueImageKey) {
    int[][] redImageArr;
    int[][] greenImageArr;
    int[][] blueImageArr;
    List<int[][]> list;
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      list = obj.split();
      redImageArr = list.get(0);
      greenImageArr = list.get(1);
      blueImageArr = list.get(2);
      this.imageCache.addImage(redImageKey, redImageArr);
      this.imageCache.addImage(greenImageKey, greenImageArr);
      this.imageCache.addImage(blueImageKey, blueImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the images from the image map for creating histogram, and stores it back.
   *
   * @param newImageKey the key that the new image has to be stored with
   * @param parentKey   the key that the target image has to be searched with
   */
  @Override
  public void histogramCommand(String parentKey, String newImageKey) {
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.histogram();
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

  /**
   * Fetches the image from the image map for applying color correct, and stores it back.
   *
   * @param parentKey    the key of the image to be fetched
   * @param newImageKey  the key that the new image has to be stored with
   * @param percentValue the percentage to split the image by
   */
  @Override
  public void colorCorrectCommand(String parentKey, String newImageKey, double percentValue) {
    if (percentValue < 0 || percentValue > 100) {
      throw new IllegalArgumentException("Invalid percent value");
    }
    if (this.imageCache.searchKey(parentKey)) {

      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr[0].length, imageArr.length, deepCopy(imageArr));
      newImageArr = obj.colorCorrectImage(percentValue);
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }

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
  @Override
  public void levelsAdjustCommand(String parentKey, String newImageKey, int b,
                                  int m, int w, double percentValue) {
    if (percentValue < 0 || percentValue > 100) {
      throw new IllegalArgumentException("Invalid percent value");
    }
    if (this.imageCache.searchKey(parentKey)) {


      if ((b < 0 || b > 255) || (m < 0 || m > 255) || (w < 0 || w > 255)) {
        throw new IllegalArgumentException("b, m, w values should be within 0 and 255");
      }
      if (b > m || m > w) {
        throw new IllegalArgumentException("b, m, w values should be ascending");
      }
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.levelsAdjustment(b, m, w, percentValue);
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }

  }

  /**
   * Loads the image.
   *
   * @param imagePath the path of the image to be loaded
   * @param imageKey  the key that the new image has to be stored with
   * @throws FileNotFoundException if the image is not found at that path.
   */
  @Override
  public void loadCommand(String imagePath, String imageKey) throws FileNotFoundException {
    BufferedImage inputImage = obj1.readImage(imagePath);
    newImageArr = obj1.convertImageToPixel(inputImage);
    this.imageCache.addImage(imageKey, newImageArr);
  }

  /**
   * Saves the image.
   *
   * @param imagePath the path of the image to be saved at
   * @param imageKey  the key that the image has to be searched with
   * @param imageType the extension of the image
   * @throws IOException if there are discrepancies
   */
  @Override
  public void saveCommand(String imagePath, String imageKey, String imageType) throws IOException {
    if (this.imageCache.searchKey(imageKey)) {
      imageArr = this.imageCache.fetchImage(imageKey);
      BufferedImage finalImage = obj1.convertPixelToImage(imageArr);
      obj1.saveImage(finalImage, imagePath, imageType);
    } else {
      throw new NoSuchElementException("Image key " + imageKey + " not present");
    }
  }

  /**
   * Produces a deep copy of the 2D array.
   *
   * @param original the array to be deep copied
   * @returns copy the deep copied image
   */
  private int[][] deepCopy(int[][] original) {
    int[][] copy = new int[original.length][];
    for (int i = 0; i < original.length; i++) {
      copy[i] = Arrays.copyOf(original[i], original[i].length);
    }
    return copy;
  }

  /**
   * Fetches the image from the image map for applying compress, and stores it back.
   *
   * @param parentKey   the key of the image to be fetched
   * @param newImageKey the key that the new image has to be stored with
   * @param percentage  the percentage to compress the image by
   */
  @Override
  public void compressCommand(String parentKey, String newImageKey, int percentage) {
    if (this.imageCache.searchKey(parentKey)) {
      imageArr = this.imageCache.fetchImage(parentKey);
      obj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
      newImageArr = obj.compress(deepCopy(imageArr), percentage);
      this.imageCache.addImage(newImageKey, newImageArr);
    } else {
      throw new NoSuchElementException("Image key " + parentKey + " not present");
    }
  }
}
