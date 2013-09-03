Colorimeter
===========

A Colorimeter that gives you RGB values for any point on your screen in either integer or float format.

![Integer](https://raw.github.com/ianb821/Colorimeter/master/Pictures/ColorimeterPictureOne.tiff)

![Float](https://raw.github.com/ianb821/Colorimeter/master/Pictures/ColorimeterPictureTwo.tiff)

##Instructions
To build, navigate to the directory containing the two .java files and compile them using:

```
javac Colorimeter.java
```

The program can then be run by typing the command:

```
java Colorimeter
```

To create a jar file, that can be used to distribute the program or to create an icon that can be double-clicked to run, type the following command after the first one above:

```
jar cvfe Colorimeter.jar Colorimeter *.class
```


##Upcoming Features:

 - Keyboard shortcut to copy RGB values to clipboard
 - Option to click to save current RGB values to clipboard