package week7;

public class Coupon {

    private String code;
    private double discountRate;  
    private double minSubtotal;

    public Coupon(String code, double discountRate, double minSubtotal) {
        this.code = code;
        if (discountRate > 50) {
            this.discountRate = 50;
        } else {
            this.discountRate = discountRate;
        }
        this.minSubtotal = minSubtotal;
    }

    public String getCode() {
        return this.code;
    }

    public double getDiscountRate() {
        return this.discountRate;
    }

    public double getMinSubtotal() {
        return this.minSubtotal;
    }

    public boolean isApplicable(double subtotal) {
        return subtotal >= this.minSubtotal;
    }
}