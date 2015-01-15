package agh.ddd.groups.group.valueobjects;

import java.util.UUID;

/**
 * Created by mikolaj on 08.01.15.
 */
public class GroupId {
    private final UUID uuid = UUID.randomUUID();

    public GroupId() {
    }

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
