package agh.ddd.groups.group;

import agh.ddd.groups.group.valueobjects.GroupId;

public class GroupFactory {
    public static Group create(GroupId id, String name) {
        return new Group(id, name);
    }
}
