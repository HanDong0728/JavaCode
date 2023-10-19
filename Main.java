import java.util.Scanner;
import java.util.ArrayList;
/**
 * @author 王汉东
 */
public class Main {		
    public static void main(String[] args) {
        Player p1 = new Player();
        Player p2 = new Player();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), flag = 1;
        scanner.nextLine();
        String str;
        String e="end";

        for (int i = 0; i < n; i++) {
            str = scanner.nextLine();

            if (str.startsWith("summon")) {
                String[] parts = str.split(" ");
                int pos1 = Integer.parseInt(parts[1]);
                int atk = Integer.parseInt(parts[2]);
                int h = Integer.parseInt(parts[3]);

                if (flag == 1) {
                    p1.summon(pos1, h, atk);
                } else {
                    p2.summon(pos1, h, atk);
                }
            } else if (str.startsWith("attack")) {
                String[] parts = str.split(" ");
                int pos1 = Integer.parseInt(parts[1]);
                int pos2 = Integer.parseInt(parts[2]);

                if (flag == 1) {
                    p1.attack(pos1, p2, pos2);
                } else {
                    p2.attack(pos1, p1, pos2);
                }

                // If one player is dead after an attack
                if (!p1.alive) {
                    System.out.println(-1);
                    p1.display();
                    p2.display();
                    return;
                } else if (!p2.alive) {
                    System.out.println(1);
                    p1.display();
                    p2.display();
                    return;
                }

            } else if (str.equals(e)) {
                flag = 3 - flag;
            }
        }

        // If the game didn't end in-between, print final state
        System.out.println(0);
        p1.display();
        p2.display();
    }

    static class Player {
        int health;
        boolean alive;
        ArrayList<Attendent> attendents;

        Player() {
            health = 30;
            alive = true;
            attendents = new ArrayList<>();
        }

        void summon(int position, int health, int attack) {
            Attendent newAttendent = new Attendent(health, attack);
            attendents.add(position - 1, newAttendent);
        }

        void attack(int p, Player that, int q) {
            int atk1 = this.attendents.get(p - 1).atk;
            
            if (q == 0) {
                that.health -= atk1;
                if (that.health <= 0) {
                    that.alive = false;
                }
            } else {
                int atk2 = that.attendents.get(q - 1).atk;

                this.attendents.get(p - 1).health -= atk2;
                that.attendents.get(q - 1).health -= atk1;

                if (this.attendents.get(p - 1).health <= 0) {
                    this.attendents.remove(p - 1);
                }
                if (q <= that.attendents.size() && that.attendents.get(q - 1).health <= 0) {
                    that.attendents.remove(q - 1);
                }
            }
        }

        void display() {
            System.out.println(health);
            System.out.print(attendents.size() + " ");
            for (int i = 0; i < attendents.size(); i++) {
                System.out.print(attendents.get(i).health + " ");
            }
            System.out.println();
        }
    }

    static class Attendent {
        int health;
        int atk;

        Attendent(int h, int a) {
            health = h;
            atk = a;
        }
    }
}
