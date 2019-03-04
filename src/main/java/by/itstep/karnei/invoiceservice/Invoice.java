package by.itstep.karnei.invoiceservice;

import java.util.Calendar;
import java.util.List;

public class Invoice {
    private Calendar date;
    private Provider provider;
    private Stock stock;
    private List<Product> listProduct;

    public Invoice() {
    }

    public Invoice(Calendar date, Provider provider, Stock stock, List <Product> listProduct) {
        this.date = date;
        this.provider = provider;
        this.stock = stock;
        this.listProduct = listProduct;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
}
