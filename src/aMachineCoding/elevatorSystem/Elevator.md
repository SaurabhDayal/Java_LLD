# Elevator System Design

## 🧠 Introduction
The elevator system in a building is a real-time, multi-agent system responsible for:
- Transporting passengers between floors
- Reducing passenger wait and travel time
- Efficient energy usage
- Ensuring fairness and responsiveness

The core component of such a system is the **scheduling algorithm**, which decides which elevator should serve which request, and in what order.

---

## 🧩 Core Concepts

### 1. Requests
- A request consists of a **source floor** and a **destination floor**.
- Requests can be:
    - **External**: Pressing the “up” or “down” button on a floor.
    - **Internal**: Pressing a floor number inside the elevator car.

### 2. Elevator States
Each elevator can be in one of the following states:
- **Idle**: Not moving and no pending requests.
- **Moving Up**: Going up to serve requests.
- **Moving Down**: Going down to serve requests.
- **Loading/Unloading**: Picking up or dropping passengers at a floor.

### 3. Constraints and Objectives
- Minimize average wait time and travel time.
- Avoid starvation (requests that are never served).
- Reduce energy usage (fewer direction changes, smooth stops).
- Ensure fairness (don’t prioritize one direction/floor unfairly).

---

## 🛠 Elevator Scheduling Algorithms

### 1. First Come First Serve (FCFS)
**👓 Description:**
- Requests are handled strictly in the order they arrive.
- No optimization for distance, direction, or urgency.

**🚦 Behavior:**  
Let’s say an elevator receives multiple requests:
- It will complete the current request before starting the next one.
- Doesn’t consider if a future request is in the same direction or nearby.

**🧾 Request Scenarios:**
1. Idle Elevator → Picks up the next request immediately.
2. Same Direction (Matching Destination) → Picks up and continues smoothly.
3. Same Direction (Opposite Destination) → Picks up but later changes direction.
4. Moving Away → Request is queued and served after others.

**✅ Pros:**
- Simple and predictable behavior.
- Easy to implement.

**❌ Cons:**
- High average wait time in busy systems.
- Starvation possible for some requests.
- Ignores optimization opportunities.

---

### 2. Shortest Seek Time First (SSTF)
**👓 Description:**
- Always picks the request that is closest to the elevator’s current location.
- Focuses on minimizing travel distance.

**🔧 Implementation:**
- Maintain a **priority queue (min-heap)** sorted by distance from the current floor.
- Could be done using:
    - Array/List (inefficient for large datasets)
    - Heap-based PriorityQueue (efficient)

**🔁 Example:**  
If the elevator is at floor `5`, and requests are at `2, 4, 6, 9` → it goes to `4` (nearest), then `6`, then `2 or 9`.

**✅ Pros:**
- Reduces average travel time.
- Better utilization of the elevator.

**❌ Cons:**
- Can cause starvation for distant requests.
- Requires real-time recalculation after each pickup/drop.

---

### 3. SCAN (Elevator Algorithm)
**👓 Description:**
- Elevator moves in one direction, serving all requests in that direction.
- Once it reaches the farthest request, it reverses direction and repeats.
- Very similar to how a disk drive arm works.

**🔁 Example:**
- Starts at floor `3`, moves up to `10` serving all upward requests.
- Then moves down to `1` serving all downward requests.
- Doesn’t skip any floors in the path (even if empty).

**✅ Pros:**
- Fairness guaranteed (no starvation).
- Predictable and efficient for multi-floor high-traffic systems.

**❌ Cons:**
- May waste time traveling to farthest end, even if no one is waiting.
- Response time to new requests could be delayed.

---

### 4. LOOK (Look-Ahead SCAN Algorithm)
**👓 Description:**
- An optimization of SCAN.
- The elevator only travels as far as the last request in the current direction, instead of going to the extreme end.

**🔁 Example:**
- If going up and last request is at floor `7` (not `10`), it reverses direction at `7`.

**✅ Pros:**
- More efficient than SCAN.
- Avoids unnecessary travel.
- Retains fairness and direction-based grouping.

**❌ Cons:**
- Slightly more complex to implement than SCAN.

---

## 🧮 Summary Comparison

| Algorithm | Behavior                  | Optimization          | Starvation | Complexity | Best Use Case              |
|-----------|---------------------------|-----------------------|------------|------------|-----------------------------|
| **FCFS**  | Serve in request order    | None                  | High       | Low        | Simple, low-traffic         |
| **SSTF**  | Nearest request first     | Distance-based        | Medium     | Medium     | Moderate traffic            |
| **SCAN**  | Serve all in one direction| Direction-based       | Low        | Medium     | High-rise buildings         |
| **LOOK**  | Like SCAN but stop early  | Direction + Efficiency| Low        | Medium     | Optimized scheduling        |

---

## 🔄 Hybrid Systems
Many real-world elevator systems use a combination of these algorithms based on:
- Traffic patterns (up-peak, down-peak, lunch hours)
- Elevator load
- Request type (VIP/priority service)
- Emergency handling

---

## ✨ Key Takeaways
- Trade-off between simplicity and optimization.
- No one-size-fits-all: design depends on building layout, traffic, and user needs.
- Prioritize fairness, efficiency, and responsiveness in real-time environments.  
