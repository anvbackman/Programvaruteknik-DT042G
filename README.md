# Project

## Environment & Tools
Lenovo Ideapad 5, Windows 10, IntelliJ IDEA, Java, Git 2.37.3, Google Chrome, Bitbucket, Trello

## Purpose
The main purpose of this project is to together with a group implement an application in Java utilizing the knowledge
gained in the course's learning objectives and by the use of a specific workflow. In order for the project to be seen
as successful, the following goals need to be met:
- The application should be implemented in accordance to Test Driven Development (TDD) principles.
- The application should be implemented using Maven.
- The application should be implemented in accordance to the workflow specified in Trello  ??????????????????
- The application should be implemented utilizing a branch for each feature in correlation to the workflow.
- The application should be ran using user input via the console.

## Procedures

### Deciding on the Project

### Project Proposal

### Gears
In this application we will need some gear for the character to buy and use. The gear will be divided into the categories
weapons, armor and consumables which will extend the gear class. These are then implemented with their own attributes and effects. 
Since we may want to add more gear in the future it could be a good idea to store the gear in a JSON file. 
This way we can easily add more gear without having to make changes to the code. 
We would then need a way to read the JSON file. This can be done by using the JSON.simple library with JSONObject and
JSONParser. We then implement methods to get the needed parts from the JSON such as the gears category (weapons, armor, consumables),
the gears name, the gears values (damage, armor, consumable effect) and the gears cost.
-----------------------------------
Since we are utilizing TDD we would need to write the tests first and
then implement the code. Hence why we start with that.
Here we need to test some key points such as making sure that the file is read, that the gears key and value pairs are
correct, that the gear categories are correct and that the cost of the gear is correct.
We can then start by setting up the test with what the JSON loader should be. We then implement the tests mentioned
above such as checkin that the file should not be null and some tests to check the gear categories and their attributes.
This will of course fail since we have not implemented the code yet.
--------------------------

### Supporting Classes
We will need some supporting classes to make the implementation easier to handle. These classes consist of the Constants
class which will simply hold the constants used in the application. 
The GearHandler which with the help of the JSONLoader
will be used to retrieve the gear from the JSON file and in turn be used to retrieve the gear in the shop.
Then we utilize the Randomizer class to generate the dice rolls for the game but also to generate things such as which 
mission to play and how many items should show up in the shop. We then implement the Output class which will be used to
print out the information to the console. This will be used to print out things such as combat logs, selections and
success/error messages. Lastly we implement the Validation class which will validate input from the user to be used for 
validation methods.



## Discussion
### 

### Alternative Approaches

## Personal Reflections