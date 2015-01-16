package agh.ddd.groups.group.valueobjects;

/**
 * Created by mikolaj on 09.01.15.
 */
public class GroupConfiguration {
    private final int maxMembers;

    public GroupConfiguration(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupConfiguration that = (GroupConfiguration) o;

        return maxMembers == that.maxMembers;
    }

    @Override
    public int hashCode() {
        return maxMembers;
    }
}
