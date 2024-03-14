package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ImeController;
import controller.ImeControllerImpl;


/**
 * This class represents the view GUI execution.
 */
public class ImeViewImpl extends JFrame implements ImeView {
  private BufferedImage bufferedImage;
  private ImageIcon originalImageIcon = null;
  private ImageIcon currentImageIcon = null;
  private JButton loadImageButton;
  private String imagePath;
  private JTextField percentField = new JTextField();
  private JButton previewButton = new JButton("Preview");
  private JButton applyButton = new JButton("Apply");
  private JLabel imageLabel;
  private JFrame frame;
  private JFrame previewFrame;
  private JLabel comboboxDisplay;
  private JComboBox<String> combobox;
  private JLabel selectedOptionLabel;
  private JLabel previewLabel;
  private JButton saveButton;
  private JPanel leftPanel;
  private JPanel rightPanel;
  private JPanel comboBoxPanel;
  private JTextField black = new JTextField();
  private JTextField mid = new JTextField();
  private JTextField white = new JTextField();
  private JLabel histogramLabel = new JLabel();

  private JScrollPane scrollPane;

  private JPanel levelAdjustPanel;


  /**
   * Constructs the GUI.
   *
   * @param caption the string caption to be provided
   */
  public ImeViewImpl(String caption) {
    JSplitPane splitPane;
    JPanel buttonPanel;
    frame = new JFrame(caption);
    frame.setSize(800, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

    leftPanel = new JPanel(new BorderLayout());
    rightPanel = new JPanel(new BorderLayout());

    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
    splitPane.setResizeWeight(0.6);

    frame.add(splitPane, BorderLayout.CENTER);

    buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    loadImageButton = new JButton("Load Image");
    loadImageButton.setActionCommand("Load Image");
    buttonPanel.add(loadImageButton);

    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save");
    saveButton.setVisible(false);
    buttonPanel.add(saveButton);

    frame.add(buttonPanel, BorderLayout.SOUTH);

    imageLabel = new JLabel();
    scrollPane = new JScrollPane(imageLabel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    leftPanel.add(scrollPane, BorderLayout.CENTER);

    comboBoxPanel = new JPanel();
    Border emptyBorder = BorderFactory.createEmptyBorder(10, 0, 10, 0);
    comboBoxPanel.setBorder(emptyBorder);
    comboboxDisplay = new JLabel("Edit Menu :");
    comboboxDisplay.setVisible(false);

    String[] options = {"None", "Red Component", "Green Component", "Blue Component",
                        "Vertical Flip", "Horizontal Flip", "Blur", "Sharpen", "Luma",
                        "Sepia", "Compress", "Color Correct", "Levels Adjust"};
    combobox = new JComboBox<>(options);
    combobox.setActionCommand("Drop down");
    combobox.setVisible(false);
    selectedOptionLabel = new JLabel("Selected Option: ");
    selectedOptionLabel.setVisible(false);


    comboBoxPanel.add(comboboxDisplay, BorderLayout.NORTH);
    comboBoxPanel.add(combobox, BorderLayout.CENTER);
    comboBoxPanel.add(selectedOptionLabel, BorderLayout.SOUTH);
    comboBoxPanel.setVisible(false);
    frame.add(comboBoxPanel, BorderLayout.NORTH);
    frame.setVisible(true);

  }

  @Override
  public void loadImage() {
    if (unsavedChanges()) {
      int choice = JOptionPane.showConfirmDialog(frame, "You have unsaved changes. "
                      + "Do you want to continue and lose the unsaved changes?", "Unsaved Changes",
              JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
      if (choice != JOptionPane.YES_OPTION) {
        return;
      }
    }

    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files",
            "jpg", "jpeg", "png", "ppm");
    fileChooser.setFileFilter(filter);

    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
      imagePath = fileChooser.getSelectedFile().getAbsolutePath();
      ImageIcon imageIcon = new ImageIcon(imagePath);
      Image image = imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth(),
              imageIcon.getIconHeight(), Image.SCALE_AREA_AVERAGING);
      imageLabel.setIcon(new ImageIcon(image));
      originalImageIcon = new ImageIcon(image);
      currentImageIcon = new ImageIcon(image);

      bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
              BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = bufferedImage.createGraphics();
      g2d.drawImage(image, 0, 0, null);
      g2d.dispose();

      comboboxDisplay.setVisible(true);
      combobox.setVisible(true);
      selectedOptionLabel.setVisible(true);
      saveButton.setVisible(true);
      setSelectedOptionLabelText("Selected Option: None");

      comboBoxPanel.setVisible(true);

      leftPanel.add(scrollPane, BorderLayout.CENTER);

      rightPanel.removeAll();
      rightPanel.add(histogramLabel, BorderLayout.CENTER);

      frame.revalidate();
    }
  }

