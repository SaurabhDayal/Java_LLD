package structural.compositePattern.compositePkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Concrete COMPOSITE class
public class CompositeBox implements Box {

    private final List<Box> children = new ArrayList<>();

    public CompositeBox(Box... boxes) {
        children.addAll(Arrays.asList(boxes));
    }

    @Override
    public double calculatePrice() {
        double total = 0.0;
        for (Box child : children) {
            total += child.calculatePrice();
        }
        return total;
    }
}