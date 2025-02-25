package src;

public class Dessert {
    public int flavor;
    public int price;
    static int count = 0;

    public Dessert(int falvor, int price) {
        this.flavor = falvor;
        this.price = price;
        count++;
    }

    public void printDessert() {
        System.out.print(this.flavor + " " + this.price + " " + count);
    }

    public static void main(String args[]) {
        System.out.println("I love dessert!");
    }
}
