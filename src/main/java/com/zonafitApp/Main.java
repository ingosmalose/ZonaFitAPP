package com.zonafitApp;

import com.zonafitApp.data.CustomerDAO;
import com.zonafitApp.data.IClienteDAO;
import com.zonafitApp.domain.Customer;

import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu();
    }

    public static void Menu(){
        var exit = false;
        var read = new Scanner(System.in);
        while (!exit){
            try{
                var option = showMenu(read);
                exit=executeOption(option,read);
            }
            catch (Exception e){
                System.out.println("* ERROR :> "+e.getMessage());
            }

        }
    }

    private static int showMenu(Scanner in){
        System.out.print("""
                .:: Zona fit APP | Menu ::
                [1] List customer.
                [2] Create customer
                [3] Update customer
                [4] Delete customer
                [5] Exit.
                >\t""");
        return Integer.parseInt(in.nextLine());
    }
    //  Execute option selected
    private static boolean executeOption(int option, Scanner read){
        var exit = false;
        IClienteDAO customer = new CustomerDAO();
        switch (option){
            case 1 ->
                    listCustomer( customer);
            case 2 ->
                    addCustomer(read, customer);
            case 3 ->
                    updateCustomer(read, customer);
            case 4 ->
                    deleteCustomer(read, customer);
            case 5->
                exit = exit();
        }
        return exit;
    }

    //  [1] Listing customer
    private static boolean listCustomer( IClienteDAO customer){
        System.out.println("Listing customer");
        var info = customer.listCustomer();
        info.forEach(System.out::println);
        return false;
    }

    //  [2] ADD customer
    private static void addCustomer(Scanner read, IClienteDAO customer){
        System.out.println(": Creating new customer :");
        var firstName = "";
        var lastName = "";
        var member = 0;
        System.out.print("First name : ");
        firstName = read.nextLine();
        System.out.print("Last name : ");
        lastName = read.nextLine();
        System.out.print("member : ");
        member = read.nextInt();

        var info = new Customer(firstName,lastName,member);

        if(customer.addCustomer(info)){
            System.out.println("Customer created successfully");
        }
        else{
            System.out.println("Verify information typed in, something is wrong.");
        }

    }

    //  [3] UPDATE customer
    private static void updateCustomer(Scanner read, IClienteDAO customer){
        System.out.println("Update customer");
        var firstName = "";
        var lastName = "";
        var member = 0;
        var id = 0;
        System.out.print("ID Customer : ");
        id = read.nextInt();
        System.out.print("First name : ");
        firstName = read.nextLine();
        System.out.print("Last name : ");
        lastName = read.nextLine();
        System.out.print("member : ");
        member = read.nextInt();
        var info = new Customer(id, firstName, lastName, member);
        if(customer.updateCustomer(info)){
            System.out.println("Customer updated successfully");
        }
        else{
            System.out.println("Verify if ID Customer is into our record.");
        }
    }

    //  [4] DELETE customer
    private static void deleteCustomer(Scanner read, IClienteDAO customer){
        System.out.println("DELETE customer");
        var id = 0;
        System.out.print("ID Customer : ");
        id = read.nextInt();
        var info = new Customer(id);
        if(customer.deleteCustomer(info)){
            System.out.println("Customer deleted successfully");
        }
        else{
            System.out.println("ID Customer typed in is not exist in our record. Please try a different ID Customer.");
        }
    }
    //  [5] Exit : Stop application
    private static boolean exit(){
        System.out.println("Have a wonderful day, back soon.");
        return true;
    }
}