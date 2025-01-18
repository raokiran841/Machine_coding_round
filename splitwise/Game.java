package splitwise;

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private static final Database db = Database.getDBInstance();
    private static User currentUser = null;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();
        while (true) {
            System.out.println("""
                    press 1 to start
                    press 2 to exit
                    """);
            int val = sc.nextInt();
            sc.nextLine();
            if(val == 1){
                game.signInSignUp();
                game.openApp();
            } else {
                return;
            }
        }
    }

    private void signInSignUp() {
        System.out.println("* Welcome to spliwise *");
        System.out.println("press 1 to login\npress 2 to register\n");
        int val = sc.nextInt();
        sc.nextLine();
        if (val == 1) {
            login(sc);
        } else {
            System.out.println("Enter name:");
            String name = sc.nextLine();
            System.out.println("Choose a password:");
            String passwd = sc.nextLine();
            String res = db.registerUser(name, passwd);
            System.out.println(res);
            login(sc);
        }
    }

    private static void login(Scanner sc) {
        System.out.println("User id:");
        long userId = sc.nextLong();
        sc.nextLine();

        System.out.println("Password:");
        String passwd = sc.nextLine();

        if (db.authenticate(userId, passwd)) {
            currentUser = db.getUser(userId);
        } else {
            System.err.println("User authentication failed");
        }
    }

    private void openApp() {
        double[] amtToPay = new double[]{currentUser.getAmtToPay()};
        double[] amtToReceive = new double[]{currentUser.getAmtToReceive()};
        if (currentUser == null) {
            System.out.println("login first");
            return;
        }
        System.out.println("Welcome " + currentUser.getName());
        System.out.println("You owe " + amtToPay[0]);
        System.out.println("And you get " + amtToReceive[0]);
        currentUser.setGroups(db.getGroupHavingUser(currentUser.getId()));

        while (true) {
            System.out.println("""
            ===============================================
            press 1 to make payment
            press 2 to request payment
            press 3 to print profile
            press 4 to exit
            ===============================================
            """);
            int val = sc.nextInt();
            sc.nextLine();
            if(val == 1){
                makePayment();
            } else if(val == 2){
                requestPayment();
            } else if(val == 3){
                printProfile();
            } else {
                System.out.println("See you again.. Bye");
                return;
            }
        }
    }

    private void printProfile(){
        HashMap<Long, Double> txns = currentUser.getTxns();
        System.out.println("You owe " + currentUser.getAmtToPay());
        System.out.println("And you get " + currentUser.getAmtToReceive());
        txns.forEach((k,v) -> {
            if(v < 0){
                System.out.println("You owe "+db.getUser(k).getName() + " $"+ v);
            } else {
                System.out.println(db.getUser(k).getName() + " owes you $"+ v);
            }
        });        
    }

    private void requestPayment(){
        System.out.println("You are part of these groups:"+currentUser.getGroups());
        System.out.println("Choose a group to request payment, group id:");
        long grpId = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter amount:");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.println("""
                Choose how you want to divide the amount
                1. Everyone pays equal amount
                2. percantage basis
                3. enter amount for each member
                """);
        int val = sc.nextInt();
        sc.nextLine();
        // only equally for today
        DivideStrategy divideStrategy = null;
        if(val == 1){
            divideStrategy = new EqualDivideStrategy();
        } else if (val == 2){
            divideStrategy = new EqualDivideStrategy();            
        } else {
            divideStrategy = new EqualDivideStrategy();            
        }
        RequestPayment requestPayment = new RequestPayment(currentUser,db.getGroup(grpId), amount, divideStrategy);
        requestPayment.divide();
    }

    private void makePayment(){
        // transaction
        System.out.println("Who do you want to pay enter his userId:");
        long recipientId = sc.nextLong();
        sc.nextLine();
        if(recipientId == currentUser.getId()){
            System.err.println("You are paying yourself !! not allowed");
            return;
        }
        System.out.println("Enter amount:");
        double amount = sc.nextDouble();
        sc.nextLine();

        currentUser.saveTrxn(recipientId, amount);
        User recipient = db.getUser(recipientId);
        recipient.saveTrxn(currentUser.getId(), 0-amount);
        System.out.println("txns successful");
    }
}