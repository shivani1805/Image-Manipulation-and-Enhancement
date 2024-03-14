#The following operations are supported by our application.

* load an image
* save an image
* apply red/green/blue component on an image
* vertically flip an image
* horizontally flip an image
* blur an image
* sharpen an image
* apply sepia on an image
* apply luma on an image
* color correct an image
* display histogram of the image displayed on screen
* level adjust an image
* compress an image
* display split preview for operations : blur, sharpen, luma, sepia, color correct , levels adjust.


#Examples of using the GUI are provided below. 

* Click on load image button and navigate to the image path using the file chooser dialog box in order to load an image.
* Choose the operation to be applied on the image using the drop-down edit menu. 
* For operations involving blur, sharpen, luma, sepia, color correct , a new preview window will open which prompts the 
user to enter the split percentage and click on preview button in order to view the split preview. In order to confirm the
change, click on apply button which will apply the change on the original image and the split preview is disposed. 
* For operations involving levels adjust, a new preview window will open which prompts the user to enter the black,mid and
white values in addition to the split percentage. The split preview and apply buttons are provided with the same use as above.
* Validations are in place in case the percentage provided is not between 0-100 and if black,mid, white values are not in ascending 
order or outside the range of 0-255. 
* In order to save an image, click on the save button on the bottom right of the screen.
* If the user loads an image and applies some operation on it and tries to load another image without saving the image on screen. Then the user is prompted to save the image.

# A few sample images - 

![new10.jpg](..%2F..%2F..%2F..%2FDownloads%2Fnew10.jpg)
![new6.jpg](..%2F..%2F..%2F..%2FDownloads%2Fnew6.jpg)
![new.jpg](..%2F..%2F..%2F..%2FDownloads%2Fnew.jpg)
![new1.jpg](..%2F..%2F..%2F..%2FDownloads%2Fnew1.jpg)
![new3.jpg](..%2F..%2F..%2F..%2FDownloads%2Fnew3.jpg)
![new4.jpg](..%2F..%2F..%2F..%2FDownloads%2Fnew4.jpg)
![new5.jpg](..%2F..%2F..%2F..%2FDownloads%2Fnew5.jpg)
![new7.jpg](..%2F..%2F..%2F..%2FDownloads%2Fnew7.jpg)
![new8.jpg](..%2F..%2F..%2F..%2FDownloads%2Fnew8.jpg)
![new9.jpg](..%2F..%2F..%2F..%2FDownloads%2Fnew9.jpg)
