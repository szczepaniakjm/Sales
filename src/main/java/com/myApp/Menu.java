package com.myApp;

import com.myApp.service.MyService;
import com.myApp.service.MyServiceImp;

public class Menu {
    private MyService service = new MyServiceImp();

    private void showMainMenu() {
        System.out.println("Select the table and then select the operation according to the following:\n"
                + "1 - Category\n"
                + "2 - Country\n"
                + "3 - Customer\n"
                + "4 - OrderTab\n"
                + "5 - Producer\n"
                + "6 - Product\n"
        );
        System.out.println("Choose option:");
        int option = MyScanner.getInt();
        switch (option) {
            case 1:
                categoryOperation(showBasicOperation());
                break;
            case 2:
                countryOperation(showBasicOperation());
                break;
            case 3:
                customerOperation(showBasicOperation());
                break;
            case 4:
                orderTabOperation(showBasicOperation());
                break;
            case 5:
                producerOperation(showBasicOperation());
                break;
            case 6:
                productOperation(showBasicOperation());
                break;
            default:
                    System.out.println("WRONG NUMBER");

        }
        repeat();
    }
    private int showBasicOperation() {
        System.out.println("Operation:\n"
                + "1 - Insert\n"
                + "2 - Update\n"
                + "3 - Delete\n"
                + "4 - Find by id\n"
                + "5 - Find all\n"
        );
        System.out.println("Choose operation");
        return MyScanner.getInt();
    }

    private void categoryOperation(int option) {
        switch (option) {
            case 1:
                service.addCategory(MyDataReader.getCategory());
                break;
            case 2:
                service.updateCategory(MyDataReader.getCategory());
                break;
            case 3:
                System.out.println("Give the id number: ");
                service.deleteCategory(MyScanner.getInt());
                break;
            case 4:
                System.out.println("Give the id number: ");
                service.findOneCategoryByID(MyScanner.getInt());
                break;
            case 5:
                service.findAllCategories();
                break;
            default:
                System.out.println("WRONG OPERATION NUMBER");
        }
    }
    private void countryOperation(int option) {
        switch (option) {
            case 1:
                service.addCountry(MyDataReader.getCountry());
                break;
            case 2:
                service.updateCountry(MyDataReader.getCountry());
                break;
            case 3:
                System.out.println("Give the id number: ");
                service.deleteCountry(MyScanner.getInt());
                break;
            case 4:
                System.out.println("Give the id number: ");
                service.findOneCountryByID(MyScanner.getInt());
                break;
            case 5:
                service.findAllCountries();
                break;
            default:
                System.out.println("WRONG OPERATION NUMBER");
        }
    }
    private void customerOperation(int option){
        switch (option){
            case 1:
                service.addCustomer(MyDataReader.getCustomer());
                break;

            case 2:
                service.updateCustomer(MyDataReader.getCustomer());
                break;
            case 3:
                System.out.println("Give the id number: ");
                service.deleteCustomer(MyScanner.getInt());
                break;
            case 4:
                System.out.println("Give the id number: ");
                service.findOneCustomerByID(MyScanner.getInt());
                break;
            case 5:
                service.findAllCustomers();
                break;
            default:
                System.out.println("WRONG OPERATION NUMBER");
        }

    }
    private void orderTabOperation(int option){
        switch (option){
            case 1:
                service.addOrderTab(MyDataReader.getOrderTab());
                break;

            case 2:
                service.updateOrderTab(MyDataReader.getOrderTab());
                break;
            case 3:
                System.out.println("Give the id number: ");
                service.deleteOrderTab(MyScanner.getInt());
                break;
            case 4:
                System.out.println("Give the id number: ");
                service.findOneOrderTabByID(MyScanner.getInt());
                break;
            case 5:
                service.findAllOrderTabs();
                break;
            default:
                System.out.println("WRONG OPERATION NUMBER");
        }

    }

    private void producerOperation(int option){
        switch (option){
            case 1:
                service.addProducer(MyDataReader.getProducer());
                break;

            case 2:
                service.updateProducer(MyDataReader.getProducer());
                break;
            case 3:
                System.out.println("Give the id number: ");
                service.deleteProducer(MyScanner.getInt());
                break;
            case 4:
                System.out.println("Give the id number: ");
                service.findOneProducerByID(MyScanner.getInt());
                break;
            case 5:
                service.findAllProducers();
                break;
            default:
                System.out.println("WRONG OPERATION NUMBER");
        }

    }

    private void productOperation(int option){
        switch (option){
            case 1:
                service.addProduct(MyDataReader.getProduct());
                break;

            case 2:
                service.updateProduct(MyDataReader.getProduct());
                break;
            case 3:
                System.out.println("Give the id number: ");
                service.deleteProduct(MyScanner.getInt());
                break;
            case 4:
                System.out.println("Give the id number: ");
                service.findOneProductByID(MyScanner.getInt());
                break;
            case 5:
                service.findAllProducts();
                break;
            default:
                System.out.println("WRONG OPERATION NUMBER");
        }

    }

    public void repeat(){
        System.out.println("Press 1 to continue or any other key to finish");
        if (MyScanner.getString().compareTo("1") == 0){
            showMainMenu();
        }
    }

    public void mainMenu() {
        showMainMenu();
    }
}
