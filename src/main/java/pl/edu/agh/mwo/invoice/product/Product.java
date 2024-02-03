package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        this.name = name;
        this.price = price;
        this.taxPercent = tax;
        if (name == null) throw new IllegalArgumentException("Product name cannot be null");
        if (name.equals("")) throw new IllegalArgumentException("Product name cannot be empty");
        if (price.equals(null)) throw new IllegalArgumentException("Product price cannot be null");
        if (price.signum() == -1) throw new IllegalArgumentException("Product price cannot be negative");
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        return price.add(price.multiply(taxPercent));
    }
}
