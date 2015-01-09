package agh.ddd.groups.group;

import agh.ddd.groups.group.valueobjects.GroupConfiguration;
import agh.ddd.groups.group.valueobjects.GroupId;

import java.util.List;

/**
 * Created by mikolaj on 09.01.15.
 */
public class GroupFactory {
    public static Group create(GroupId groupId, GroupConfiguration configuration) {
        return new Group(groupId, configuration);
    }
}
