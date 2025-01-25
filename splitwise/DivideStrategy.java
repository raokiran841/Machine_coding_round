package splitwise;

public interface DivideStrategy {
    public void divide(Group group, double amount, User currentUser);
}
