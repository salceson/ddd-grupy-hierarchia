package agh.ddd.groups.group.commands;

import agh.ddd.groups.group.Member;
import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by mikolaj on 08.01.15.
 */
public class AddMemberToGroupCommand {
    @TargetAggregateIdentifier
    private GroupId groupId;
    private Member member;

    public AddMemberToGroupCommand(GroupId groupId, Member member) {
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
