package agh.ddd.groups.group.events;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;

/**
 * Created by mikolaj on 09.01.15.
 */
public class MemberRemovedEvent {
    private final GroupId groupId;
    private final Member member;

    public MemberRemovedEvent(GroupId groupId, Member member) {
        this.groupId = groupId;
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public GroupId getGroupId() {
        return groupId;
    }
}
