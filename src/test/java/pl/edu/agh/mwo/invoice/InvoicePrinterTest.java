package pl.edu.agh.mwo.invoice;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.mwo.invoice.product.DairyProduct;
import pl.edu.agh.mwo.invoice.product.OtherProduct;
import pl.edu.agh.mwo.invoice.product.TaxFreeProduct;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class InvoicePrinterTest {
    private Invoice invoice;
    private InvoicePrinter invoicePrinter;

    @Before
    public void createEmptyInvoiceForTheTest() {

        invoice = new Invoice();
        invoice.addProduct(new TaxFreeProduct("Mąka", new BigDecimal("5")), 1);
        invoice.addProduct(new DairyProduct("Ser", new BigDecimal("10")), 5);
        invoice.addProduct(new OtherProduct("Szampon", new BigDecimal("45")), 2);
        invoicePrinter = new InvoicePrinter(invoice);
    }

    @Test
    public void testInvoicePrintHeader(){
        String invoiceHeader = invoicePrinter.print().split(System.lineSeparator())[0];
        Assert.assertThat("Numer faktury: " + invoice.getNumber(), Matchers.comparesEqualTo(invoiceHeader));
    }

    @Test
    public void testInvoicePrintFooter(){
        String[] lines = invoicePrinter.print().split(System.lineSeparator());
        String footer = lines[lines.length - 1];
        Assert.assertThat("Liczba pozycji: " + invoice.countProducts(), Matchers.comparesEqualTo(footer));
    }

    @Test
    public void testInvoicePrintLength() {
        String[] lines = invoicePrinter.print().split(System.lineSeparator());
        HashSet<String> productNames = new HashSet<>();
        invoice.getProducts().keySet().forEach(product -> productNames.add(product.getName()));
        // unique products + header + footer
        Assert.assertThat(productNames.size() + 2, Matchers.comparesEqualTo(lines.length));
    }

    @Test
    public void testPrintMultipleProducts(){
        String expectedPrint = "Numer faktury: " + invoice.getNumber() + System.lineSeparator() +
                "Produkt: Mąka Ilosc: 1 Cena jdn.: 5 PLN Cena calkowita: 5 PLN" + System.lineSeparator() +
                "Produkt: Ser Ilosc: 5 Cena jdn.: 10.80 PLN Cena calkowita: 54.00 PLN" + System.lineSeparator() +
                "Produkt: Szampon Ilosc: 2 Cena jdn.: 55.35 PLN Cena calkowita: 110.70 PLN" + System.lineSeparator() +
                "Liczba pozycji: 3";

        assertEquals(expectedPrint, invoicePrinter.print());
    }
}
