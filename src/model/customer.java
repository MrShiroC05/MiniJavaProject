package model;
public class customer {
    private float amount = -1;
    private boolean isBan = false;
    private int id = 0;
    private String name = "Unknow";
    private String username = "username";
    private String password = "password";
    private String email = "@email";

    public customer(int id, float amount, boolean isBan, String name, String username, String password, String email){
        this.id = id;
        this.amount = amount;
        this.isBan = isBan;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getInfo(){
        return String.format("Id\t: %d\nName\t: %s\nUsername\t: %s\nEmail\t: %s\nAmount\t: %.2f\nisBan\t: %b\n", id, name, username, email, amount, isBan);
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public float getAmount(){
        return amount;
    }
    public boolean isBan(){
        return isBan;
    }

    public boolean changeName(String nname){
        name = nname;
        return true;
    }
    public boolean changeUsername(String nusername){
        if (username.equals(nusername))
            return false;
        username = nusername;
        return true;
    }
    public boolean changepassword(String npassword){
        if (password.equals(npassword))
            return false;
        password = npassword;
        return true;
        
    }
    public boolean changeEmail(String nemail){
        if (email.equals(nemail))
            return false;
        email = nemail;
        return true;
    }

    public boolean add(float amount){
        this.amount += amount;
        return true;
    } 
    public boolean reduce(float amount){
        if (this.amount < amount)
            return false;
        this.amount -= amount;
        return true;
    }

    public boolean login(String cusername, String cpassword){
        if (username.equals(cusername) && password.equals(cpassword))
            return true;
        
        return false;
    }


}
