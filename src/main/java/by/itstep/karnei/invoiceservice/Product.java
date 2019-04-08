package by.itstep.karnei.invoiceservice;

import java.util.Objects;

public class Product {
    private String nameOfProduct;
    private String unit;
    private double quantity;
    private double price;
    private double sum;

    public Product() {
    }

    public Product(String nameOfProduct, String unit, double quantity, double price) {
        this.nameOfProduct = nameOfProduct;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.sum = price * quantity;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                getNameOfProduct().equals(product.getNameOfProduct()) &&
                getUnit().equals(product.getUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameOfProduct(), getUnit(), getPrice());
    }

    @Override
    public String toString() {
        return "Наименование=" + nameOfProduct +
                ", Ед. изм.=" + unit +
                ", Количество=" + quantity +
                ", Цена=" + price +
                ", Сумма=" + sum + "\n";
    }

    public String toPrint() {
        return nameOfProduct;
    }
}
