package by.itstep.karnei.invoiceservice;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public interface InvoiceInterface {

    String printInvoice(Invoice invoice);

    Invoice incomingInvoice(Calendar date, Provider provider, Stock stock, List<Product> productListe);

    Invoice outgoingInvoice(Calendar date, Provider provider, Stock stock, List<Product> productList);

    Set<Provider> returnExternalProviders();

    String returnAllProductsOnStock(Stock stock);


}
