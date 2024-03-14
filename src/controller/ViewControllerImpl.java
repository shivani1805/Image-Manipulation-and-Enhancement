package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import view.ImeView;
import model.ImeModel;
import model.ImeModelImpl;

/**
 * This class represents the view GUI capabilities.
 */
public class ViewControllerImpl implements ViewController, ActionListener {

  private ImeView view;
  private ImeController imeObj = new ImeControllerImpl();
  private String splitOperation;

  /**
   * Constructs an object of the view controller imp file.
   *
   * @param view  an object of the ImeView class
   */
  public ViewControllerImpl(ImeView view) {
    this.view = view;
    this.splitOperation = null;
    view.setListeners(this);
  }

  /**
   * Contains the action listeners.
   *
   * @param e the action event
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    int[][] imageArr;
    ImeModel funcObj;
    BufferedImage image;
    switch (e.getActionCommand()) {
      case "Load Image":
        view.setHistogramLabelFalse();
        view.loadImage();
        image = view.returnImage();
        imageArr = imeObj.convertImageToPixel(image);
        funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
        view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
        break;

      case "Drop down":
        String selectedOption = (String) view.getComboBox().getSelectedItem();
        view.setSelectedOptionLabelText("Selected option: " + selectedOption);

        switch (selectedOption) {
          case "Red Component":
            image = view.returnImage();
            imageArr = imeObj.convertImageToPixel(image);
            funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
            imageArr = funcObj.rComponent();
            image = imeObj.convertPixelToImage(deepCopy(imageArr));
            view.loadImageIconToBuffered(image);
            view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
            break;

          case "Green Component":
            image = view.returnImage();
            imageArr = imeObj.convertImageToPixel(image);
            funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
            imageArr = funcObj.gComponent();
            image = imeObj.convertPixelToImage(deepCopy(imageArr));
            view.loadImageIconToBuffered(image);
            view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
            break;

          case "Blue Component":
            image = view.returnImage();
            imageArr = imeObj.convertImageToPixel(image);
            funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
            imageArr = funcObj.bComponent();
            image = imeObj.convertPixelToImage(deepCopy(imageArr));
            view.loadImageIconToBuffered(image);
            view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
            break;

          case "Vertical Flip":
            image = view.returnImage();
            imageArr = imeObj.convertImageToPixel(image);
            funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
            imageArr = funcObj.flipVertical();
            image = imeObj.convertPixelToImage(deepCopy(imageArr));
            view.loadImageIconToBuffered(image);
            view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
            break;

          case "Horizontal Flip":
            image = view.returnImage();
            imageArr = imeObj.convertImageToPixel(image);
            funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
            imageArr = funcObj.flipHorizontal();
            image = imeObj.convertPixelToImage(deepCopy(imageArr));
            view.loadImageIconToBuffered(image);
            view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
            break;

          case "Blur":
            splitOperation = "blur";
            image = view.returnImage();
            view.splitPreview(image, "Blur");
            break;

          case "Sharpen":
            splitOperation = "sharpen";
            image = view.returnImage();
            view.splitPreview(image, "Sharpen");
            break;

          case "Sepia":
            splitOperation = "sepia";
            image = view.returnImage();
            view.splitPreview(image, "Sepia");
            break;


          case "Luma":
            splitOperation = "luma";
            image = view.returnImage();
            view.splitPreview(image, "Luma");
            break;


          case "Color Correct":
            splitOperation = "color-correct";
            image = view.returnImage();
            view.splitPreview(image, "Color-Correct");
            break;

          case "Levels Adjust":
            splitOperation = "levels-adjust";
            image = view.returnImage();
            view.levelAdjustPreview(image, "Levels-Adjust");
            break;

          case "Compress":
            String input = view.getPopUpTextValue();
            if (input != null && !input.isEmpty()) {
              if (!input.matches("\\d+")) {
                view.numericFieldError();
              } else if ((Integer.parseInt(input) < 0 || (Integer.parseInt(input) > 100))) {
                view.numericFieldError();
              } else {
                image = view.returnImage();
                imageArr = imeObj.convertImageToPixel(image);
                funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
                imageArr = funcObj.compress(imageArr, Integer.parseInt(input));
                image = imeObj.convertPixelToImage(deepCopy(imageArr));
                view.loadImageIconToBuffered(image);
                view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
              }
            }
            break;

          default:
            System.out.println("no valid option");
        }
        break;
      case "Split-Blur":
        image = view.returnImage();
        if (view.getPercentText() != null && !view.getPercentText().isEmpty()) {
          if (!view.getPercentText().matches("\\d+")) {
            view.numericFieldError();
          } else if ((Integer.parseInt(view.getPercentText())) < 0
                  || (Integer.parseInt(view.getPercentText())) > 100) {
            view.numericFieldError();
          } else {

            imageArr = imeObj.convertImageToPixel(image);
            funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
            imageArr = funcObj.blur(Integer.parseInt(view.getPercentText()));
            image = imeObj.convertPixelToImage(deepCopy(imageArr));
            view.loadPreviewImage(image);
          }
        } else {
          view.numericFieldError();
        }

        break;
      case "Split-Sepia":
        image = view.returnImage();
        if (view.getPercentText() != null && !view.getPercentText().isEmpty()) {
          if (!view.getPercentText().matches("\\d+")) {
            view.numericFieldError();
          } else if ((Integer.parseInt(view.getPercentText())) < 0
                  || (Integer.parseInt(view.getPercentText())) > 100) {
            view.numericFieldError();
          } else {
            imageArr = imeObj.convertImageToPixel(image);
            funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
            imageArr = funcObj.sepia(Integer.parseInt(view.getPercentText()));
            image = imeObj.convertPixelToImage(deepCopy(imageArr));
            view.loadPreviewImage(image);

          }
        } else {
          view.numericFieldError();
        }
        break;
      case "Split-Luma":
        image = view.returnImage();
        if (view.getPercentText() != null && !view.getPercentText().isEmpty()) {
          if (!view.getPercentText().matches("\\d+")) {
            view.numericFieldError();
          } else if ((Integer.parseInt(view.getPercentText())) < 0
                  || (Integer.parseInt(view.getPercentText())) > 100) {
            view.numericFieldError();
          } else {
            imageArr = imeObj.convertImageToPixel(image);
            funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
            imageArr = funcObj.luma(Integer.parseInt(view.getPercentText()));
            image = imeObj.convertPixelToImage(deepCopy(imageArr));
            view.loadPreviewImage(image);

          }
        } else {
          view.numericFieldError();
        }
        break;
      case "Split-Sharpen":
        image = view.returnImage();
        if (view.getPercentText() != null && !view.getPercentText().isEmpty()) {
          if (!view.getPercentText().matches("\\d+")) {
            view.numericFieldError();
          } else if ((Integer.parseInt(view.getPercentText())) < 0
                  || (Integer.parseInt(view.getPercentText())) > 100) {
            view.numericFieldError();
          } else {
            imageArr = imeObj.convertImageToPixel(image);
            funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
            imageArr = funcObj.sharpen(Integer.parseInt(view.getPercentText()));
            image = imeObj.convertPixelToImage(deepCopy(imageArr));
            view.loadPreviewImage(image);

          }
        } else {
          view.numericFieldError();
        }
        break;
      case "Split-Color-Correct":
        image = view.returnImage();
        if (view.getPercentText() != null && !view.getPercentText().isEmpty()) {
          if (!view.getPercentText().matches("\\d+")) {
            view.numericFieldError();
          } else if ((Integer.parseInt(view.getPercentText())) < 0
                  || (Integer.parseInt(view.getPercentText())) > 100) {
            view.numericFieldError();
          } else {
            imageArr = imeObj.convertImageToPixel(image);
            funcObj = new ImeModelImpl(imageArr[0].length, imageArr.length, deepCopy(imageArr));
            imageArr = funcObj.colorCorrectImage(Integer.parseInt(view.getPercentText()));
            image = imeObj.convertPixelToImage(deepCopy(imageArr));
            view.loadPreviewImage(image);

          }
        } else {
          view.numericFieldError();
        }
        break;
      case "Split-Levels-Adjust":
        if (view.getBlackValue().isEmpty()
                && view.getMidValue().isEmpty() && view.getWhiteValue().isEmpty()) {
          view.levelsAdjustError();
        } else {
          int b = Integer.parseInt(view.getBlackValue());
          int m = Integer.parseInt(view.getMidValue());
          int w = Integer.parseInt(view.getWhiteValue());
          image = view.returnImage();

          if (b > m || m > w) {
            view.levelsAdjustError();
            break;
          }
          if ((b < 0 || b > 255) || (m < 0 || m > 255) || (w < 0 || w > 255)) {
            view.levelsAdjustError();
            break;
          }
          if (view.getPercentText() != null && !view.getPercentText().isEmpty()) {
            if (!view.getPercentText().matches("\\d+")) {
              view.numericFieldError();
            } else if ((Integer.parseInt(view.getPercentText())) < 0
                    || (Integer.parseInt(view.getPercentText())) > 100) {
              view.numericFieldError();
            } else {
              imageArr = imeObj.convertImageToPixel(image);
              funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
              imageArr = funcObj.levelsAdjustment(Integer.parseInt(view.getBlackValue()),
                      Integer.parseInt(view.getMidValue()),
                      Integer.parseInt(view.getWhiteValue()),
                      Integer.parseInt(view.getPercentText()));
              image = imeObj.convertPixelToImage(deepCopy(imageArr));
              view.loadPreviewImage(image);

            }
          } else {
            view.numericFieldError();
          }
        }
        break;
      case "Apply":
        if (view.getPercentText() != null && !view.getPercentText().isEmpty()) {
          if (!view.getPercentText().matches("\\d+")) {
            view.numericFieldError();
            break;
          } else if ((Integer.parseInt(view.getPercentText())) < 0
                  || (Integer.parseInt(view.getPercentText())) > 100) {
            view.numericFieldError();
            break;
          }
        }
        image = view.returnImage();
        imageArr = imeObj.convertImageToPixel(image);
        funcObj = new ImeModelImpl(imageArr.length, imageArr[0].length, deepCopy(imageArr));
        if (splitOperation.equalsIgnoreCase("blur")) {
          imageArr = funcObj.blur(100);
          image = imeObj.convertPixelToImage(deepCopy(imageArr));
          funcObj = new ImeModelImpl(imageArr[0].length, imageArr.length, deepCopy(imageArr));
          view.loadImageIconToBuffered(image);
          view.previewFrameDispose();
          view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
        } else if (splitOperation.equalsIgnoreCase("sepia")) {
          imageArr = funcObj.sepia(100);
          image = imeObj.convertPixelToImage(deepCopy(imageArr));
          funcObj = new ImeModelImpl(imageArr[0].length, imageArr.length, deepCopy(imageArr));
          view.loadImageIconToBuffered(image);
          view.previewFrameDispose();
          view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
        } else if (splitOperation.equalsIgnoreCase("luma")) {
          imageArr = funcObj.luma(100);
          image = imeObj.convertPixelToImage(deepCopy(imageArr));
          funcObj = new ImeModelImpl(imageArr[0].length, imageArr.length, deepCopy(imageArr));
          view.loadImageIconToBuffered(image);
          view.previewFrameDispose();
          view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
        } else if (splitOperation.equalsIgnoreCase("sharpen")) {
          imageArr = funcObj.sharpen(100);
          image = imeObj.convertPixelToImage(deepCopy(imageArr));
          funcObj = new ImeModelImpl(imageArr[0].length, imageArr.length, deepCopy(imageArr));
          view.loadImageIconToBuffered(image);
          view.previewFrameDispose();
          view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
        } else if (splitOperation.equalsIgnoreCase("color-correct")) {
          funcObj = new ImeModelImpl(imageArr[0].length, imageArr.length, deepCopy(imageArr));
          imageArr = funcObj.colorCorrectImage(100);
          image = imeObj.convertPixelToImage(deepCopy(imageArr));
          funcObj = new ImeModelImpl(imageArr[0].length, imageArr.length, deepCopy(imageArr));
          view.loadImageIconToBuffered(image);
          view.previewFrameDispose();
          view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
        } else if (splitOperation.equalsIgnoreCase("levels-adjust")) {
          if (!view.getBlackValue().isEmpty() && !view.getMidValue().isEmpty()
                  && !view.getWhiteValue().isEmpty()) {

            int b = Integer.parseInt(view.getBlackValue());
            int m = Integer.parseInt(view.getMidValue());
            int w = Integer.parseInt(view.getWhiteValue());
            if (b > m || m > w) {
              view.levelsAdjustError();
              break;
            }
            if ((b < 0 || b > 255) || (m < 0 || m > 255) || (w < 0 || w > 255)) {
              view.levelsAdjustError();
              break;
            }

            imageArr = funcObj.levelsAdjustment(b, m, w, 100);
            image = imeObj.convertPixelToImage(deepCopy(imageArr));
            funcObj = new ImeModelImpl(imageArr[0].length, imageArr.length, deepCopy(imageArr));
            view.loadImageIconToBuffered(image);
            view.previewFrameDispose();
            view.displayHistogramImage(imeObj.convertPixelToImage(funcObj.histogram()));
          } else {
            view.levelsAdjustError();
          }

        }
        break;

      case "Save":
        view.saveImage();
        break;


      case "Exit Button":
        System.exit(0);
        break;

      default:
        System.out.println("no valid option");
    }
  }

  /**
   * Creates a copy of the array.
   *
   * @param original the array to be copied
   */
  private int[][] deepCopy(int[][] original) {
    int[][] copy = new int[original.length][];
    for (int i = 0; i < original.length; i++) {
      copy[i] = Arrays.copyOf(original[i], original[i].length);
    }
    return copy;
  }
}