  @Override
  public void displayHistogramImage(BufferedImage histogram) {
    int leftPadding = 100;
    JScrollPane histogramScrollPane;
    histogramLabel.setVisible(true);
    histogramLabel.setIcon(new ImageIcon(histogram));
    histogramScrollPane = new JScrollPane(histogramLabel);
    Border emptyBorder = BorderFactory.createEmptyBorder(0, leftPadding, 0, 0);
    histogramScrollPane.setBorder(emptyBorder);

    histogramScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    histogramScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    rightPanel.removeAll();
    rightPanel.add(histogramScrollPane, BorderLayout.CENTER);

    frame.revalidate();
  }

  @Override
  public void setHistogramLabelFalse() {
    histogramLabel.setVisible(false);
  }

  @Override
  public void loadImageIconToBuffered(BufferedImage bImage) {
    ImageIcon imageIcon = new ImageIcon(bImage.getScaledInstance(bImage.getWidth(),
            bImage.getHeight(), Image.SCALE_AREA_AVERAGING));
    imageLabel.setIcon(imageIcon);
    bufferedImage = bImage;
    currentImageIcon = imageIcon;
  }

  @Override
  public void loadPreviewImage(BufferedImage bImage) {
    ImageIcon imageIcon = new ImageIcon(bImage.getScaledInstance(bImage.getWidth(),
            bImage.getHeight(), Image.SCALE_AREA_AVERAGING));
    previewLabel.setIcon(imageIcon);
  }

  @Override
  public void splitPreview(BufferedImage previewImage, String operation) {
    preview(previewImage, operation);
    previewFrame.setVisible(true);
  }

