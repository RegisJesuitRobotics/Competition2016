package game;

// change the package to whatever it's in




import java.util.Scanner;

import java.util.Random;




public class Main {




	public static void main(String[] args) {




		// system objects.




		Scanner in = new Scanner(System.in);

		Random rand = new Random();




		// game variables

		String[] enemies = { "Skeleton", "Bandit", "Warrior", "Golem", "Kobold", "Troll" };

		int maxEnemyHealth = 75;

		int enemyAttackDamage = 25;




		// player variables

		int health = 100;

		int attackDamage = 50;

		int numHealthPotions = 3;

		int healthPotionHealAmount = 30;

		int healthPotionDropChance = 50; // percentage

		int key = 0;




		boolean running = true;

		System.out.println("Welcome to the Dungeon! -Jackson");




		GAME: while (running) {




			System.out.println("-----------------------------------------------------------");

			System.out.println("You awaken in a dark room. Behind you a stray ray of");

			System.out.println("moonlight shines in through the cracks of a locked wooden");

			System.out.println("door. Suddenly you see a movment in the shadows!");




			int enemyHealth = rand.nextInt(maxEnemyHealth);

			String enemy = enemies[rand.nextInt(enemies.length)];

			System.out.println("");

			System.out.println("\t# A " + enemy + " appeared! #\n"); // the #n is

																	// a

																	// line

																	// break




			while (enemyHealth > 0) {

				System.out.println("\t Your HP " + health);

				System.out.println("\t" + enemy + "'s HP " + enemyHealth);

				System.out.println("\n\t What would you like to do?");

				System.out.println("\t1. Attack");

				System.out.println("\t2. Drink a Health Potion");

				System.out.println("\t3. Run");




				String input = in.nextLine();

				if (input.equals("1")) {

					int damageDealt = rand.nextInt(50);

					int damageTaken = rand.nextInt(25);




					enemyHealth -= damageDealt;

					health -= damageTaken;




					System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage!");

					System.out.println("\t> The " + enemy + " retaliates for for " + damageTaken + " damage!");




					if (health < 1) {

						System.out.println("\t> You have taken too much damage and cannot go on.");

						break;

					}

				} else if (input.equals("2")) {

					if (numHealthPotions >= 1) {

						health += healthPotionHealAmount;

						numHealthPotions--;

						System.out.println(

								"\t> You've downed the health potion and healed for " + healthPotionHealAmount + " HP");

						System.out.println("\t> You now have " + health + " total health points!");

						System.out.println("\t> You have " + numHealthPotions + " left");

					} else {

						System.out.println("\t> You dont have any potions left");

					}

				} else if (input.equals("3")) {

					System.out.println("\t> You run away from the " + enemy + "!");

					continue GAME;

					// so this basically stops this loop and strats over from

					// GAME




				} else {

					System.out.println("\t You dont know how to do that");

				}

			}

			if (health < 1) {

				System.out.println("You limp out of the dungeon, weak from battle");

				break;

			}

			System.out.println("-----------------------------------------------------------");

			System.out.println(" # " + enemy + " has been defeated #");

			System.out.println(" # You have " + health + "HP left #");

			if (key < 1) {

				key++;

				System.out.println(" # The " + enemy + " dropped a key. It looks like it will");

				System.out.println(" # unlock the door so you can escape!");

			}

			if (rand.nextInt(100) < healthPotionDropChance) {

				numHealthPotions++;

				System.out.println(" # The " + enemy + " has dropped a health potion");

				System.out.println(" # You now have " + numHealthPotions + " health potions");




			}

			System.out.println("What would you like to do now? ");

			System.out.println("1. Continue fighting");

			System.out.println("2. Exit the Dungeon");




			String input = in.nextLine();




			while (!input.equals("1") && !input.equals("2")) {

				System.out.println("You dont know how to do that");

				input = in.nextLine();

			}

			if (input.equals("1")) {

				System.out.println("\tYou continue walking deeper into the dungeon");




			} else if (input.equals("2")) {

				System.out.println("\tYou leave the dungeon succesful from your adventure");

				break;

			}




		}

		System.out.println("\t######################");

		System.out.println("\t# THANKS FOR PLAYING #");

		System.out.println("\t#   BY JACKSON DEAN  #");

		System.out.println("\t######################");

	}

}
