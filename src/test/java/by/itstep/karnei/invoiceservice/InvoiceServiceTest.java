package by.itstep.karnei.invoiceservice;

import by.itstep.karnei.invoiceservice.exception.SupplierAndRecipientOneSTock;
import by.itstep.karnei.invoiceservice.exception.ThisProductIsNotInStockException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.*;


public class InvoiceServiceTest {

//    @Data Provider
    @Test
    public void workWithInvoiceTest() throws SupplierAndRecipientOneSTock {

        InvoiceService invoiceService = new InvoiceService();

        Calendar date = getInstance();
        date.set(2018, MARCH, 12);
        Provider provider = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3.4)));
        Invoice invoice = invoiceService.workWithInvoice(date, provider, Stock.STOCK_2_XXX, productList);

        Assert.assertEquals(3, invoice.getListProduct().size());
    }

    @Test
    public void returnExternalProvidersPositiveTest() throws SupplierAndRecipientOneSTock {

        InvoiceService invoiceService = new InvoiceService();

        Calendar date = getInstance();
        date.set(2018, MARCH, 12);
        Provider provider = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3.4)));
        Invoice invoice = invoiceService.workWithInvoice(date, provider, Stock.STOCK_3_XXX, productList);

        Calendar date1 = getInstance();
        date1.set(2018, FEBRUARY, 16);
        Provider provider1 = new Provider("Тория", "г. Гродно Кахановского 24");
        List<Product> productList1 = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штука", 5, 258.2),
                new Product("Подоконник", "метр", 50, 3.2),
                new Product("Отлив", "метр", 21, 4)));
        Invoice invoice1 = invoiceService.workWithInvoice(date1, provider1, Stock.STOCK_2_XXX, productList1);

        Calendar date2 = getInstance();
        date2.set(2018, JANUARY, 2);
        Provider provider2 = new Provider("Ксеф", "г. Гродно ул. Озерское шоссе ");
        List<Product> productList2 = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штука", 10, 2.5),
                new Product("Подоконник", "метр", 20, 3),
                new Product("Отлив", "метр", 18, 3.4)));
        Invoice invoice2 = invoiceService.workWithInvoice(date2, provider2, Stock.STOCK_1_XXX, productList2);

        Calendar date3 = getInstance();
        date3.set(2018, JANUARY, 22);
        Provider provider3 = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList3 = new ArrayList<>(Arrays.asList(
                new Product("Вода 0,5", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3.4)));
        Invoice invoice3 = invoiceService.workWithInvoice(date3, provider3, Stock.STOCK_2_XXX, productList3);

        Assert.assertEquals(2, invoiceService.returnExternalProviders().size());
        System.out.println(invoiceService.returnExternalProviders());

    }

    @Test(expected = SupplierAndRecipientOneSTock.class)
    public void returnExternalProvidersNegativeTest() throws SupplierAndRecipientOneSTock {

        InvoiceService invoiceService = new InvoiceService();

        Calendar date = getInstance();
        date.set(2018, MARCH, 12);
        Provider provider = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3.4)));
        invoiceService.workWithInvoice(date, provider, Stock.STOCK_1_XXX, productList);

        Calendar date1 = getInstance();
        date1.set(2018, FEBRUARY, 16);
        Provider provider1 = new Provider("Тория", "г. Гродно Кахановского 24");
        List<Product> productList1 = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штука", 5, 258.2),
                new Product("Подоконник", "метр", 50, 3.2),
                new Product("Отлив", "метр", 21, 4)));
        invoiceService.workWithInvoice(date1, provider1, Stock.STOCK_2_XXX, productList1);

        Calendar date2 = getInstance();
        date2.set(2018, JANUARY, 2);
        Provider provider2 = new Provider("Ксеф", "г. Гродно ул. Озерское шоссе ");
        List<Product> productList2 = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штука", 10, 2.5),
                new Product("Подоконник", "метр", 20, 3),
                new Product("Отлив", "метр", 18, 3.4)));
        invoiceService.workWithInvoice(date2, provider2, Stock.STOCK_1_XXX, productList2);

        Calendar date3 = getInstance();
        date3.set(2018, JANUARY, 22);
        Provider provider3 = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList3 = new ArrayList<>(Arrays.asList(
                new Product("Вода 0,5", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3.4)));
        invoiceService.workWithInvoice(date3, provider3, Stock.STOCK_2_XXX, productList3);

        System.out.println(invoiceService.returnExternalProviders());

    }

    @Test
    public void returnAllProductsOnStockTest() throws SupplierAndRecipientOneSTock {

        InvoiceService invoiceService = new InvoiceService();
        Calendar date = getInstance();
        date.set(2018, MARCH, 12);
        Provider provider = new Provider("Тория", "г. Гродно Кахановского 24");
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3)));
        invoiceService.workWithInvoice(date, provider, Stock.STOCK_1_XXX, productList);

        Calendar date1 = getInstance();
        date1.set(2018, FEBRUARY, 16);
        Provider provider1 = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList1 = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 5, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 10, 3),
                new Product("Пепси 0,5л", "бутылка", 9, 3)));
        invoiceService.workWithInvoice(date1, provider1, Stock.STOCK_2_XXX, productList1);

        Calendar date2 = getInstance();
        date2.set(2018, JANUARY, 2);
        Provider provider2 = new Provider("Ксеф", "г. Гродно ул. Озерское шоссе 22");
        List<Product> productList2 = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штука", 10, 258.2),
                new Product("Подоконник", "метр", 20, 3.2),
                new Product("Отлив", "метр", 18, 4)));
        invoiceService.workWithInvoice(date2, provider2, Stock.STOCK_2_XXX, productList2);

        Calendar date3 = getInstance();
        date3.set(2018, JANUARY, 22);
        Provider provider3 = new Provider(Stock.STOCK_1_XXX);
        List<Product> productList3 = new ArrayList<>(Arrays.asList(
                new Product("Вода 1л", "бутылка", 5, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 10, 3),
                new Product("Пепси 0,5л", "бутылка", 9, 3)));
       invoiceService.workWithInvoice(date3, provider3, Stock.STOCK_3_XXX, productList3);

        Assert.assertEquals(0,invoiceService.returnAllProductsOnStock(Stock.STOCK_1_XXX).size());
        Assert.assertEquals(6,invoiceService.returnAllProductsOnStock(Stock.STOCK_2_XXX).size());

        System.out.println(invoiceService.returnAllProductsOnStock(Stock.STOCK_1_XXX));
        System.out.println(invoiceService.returnAllProductsOnStock(Stock.STOCK_2_XXX));
    }

    @Test
    public void setForGoodInStockPositiveTest() throws SupplierAndRecipientOneSTock, ThisProductIsNotInStockException {

        InvoiceService invoiceService = new InvoiceService();

        Calendar date = Calendar.getInstance();
        date.set(2018, Calendar.MARCH, 12);
        Provider provider = new Provider("Ксеф", "г. Гродно ул. Озерское шоссе 22");
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штук", 10, 258.2),
                new Product("Подоконник", "метр", 20, 3.2),
                new Product("Отлив", "метр", 18, 4)));
        invoiceService.workWithInvoice(date, provider, Stock.STOCK_1_XXX, productList);

        Calendar date1 = Calendar.getInstance();
        date1.set(2018, Calendar.FEBRUARY, 16);
        Provider provider1 = new Provider("Тория", "г. Гродно Кахановского 24");
        List<Product> productList1 = new ArrayList<>(Arrays.asList(
                new Product("Окно", "штук", 5, 258.2),
                new Product("Подоконник", "метр", 20, 3.2),
                new Product("Отлив", "метр", 18, 4)));
       invoiceService.workWithInvoice(date1, provider1, Stock.STOCK_2_XXX, productList1);

        System.out.println(invoiceService.setForGoodInStock("Окно"));
    }

    @Test(expected = ThisProductIsNotInStockException.class)
    public void setForGoodInStockNegativeTest() throws SupplierAndRecipientOneSTock, ThisProductIsNotInStockException {

        InvoiceService invoiceService = new InvoiceService();

        Calendar date = Calendar.getInstance();
        date.set(2018, Calendar.MARCH, 12);
        Provider provider = new Provider("Ксеф", "г. Гродно ул. Озерское шоссе 22");
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Вода", "бутылка", 10, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 20, 3),
                new Product("Пепси 0,5л", "бутылка", 18, 3.4)));
        Invoice invoice = invoiceService.workWithInvoice(date, provider, Stock.STOCK_1_XXX, productList);

        Calendar date1 = Calendar.getInstance();
        date1.set(2018, Calendar.FEBRUARY, 16);
        Provider provider1 = new Provider("Тория", "г. Гродно Кахановского 24");
        List<Product> productList1 = new ArrayList<>(Arrays.asList(
                new Product("Вода", "бутылка", 5, 2.5),
                new Product("Кока-кола 0,5л", "бутылка", 3, 3),
                new Product("Пепси 0,5л", "бутылка", 4, 3.4)));
        Invoice invoice1 = invoiceService.workWithInvoice(date1, provider1, Stock.STOCK_2_XXX, productList1);

        System.out.println(invoiceService.setForGoodInStock("Окно"));
    }
}
