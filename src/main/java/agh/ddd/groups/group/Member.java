package agh.ddd.groups.group;

import agh.ddd.groups.group.valueobjects.Email;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;

/**
 * Created by mikolaj on 08.01.15.
 */
public class Member extends AbstractAnnotatedEntity {
    private final Email email;
    private String firstName;
    private String lastName;

    public Member(Email email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Email getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member that = (Member) o;

        if(email == null) {
            return that.email == null;
        }
        else {
            return email.equals(that.email);
        }
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

}
