package agh.ddd.groups.group.exceptions;

import agh.ddd.groups.group.valueobjects.GroupId;

public class GroupAlreadyClosedException extends RuntimeException {
    private final GroupId id;

    public GroupAlreadyClosedException(GroupId id) {
        this.id = id;
    }
}
