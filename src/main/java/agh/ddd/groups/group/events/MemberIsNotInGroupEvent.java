package agh.ddd.groups.group.events;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;

/**
 * Created by mikolaj on 09.01.15.
 */
public class MemberIsNotInGroupEvent {
    private final GroupId id;
    private final Member member;

    public MemberIsNotInGroupEvent(GroupId id, Member member) {
        this.id = id;
        this.member = member;
    }

    public GroupId getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }
}
