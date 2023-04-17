package org.example.view;

import org.example.controller.CarController;
import org.example.controller.ClientController;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu {
    private CarController carController;
    private Scanner scanner;
    public Menu() {
        carController = new CarController();
    }

    public void getStarted(){
    do {
        System.out.println("\n********************** Welcome to Rent A Car *****************************");
        System.out.println("\n\t1. Manage Clients.\t\t\t\t\t4. Manage Models.");
        System.out.println("\n\t2. Manage Cars.\t\t\t\t\t\t5. Manage Rents.");
        System.out.println("\n\t3. Manage Offices.\t\t\t\t\t0. Exit.");
        System.out.println("\n**************************************************************************");

        scanner = new Scanner(System.in);

        try {
            int choice = scanner.nextInt();
            if (choice == 1) {
                //clientManager();
            } else if (choice == 2) {
                carManager();
            } else if (choice == 3) {
                //TODO
            } else if (choice == 4) {
                //TODO
            } else if (choice == 5) {
                //TODO
            }else if(choice==0) {
                System.out.println("Bye!!");
                System.exit(0);
            } else {
                System.err.println("[ERROR] Your option is incorrect!! Try again!!");
            }

        } catch (
                InputMismatchException e) {
            System.err.println("[ERROR] You must type a number!!!");
            scanner.next();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    } while (true);
}

    public void carManager(){
        do {
            System.out.println("\n********************** Welcome to Rent A Car *****************************");
            System.out.println("\n\t1. Add license plates.\t\t\t\t4. Find by license plates.");
            System.out.println("\n\t2. Delete license plates.\t\t\t5. List License plates.");
            System.out.println("\n\t3. Update license plates.\t\t\t0. Back.");
            System.out.println("\n**************************************************************************");
            int choice = scanner.nextInt();
            try {
                if(choice == 1){
                    scanner.nextLine();
                    System.out.println("Numero de matricula: ");
                    String licensePlate = scanner.nextLine();
                    carController.add(licensePlate);
                } else if (choice == 2) {
                    System.out.println("Car id: ");
                    int id = scanner.nextInt();
                    carController.deleteById((long) id);
                    System.out.println(carController.findAll());
                } else if (choice == 3) {
                    System.out.println("Car id: ");
                    int id = scanner.nextInt();
                    System.out.println("New License plate number");
                    String licensePlate = scanner.next();
                    carController.update((long) id, licensePlate);
                    carController.findAll();
                } else if (choice == 4) {
                    scanner.nextLine();
                    System.out.println("License Plate: ");
                    String licensePlate = scanner.nextLine();
                    System.out.println(carController.findByLicensePlate(licensePlate));
                } else if (choice == 5) {
                    System.out.println(carController.findAll());
                } else if(choice==0) {
                    getStarted();
                } else {
                    System.err.println("[ERROR] Your option is incorrect!! Try again!!");
                }

            } catch (InputMismatchException e) {
                System.err.println("[ERROR] You must type a number!!!");
                scanner.next();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (true);
    }
}
