package model;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static java.lang.Math.round;

/**
 * This class represents the image manipulation functions.
 */
public class ImeModelImpl implements ImeModel {

  private int width;
  private int height;
  private int[][] imageArr;
  private int[][] imageArrTwo;
  private int[][] imageArrThree;

  /**
   * Creates an object of the ImeModelImpl class.
   */
  public ImeModelImpl(int width, int height, int[][] imageArr) {
    this.width = width;
    this.height = height;
    this.imageArr = imageArr;
  }

  /**
   * Creates an object of the ImeModelImpl class.
   */
  public ImeModelImpl(int width, int height, int[][] imageArr1, int[][] imageArr2,
                      int[][] imageArr3) {
    this.width = width;
    this.height = height;
    this.imageArr = imageArr1;
    this.imageArrTwo = imageArr2;
    this.imageArrThree = imageArr3;
  }


  /**
   * Performs the luma filter application function.
   */
  @Override
  public int[][] luma(double percentValue) {

    int[][] outputImage = new int[width][height];
    double newPercent = Math.max(0.0, Math.min(100.0, percentValue));
    int splitLine = (int) (width * newPercent / 100);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = imageArr[x][y];

        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;

        int luma = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);

        int newPixel = (luma << 16) | (luma << 8) | luma;


        if (x < splitLine) {
          outputImage[x][y] = newPixel;
        } else if (x == splitLine) {
          if (y % 5 <= 2) {
            outputImage[x][y] = Color.MAGENTA.getRGB();
          } else {
            outputImage[x][y] = newPixel;
          }
        } else {
          outputImage[x][y] = imageArr[x][y];
        }
      }
    }

    return outputImage;
  }

  /**
   * Performs the sepia filter application function.
   */
  @Override
  public int[][] sepia(double percentValue) {

    int splitLine = (int) (width * percentValue / 100);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = imageArr[x][y];

        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;

        int sepiaRed = (int) (0.393 * red + 0.769 * green + 0.189 * blue);
        int sepiaGreen = (int) (0.349 * red + 0.686 * green + 0.168 * blue);
        int sepiaBlue = (int) (0.272 * red + 0.534 * green + 0.131 * blue);

        int newSepiaRed = Math.min(255, Math.max(0, sepiaRed));
        int newSepiaGreen = Math.min(255, Math.max(0, sepiaGreen));
        int newSepiaBlue = Math.min(255, Math.max(0, sepiaBlue));

        if (x < splitLine) {
          imageArr[x][y] = (newSepiaRed << 16) | (newSepiaGreen << 8) | newSepiaBlue;
        } else if (x == splitLine) {
          if (y % 5 <= 2) {
            imageArr[x][y] = Color.MAGENTA.getRGB();
          } else {
            imageArr[x][y] = (newSepiaRed << 16) | (newSepiaGreen << 8) | newSepiaBlue;
          }

        } else {
          imageArr[x][y] = imageArr[x][y];
        }
      }
    }

    return imageArr;
  }

  /**
   * Performs the horizontal flip function.
   */
  @Override
  public int[][] flipHorizontal() {

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width / 2; x++) {
        int pixelTemp = imageArr[x][y];
        imageArr[x][y] = imageArr[width - x - 1][y];
        imageArr[width - x - 1][y] = pixelTemp;

      }

    }
    return imageArr;

  }

  /**
   * Performs the vertical flip function.
   */
  @Override
  public int[][] flipVertical() {


    for (int y = 0; y < height / 2; y++) {
      for (int x = 0; x < width; x++) {
        int pixelTemp = imageArr[x][y];
        imageArr[x][y] = imageArr[x][height - y - 1];
        imageArr[x][height - y - 1] = pixelTemp;

      }
    }
    return imageArr;

  }

  /**
   * Performs the red component extraction function.
   */
  @Override
  public int[][] rComponent() {

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = imageArr[x][y];

        int red = (pixel >> 16) & 0xff;
        int blue = 0;

        int newPixel = (red << 16) | (0) | blue;
        imageArr[x][y] = newPixel;
      }
    }

    return imageArr;
  }

  /**
   * Performs the green component extraction function.
   */
  @Override
  public int[][] gComponent() {


    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = imageArr[x][y];

        int green = (pixel >> 8) & 0xff;
        int blue = 0;

        int newPixel = (0) | (green << 8) | blue;
        imageArr[x][y] = newPixel;
      }
    }

    return imageArr;
  }

  /**
   * Performs the blue component extraction function.
   */
  @Override
  public int[][] bComponent() {


    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = imageArr[x][y];

        int blue = pixel & 0xff;

        int newPixel = (0) | blue;
        imageArr[x][y] = newPixel;
      }
    }

    return imageArr;
  }

  /**
   * Performs the brighten/ darken function.
   *
   * @param brightFactor the degree to brighten/ darken by
   */
  @Override
  public int[][] brighten(int brightFactor) {


    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = imageArr[x][y];

        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;

        int newRed = red + brightFactor;
        int newGreen = green + brightFactor;
        int newBlue = blue + brightFactor;

        newRed = Math.min(255, Math.max(0, newRed));
        newGreen = Math.min(255, Math.max(0, newGreen));
        newBlue = Math.min(255, Math.max(0, newBlue));

        int newPixel = (newRed << 16) | (newGreen << 8) | newBlue;
        imageArr[x][y] = newPixel;
      }
    }

    return imageArr;
  }

  /**
   * Performs the combine function.
   */
  @Override
  public int[][] combineChannel() {

    int[][] colorImageArr = new int[width][height];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {

        int newRed = imageArr[x][y] >> 16 & 0xFF;
        int newGreen = imageArrTwo[x][y] >> 8 & 0xFF;
        int newBlue = imageArrThree[x][y] & 0xFF;

        int color = (newRed << 16) | (newGreen << 8) | newBlue;
        colorImageArr[x][y] = color;

      }
    }

    return colorImageArr;
  }

  /**
   * Performs the blur function.
   */
  @Override
  public int[][] blur(double percentValue) {

    int[][] outputImage = new int[width][height];
    double newPercent = Math.max(0.0, Math.min(100.0, percentValue));
    int splitLine = (int) (width * newPercent / 100);

    double[][] filter = {
            {1.0 / 16, 1.0 / 8, 1.0 / 16},
            {1.0 / 8, 1.0 / 4, 1.0 / 8},
            {1.0 / 16, 1.0 / 8, 1.0 / 16}
    };

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double red = 0;
        double green = 0;
        double blue = 0;

        for (int dy = -1; dy <= 1; dy++) {
          for (int dx = -1; dx <= 1; dx++) {

            int pixelX = Math.max(0, Math.min(width - 1, x + dx));
            int pixelY = Math.max(0, Math.min(height - 1, y + dy));

            int pixel = imageArr[pixelX][pixelY];
            red += ((pixel >> 16) & 0xFF) * filter[dy + 1][dx + 1];
            green += ((pixel >> 8) & 0xFF) * filter[dy + 1][dx + 1];
            blue += (pixel & 0xFF) * filter[dy + 1][dx + 1];
          }
        }

        int newRed = (int) red;
        int newGreen = (int) green;
        int newBlue = (int) blue;

        if (x < splitLine) {
          outputImage[x][y] = (newRed << 16) | (newGreen << 8) | newBlue;
        } else if (x == splitLine) {
          if (y % 5 <= 2) {
            outputImage[x][y] = Color.MAGENTA.getRGB();
          } else {
            outputImage[x][y] = (newRed << 16) | (newGreen << 8) | newBlue;
          }
        } else {
          outputImage[x][y] = imageArr[x][y];
        }
      }
    }
    return outputImage;
  }

  /**
   * Performs the sharpen function.
   */
  @Override
  public int[][] sharpen(double percentValue) {

    int splitLine = (int) (width * percentValue / 100);
    double[][] filter = {
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 0.25, 0.25, 0.25, -1.0 / 8},
            {-1.0 / 8, 0.25, 1, 0.25, -1.0 / 8},
            {-1.0 / 8, 0.25, 0.25, 0.25, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };

    int[][] outputImage = new int[width][height];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double red = 0;
        double green = 0;
        double blue = 0;

        for (int dy = -2; dy <= 2; dy++) {
          for (int dx = -2; dx <= 2; dx++) {

            int pixelX = Math.max(0, Math.min(width - 1, x + dx));
            int pixelY = Math.max(0, Math.min(height - 1, y + dy));

            int pixel = imageArr[pixelX][pixelY];
            red += ((pixel >> 16) & 0xFF) * filter[dy + 2][dx + 2];
            green += ((pixel >> 8) & 0xFF) * filter[dy + 2][dx + 2];
            blue += (pixel & 0xFF) * filter[dy + 2][dx + 2];
          }
        }

        int newRed = Math.min(255, Math.max(0, (int) red));
        int newGreen = Math.min(255, Math.max(0, (int) green));
        int newBlue = Math.min(255, Math.max(0, (int) blue));

        if (x < splitLine) {
          outputImage[x][y] = (newRed << 16) | (newGreen << 8) | newBlue;
        } else if (x == splitLine) {
          if (y % 5 <= 2) {
            outputImage[x][y] = Color.MAGENTA.getRGB();
          } else {
            outputImage[x][y] = (newRed << 16) | (newGreen << 8) | newBlue;
          }
        } else {
          outputImage[x][y] = imageArr[x][y];
        }
      }
    }

    return outputImage;
  }

  /**
   * Performs the value filter application function.
   */
  @Override
  public int[][] value() {

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = imageArr[x][y];
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;
        int newRed = Math.max(red, Math.max(green, blue));
        int newGreen = Math.max(red, Math.max(green, blue));
        int newBlue = Math.max(red, Math.max(green, blue));

        int newPixel = (newRed << 16) | (newGreen << 8) | newBlue;

        imageArr[x][y] = newPixel;
      }
    }
    return imageArr;
  }

  /**
   * Performs the intensity filter application function.
   */
  @Override
  public int[][] intensity() {

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = imageArr[x][y];

        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;

        int newRed = ((red + green + blue) / 3);
        int newGreen = ((red + green + blue) / 3);
        int newBlue = ((red + green + blue) / 3);

        int newPixel = (newRed << 16) | (newGreen << 8) | newBlue;

        imageArr[x][y] = newPixel;
      }
    }
    return imageArr;
  }

  /**
   * Performs the split function.
   */
  @Override
  public List<int[][]> split() {

    int[][] redComp = new ImeModelImpl(width, height, (arrayClone(imageArr))).rComponent();
    int[][] greenComp = new ImeModelImpl(width, height, (arrayClone(imageArr))).gComponent();
    int[][] blueComp = new ImeModelImpl(width, height, (arrayClone(imageArr))).bComponent();
    List<int[][]> list = new ArrayList<>();
    list.add(0, redComp);
    list.add(1, greenComp);
    list.add(2, blueComp);
    return list;
  }

  @Override
  public int[][] histogram() {
    int width = 256;
    int height = 256;

    int[][] histogramPixels = new int[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        histogramPixels[i][j] = 0xFFFFFFFF;
      }
    }
    int maxCount = getHistogramMaxCount(imageArr);
    drawHistogram(histogramPixels, imageArr, 0, 0xFFFF0000, maxCount);
    drawHistogram(histogramPixels, imageArr, 1, 0xFF00FF00, maxCount);
    drawHistogram(histogramPixels, imageArr, 2, 0xFF0000FF, maxCount);

    return histogramPixels;
  }

  private int getHistogramMaxCount(int[][] imageArr) {
    int[] pixelArr = new int[256];
    int maxCount = 0;

    for (int[] ints : imageArr) {
      for (int pixel : ints) {
        int value = (pixel >> 16) & 0xFF;
        pixelArr[value]++;
        value = (pixel >> 8) & 0xFF;
        pixelArr[value]++;
        value = pixel & 0xFF;
        pixelArr[value]++;
      }
    }

    for (int count : pixelArr) {
      if (count > maxCount) {
        maxCount = count;
      }
    }

    return maxCount;
  }

  private void drawHistogram(int[][] histogramPixels,
                             int[][] imageArr, int channel, int lineColor, int maxCount) {

    int[] histogramArr = new int[256];

    for (int[] ints : imageArr) {
      for (int x = 0; x < imageArr[0].length; x++) {
        int pixel = ints[x];
        int value;

        if (channel == 0) {
          value = (pixel >> 16) & 0xFF;
        } else if (channel == 1) {
          value = (pixel >> 8) & 0xFF;
        } else {
          value = pixel & 0xFF;
        }

        histogramArr[value]++;
      }
    }
    double scaleFactor = (double) 256 / maxCount;


    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        if (i % 16 == 0 || j % 16 == 0) {
          if (i < histogramPixels.length && j < histogramPixels[0].length) {
            histogramPixels[i][j] = 0xFFDDDDDD;
          }
        }
      }
    }

    for (int i = 1; i < 256; i++) {
      int x1 = i - 1;
      int y1 = (int) (256 - (histogramArr[i - 1] * scaleFactor));
      int y2 = (int) (256 - (histogramArr[i] * scaleFactor));

      drawLine(histogramPixels, x1, y1, i, y2, lineColor);
    }


  }


  private void drawLine(int[][] histogramPixels, int x1, int y1, int x2, int y2, int lineColor) {
    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);
    int sx = (x1 < x2) ? 1 : -1;
    int sy = (y1 < y2) ? 1 : -1;
    int err = dx - dy;

    while (true) {
      if (x1 >= 0 && x1 < histogramPixels.length && y1 >= 0 && y1 < histogramPixels[0].length) {
        histogramPixels[x1][y1] = lineColor;
      }

      if ((x1 == x2) && (y1 == y2)) {
        break;
      }

      int e2 = 2 * err;
      if (e2 > -dy) {
        err -= dy;
        x1 += sx;
      }
      if (e2 < dx) {
        err += dx;
        y1 += sy;
      }
    }
  }

  @Override
  public int[][] levelsAdjustment(int b, int m, int w, double percentValue) {

    double newPercent = Math.max(0.0, Math.min(100.0, percentValue));
    int[][] outputImage = new int[width][height];
    int splitLine = (int) (width * newPercent / 100);
    double aVal = (((b * b) * (m - w)) - (b * ((m * m) - (w * w))) + (w * m * m) - (m * w * w));
    double aA = ((-b * (128 - 255)) + (128 * w) - (255 * m));
    double aB = (((b * b) * (128 - 255)) + (255 * m * m) - (128 * w * w));
    double aC = (((b * b) * ((255 * m) - (128 * w))) - (b * ((255 * m * m) - (128 * w * w))));
    double a = aA / aVal;
    double bB = aB / aVal;
    double c = aC / aVal;

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = imageArr[x][y];

        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;

        double newRed = round((a * red * red) + (bB * red) + c);
        double newGreen = round((a * green * green) + (bB * green) + c);
        double newBlue = round((a * blue * blue) + (bB * blue) + c);


        double clampRed = Math.min(255, Math.max(0, newRed));
        double clampGreen = Math.min(255, Math.max(0, newGreen));
        double clampBlue = Math.min(255, Math.max(0, newBlue));

        int newPixel = ((int) clampRed << 16) | ((int) clampGreen << 8) | (int) clampBlue;


        if (x < splitLine) {
          outputImage[x][y] = newPixel;
        } else if (x == splitLine) {
          if (y % 5 <= 2) {
            outputImage[x][y] = Color.MAGENTA.getRGB();
          } else {
            outputImage[x][y] = newPixel;
          }
        } else {
          outputImage[x][y] = imageArr[x][y];
        }

      }
    }
    return outputImage;
  }

  @Override
  public int[][] colorCorrectImage(double percentValue) {

    double newPercent = Math.max(0.0, Math.min(100.0, percentValue));
    int splitLine = (int) (height * newPercent / 100);
    int[][] correctedPixels = new int[height][width];

    int redPeak = findPeak(imageArr, 0);
    int greenPeak = findPeak(imageArr, 1);
    int bluePeak = findPeak(imageArr, 2);

    int averagePeak = (redPeak + greenPeak + bluePeak) / 3;

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = imageArr[y][x];

        int red = (pixel >> 16) & 0xFF;
        int green = (pixel >> 8) & 0xFF;
        int blue = pixel & 0xFF;

        red = colorCorrectChannel(red, redPeak, averagePeak);
        green = colorCorrectChannel(green, greenPeak, averagePeak);
        blue = colorCorrectChannel(blue, bluePeak, averagePeak);

        if (y < splitLine) {
          correctedPixels[y][x] = (red << 16) | (green << 8) | blue;
        } else if (y == splitLine) {
          if (x % 5 <= 2) {
            correctedPixels[y][x] = Color.MAGENTA.getRGB();
          } else {
            correctedPixels[y][x] = (red << 16) | (green << 8) | blue;
          }
        } else {

          correctedPixels[y][x] = imageArr[y][x];
        }

      }
    }

    return correctedPixels;
  }

  private int findPeak(int[][] imageArr, int channel) {

    int[] histogram = new int[256];
    int maxCount = 0;
    int peakValue = 0;

    for (int[] ints : imageArr) {
      for (int x = 0; x < imageArr[0].length; x++) {
        int pixel = ints[x];
        int value;

        if (channel == 0) {
          value = (pixel >> 16) & 0xFF;
        } else if (channel == 1) {
          value = (pixel >> 8) & 0xFF;
        } else {
          value = pixel & 0xFF;
        }

        histogram[value]++;
      }
    }

    for (int i = 11; i < 245; i++) {
      if (histogram[i] > maxCount) {
        maxCount = histogram[i];
        peakValue = i;
      }
    }

    return peakValue;
  }

  private int colorCorrectChannel(int channelValue, int peak, int averagePeak) {
    int offset = averagePeak - peak;
    int correctedValue = channelValue + offset;
    return Math.max(0, Math.min(255, correctedValue));
  }

  private int[][] arrayClone(int[][] original) {
    int[][] copy = new int[original.length][];
    for (int i = 0; i < original.length; i++) {
      copy[i] = Arrays.copyOf(original[i], original[i].length);
    }
    return copy;
  }


  @Override
  public int[][] compress(int[][] imageArr, int percentage) {
    int width = imageArr.length;
    int height = imageArr[0].length;
    int p = nextRelevantPower(Math.max(width, height));


    int[][][] sample = new int[width][height][3];
    int[][][] image = new int[width][height][3];


    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = imageArr[x][y];

        int redp = (pixel >> 16) & 0xff;
        int greenp = (pixel >> 8) & 0xff;
        int bluep = pixel & 0xff;

        sample[x][y][0] = redp;
        sample[x][y][1] = greenp;
        sample[x][y][2] = bluep;

      }
    }

    int[][][] finalArray = new int[p][p][3];
    finalArray = padding(sample);

    for (int k = 0; k < 3; k++) {
      float[][] indChannel = new float[p][p];
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          indChannel[x][y] = finalArray[x][y][k];
        }
      }
      indChannel = haar(indChannel);
      int h = (int) (p * p * (percentage / 100.0));
      indChannel = makeZero(indChannel, h);
      indChannel = inverseHaar(indChannel);
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          image[x][y][k] = (int) Math.max(0, Math.min(255, Math.round(indChannel[x][y])));
        }
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixelRed = image[x][y][0];
        int pixelGreen = image[x][y][1];
        int pixelBlue = image[x][y][2];

        pixelRed = Math.min(255, Math.max(0, pixelRed));
        pixelGreen = Math.min(255, Math.max(0, pixelGreen));
        pixelBlue = Math.min(255, Math.max(0, pixelBlue));

        int newPixel = (pixelRed << 16) | (pixelGreen << 8) | pixelBlue;
        imageArr[x][y] = newPixel;
      }
    }
    return imageArr;
  }


  private float[][] makeZero(float[][] imageData, int zeroCount) {
    int size = imageData.length;
    float[][] finalArr = arrayCloneFloat(imageData);
    for (int i = size - 1; i >= 0 && zeroCount > 0; i--) {
      for (int j = size - 1; j >= 0 && zeroCount > 0; j--) {
        finalArr[i][j] = 0;
        zeroCount--;
      }
    }
    return finalArr;
  }


  private float[][] haar(float[][] originalArray) {
    float[][] newArray = arrayCloneFloat(originalArray);
    int c = originalArray.length;

    while (c > 1) {
      for (int i = 0; i < c; i++) {
        originalArray[i] = transform(originalArray[i]);
      }
      for (int i = 0; i < c; i++) {
        float[] col = new float[c];
        for (int j = 0; j < c; j++) {
          col[j] = originalArray[j][i];
        }
        col = transform(col);
        for (int j = 0; j < c; j++) {
          originalArray[j][i] = col[j];
        }
      }
      c = c / 2;
    }
    return originalArray;

  }

  private float[][] arrayCloneFloat(float[][] original) {
    float[][] copy = new float[original.length][];
    for (int i = 0; i < original.length; i++) {
      copy[i] = Arrays.copyOf(original[i], original[i].length);
    }
    return copy;
  }


  private float[] transform(float[] elements) {
    List<Float> avg = new ArrayList<>();
    List<Float> diff = new ArrayList<>();
    for (int i = 0; i < elements.length; i = i + 2) {
      float avgnum = (float) ((elements[i] + elements[i + 1]) / Math.sqrt(2));
      float diffnum = (float) ((elements[i] - elements[i + 1]) / Math.sqrt(2));
      avg.add(avgnum);
      diff.add(diffnum);
    }
    avg.addAll(diff);
    float[] result = new float[avg.size()];
    for (int i = 0; i < avg.size(); i++) {
      result[i] = avg.get(i);
    }
    return result;
  }

  private float[][] inverseHaar(float[][] originalArray) {
    int s = originalArray.length;
    int c = 2;
    while (c <= s) {

      for (int i = 0; i < c; i++) {
        float[] col = new float[c];
        for (int j = 0; j < c; j++) {
          col[j] = originalArray[j][i];
        }
        col = inverse(col);
        for (int j = 0; j < c; j++) {
          originalArray[j][i] = col[j];
        }
      }

      for (int i = 0; i < c; i++) {
        originalArray[i] = inverse(originalArray[i]);
      }

      c = c * 2;

    }
    return originalArray;
  }


  private float[] inverse(float[] elements) {
    float[] average = new float[elements.length / 2];
    float[] difference = new float[elements.length / 2];
    float[] newArray;

    for (int i = 0, j = (elements.length) / 2; i < (elements.length) / 2; i = i + 1, j = j + 1) {
      double a = elements[i];
      double b = elements[j];
      double avg = ((a + b) / Math.sqrt(2));
      double diff = ((a - b) / Math.sqrt(2));
      average[i] = (float) avg;
      difference[i] = (float) diff;
    }
    newArray = interleavedArray(average, difference);
    return newArray;
  }

  private static float[] interleavedArray(float[] array1, float[] array2) {
    int length = array1.length + array2.length;
    float[] interleavedArray = new float[length];

    int index = 0;
    int i = 0;
    int j = 0;

    while (i < array1.length && j < array2.length) {
      interleavedArray[index++] = array1[i++];
      interleavedArray[index++] = array2[j++];
    }

    while (i < array1.length) {
      interleavedArray[index++] = array1[i++];
    }
    while (j < array2.length) {
      interleavedArray[index++] = array2[j++];
    }
    return interleavedArray;
  }


  private static int nextRelevantPower(int n) {
    int count = 0;
    if ((n & (n - 1)) != 0) {
      while (n != 0) {
        n >>= 1;
        count++;
      }
      return 1 << count;
    }
    return n;
  }


  private int[][][] padding(int[][][] originalImage) {
    int height = originalImage.length;
    int width = originalImage[0].length;
    int channels = originalImage[0][0].length;
    int size = Math.max(width, height);
    size = nextRelevantPower(size);
    int[][][] newImage = new int[size][size][channels];

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        for (int k = 0; k < channels; k++) {
          if (i < height && j < width) {
            newImage[i][j][k] = originalImage[i][j][k];
          } else {
            newImage[i][j][k] = 0;
          }
        }
      }
    }

    return newImage;
  }

}