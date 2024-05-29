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

### Enemies
The Enemies package contains the different classes for the enemies and their abilities, which are used for the combat system.
The enemies are divided into different classes, each with their own stats and abilities.
The enemies are then implemented in the game using the Template design pattern.
This way additional types of enemies, abilities and bosses may be implemented in the future without having to make major changes to the code for new bosses or enemies.
the base class of the template design is the Enemy class which contains the basic attributes and methods for the enemies.
This base class provides the basic structure for the enemies and their abilities, as well methods to set and get the enemies stats, and relevant methods for the combat system.
The enemies are then implemented by extending the Enemy class and their ability is a part of their implementation, but vary depending on the enemy type.
The current enemies are Goblins, Kobolds, Zombies as well as additional bosses of each type.
The bosses are unique entities and has some additional stat bonuses, abilities and a unique ability.

### Scenarios
Similar to the enemies package, scenarios follows the template design model, the class "BaseScenario" is the base class for the scenarios.
Through this class the game can implement different scenarios such as combat, shop, mission and event scenarios.
The constructor takes in the mission, and generates encounter using the encounterGenerator class.
The types of missions are divided into different classes, each with their own attributes and methods.
Social, Rest, Puzzle, Boss, MiniBoss and Battles.
Each encounter recieves the mission type, the playable hero and the scanner for user input.
depending on the difficulty and length of the mission the number of encounters and which types of encounters are generated.
A Puzzle encounter will generate a puzzle, which can be solved in different ways in some case depending on player class, stats and gear.
a Rest encounter will allow the player to rest and regain health, but may also have a chance of an event happening, however a player with the ranger class has an additional bonus.
A Social encounter will allow the player to interact with the NPC, and depending on the player class and stats, the player may get a reward or a penalty.
A Battle encounter will generate a battle with the enemies available for the mission, in some events it can also spawn a mini-boss, the combat is handled through the combatHandler and GameEngine.
A Boss encounter will generate a boss battle, which is a more difficult battle than a regular battle by buffing the boss and giving it additional abilities.
The Shop encounter will generate a shop, where the player can buy gear, consumables and sell gear, the shop is generated through the gearHandler and the player can buy gear using the gold they have earned from missions.

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
for example levels up the character which adds value to the characters stats, adds to the reroll amount and increases the characters
health and mana. The class also should have a method for the user to reroll the stats for the character and then update
and showcase the new stats. This is done with the help of the Randomizer class which rerolls the stats for the user.

### Creator
The creator package contains the classes to create a character as well as creating the missions, populating each mission with scenarios and encounters.
The CharacterCreator class is used to create a character, where the user can choose the name, class and stats for the character.
using the Validation to validate the user input, the user can choose the name, class and stats for the character.
Stats are rolled using the Randomizer class, and the user can reroll one of the stats up to 3 times if they are not satisfied with the stats.
The selected class receives an ability based on their class, and the character is then created with the selected stats, class and ability.
The ability is currently similar between all classes, but with all the building blocks in place altering the abilities and giving additional abilities is easily implemented.
The reason they are the same is mainly for balance reasons, because whilst the game is fully functional, it is not balanced.

The MissionCreator class is used to create missions, where the user can choose the difficulty of the mission through the GameEngine.
The Mission keeps track on which fork the player is located in, which difficulty the mission is, and length of the mission.
Each fork on the mission has a scenario, which is generated by the Scenario class, and the scenario is then populated with encounters.
The first scenario is always a combat scenario, but during the route to the boss the player can encounter different scenarios, which is handled through generateFork.

### Main Package
The main package contains the main class for the game, the Main file for executing the game, the CombatHandler and the GameEngine class.
The Main class is used to start the game, and the user can choose to create a character.
The CombatHandler class is used to handle the combat between the player and the enemies.
Rolling initiative seeing which character goes first, and then the player and the enemies take turns attacking each other.
A player has 2 actions per turn, and enemies only have one action per turn, when an entity finishes their turn, the next entity takes their turn.
The playerTurn() method is used to handle the player turn, where the player can choose to attack, use an ability, use a consumable or escape.
the EnemyTurn() method is used to handle the enemy turn, where the enemy attacks the player.

During the player turn, the chooseTarget() method is used to select which enemy to attack.
After defeating the enemies, the player receives gold and experience using determineReward, and the player can choose to continue to the next scenario or return to the main menu.

The GameEngine class is used to handle the game, traversing through the different scenarios and encounters, as well as handling the player's inventory and gold.



 





## Discussion
### 

### Alternative Approaches

## Personal Reflections