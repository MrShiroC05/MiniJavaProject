package Msystem;

import model.customer;
import java.util.Scanner;

public class mainSystem {
    private CustomerManagement cm = new CustomerManagement();
    private customer user;
    private boolean isLogin = false;

    public void manu(){
        if(isLogin){
            System.out.printf("MANU\nyour money in our bank is %.2f\n1.Deposti\n2.withdraw\n3.log out",user.getAmount());
        }
        else{
            
        }
    }

    public void Login(){
        Scanner read = new Scanner(System.in);
        String username, password;
        do{
            System.out.print("Login\nusername\t: ");
            username = read.nextLine();
            System.out.print("password\t: ");
            password = read.nextLine();
            
            customer login = cm.Login(username, password);
            if(login != null){
                user = login;
                isLogin = true;
            }
        }while(!isLogin);
        
        read.close();

    }
}
