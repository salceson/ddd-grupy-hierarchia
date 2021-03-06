package agh.ddd.groups.poll.valueobjects;

/**
 * @author Michal Janczykowski
 */
public class UserId {
    private final long id;

    private UserId(long id) {
        this.id = id;
    }

    public static UserId of(long id) {
        return new UserId(id);
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserId userId = (UserId) o;

        if (id != userId.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "UserId{" +
                "id=" + id +
                '}';
    }
}
