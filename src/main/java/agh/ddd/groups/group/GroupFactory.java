package agh.ddd.groups.group;

import agh.ddd.groups.group.valueobjects.GroupConfiguration;
import agh.ddd.groups.group.valueobjects.GroupId;

public class GroupFactory {
    public static Group create(GroupId groupId, GroupConfiguration configuration, String name) {
        return new Group(groupId, configuration, name);
    }
}
