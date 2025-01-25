// package splitwise;

// import java.time.LocalDateTime;

// public class Transaction {
//     private long txnId;
//     private User payee;
//     private User recipient;
//     private double amount;
//     private LocalDateTime timestamp;
    

//     @Override
//     public String toString() {
//         if(amount > 0)
//             return payee.getName() + " paid $" + amount + " to " + recipient.getName() + " at "+timestamp;
//         else
//             return recipient.getName() +" requested for payment of $"+(0 - amount) +" at "+timestamp;
//     }

//     public Transaction(User payee, User recipient, double amount, LocalDateTime timestamp) {
//         this.payee = payee;
//         this.recipient = recipient;
//         this.amount = amount;
//         this.timestamp = timestamp;
//     }
    
//     public User getPayee() {
//         return payee;
//     }
//     public void setPayee(User payee) {
//         this.payee = payee;
//     }
//     public User getRecipient() {
//         return recipient;
//     }
//     public void setRecipient(User recipient) {
//         this.recipient = recipient;
//     }
//     public double getAmount() {
//         return amount;
//     }
//     public void setAmount(double amount) {
//         this.amount = amount;
//     }

//     public LocalDateTime getTimestamp() {
//         return timestamp;
//     }

//     public void setTimestamp(LocalDateTime timestamp) {
//         this.timestamp = timestamp;
//     }

//     public long getTxnId() {
//         return txnId;
//     }
//     public void setTxnId(long txnId) {
//         this.txnId = txnId;
//     }
// }
