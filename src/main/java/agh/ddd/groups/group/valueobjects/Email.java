package agh.ddd.groups.group.valueobjects;

import com.google.common.base.Preconditions;

/**
 * Created by mikolaj on 08.01.15.
 */
public class Email {
    private final String value;

    public Email(String value) {
        Preconditions.checkNotNull(value);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email = (Email) o;

        if (!value.equals(email.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
