package arena;

public class Main {
    public static void main(String[] args) {

//Creates fightingArena
        FightingArena fightingArena = new FightingArena("Armeec");

//Creates stats
        Stat firstGlariatorStat = new Stat(20, 25, 35, 14, 48);
        Stat secondGlariatorStat = new Stat(40, 40, 40, 40, 40);
        Stat thirdGlariatorStat = new Stat(20, 25, 35, 14, 48);

//Creates weapons
        Weapon firstGlariatorWeapon = new Weapon(5, 28, 100);
        Weapon secondGlariatorWeapon = new Weapon(5, 28, 100);
        Weapon thirdGlariatorWeapon = new Weapon(50, 50, 50);

//Creates gladiators
        Gladiator firstGladiator = new Gladiator("Stoyan", firstGlariatorStat, firstGlariatorWeapon);
        Gladiator secondGladiator = new Gladiator("Pesho", secondGlariatorStat, secondGlariatorWeapon);
        Gladiator thirdGladiator = new Gladiator("Author", thirdGlariatorStat, thirdGlariatorWeapon);

//Adds gladiators to fightingArena
        fightingArena.add(firstGladiator);
        fightingArena.add(secondGladiator);
        fightingArena.add(thirdGladiator);

//Prints gladiators count at the fightingArena
        System.out.println(fightingArena.getCount());

//Gets strongest gladiator and print him
        Gladiator strongestGladiator = fightingArena.getGladiatorWithHighestTotalPower();
        System.out.println(strongestGladiator);

//Gets gladiator with the strongest weapon and print him
        Gladiator bestWeaponGladiator = fightingArena.getGladiatorWithHighestWeaponPower();
        System.out.println(bestWeaponGladiator);

//Gets gladiator with the strongest stat and print him
        Gladiator bestStatGladiator = fightingArena.getGladiatorWithHighestStatPower();
        System.out.println(bestStatGladiator);

//Removes gladiator
        fightingArena.remove("Author");

//Prints gladiators count at the fightingArena
        System.out.println(fightingArena.getCount());

//Prints the fightingArena
        System.out.println(fightingArena);


    }
}
