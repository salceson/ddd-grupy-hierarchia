package agh.ddd.groups.group.valueobjects;

public class GroupId {
    private final String id;

    private GroupId(String id) {
        this.id = id;
    }

    public static GroupId of(String groupId) {
        return new GroupId(groupId);
    }

    //THOSE THREE METHODS HAS TO BE IMPLEMENTED
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupId that = (GroupId) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}
