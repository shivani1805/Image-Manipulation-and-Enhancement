import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

import model.ImeModel;
import model.ImeModelImpl;


/**
 * A JUnit test class for the ImeModel.
 */
public class ImeModelTest {

  private ImeModel model;

  @Before
  public void setUp() {


    int[][] inputImagePixel = {
            {255, 0, 0},
            {0, 255, 0},
            {0, 0, 255}
    };
    model = new ImeModelImpl(inputImagePixel.length, inputImagePixel[0].length, inputImagePixel);


  }

  @Test
  public void testLuma() {

    int[][] expected = {
            {1184274, 0, 0},
            {0, 1184274, 0},
            {0, 0, 1184274}
    };
    int[][] result = model.luma(100);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }
  }

  @Test
  public void testLumaSplit() {

    int[][] expected = {
            {1184274, 0, 0},
            {-65281, -65281, -65281},
            {0, 0, 255}
    };
    int[][] result = model.luma(50);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {

        assertEquals(expected[i][j], result[i][j]);
      }

    }
  }

  @Test
  public void testSepia() {

    int[][] expected = {
            {3156513, 0, 0},
            {0, 3156513, 0},
            {0, 0, 3156513}
    };
    int[][] result = model.sepia(100);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }
  }

  @Test
  public void testSepiaSplit() {

    int[][] expected = {
            {3156513, 0, 0},
            {-65281, -65281, -65281},
            {0, 0, 255}
    };
    int[][] result = model.sepia(50);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {

        assertEquals(expected[i][j], result[i][j]);
      }

    }
  }

  @Test
  public void testFlipHorizontal() {

    int[][] expected = {
            {0, 0, 255},
            {0, 255, 0},
            {255, 0, 0}
    };
    int[][] result = model.flipHorizontal();

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }

  }

  @Test
  public void testFlipVertical() {

    int[][] expected = {
            {0, 0, 255},
            {0, 255, 0},
            {255, 0, 0}
    };
    int[][] result = model.flipVertical();

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }
  }

  @Test
  public void testRComponent() {

    int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    int[][] result = model.rComponent();

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }


  }

  @Test
  public void testGComponent() {


    int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    int[][] result = model.gComponent();

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }


  }

  @Test
  public void testBComponent() {

    int[][] expected = {
            {255, 0, 0},
            {0, 255, 0},
            {0, 0, 255}
    };
    int[][] result = model.bComponent();

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }


  }

  @Test
  public void testBrighten() {

    int[][] expected = {
            {3289855, 3289650, 3289650},
            {3289650, 3289855, 3289650},
            {3289650, 3289650, 3289855}
    };
    int[][] result = model.brighten(50);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }


  }

  @Test
  public void testDarken() {

    int[][] expected = {
            {205, 0, 0},
            {0, 205, 0},
            {0, 0, 205}
    };
    int[][] result = model.brighten(-50);
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }


  }

  @Test
  public void testBlur() {


    int[][] expected = {
            {159, 79, 15},
            {79, 95, 79},
            {15, 79, 159}
    };
    int[][] result = model.blur(100);
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }


  }

  @Test
  public void testBlurSplit() {


    int[][] expected = {
            {159, 79, 15},
            {-65281, -65281, -65281},
            {0, 0, 255}
    };
    int[][] result = model.blur(50);
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {

        assertEquals(expected[i][j], result[i][j]);
      }

    }
  }

  @Test
  public void testSharpen() {


    int[][] expected = {
            {255, 0, 0},
            {0, 191, 0},
            {0, 0, 255}
    };
    int[][] result = model.sharpen(100);
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }


  }

  @Test
  public void testSharpenSplit() {


    int[][] expected = {
            {255, 0, 0},
            {-65281, -65281, -65281},
            {0, 0, 255}
    };
    int[][] result = model.sharpen(50);
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {

        assertEquals(expected[i][j], result[i][j]);
      }

    }


  }

  @Test
  public void testValue() {

    int[][] expected = {
            {16777215, 0, 0},
            {0, 16777215, 0},
            {0, 0, 16777215}
    };
    int[][] result = model.value();
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }


  }

  @Test
  public void testIntensity() {

    int[][] expected = {
            {5592405, 0, 0},
            {0, 5592405, 0},
            {0, 0, 5592405}
    };
    int[][] result = model.intensity();
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }


  }

  @Test
  public void testHistogram() {

    int[][] expected = {
            {-2236963, -2236963, -2236963},
            {-2236963, -1, -1},
            {-2236963, -1, -1}
    };
    int[][] result = model.histogram();
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);

      }

    }


  }

  @Test
  public void testLevelsAdjust() {

    int[][] expected = {
            {255, 0, 0},
            {0, 255, 0},
            {0, 0, 255}
    };
    int[][] result = model.levelsAdjustment(10, 50, 255, 100);
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);

      }

    }


  }

  @Test
  public void testLevelsAdjustSplit() {

    int[][] expected = {
            {255, 0, 0},
            {-65281, -65281, -65281},
            {0, 0, 255}
    };
    int[][] result = model.levelsAdjustment(10, 50, 255, 50);
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);

      }

    }


  }

  @Test
  public void testColorCorrect() {

    int[][] expected = {
            {255, 0, 0},
            {0, 255, 0},
            {0, 0, 255}
    };
    int[][] result = model.colorCorrectImage(100);
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);

      }

    }


  }

  @Test
  public void testColorCorrectSplit() {

    int[][] expected = {
            {255, 0, 0},
            {-65281, -65281, -65281},
            {0, 0, 255}
    };
    int[][] result = model.colorCorrectImage(50);
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);

      }

    }


  }

  @Test
  public void testCombine() {

    int[][] inputPixelRed = {
            {255, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    int[][] inputPixelGreen = {
            {0, 0, 0},
            {0, 255, 0},
            {0, 0, 0}
    };
    int[][] inputPixelBlue = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 255}
    };
    int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 255}
    };
    model = new ImeModelImpl(inputPixelRed.length, inputPixelRed[0].length,
            inputPixelRed, inputPixelGreen, inputPixelBlue);

    int[][] result = model.combineChannel();
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j]);
      }

    }
  }
}
