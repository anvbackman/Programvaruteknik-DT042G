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
The main gear class is simply implemented with setters and getters.
Since we may want to add more gear in the future it could be a good idea to store the gear in a JSON file. 
This way we can easily add more gear without having to make changes to the code. 
We would then need a way to read the JSON file. This can be done by using the JSON.simple library with JSONObject and
JSONParser. We then implement methods to get the needed parts from the JSON such as the gears category (weapons, armor, consumables),
the gears name, the gears values (damage, armor, consumable effect) and the gears cost.
The implementation is pretty straight forward where the gear type is retrieved using the category while the gear value and
cost is retrieved using the category and name.
```
    public int getCost(String category, String name) {
        JSONObject typeObject = (JSONObject) jsonObject.get(category);
        if (typeObject != null) {
            JSONObject itemObject = (JSONObject) typeObject.get(name);
            if (itemObject != null) {
                Long cost = (Long) itemObject.get("cost");
                if (cost != null) {
                    return cost.intValue();
                }
            }
        }
        return 0;
    }
```

### Supporting Classes
We will need some supporting classes to make the implementation easier to handle. These classes consist of the Constants
class which will simply hold the constants used in the application. 
The GearHandler which with the help of the JSONLoader will be used to retrieve the gear from the JSON file and in turn 
be used to retrieve the name, value and cost of gear that will be displayed in the shop.
Then we utilize the Randomizer class to generate the dice rolls for the game but also to generate things such as which 
mission to play and how many items should show up in the shop. For example a method to roll a D20 (20 sided dice) is implemented
as follows:
```
public static int rollD20(int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += (int) (Math.random() * 20) + 1;
        }
        return result;
    }
```
Where we take the amount of rolls as a parameter and then roll the dice n times and add the result to the total result.
We then implement the Output class which will be used to
print out the information to the console. This will be used to print out things such as combat logs, selections and
success/error messages. Lastly we implement the Validation class which will validate input from the user to be used for 
validation methods.


### Abilities
In the game we will have abilities that the character can use in combat. These abilities will extend the BaseAbility class
return the name of the ability and its cost. The BaseAbility class is an abstract class that should abstract methods that
calculate the damage based on the characters level, to get the targets and to execute the ability based on the
targets and character level. Apart from that the class should have getters to retrieve the ability name and cost.
Although the current implementation does not return the damage done based on the amount of targets or other factories 
such as element. The building blocks are there to implement this in the future. For example the ability WildBolt 
which will also take a specific element.
```
private String generateElement() {
        int index = Randomizer.rollD4(1);
        return ELEMENTS[index];
    }
```


### Characters
The characters in the game consists of the two classes Hero and StatSheet. The Hero class will hold the characters
name, stats (from the StatSheet), character class, its gear (weapons, armor, consumables) which we get from their
respective classes, the characters gold, abilities
and the characters health and mana. To accomplish this we will make use of mostly setters and getters. But there are also
some notable methods which will need further explanation. For example the showInventory method which will print out the 
gear that is currently equipped by the character and also the amount of gold. Since the amount of consumables a character
can have is plenty, we iterate over a list of consumables to showcase these.
We then need to be able to use these consumables which will take an input regarding which consumable to use and get the 
consumable that matches the input. We then use the consumable by passing it to the adjustHealth/Mana 
methods and remove it from the list of consumables. 
```
Consumables consumable = consumables.get(input - 1);
                    if (consumable.getName().contains(Constants.CONSUMABLE_TYPE_HEALTH)) {
                        System.out.println("You used " + consumable.getName() +
                                " and healed for " + consumable.getValue() + " HP.");
                        this.adjustHealth(consumable.getValue());
```
The adjustHealth and Mana methods simply adds the new value to the 
old value as long as the character is not already at full health/mana. Adding gold to the user utilizes a similar method to
add the gold. Since the character has no max gold we simply add the gold to the characters gold. 

The StatSheet class will hold elements such as the character level, experience, amount of rerolls when creating the character
and which stats the character should have. This class also utilizes setters and getters but also has some methods that
for example levels up the character which adds value to tthe characters stats, adds to the reroll amount and increases the characters
health and mana. The class also should have a method for the user to reroll the stats for the character and then update
and showcase the new stats. This is done with the help of the Randomizer class which rerolls the stats for the user.

### Creator

```

 





## Discussion
### 

### Alternative Approaches

## Personal Reflections