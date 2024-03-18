package Enemies;

public abstract class Enemies {

    EnemyAbility ability;

    String type;
    int health;
    int damage;
    int armor;

    public Enemies(String type, int health, int damage, int armor) {
        this.type = type;
        this.health = health;
        this.damage = damage;
        this.armor = armor;
        this.ability = new EnemySpecial();
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHealth() {
        if(health <= 0) {
            health = 0;
            Death();
        }
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    public String getType() {
        return type;
    }

    public void attack(int dmg) {
        System.out.println(type + " is Attacking");
        damage = dmg;
    }

    public void doAbility() {
        ability.performAbility();
        System.out.println(type + " used special ability");
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(type + " took " + damage + " damage");
    }

    public void Death() {
        System.out.println(type + " has died");
    }

}
