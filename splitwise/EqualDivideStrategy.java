package splitwise;

public class EqualDivideStrategy implements DivideStrategy{

    Database db = Database.getDBInstance();

    @Override
    public void divide(Group group, double amount, User currentUser) {
        int grpSize = group.getUserIds().size();
        double val = amount/grpSize;
        for(long uid: group.getUserIds()){
            if(uid != currentUser.getId()){
                User user = db.getUser(uid);
                user.saveTrxn(currentUser.getId(), 0-val);
                currentUser.saveTrxn(uid, val);
            }
        }
        
    }
    
}
