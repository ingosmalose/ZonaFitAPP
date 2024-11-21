package com.zonafitApp;

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
                System.out.println("ERROR : "+e.getMessage());
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
        switch (option){
            case 1 ->
                System.out.println("Listing customer");

            case 2 ->
                    System.out.println("ADD customer");

            case 3 ->
                    System.out.println("UPDATE customer");

            case 4 ->
                    System.out.println("DELETE customer");
            case 5->
                exit = exit();
        }
        return exit;
    }

    //  [5] Exit : Stop application
    private static boolean exit(){
        System.out.println("Have a wonderful day, back soon.");
        return true;
    }
}