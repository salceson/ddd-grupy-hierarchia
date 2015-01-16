package agh.ddd.groups.group.valueobjects;

import java.util.UUID;

public class GroupId {
    private final UUID uuid;

    public GroupId(UUID uuid) {
        this.uuid = uuid;
    }

    public static GroupId of(UUID uuid) {
        return new GroupId(uuid);
    }

    //THOSE THREE METHODS HAS TO BE IMPLEMENTED
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupId groupId = (GroupId) o;

        return uuid.equals(groupId.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}
