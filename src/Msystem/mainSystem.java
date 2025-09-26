package Msystem;

import model.customer;
import java.util.Scanner;

public class mainSystem {
    private static customer user;
    private static boolean isLogin = false;
    private static Scanner read = new Scanner(System.in);
    public mainSystem(){
        CustomerManagement.load();
    }
    public void manu(){
        boolean exit = false;
        do{
            
            if(isLogin && !user.isBan()){
                System.out.printf("MANU\nsir %s. your money in our bank is %.2f\n",user.getName(), user.getAmount());
                int choice = select(new String[]{"Deposti", "withdraw", "transfer", "history","log out"});
                switch (choice) {
                    case 1:
                        deposti();
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        transfer();
                        break;
                    case 4:
                        history(user.getId());
                        break;
                    case 5:
                        logout();
                        break;
                }
            }
            else if (isLogin){
                System.out.println("You are banned from using our services.\nPlease contact the bank for more information.");
                int choice = select(new String[]{"log out"});
                if (choice == 1)
                    logout();

            }
            else{
                System.out.println("MANU");
                int choice = select(new String[]{"Login", "Exit"});
                switch (choice) {
                    case 1:
                        Login();
                        break;
                    case 2:
                        System.out.println("Thank you for using our services.");
                        exit = true;
                        break;
                }
            }

            
        }while (!exit);
        read.close();
    }

    private void Login(){
        
        do{
            System.out.print("Login\nusername\t: ");
            String username = read.nextLine();
            System.out.print("password\t: ");
            String password = read.nextLine();
            
            customer login = CustomerManagement.Login(username, password);

            if(login != null){
                user = login;
                isLogin = true;
            }
        }while(!isLogin);
        

    }
    private void logout(){
        if(!isLogin)
            return;
        isLogin = false;
        user = null;
    }

    private void transfer(){
        if(!isLogin)
            return;

        int recipientId;
        float amount;
        customer recipient;

        do{
            System.out.print("Transfer\nrecipient id\t: ");
            recipientId = read.nextInt();
            recipient = CustomerManagement.searchById(recipientId);
            
        }while(recipient == null || recipient.getId() == user.getId());

        do{
            System.out.print("amount\t: ");
            amount = read.nextFloat();
        }while(amount <= 0);

        String result = CustomerManagement.transfer(amount, user, recipient);

        if(result.isEmpty())
            System.out.println("Transfer complete.");
        else
            System.out.println("Transfer fail. " + result);
    }
    private void deposti(){
        if(!isLogin)
            return;

        float amount;
        do{
            System.out.print("Deposti\namount\t: ");
            amount = read.nextFloat();
        }while(amount <= 0);

        if(CustomerManagement.deposit(amount, user))
            System.out.println("Deposti complete.");
        else
            System.out.println("Deposti fail.");


    }
    private static void withdraw(){
        if(!isLogin)
            return;
            
        float amount;
        String result;

        do{
            System.out.print("withdraw\namount\t: ");
            amount = read.nextFloat();
        }while(amount <= 0);

        result = CustomerManagement.withdraw(amount, user);

        if(result.isEmpty())
            System.out.println("withdraw complete.");
        else
            System.out.println("withdraw fail. " + result);

    }

    private void history(int id){
        CustomerManagement.getTransactionsList(id).forEach(ts -> System.out.println(ts.getInfo()));
    }

    private int select(String[] options){
        int anwser;
        
        do {
            for(int choice = 0; choice < options.length; choice++)
                System.out.printf("%d. %s\n", choice+1,  options[choice]);
            System.out.print("Your choice\t: ");
            anwser = read.nextInt(); 
            read.nextLine();
        } while (anwser < 0 && anwser <= options.length);
        
        return anwser;
    }
}
