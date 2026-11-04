package week7;

public class MenuItem {

    private String name;
    private double price;
    private int quantity;

    public MenuItem(String name, double price, int quantity) throws InvalidQuantityException {
        this.name = name;
        setPrice(price);
        setQuantity(quantity);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be positive");
        }
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) throws InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than 0");
        }
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return this.price * this.quantity;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", "
                + "Price: " + this.price + ", "
                + "Quantity: " + this.quantity;
    }
}