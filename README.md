## How to use our program - 
There are 3 ways to use our program :
* java -jar Program.jar -file path-of-script-file : please use the command : java -jar Archive.jar -file script.txt. - file is an option command line argument. when invoked in this manner the program will open the script file, execute it and then shut down.
* java -jar Program.jar -text : when invoked in this manner the program will open in an interactive text mode, allowing the user to type the script and execute it one line at a time. This is how the program worked in the last assignment.
* java -jar Program.jar : when invoked in this manner the program will open the graphical user interface. This is what will happen if you simply double-click on the jar file.However,when running the jar file by double-clicking on it, when you click on the load image button, the file chooser dialog box might not load all the files/images in MacOS. It seems to be access issue on MacOS.
* Pressing the run button on the ImageManipulationProgram.java file in the IDE : this opens the GUI and on screen buttons can be used to load images, manipulate them and save them. 

## Complete parts of our assignment -
* Our assignment has the existing functionality from assignments 4 and 5 as well as the interactive GUI functionality from assignment 6 that supports the following functions:
  blueComponent, blur, colorCorrect, compress, greenComponent, histogram, horizontal flip, levelsAdjust, load, luma, redComponent, save, sepia, sharpen, vertical flip.
* The existing functionality it already supported includes -
* Load an image
* Save the image
* Perform following image manipulation functions:
blueComponent, blur, brighten, colorCorrect, compress, greenComponent, histogram, horizontal, intensity, levelsAdjust, load, luma, redComponent, rgbCombine, rgbSplit, run, save, sepia, sharpen, value, vertical.
* Execute a file of commands from the console
* Run the assignment from the CLI using the JAR file

## Design changes and justification:
* We have introduced a new controller - ViewControllerImpl - that implements the ViewController interface. Our action listeners lie here, and we needed this controller to segregate activities so that we don't have to make changed to our existing controllers.
* We have introduced the view package to our project. Within this we have our ImeViewImpl class that implements the ImeView interface. We needed this to create the interactive GUI of our project. Here lies our frontend design logic using JFrame, and and the action events are set as well.
* Our ImageManipulationProgram.java file houses our main function. We now send our view object to the controller too, in addition to the model object which was already being sent. This is the only modification being made here, as we needed to connect our view with the controller.

## Existing project code from previous assignments which has not been modified: 
## Controller - 
Classes:
1. commands folder - this folder includes classes for every individual command that we applied. Within each of these classes we have functionality to execute the commands and check arguments provided. These classes are called from our hashmap of commands in the InputControllerImpl class. The classes included in this folder are:
blueComponent, blur, brighten, colorCorrect, compress, greenComponent, histogram, horizontal, intensity, levelsAdjust, load, luma, redComponent, rgbCombine, rgbSplit, run, save, sepia, sharpen, value, vertical. 
2. ImeControllerImpl class - It implements the ImeController interface.  
3. InputControllerImpl class - It implements the InputController interface. The hashmap of commands is in this class, as well as functionality needed to process the command line input obtained by the program and pass control to the relevant command class. 

Interfaces:
1. CommandController interface - All the classes in the commands folder implement this interface. It has functionality to execute the particular command and check the arguments provided. 
2. ImeController interface - It is implemented by the ImeControllerImpl class and has functions for BufferedImage to 2D pixel array conversion as well as read and save image. 
3. InputController interface - It is implemented by the InputControllerImpl class. It contains methods for input processing and command execution. 
4. FileController interface - It extends the CommandController interface. It is implemented by the run.java class in the commands folder within the controller. It facilitates taking in command line interface commands and running our application via the CLI. 

## Model - 
Classes:
1. CommandModelImpl class - It implements the CommandModel interface. It has functionality for every command to access the image map, check if a particular image with a parent key exists in it, extract it, send it to the ImeModelImpl class to perform the image manipulation function on it, get the updated version back and add it to the image map. Also has save, load and deep copy functions.
2. ImageMapImpl class - It implements the imageMapImpl interface. It performs the creation of the image cache hashmap, and the functionality for adding, fetching, searching images and getting the entire map. 
3. ImeModelImpl class -  It implements the ImeModel interface. It contains the business logic of each image manipulation command. The new image manipulation command implementations provided with this assignment, and their logic is in this file.

Interfaces:
1. CommandModel interface - It is implemented by the CommandModelImpl class. It has functionality for every command to provide the parent key and final key for the image being manipulated in order to store and access the images in the image map.
2. ImageMap interface - It is implemented by the imageMapImpl class. It has functions needed for image hashmap manipulation. Facilitates proper storage of the images where they can be identified by the image key and no overwriting occurs.
3. ImeModel interface -  It is implemented by the ImeModelImpl class. It contains the signatures of all the command implementation functions.

## Image source/ citation: The image used in our program is our own property. We own it and are authorising its use in our project.