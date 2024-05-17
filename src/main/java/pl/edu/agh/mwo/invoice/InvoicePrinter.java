package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;

public class InvoicePrinter {
    public Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public String header() {
        return "Numer faktury: " + invoice.getNumber() + System.lineSeparator();
    }

    private String footer() {
        return "Liczba pozycji: " + invoice.countProducts();
    }

    public String print() {
        HashSet<String> printedProducts = new HashSet<>();

        String invoicePrint = header();
        Map<Product, Integer> products = invoice.getProducts();

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            String name = product.getName();

            if (printedProducts.contains(name)) {
                continue;
            }

            BigDecimal taxPrice = product.getPriceWithTax();
            BigDecimal totalPrice = product.getPriceWithTax().multiply(new BigDecimal(quantity));

            invoicePrint += "Produkt: " + name
                    + " Ilosc: " + quantity
                    + " Cena jdn.: " + taxPrice
                    + " PLN Cena calkowita: " + totalPrice + " PLN"
                    + System.lineSeparator();
            printedProducts.add(name);
        }
        invoicePrint += footer();
        return invoicePrint;
    }
}
