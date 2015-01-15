package agh.ddd.groups.group.commands;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by mikolaj on 09.01.15.
 */
public class RemoveMemberCommand {
    @TargetAggregateIdentifier
    private final GroupId groupId;
    private final Member member;

    public RemoveMemberCommand(GroupId groupId, Member member) {
        this.groupId = groupId;
        this.member = member;
    }

    public GroupId getGroupId() {
        return groupId;
    }

    public Member getMember() {
        return member;
    }
}
