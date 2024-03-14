package model;

import java.util.HashMap;
import java.util.Map;


/**
 * CacheImpl class implements Cache interface and provides methods for adding, fetching, deleting
 * and searching images from the cache.
 */
class ImageMapImpl implements ImageMap {

  private static ImageMap cacheInstance;
  private final Map<String, int[][]> imageCache;

  /**
   * Constructor for CacheImpl class.
   *
   * @param imageCache HashMap to store images in cache
   */
  public ImageMapImpl(Map<String, int[][]> imageCache) {
    this.imageCache = imageCache;
  }

  @Override
  public void addImage(String imageName, int[][] imageArr) throws IllegalArgumentException {
    if (imageName == null || imageArr == null) {
      throw new IllegalArgumentException("image key and provided image must be non null");
    } else if (imageName.isEmpty()) {
      throw new IllegalArgumentException("image key provided must have more than 1 character");
    }
    this.imageCache.put(imageName, imageArr);
  }

  @Override
  public int[][] fetchImage(String imageName) throws IllegalArgumentException {
    if (!this.searchKey(imageName)) {
      throw new IllegalArgumentException("image key not found");
    }
    return imageCache.get(imageName);
  }

  @Override
  public boolean searchKey(String imageName) {
    return imageCache.containsKey(imageName);
  }


  /**
   * Gets the entire hashmap.
   */
  public static ImageMap getMap() {
    if (cacheInstance == null) {
      Map<String, int[][]> imageCache = new HashMap<>();
      cacheInstance = new ImageMapImpl(imageCache);
    }
    return cacheInstance;
  }

}
