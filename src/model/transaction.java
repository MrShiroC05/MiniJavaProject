package model;

public class transaction {
    private int giverId = -1;
    private int recipientId = -1;
    private float amount = -1;
    
    public transaction(int giverId, int recipientId, float amount){
        this.giverId = giverId;
        this.recipientId = recipientId;
        this.amount = amount;
    }
    public transaction(int recipientId, float amount){
        this.recipientId = recipientId;
        this.amount = amount;
    }
    public int getGiverId(){
        return giverId;
    }
    public int getRecipientId(){
        return recipientId;
    }
    public String getInfo(){
        return String.format("Giver's id\t: %d\nAmount\t: %.2f\nrecipient's id\t:%d", giverId, amount, recipientId);
    }
}
