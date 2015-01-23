package agh.ddd.groups.poll.valueobjects;

import java.util.UUID;

/**
 * @author Michał Ciołczyk
 */
public class PollId {
    private final UUID id;

    private PollId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
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
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "PollId{" +
                "id=" + id +
                '}';
    }

    public static PollId of(UUID id){
        return new PollId(id);
    }
}
