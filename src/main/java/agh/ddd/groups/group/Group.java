package agh.ddd.groups.group;

import agh.ddd.groups.group.events.*;
import agh.ddd.groups.group.valueobjects.GroupConfiguration;
import agh.ddd.groups.group.valueobjects.GroupId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mikolaj on 08.01.15.
 */

public class Group extends AbstractAnnotatedAggregateRoot {
    @AggregateIdentifier
    private GroupId id;
    private GroupConfiguration configuration;
    private Set<Member> members = new HashSet<Member>();

    private Group() {

    }

    public Group(GroupId groupId, GroupConfiguration configuration) {
        apply(new GroupCreatedEvent(groupId, configuration));
    }

    public void addMember(Member member) {
        if(members.contains(member)) {
            apply(new MemberAlreadyInGroupEvent(id, member));
            return;
        }

        if(members.size() >= configuration.getMaxMembers()) {
            apply(new MaximumMembersNumberReachedEvent(id));
            return;
        }

        apply(new MemberAddedEvent(id, member));
    }

    public void removeMember(Member member) {
        if(!members.contains(member)) {
            apply(new MemberIsNotInGroupEvent(id, member));
            return;
        }

        apply(new MemberRemovedEvent(id, member));
    }

    @EventSourcingHandler
    public void onGroupCreatedEvent(GroupCreatedEvent event) {
        this.id = event.getGroupId();
        this.configuration = event.getConfiguration();
    }

    @EventSourcingHandler
    public void onMemberAddedEvent(MemberAddedEvent event) {
        this.members.add(event.getMember());
    }

    @EventSourcingHandler
    public void onMemberRemovedEvent(MemberRemovedEvent event) {
        this.members.remove(event.getMember());
    }
}
