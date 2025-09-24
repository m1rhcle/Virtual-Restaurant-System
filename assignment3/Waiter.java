package assignment3;

public class Waiter extends Thread {
    private final Restaurant restaurant;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String order;
                synchronized (restaurant) {
                    if (restaurant.orderItem.isEmpty()) {
                        restaurant.wait(); // waits for new orders
                    }

                    if (!restaurant.orderItem.isEmpty()) {
                        order = restaurant.orderItem.remove(0);
                    } else {
                        continue;
                    }
                }

                int table = (int) (Math.random() * 10 + 1); // sets a random table number
                Chef chef = new Chef(restaurant, order, table);
                chef.start();

                synchronized (restaurant) {
                    restaurant.wait(); // waits for chef to finish preparing the food
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
