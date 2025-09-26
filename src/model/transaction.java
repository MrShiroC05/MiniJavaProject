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
        String text = "";
        if (recipientId > 0 && giverId == -1){
            if (amount > 0)
                text = String.format("Deposit %.2f\n", amount);
            else
                text = String.format("Withdraw %.2f\n", amount);
        }
        else 
            text = String.format("Transfer %.2f from %d to %d\n", amount, giverId, recipientId);
        return text;
    }
}
