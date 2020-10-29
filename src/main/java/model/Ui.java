package model;

import DB_Connector.mySQLDao;


import java.util.Scanner;

public class Ui {

    mySQLDao mySQLDao = new mySQLDao();

    private void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("please enter the contact details\nName:");
        String name = scanner.nextLine();
        System.out.print("Surname:");
        String surname = scanner.nextLine();
        System.out.print("Age:");
        Integer age = scanner.nextInt();

        Contact contact = new Contact(name, surname, age);
        System.out.println("\nConfirm details »»--------►\n" + contact);
        System.out.println("\n- 1: [ Confirm Contact details]\n- 2: [Cancel and return to menu]");
        Scanner scanner1 = new Scanner(System.in);
        switch (scanner1.nextLine()) {
            case "1":
                mySQLDao.addNew(contact);
                System.out.println(
                        "\n(¯`·._.· " + contact.getName().toUpperCase() + " " + contact.getSurname().toUpperCase() + " WAS ADDED ·._.·´¯)");
                break;
            case "2":
                showUi();
                break;
            default:
                System.exit(0);
        }
        showUi();
    }

    public void showUi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n- 1: [ Add Contact]\n- 2: [ Show All Contacts ]\n- 3: [ Find Contact by Id ]\n- 4: [ Delete Contact ]\n- 5: [ Edit Data ]\n- 0: [ Exit ]");
        switch (scanner.nextLine()) {
            case "1":
                addContact();
            case "2":
                showAllData();
                break;
            case "3":
                showById();
                break;
            case "4":
                deleteContact();
                break;
            case "5":
                editAllContact();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Wrong Input, you have left the program");
                System.exit(0);
        }
    }

    public void showById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the numerical contact Id\nID:");
        String id = scanner.nextLine();
        mySQLDao.readDataById(id);
        //Todo give options if to edit data or back to menu
        System.out.println("\n- 1: [ Edit Contact ]\n- 2: [ Back to Menu ]");
        switch (scanner.nextLine()){
            case "1":
                editData();
                break;
            case "2":
                showUi();
                break;
            default:
                System.exit(0);
        }
        showUi();
    }

    //ToDo: implement every data that can be updated separately
    public void editData(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("type below the information you want to change:" +
                "\n [ Name ] [ Surname ] [ Age ] [ id ]");
        var updateInput = scanner.nextLine();
        switch (scanner.nextLine()){
            case "Name":
                editName();
                break;
            case "Surname":
                editSurname();
                break;
            case "Age":
                editAge();
                break;
        }

    }

    private void editAge() {
    }

    private void editSurname() {
    }

    private void editName() {
    }

    public void editAllContact(){
        Scanner scanner = new Scanner(System.in);
        //Todo request Contact Id to edit
        System.out.println("Please Enter the existing Id to edit: ");
        int idToEdit = scanner.nextInt();
//        mySQLDao.readDataById(String.valueOf(idToEdit));
        System.out.print("Please enter the contact details:");
        System.out.print("\nUpdated ID:");
        int id = scanner.nextInt();
        System.out.print("Updated Name: ");
        String name = scanner.next();
        System.out.print("Updated Surname: ");
        String surname = scanner.next();
        System.out.print("Updated Age: ");
        int age = scanner.nextInt();
        Contact contact = new Contact(id, name, surname, age);
        System.out.println("\nConfirm details »»--------►\n" + contact);
        System.out.println("\n- 1: [ Confirm Contact details]\n- 2: [Cancel and return to menu]");
        Scanner scanner1 = new Scanner(System.in);
        switch (scanner1.nextLine()) {
            case "1":
                mySQLDao.updateData(contact, idToEdit);
                System.out.println(
                        "\n(¯`·._.· " + contact.getName().toUpperCase() + " " + contact.getSurname().toUpperCase() + " " + "WAS UPDATED ·._.·´¯)");
                break;
            case "2":
                showUi();
                break;
            default:
                System.exit(0);
        }
        showUi();

    }

    public void showAllData() {
        mySQLDao.readData();
        showUi();
    }

    public void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the numerical contact Id you want to delete\nID:");
        mySQLDao.deleteDataById(scanner);
        showUi();
    }
}
