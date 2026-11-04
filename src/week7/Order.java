package week7;

import java.util.ArrayList;

public class Order {

    private int orderNumber;
    private Customer customer;
    private ArrayList<MenuItem> items;
    private Coupon coupon;
    private boolean completed;

    static int nextOrderNumber = 1001;
    static final double MIN_ORDER = 100;
    static final double DELIVERY_FEE = 20;
    private static ArrayList<Order> completedOrders = new ArrayList<>();
    private static double totalRevenue = 0;

    public Order(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
        this.orderNumber = nextOrderNumber;
        nextOrderNumber++;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    private MenuItem findItem(String name) {
        for (MenuItem item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void addItem(MenuItem item) throws InvalidQuantityException {
        MenuItem existing = findItem(item.getName());
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
        } else {
            items.add(item);
        }
    }

    public void removeItem(String name) throws ItemNotFoundException {
        MenuItem existing = findItem(name);
        if (existing == null) {
            throw new ItemNotFoundException("Item not found: " + name);
        }
        items.remove(existing);
    }

    public void updateQuantity(String name, int quantity) throws ItemNotFoundException, InvalidQuantityException {
        MenuItem existing = findItem(name);
        if (existing == null) {
            throw new ItemNotFoundException("Item not found: " + name);
        }
        existing.setQuantity(quantity);
    }

    public double getSubtotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

     
    public ArrayList<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public void applyCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public double checkout() throws MinimumOrderException {
        if (completed) {
            throw new IllegalStateException("Order " + orderNumber + " is already completed");
        }

        double subtotal = getSubtotal();
        if (subtotal < MIN_ORDER) {
            throw new MinimumOrderException("Subtotal " + subtotal + " TL is below the minimum order of " + MIN_ORDER + " TL");
        }

        double discount = 0;
        if (coupon != null && coupon.isApplicable(subtotal)) {
            discount = subtotal * coupon.getDiscountRate() / 100;
        }
        double total = subtotal - discount + DELIVERY_FEE;

        System.out.println("----- RECEIPT -----");
        System.out.println("Order Number: " + orderNumber);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Address: " + customer.getAddress());
        for (MenuItem item : items) {
            System.out.println(item);
        }
        System.out.println("Subtotal: " + subtotal + " TL");
        System.out.println("Coupon Discount: " + discount + " TL");
        System.out.println("Delivery Fee: " + DELIVERY_FEE + " TL");
        System.out.println("Total: " + total + " TL");
        System.out.println("-------------------");

        completed = true;
        completedOrders.add(this);
        totalRevenue += total;
        return total;
    }

    public static ArrayList<Order> getCompletedOrders() {
        return new ArrayList<>(completedOrders);
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }
}