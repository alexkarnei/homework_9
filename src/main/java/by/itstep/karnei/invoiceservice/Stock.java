package by.itstep.karnei.invoiceservice;

public enum Stock {
    STOCK_1_XXX("Склад №1 ХХХ","г.Минск, ул. Урицкого д.125 "),
    STOCK_2_XXX("Склад №2 XXX","г.Гродно, ул. Кленовая 12A"),
    STOCK_3_XXX("Склад №3 XXX","г.Брест, ул. Куприянова 1B");

    private final String adress;
    private final String name;

    Stock(String name ,String adress) {
        this.adress = adress;
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name +","+adress;
    }
}
