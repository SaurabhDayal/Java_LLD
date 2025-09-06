package aMachineCoding.quadTree;

public class QuadTreeDemo {
    public static void main(String[] args) {

        QuadTree tree = new QuadTree(0, 0, 100, 100);

        tree.insert(new Restaurant(1, "Pizza Place", 10.1, 20.1));   // top-left
        tree.insert(new Restaurant(2, "Burger Joint", 15.2, 25.3));  // top-left
        tree.insert(new Restaurant(3, "Sushi Spot", 30.4, 70.5));    // top-right
        tree.insert(new Restaurant(4, "Taco House", 60.6, 40.7));    // bottom-left
        tree.insert(new Restaurant(5, "Pasta Corner", 80.8, 80.9));  // bottom-right

        System.out.println();
        System.out.println("Find near (15, 20): ");
        for (Restaurant r : tree.find(15, 20)) {
            System.out.println(r);
        }
        System.out.println();

        System.out.println("Find near (55, 20): ");
        for (Restaurant r : tree.find(55, 20)) {
            System.out.println(r);
        }
        System.out.println();

        System.out.println("Find near (88, 90): ");
        for (Restaurant r : tree.find(88, 90)) {
            System.out.println(r);
        }
        System.out.println();
        tree.delete(new Restaurant(2, "Burger Joint", 15, 25));
        System.out.println("After deleting Burger Joint: ");
        for (Restaurant r : tree.find(15, 25)) {
            System.out.println(r);
        }
    }
}
