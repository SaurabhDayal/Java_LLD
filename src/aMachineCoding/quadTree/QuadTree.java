package aMachineCoding.quadTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class QuadTree {

    double top, left, bottom, right; // boundaries
    List<Restaurant> restaurants;    // only for leaf nodes
    QuadTree[] children;             // only for internal nodes
    static final int SPLIT_THRESHOLD = 4;

    QuadTree(double top, double left, double bottom, double right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        this.restaurants = new ArrayList<>();
        this.children = null;
    }

    boolean isLeaf() {
        return children == null;
    }

    // Insert a restaurant into the QuadTree
    void insert(Restaurant r) {
        if (!inBounds(r.latitude, r.longitude)) {
            return; // outside this node
        }

        if (isLeaf()) {
            restaurants.add(r);
            if (restaurants.size() > SPLIT_THRESHOLD) {
                split();
            }
        } else {
            for (QuadTree child : children) {
                child.insert(r);
            }
        }
    }

    // Find restaurants in the same cell as given location
    List<Restaurant> find(double lat, double lon) {
        if (!inBounds(lat, lon)) {
            return new ArrayList<>();
        }

        if (isLeaf()) {
            return new ArrayList<>(restaurants);
        } else {
            for (QuadTree child : children) {
                if (child.inBounds(lat, lon)) {
                    return child.find(lat, lon);
                }
            }
        }
        return new ArrayList<>();
    }

    // Delete a restaurant from the tree
    boolean delete(Restaurant r) {
        if (!inBounds(r.latitude, r.longitude)) {
            return false;
        }

        if (isLeaf()) {
            Iterator<Restaurant> it = restaurants.iterator();
            while (it.hasNext()) {
                Restaurant rest = it.next();
                if (rest.id == r.id) {
                    it.remove();
                    return true;
                }
            }
            return false;
        } else {
            boolean removed = false;
            for (QuadTree child : children) {
                removed |= child.delete(r);
            }
            // Optional: merge children back if total restaurants <= SPLIT_THRESHOLD
            int total = totalRestaurantsInChildren();
            if (total <= SPLIT_THRESHOLD) {
                mergeChildren();
            }
            return removed;
        }
    }

    // ---------------- private ------------------------------------

    private boolean inBounds(double lat, double lon) {
        return lat >= left && lat < right && lon >= top && lon < bottom;
    }

    private void split() {
        children = new QuadTree[4];
        double midLat = (left + right) / 2;
        double midLon = (top + bottom) / 2;

        children[0] = new QuadTree(top, left, midLon, midLat); // top-left
        children[1] = new QuadTree(top, midLat, midLon, right); // top-right
        children[2] = new QuadTree(midLon, left, bottom, midLat); // bottom-left
        children[3] = new QuadTree(midLon, midLat, bottom, right); // bottom-right

        for (Restaurant r : restaurants) {
            for (QuadTree child : children) {
                child.insert(r);
            }
        }
        restaurants.clear();
    }

    private int totalRestaurantsInChildren() {
        int total = 0;
        for (QuadTree child : children) {
            if (child.isLeaf()) {
                total += child.restaurants.size();
            } else {
                total += child.totalRestaurantsInChildren();
            }
        }
        return total;
    }

    private void mergeChildren() {
        List<Restaurant> merged = new ArrayList<>();
        for (QuadTree child : children) {
            if (child.isLeaf()) {
                merged.addAll(child.restaurants);
            } else {
                child.collectAll(merged);
            }
        }
        children = null;
        restaurants = merged;
    }

    private void collectAll(List<Restaurant> list) {
        if (isLeaf()) {
            list.addAll(restaurants);
        } else {
            for (QuadTree child : children) {
                child.collectAll(list);
            }
        }
    }
}

