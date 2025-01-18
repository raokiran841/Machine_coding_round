package splitwise;

public class RequestPayment {

    private User currentUser;
    private Group group;
    private double amount;
    private DivideStrategy divideStrategy;
    public RequestPayment(User currentUser, Group group, double amount, DivideStrategy divideStrategy) {
        this.currentUser = currentUser;
        this.group = group;
        this.amount = amount;
        this.divideStrategy = divideStrategy;
    }

    public void divide(){
        divideStrategy.divide(group, amount, currentUser);
    }
}
