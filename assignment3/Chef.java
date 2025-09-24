package assignment3;




/* this class represents a chef thread 
 * that waits a specified amount of time before completing an order 
 * 
 * */
public class Chef extends Thread {
	private final Restaurant restaurant;
    private final String order;
    private final int table;

    public Chef(Restaurant restaurant, String order, int table) {
        this.restaurant = restaurant;
        this.order = order;
        this.table = table;
    } 
    
    

    @Override
    public void run() {
        try {
            System.out.println("Chef is preparing: " + order);
            Thread.sleep(5000); // preparation time
            synchronized (restaurant) {
                restaurant.notify(); // notifies the waiter that the food is ready
                restaurant.foodPrepared(order, table); // updates GUI
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
