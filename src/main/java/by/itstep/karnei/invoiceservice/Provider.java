package by.itstep.karnei.invoiceservice;

import java.util.Arrays;

public class Provider {
    private String nameProvider;
    private String legalAdress;
    private Stock stock;

    public Provider() {
    }

    public Provider(String nameProvider, String legalAdress) {
        this.nameProvider = nameProvider;
        this.legalAdress = legalAdress;
    }

    public Provider(Stock stock) {
        this.nameProvider = stock.getName();
        this.legalAdress = stock.getAdress();
        this.stock = stock;
    }

    public String getNameProvider() {
        return nameProvider;
    }

    public void setNameProvider(String nameProvider) {
        this.nameProvider = nameProvider;
    }

    public String getLegalAdress() {
        return legalAdress;
    }

    public void setLegalAdress(String legalAdress) {
        this.legalAdress = legalAdress;
    }

    public Stock getStock() {
        return stock;
    }

    @Override
    public String toString() {

        return nameProvider + "," + legalAdress;
    }
}
