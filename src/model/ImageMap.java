package model;

/**
 * The ImageMap interface provides methods for adding, fetching,
 * and deleting images from an image map
 * as well as checking if an image is present in the cache and getting the size of the cache.
 * Cache has been implemented as a HashMap.
 */
interface ImageMap {

  /**
   * Adds an image with the given imageName and inputImage to the image HashMap.
   *
   * @param imageName the name of the image to be added to the image map.
   * @param imageArr  the Image object to be added to the cache.
   */
  void addImage(String imageName, int[][] imageArr) throws IllegalArgumentException;

  /**
   * Fetches the image with the given imageName from the image HashMap.
   *
   * @param imageName the name of the image to be fetched from the image map.
   * @return the Image object with the given imageName.
   */
  int[][] fetchImage(String imageName) throws IllegalArgumentException;

  /**
   * Checks if an image with the given imageName is present in the image map.
   *
   * @param imageName the name of the image to be checked in the image map.
   * @return true if the image is present in the image map, false otherwise.
   */
  boolean searchKey(String imageName);


}