  @Override
  public void preview(BufferedImage previewImage, String operation) {
    previewFrame = new JFrame("Preview");
    previewFrame.setSize(600, 400);
    previewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    int xOffset = previewFrame.getWidth();
    previewFrame.setLocation(frame.getLocationOnScreen().x + xOffset,
            frame.getLocationOnScreen().y);

    JLabel label = new JLabel("Enter Split Percent:");
    percentField.setColumns(10);
    percentField.setText("");
    JPanel inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(label);
    inputPanel.add(percentField);
    inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));

    previewButton.setActionCommand("Split-" + operation);
    applyButton.setActionCommand("Apply");

    previewLabel = new JLabel();
    ImageIcon imageIcon = new ImageIcon(previewImage.getScaledInstance(previewImage.getWidth(),
            previewImage.getHeight(), Image.SCALE_DEFAULT));
    previewLabel.setIcon(imageIcon);
    JScrollPane scrollPane = new JScrollPane(previewLabel);
    scrollPane.setPreferredSize(new Dimension(1200, 600));
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    previewFrame.setLayout(new FlowLayout());

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(previewButton);
    buttonPanel.add(applyButton);

    buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    mainPanel.add(inputPanel);
    mainPanel.add(scrollPane);
    levelAdjustPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    levelAdjustPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    levelAdjustPanel.setVisible(false);
    mainPanel.add(levelAdjustPanel);
    mainPanel.add(buttonPanel);
    previewFrame.add(mainPanel);
    JScrollPane frameScrollPane = new JScrollPane(mainPanel);
    frameScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    frameScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    previewFrame.add(frameScrollPane);
    previewFrame.pack();
  }

  @Override
  public void levelAdjustPreview(BufferedImage previewImage, String operation) {
    preview(previewImage, operation);
    percentField.setText("");
    black.setText("");
    mid.setText("");
    white.setText("");
    JLabel blackLabel = new JLabel("black:");
    black.setColumns(5);
    JLabel midLabel = new JLabel("mid:");
    mid.setColumns(5);
    JLabel whiteLabel = new JLabel("white:");
    white.setColumns(5);
    levelAdjustPanel.add(blackLabel);
    levelAdjustPanel.add(black);
    levelAdjustPanel.add(midLabel);
    levelAdjustPanel.add(mid);
    levelAdjustPanel.add(whiteLabel);
    levelAdjustPanel.add(white);
    levelAdjustPanel.setVisible(true);
    previewFrame.setVisible(true);

  }

  @Override
  public void setListeners(ActionListener actionListener) {
    this.loadImageButton.addActionListener(actionListener);
    this.combobox.addActionListener(actionListener);
    this.previewButton.addActionListener(actionListener);
    this.applyButton.addActionListener(actionListener);
    this.saveButton.addActionListener(actionListener);
  }


  @Override
  public JComboBox<String> getComboBox() {
    return combobox;
  }

  @Override
  public String getPercentText() {
    return percentField.getText();
  }

  @Override
  public void setSelectedOptionLabelText(String text) {
    selectedOptionLabel.setText(text);
  }

  @Override
  public BufferedImage returnImage() {
    return bufferedImage;
  }

  @Override
  public void numericFieldError() {
    JOptionPane.showMessageDialog(previewFrame, "Please enter a percent value "
            + "between 0 and 100", null, JOptionPane.ERROR_MESSAGE);
    percentField.setText("");
    percentField.requestFocus();
  }

  @Override
  public void levelsAdjustError() {
    JOptionPane.showMessageDialog(previewFrame, "black, mid, white values should be "
            + "ascending and between 0-255", null, JOptionPane.ERROR_MESSAGE);
    black.requestFocus();
  }

  @Override
  public void previewFrameDispose() {
    previewFrame.dispose();
  }

  @Override
  public String getPopUpTextValue() {
    return JOptionPane.showInputDialog(frame, "Enter percentage : ",
            "Compression Factor", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void saveImage() {
    if (bufferedImage != null) {
      JFileChooser fileChooser = new JFileChooser();

      FileNameExtensionFilter pngFilter =
              new FileNameExtensionFilter("PNG Images", "png");
      FileNameExtensionFilter jpgFilter =
              new FileNameExtensionFilter("JPEG Images", "jpeg", "jpg");

      FileNameExtensionFilter ppmFilter =
              new FileNameExtensionFilter("PPM Images", "ppm");

      fileChooser.addChoosableFileFilter(pngFilter);
      fileChooser.addChoosableFileFilter(jpgFilter);
      fileChooser.addChoosableFileFilter(ppmFilter);


      fileChooser.setFileFilter(pngFilter);

      int result = fileChooser.showSaveDialog(null);

      if (result == JFileChooser.APPROVE_OPTION) {
        try {
          File selectedFile = fileChooser.getSelectedFile();
          String filePath = selectedFile.getAbsolutePath();

          FileNameExtensionFilter selectedFilter =
                  (FileNameExtensionFilter) fileChooser.getFileFilter();
          String[] extensions = selectedFilter.getExtensions();

          if (!filePath.toLowerCase().endsWith("." + extensions[0])) {
            filePath += "." + extensions[0];
          }

          if (extensions[0].equalsIgnoreCase("ppm")) {
            ImeController save = new ImeControllerImpl();
            save.saveImage(bufferedImage, filePath, extensions[0]);


          } else {
            ImageIO.write(bufferedImage, extensions[0], new File(filePath));
          }
          JOptionPane.showMessageDialog(frame, "Image saved successfully.",
                  "Save Image", JOptionPane.INFORMATION_MESSAGE);

          originalImageIcon = currentImageIcon;

        } catch (IOException ex) {
          JOptionPane.showMessageDialog(frame, "Error saving image.",
                  "Save Image Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    } else {
      JOptionPane.showMessageDialog(frame, "No image to save.",
              "Save Image Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private boolean unsavedChanges() {
    return !areImageIconsEqual(originalImageIcon, currentImageIcon);
  }

  private boolean areImageIconsEqual(ImageIcon icon1, ImageIcon icon2) {
    if (icon1 == null) {
      return true;
    }
    Image image1 = icon1.getImage();
    Image image2 = icon2.getImage();
    return image1.equals(image2);
  }

  @Override
  public String getBlackValue() {
    return black.getText();
  }

  @Override
  public String getWhiteValue() {
    return white.getText();
  }

  @Override
  public String getMidValue() {
    return mid.getText();
  }
}
