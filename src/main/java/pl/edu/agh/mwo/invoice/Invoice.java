package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products;

    private BigDecimal netPrice;

    private BigDecimal tax;

    private BigDecimal grossPrice;

    public Invoice() {
        this.netPrice = new BigDecimal(0);
        this.tax = new BigDecimal(0);
        this.grossPrice = new BigDecimal(0);
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
        BigDecimal newNetPrice = getNetPrice().add(product.getPrice());
        setNetPrice(newNetPrice);

        BigDecimal newTax = getTax().add(product.getTaxPercent().multiply(product.getPrice()));
        setTax(newTax);

        BigDecimal newGrossPrice = getGrossPrice().add(product.getPriceWithTax());
        setGrossPrice(newGrossPrice);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Product quantity cannot be null or quantity must be greater than 0");
        }
        for (int i = 0; i < quantity; i++) {
            addProduct((product));
        }
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public BigDecimal getTax() {
//        return BigDecimal.ZERO;
        return tax;
    }

    public BigDecimal getGrossPrice() {
        return grossPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public void setGrossPrice(BigDecimal grossPrice) {
        this.grossPrice = grossPrice;
    }
}
