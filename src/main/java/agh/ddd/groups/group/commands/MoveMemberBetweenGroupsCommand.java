package agh.ddd.groups.group.commands;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;

/**
 * Created by mikolaj on 09.01.15.
 */
public class MoveMemberBetweenGroupsCommand {
    private final GroupId fromGroup;
    private final GroupId toGroup;
    private final Member member;

    public MoveMemberBetweenGroupsCommand(GroupId fromGroup, GroupId toGroup, Member member) {
        this.fromGroup = fromGroup;
        this.toGroup = toGroup;
        this.member = member;
    }

    public GroupId getFromGroup() {
        return fromGroup;
    }

    public GroupId getToGroup() {
        return toGroup;
    }

    public Member getMember() {
        return member;
    }
}
