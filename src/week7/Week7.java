 
package week7;
 
public class Week7 {
 
    public static void main(String[] args) {
        Customer c1 = new Customer("Ali Yilmaz", "5551112233", "Cumhuriyet Cad. No:5", "Melikgazi", "Kayseri");
        Customer c2 = new Customer("Ayse Demir", "5554445566", "Istasyon Sok. No:12", "Talas", "Kayseri");
 
        Order o1 = new Order(c1);
        Order o2 = new Order(c2);
        System.out.println("Order 1 number: " + o1.getOrderNumber());
        System.out.println("Order 2 number: " + o2.getOrderNumber());
 
         
        try {
            o1.addItem(new MenuItem("Pizza", 150, 1));
            o1.addItem(new MenuItem("Pizza", 150, 1));
            o1.addItem(new MenuItem("Ayran", 20, 2));
            o1.addItem(new MenuItem("Baklava", 60, 1));
            o1.updateQuantity("Ayran", 3);
            o1.removeItem("Baklava");
            o1.applyCoupon(new Coupon("INDIRIM10", 10, 100));
            o1.checkout();
        } catch (ItemNotFoundException | InvalidQuantityException | MinimumOrderException e) {
            System.out.println("Error: " + e.getMessage());
        }
 
         
        try {
            o2.addItem(new MenuItem("Lahmacun", 70, 3));
            o2.applyCoupon(new Coupon("INDIRIM20", 20, 500));
            o2.checkout();
        } catch (InvalidQuantityException | MinimumOrderException e) {
            System.out.println("Error: " + e.getMessage());
        }
 
         
        try {
            o1.removeItem("Hamburger");
        } catch (ItemNotFoundException e) {
            System.out.println("Caught ItemNotFoundException: " + e.getMessage());
        }
 
        
        try {
            o1.addItem(new MenuItem("Kola", 25, 0));
        } catch (InvalidQuantityException e) {
            System.out.println("Caught InvalidQuantityException: " + e.getMessage());
        }
 
         
        try {
            Order o3 = new Order(c1);
            o3.addItem(new MenuItem("Su", 10, 2));
            o3.checkout();
        } catch (InvalidQuantityException | MinimumOrderException e) {
            System.out.println("Caught MinimumOrderException: " + e.getMessage());
        }
 
        System.out.println("Completed orders: " + Order.getCompletedOrders().size());
        System.out.println("Total revenue: " + Order.getTotalRevenue() + " TL");
         
    }
    
}
