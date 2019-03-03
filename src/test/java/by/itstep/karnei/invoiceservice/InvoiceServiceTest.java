package by.itstep.karnei.invoiceservice;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;


public class InvoiceServiceTest {

    @Test
    public void incomingInvoiceTest() {

        InvoiceService invoiceService = new InvoiceService();

        Calendar date = Calendar.getInstance();
        date.set(2018, Calendar.MARCH, 12);
        Provider provider = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3.4)));
        Invoice invoice = invoiceService.incomingInvoice(date, provider, Stock.STOCK_3_XXX, productList);

        Assert.assertEquals(3, invoice.getListProduct().size());
    }

    @Test
    public void outgoingInvoiceTest() {

        InvoiceService invoiceService = new InvoiceService();

        Calendar date = Calendar.getInstance();
        date.set(2018, Calendar.MARCH, 12);
        Provider provider = new Provider("Тория", "г. Гродно Кахановского 24");
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3)));
        Invoice invoice = invoiceService.incomingInvoice(date, provider, Stock.STOCK_1_XXX, productList);

        Calendar date1 = Calendar.getInstance();
        date1.set(2018, Calendar.FEBRUARY, 16);
        Provider provider1 = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList1 = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 5, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 10, 3)));
        Invoice invoice1 = invoiceService.outgoingInvoice(date1, provider1, Stock.STOCK_2_XXX, productList1);
        invoice1 = invoiceService.incomingInvoice(date1, provider1, Stock.STOCK_2_XXX, productList1);

        Assert.assertEquals(2, invoice.getListProduct().size());
    }

    @Test
    public void returnExternalProvidersTest() {

        InvoiceService invoiceService = new InvoiceService();

        Calendar date = Calendar.getInstance();
        date.set(2018, Calendar.MARCH, 12);
        Provider provider = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3.4)));
        Invoice invoice = invoiceService.incomingInvoice(date, provider, Stock.STOCK_3_XXX, productList);

        Calendar date1 = Calendar.getInstance();
        date1.set(2018, Calendar.FEBRUARY, 16);
        Provider provider1 = new Provider("Тория", "г. Гродно Кахановского 24");
        List<Product> productList1 = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штука", 5, 258.2),
                new Product("Подоконник", "метр", 50, 3.2),
                new Product("Отлив", "метр", 21, 4)));
        Invoice invoice1 = invoiceService.incomingInvoice(date1, provider1, Stock.STOCK_2_XXX, productList1);

        Calendar date2 = Calendar.getInstance();
        date2.set(2018, Calendar.JANUARY, 2);
        Provider provider2 = new Provider("Ксеф", "г. Гродно ул. Озерское шоссе ");
        List<Product> productList2 = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штука", 10, 2.5),
                new Product("Подоконник", "метр", 20, 3),
                new Product("Отлив", "метр", 18, 3.4)));
        Invoice invoice2 = invoiceService.incomingInvoice(date2, provider2, Stock.STOCK_1_XXX, productList2);

        Calendar date3 = Calendar.getInstance();
        date3.set(2018, Calendar.JANUARY, 22);
        Provider provider3 = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList3 = new ArrayList<>(Arrays.asList(
                new Product("Вода 0,5", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3.4)));
        Invoice invoice3 = invoiceService.incomingInvoice(date3, provider3, Stock.STOCK_2_XXX, productList3);

        Assert.assertEquals(2, invoiceService.returnExternalProviders().size());

    }

    @Test
    public void returnAllProductsOnStockTest() {

        InvoiceService invoiceService = new InvoiceService();
        Calendar date = Calendar.getInstance();
        date.set(2018, Calendar.MARCH, 12);
        Provider provider = new Provider("Тория", "г. Гродно Кахановского 24");
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3)));
        Invoice invoice = invoiceService.incomingInvoice(date, provider, Stock.STOCK_1_XXX, productList);

        Calendar date1 = Calendar.getInstance();
        date1.set(2018, Calendar.FEBRUARY, 16);
        Provider provider1 = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList1 = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 5, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 10, 3),
                new Product("Пепси 0,5л", "бутылка", 9, 3)));
        Invoice invoice1 = invoiceService.outgoingInvoice(date1, provider1, Stock.STOCK_2_XXX, productList1);
        invoice1 = invoiceService.incomingInvoice(date1, provider1, Stock.STOCK_2_XXX, productList1);

        Calendar date2 = Calendar.getInstance();
        date2.set(2018, Calendar.JANUARY, 2);
        Provider provider2 = new Provider("Ксеф", "г. Гродно ул. Озерское шоссе ");
        List<Product> productList2 = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штука", 10, 258.2),
                new Product("Подоконник", "метр", 20, 3.2),
                new Product("Отлив", "метр", 18, 4)));
        Invoice invoice2 = invoiceService.incomingInvoice(date2, provider2, Stock.STOCK_2_XXX, productList2);

        Calendar date3 = Calendar.getInstance();
        date3.set(2018, Calendar.JANUARY, 22);
        Provider provider3 = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList3 = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 5, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 10, 3),
                new Product("Пепси 0,5л", "бутылка", 9, 3)));
        Invoice invoice3 = invoiceService.outgoingInvoice(date3, provider3, Stock.STOCK_3_XXX, productList3);
        invoice3= invoiceService.incomingInvoice(date3, provider3, Stock.STOCK_3_XXX, productList3);

        System.out.println(invoiceService.returnAllProductsOnStock(Stock.STOCK_1_XXX));
        System.out.println(invoiceService.returnAllProductsOnStock(Stock.STOCK_2_XXX));
    }

    /*@Test
    public void removeProductsOnStockTest() {

        InvoiceService invoiceService = new InvoiceService();

        Calendar date = Calendar.getInstance();
        date.set(2018, Calendar.MARCH, 12);
        Provider provider = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3.4)));
        Invoice invoice = invoiceService.incomingInvoice(date, provider, Stock.STOCK_2_XXX, productList);

        Calendar date1 = Calendar.getInstance();
        date1.set(2018, Calendar.FEBRUARY, 16);
        Provider provider1 = new Provider("Тория", "г. Гродно Кахановского 24");
        List<Product> productList1 = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штука", 5, 258.2),
                new Product("Подоконник", "метр", 50, 3.2),
                new Product("Отлив", "метр", 21, 4)));
        Invoice invoice1 = invoiceService.incomingInvoice(date1, provider1, Stock.STOCK_2_XXX, productList1);

        Calendar date2 = Calendar.getInstance();
        date2.set(2018, Calendar.JANUARY, 2);
        Provider provider2 = new Provider("Ксеф", "г. Гродно ул. Озерское шоссе ");
        List<Product> productList2 = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штука", 10, 2.5),
                new Product("Подоконник", "метр", 20, 3),
                new Product("Отлив", "метр", 18, 3.4)));
        Invoice invoice2 = invoiceService.incomingInvoice(date2, provider2, Stock.STOCK_1_XXX, productList2);

        Calendar date3 = Calendar.getInstance();
        date3.set(2018, Calendar.JANUARY, 22);
        Provider provider3 = new Provider(Stock.STOCK_2_XXX);
        List<Product> productList3 = new ArrayList<>(Arrays.asList(
                new Product("Вода 0,5л", "бутылка", 5, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 10, 3),
                new Product("Пепси 0,5л", "бутылка", 9, 3.4)));
        Invoice invoice3 = invoiceService.incomingInvoice(date3, provider3, Stock.STOCK_1_XXX, productList3);

        System.out.println(invoiceService.returnAllProductsOnStock(Stock.STOCK_1_XXX));
    }*/
}
