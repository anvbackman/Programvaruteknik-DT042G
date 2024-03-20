package abilities;

import support.Randomizer;

public class WarCry extends BaseAbility {

    public WarCry() {
        super("WarCry");
    }

    private int damageCalc(int CharLevel) {

        int result = Randomizer.rollD10(CharLevel);
        return result;

    }

    private int getTargets(){
        return 1;
    }

    public void execute(int target, int charLevel) {
        int targets = getTargets();
        int damage = damageCalc(charLevel);
    }
}

