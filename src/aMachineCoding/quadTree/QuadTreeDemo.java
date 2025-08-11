package aMachineCoding.quadTree;

public class QuadTreeDemo {
    public static void main(String[] args) {
        QuadTree tree = new QuadTree(0, 0, 100, 100);

        tree.insert(new Restaurant(1, "Pizza Place", 10, 20));
        tree.insert(new Restaurant(2, "Burger Joint", 15, 25));
        tree.insert(new Restaurant(3, "Sushi Spot", 30, 40));
        tree.insert(new Restaurant(4, "Taco House", 35, 45));
        tree.insert(new Restaurant(5, "Pasta Corner", 50, 60));

        System.out.println("Find near (15, 25): " + tree.find(15, 25));

        tree.delete(new Restaurant(2, "Burger Joint", 15, 25));
        System.out.println("After deleting Burger Joint: " + tree.find(15, 25));
    }
}
