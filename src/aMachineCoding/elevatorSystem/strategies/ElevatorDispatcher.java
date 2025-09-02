package aMachineCoding.elevatorSystem.strategies;

import aMachineCoding.elevatorSystem.models.Elevator;
import aMachineCoding.elevatorSystem.models.Request;

import java.util.List;

public interface ElevatorDispatcher {
    Elevator selectElevator(List<Elevator> elevators, Request request);
}

/*
    Elevator System Dispatching Algorithms
    --------------------------------------

    These are scheduling algorithms originally from disk scheduling,
    but also used in elevator control systems to decide the order in
    which requests (floors) are served.

    ---------------------------------------------------------------
    1. FCFS (First Come, First Serve)
       - Meaning        : Serve the requests in the exact order they arrive.
       - Elevator analogy: If requests for floors 2, 8, 4 come in that order,
                           the elevator goes 2 → 8 → 4, even if it means
                           extra travel.
       - Pros           : Simple, fair.
       - Cons           : May lead to long travel and inefficiency.

    ---------------------------------------------------------------
    2. SSTF (Shortest Seek Time First)
       - Meaning        : Always serve the closest request to the current position.
       - Elevator analogy: If the elevator is at floor 5 and requests are at
                           2, 6, and 9 → it will go to 6 first (closest),
                           then 9, then 2.
       - Pros           : Reduces travel time for each request.
       - Cons           : Can cause starvation (far-away requests may be
                           ignored for a long time).

    ---------------------------------------------------------------
    3. SCAN (Elevator Algorithm)
       - Meaning        : The elevator keeps moving in one direction, serving
                           all requests along the way, until it reaches the end.
                           Then it reverses direction.
       - Elevator analogy: If moving upward, it will serve all requests going
                           up until the top, then reverse and serve requests
                           on the way down.
       - Pros           : Fair, avoids starvation, predictable.
       - Cons           : May serve some requests later even if they arrived
                           earlier.

    ---------------------------------------------------------------
    4. LOOK (Optimized SCAN)
       - Meaning        : Same as SCAN, but instead of going all the way to the
                           extreme end (top/bottom floor), it reverses as soon
                           as the last request in that direction is served.
       - Elevator analogy: If the last upward request is at floor 10 (not the top
                           floor 20), the elevator stops at 10 and turns around,
                           instead of going all the way up to 20 unnecessarily.
       - Pros           : More efficient than SCAN.
       - Cons           : Still not optimal for all cases.
*/
