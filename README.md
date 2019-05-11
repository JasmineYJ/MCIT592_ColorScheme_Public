
Week 1 First milestone - not allow user input - one test image; 

Make all the classes - class names and files existing;
Write as many method signatures as possible (name, return type, argument) and leave those method empty - have the structure;
Can start with a couple method; 

-------------
Final Project Proposal

Team Member: Yuhong Du(yuhongdu@seas.upenn.edu), Yue Jian(yuejian@seas.upenn.edu), Zikai Xu(zikai@seas.upenn.edu) (ordered by last name)

Topic: Color Scheme Analysis and Recommendation

Purpose: Party planning sometimes is all about choosing the right color. Before we can doing anything about choosing the decor, food and dressing code, we have to make a decision: what colors best represent this event and speak for the hoster? Simple is powerful. Why not just start with one picture? And save all the troubles as it can help you find out the missing puzzles! 

We empower our user with the ability to focus by extracting the top N colors (N is decided by user) and help our user to explore by offering a set of similar pictures to inspire sparkling ideas.

Analysis: By reading and storing the HSB value of each pixel, we generate a Map containing 20 ArrayList, with key being a range of Hue.  We then compute the K-means of each color group and return an ArrayList called average color. Finally, sort the average colors by the number of pixels each color associated with. Please refer to below allocation breakout for further details. 

Evaluation: We further evaluate our color scheme analysis against existing color scheme from Pinterest, which aims to establish baselines for the development of our algorithms. 
   
Recommendation: Building on the existing color scheme analysis we performed over library, we make further suggestions to user based on the top N colors we read. Our dataset contains 200 theme photos and we will push the top three to the user through a user-friendly interface. 


Class and Allocation:

All Main: build all the necessary objects in a main class that reads in the file and calls the methods; 
ZX User Input: read and process uploaded picture and the number of colors that a user wish to see; 
ZX GUI:  friendly interface connection between user and program; 
YD Top N Color Calculation: this class store and sort the average color of each Hue range. 
ZX/YJ SystemOutput: this class converts HSB to RGB and generates the output top N color and suggested picture;
YD K-Mean Calculation: find the average color range of each Hue group; 
YJ ColorPictureLibrary: the “Color_Picture” class contains a map of pictures and “HSB_Values”. Each picture should have instance variables for the “HSB Values” and “FileName”; 
YJ ColorNameLibrary: a class holds of “HSB_Values” and “Color Names”;
YJ ColorInt: given color is primitive, we add a calss for colorInt to hold the RGB value;

Procedures: 

Collect 200 pictures from Pinterest that represents different color schemes and party theme. Analyzing color patterns through these 200 pictures. The results serve as our “color library ” and will be used for further recommendations. - try automatically download - for now 10 diversified pictures make sure the code works. Then research a bit how to - 

Collect 500 color groups read from the aforementioned pictures based on HUE values and assign color name to each color; store them in a map<Hue Value, Name>.    
Design the user interface which prompts the user to upload a picture, and to choose how many colors the user wants in the output color scheme. Also, we may add some more features on the interface where it can more accurately give recommended color.
As for the interface, the graphic classes provided in JDK are reused to construct our own GUI.   
As there are many type of images (jpg, png, tiff, ext), check and confirm the image type from the user is readable. 
Potential Need: Reduce the size of the image to a workable size (maybe 500 pixels max for each side), if the image uploaded by user is too large.
Loop through each pixel from the image, for each pixel, get its RGB (red, green, blue) value, and then convert RGB to HSB (Hue, Saturation, Brightness), finally store it in an Array or a Map.
As Hue value is from 0 to 360, which represent the color, divide Hue by 18, sort pixels into 20 color category. Get the size of the 20 color group, if some color group has much more pixels than average, then divide this group into some sub groups, to return top N colors. For other groups, return an average color. (To compute  the average or the top N colors, might need to check K-means). For all the colors got from the above steps, compare how many pixels it associated with, then get the top N colors.
Now we have the HSB value of the top N colors, convert HSB to RGB values, then transfer this information and print out to an image, to display these colors to user. 
Use the returned N colors as keys to pull color names and similar pictures from aforementioned maps, which holds an ArrayList of top N colors, color names and corresponding pictures.
 For the N colors we generate, if the user wants to refine fine tunes (like adjust brightness, saturation), we display some options for users to choose from.

Feeds user the color scheme results and top three photos with the most relevant color scheme.
