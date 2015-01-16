package agh.ddd.groups.group.events;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;

/**
 * Created by mikolaj on 09.01.15.
 */
public class MemberAddedEvent {
    private final GroupId id;
    private final Member member;

    public MemberAddedEvent(GroupId id, Member member) {
        this.id = id;
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public GroupId getId() {
        return id;
    }
}
