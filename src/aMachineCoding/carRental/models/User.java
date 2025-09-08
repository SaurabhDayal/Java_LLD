package aMachineCoding.carRental.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final int id;
    private final String name;
    private final String email;
    private final List<Reservation> reservations;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void deleteReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
