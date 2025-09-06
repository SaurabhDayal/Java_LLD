package aMachineCoding.quadTree;

class Restaurant {
    int id;
    String name;
    double latitude;
    double longitude;

    Restaurant(int id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return String.format("Restaurant{id=%d, name='%s', lat=%.2f, lon=%.2f}",
                id, name, latitude, longitude);
    }
}
