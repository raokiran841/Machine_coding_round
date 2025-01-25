package splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private long id;
    private String name;
    private double amtToPay;
    private double amtToReceive;
    private List<Group> groups = new ArrayList<>();
    // private List<Transaction> txns = new ArrayList<>();
    // private final AtomicLong txnId = new AtomicLong(10);
    private HashMap<Long, Double> txns = new HashMap<>();
    
    @Override
    public String toString() {
        return "\nUser [id=" + id + ", name=" + name + "]";
    }
    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getAmtToPay() {
        double amt = 0;
        for(Map.Entry<Long, Double> entry : txns.entrySet()){
            double val = entry.getValue();
            if(val < 0) amt += val;
        }
        return amt;
    }
    
    public double getAmtToReceive() {
        double amt = 0;
        for(Map.Entry<Long, Double> entry : txns.entrySet()){
            double val = entry.getValue();
            if(val > 0) amt += val;
        }
        return amt;
    }
    
    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    public HashMap<Long, Double> getTxns() {
        return txns;
    }
    public void setTxns(HashMap<Long, Double> txns) {
        this.txns = txns;
    }

    public void saveTrxn(long userId, double amout) {
        txns.put(userId, txns.getOrDefault(userId, 0.0)+amout);
    }
}

