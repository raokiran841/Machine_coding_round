package splitwise;

import java.time.LocalDate;
import java.util.Set;

public class Group {;
    private long groupId;
    private String groupName;
    private Set<Long> userIds;
    private User owner;
    private LocalDate createdAt;

    @Override
    public String toString() {
        return "\nGroup [groupId=" + groupId + ", groupName=" + groupName + "]";
    }

    public Group(long groupId, String groupName, Set<Long> userIds, User owner, LocalDate createdAt) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.userIds = userIds;
        this.owner = owner;
        this.createdAt = createdAt;
    }

    public Set<Long> getUserIds() {
        return userIds;
    }

    public long getGroupId() {
        return this.groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public User getOwner() {
        return owner;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    
    
}
