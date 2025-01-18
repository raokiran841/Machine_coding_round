package splitwise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public final class Database {
    private static Database database = null;
    private final AtomicLong userId = new AtomicLong(1000l);
    private final AtomicLong groupId = new AtomicLong(100);
    private HashMap<Long, Pair> credentials = new HashMap<>();
    private List<Group> dbGroups = new ArrayList<>();

    private Database(){
        initDB();
    }

    public static Database getDBInstance(){
        if(database == null){
            database = new Database();
        }
        return database;
    }

    private void initDB(){
        List<String> userNames = List.of("John","Alice","Bob","Peter");
        userNames.forEach(u -> registerUser(u, "1234"));

        List<User> users = credentials.values().stream().map(p -> p.getUser()).toList();

        List<Group> groups = List.of(
            new Group(groupId.incrementAndGet(),
                "fun friday", 
                Set.of(users.get(0).getId(), users.get(1).getId()), 
                users.get(0), 
                LocalDate.now().minusDays(1)),
            new Group(groupId.incrementAndGet(),
                "weekend plan", 
                Set.of(users.get(2).getId(), users.get(1).getId(), users.get(3).getId()), 
                users.get(1), 
                LocalDate.now().minusDays(1))
        );

        groups.forEach(g -> createGroup(g.getGroupName(), g.getOwner(), g.getUserIds()));

        System.out.println("Existing Users:"+users);
        System.out.println("Existing groups:"+dbGroups);
    }

    public void createGroup(String name, User user, Set<Long> userIds){
        Group group = new Group(groupId.incrementAndGet(), name, userIds, user, LocalDate.now());
        dbGroups.add(group);
    }

    public void addGroup(Group grp){
        dbGroups.add(grp);
    }

    public Group getGroup(long id){
        return dbGroups.stream().filter(g -> g.getGroupId() == id).findFirst().get();
    }

    public List<Group> getGroupHavingUser(long id){
        return dbGroups.stream()
                .filter(grp -> grp.getUserIds().contains(id))
                .collect(Collectors.toList());
    }

    public String registerUser(String name, String passwd){
        long newid = userId.incrementAndGet();
        User user = new User(newid, name);
        if(credentials.containsKey(newid)){
            return "User already exist";
        }
        credentials.put(newid, new Pair(user, passwd));
        return "new user created with user id: "+newid;
    }

    public boolean authenticate(long id, String passwd){
        if(credentials.containsKey(id)){
            if(passwd.equals(credentials.get(id).getPasswd())){
                return true;
            }
        }
        return false;
    }

    public User getUser(long id){
        return credentials.get(id).getUser();
    }
}

class Pair{
    User user;
    String passwd;

    Pair(User u, String p){
        this.user = u;
        this.passwd = p;
    }

    public User getUser() {
        return user;
    }

    public String getPasswd() {
        return passwd;
    }
}
