package me.spring.jpa.lockmode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Optimistic {

    @Id
    private final Long id;

    public String name;

    @Version
    public final long version;

    protected Optimistic(Long id, String name) {
        this.id = id;
        this.name = name;
        this.version = 0;
    }

}
