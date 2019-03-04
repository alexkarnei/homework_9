package by.itstep.karnei.invoiceservice;

import by.itstep.karnei.invoiceservice.exception.SupplierAndRecipientOneSTock;
import by.itstep.karnei.invoiceservice.exception.ThisProductIsNotInStockException;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface InvoiceInterface {

    String printInvoice(Invoice invoice);

    Invoice workWithInvoice(Calendar date, Provider provider, Stock stock, List<Product> productList) throws SupplierAndRecipientOneSTock;

    Set<Provider> returnExternalProviders();

    Set<Product> returnAllProductsOnStock(Stock stock);

  String/*Map<String, Map<Stock, Map<Double, String>>>*/ setForGoodInStock(String nameOfProduct) throws ThisProductIsNotInStockException;


}
