package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products;

    private BigDecimal subTotal;

    private  BigDecimal tax;

    private  BigDecimal total;

    public Invoice() {

    }

    public void addProduct(Product product) {
        // TODO: implement
    }

    public void addProduct(Product product, Integer quantity) {
        // TODO: implement
    }

    public BigDecimal getSubtotal() {
        return subTotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return subTotal.add(subTotal.multiply(tax));
    }
}
