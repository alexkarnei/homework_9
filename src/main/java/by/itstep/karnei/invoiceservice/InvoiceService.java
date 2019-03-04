package by.itstep.karnei.invoiceservice;

import by.itstep.karnei.invoiceservice.exception.SupplierAndRecipientOneSTock;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InvoiceService implements InvoiceInterface {

    private Set<Provider> providerSet = new HashSet<>();
    private Set<Product> productSet = new HashSet<>();
    private Set<Stock> stockSet = new HashSet<>(Arrays.asList(Stock.values()));
    private Map<Stock, Set<Product>> productMap = new ConcurrentHashMap<>();

    public Invoice workWithInvoice(Calendar date, Provider provider, Stock stock, List<Product> productList) throws SupplierAndRecipientOneSTock {
      if (provider.getStock()==(stock)) {
            throw new SupplierAndRecipientOneSTock();
        } else {
            externalProviders(provider);
            if (stockSet.contains(provider.getStock())) {
                removeProductAtStock(provider, productList);
            }
            addProductAtStock(stock, productList);
        }
        return new Invoice(date, provider, stock, productList);
    }

    private void removeProductAtStock(Provider provider, List<Product> productList) {
        if (stockSet.contains(provider.getStock()) && productMap.get(provider.getStock()) != null) {
            Set<Product> products = productMap.get(provider.getStock());
            Set<Product> notNullProduct = new HashSet<>();
            Set<Product> missingElementSet = new HashSet<>();
            for (Product product : products) {
                for (Product product1 : productList) {
                    if (product.equals(product1) && (product.getQuantity() >= product1.getQuantity())) {
                        product.setQuantity(product.getQuantity() - product1.getQuantity());
                        product.setSum(product.getQuantity() * product.getPrice());
                        if (product.getQuantity() != 0) {
                            notNullProduct.add(product);
                        } else productSet.add(product);
                    }
                }
            }
            productMap.put(provider.getStock(), notNullProduct);
        }
        productSet.clear();
    }

    private void addProductAtStock(Stock stock, List<Product> productList) {
        if (productMap.get(stock) != null) {
            for (Product product : productList) {
                Set<Product> productSet = new HashSet<>(productMap.get(stock));
                for (Product product1 : productSet) {
                    if (product.equals(product1)) {
                        product1.setQuantity(product1.getQuantity() + product.getQuantity());
                        product1.setSum(product1.getQuantity() * product1.getPrice());
                        productMap.get(stock).add(product1);
                    }
                    productMap.get(stock).add(product);
                }
            }
            productMap.put(stock, productMap.get(stock));
        } else {
            productMap.put(stock, new HashSet<>(productList));
        }
    }

    private Set<Provider> externalProviders(Provider provider) {
        if (!stockSet.contains(provider.getStock())) {
            providerSet.add(provider);
        }
        return providerSet;
    }

    public Set<Provider> returnExternalProviders() {
        return providerSet;
    }


    public Set<Product> returnAllProductsOnStock(Stock stock) {
        return productMap.get(stock);
    }


    public String printInvoice(Invoice invoice) {
        SimpleDateFormat format1 = new SimpleDateFormat(" dd  MMMM yyyy", Locale.getDefault());
        String formatted = format1.format(invoice.getDate().getTime());
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t Накладная").append("\n").
                append(formatted).append("\n").
                append("От кого  : ").append(invoice.getProvider().toString()).append("\n").
                append("Кому  :  ").append(invoice.getStock().toString()).append("\n").
                append("Основание : ").append("Договор").append("\n").
                append(invoice.getListProduct().toString());
        return sb.toString();
    }
}
