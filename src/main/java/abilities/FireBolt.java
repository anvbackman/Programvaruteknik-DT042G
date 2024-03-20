package abilities;

import support.Randomizer;

public class FireBolt extends BaseAbility{

    public FireBolt() {
        super("FireBolt");
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
