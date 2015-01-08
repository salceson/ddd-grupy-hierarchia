package agh.ddd.groups.valueobjects;

/**
 * @author Michał Ciołczyk
 */
public class PollId {
    private final long id;

    public PollId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PollId pollId = (PollId) o;

        if (id != pollId.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "PollId{" +
                "id=" + id +
                '}';
    }

    public static PollId of(long id){
        return new PollId(id);
    }
}
