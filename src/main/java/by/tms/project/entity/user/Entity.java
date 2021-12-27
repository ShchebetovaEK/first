package by.tms.project.entity.user;

import java.io.Serializable;

public abstract class Entity <T> implements Serializable {

    public Entity(){}

    public abstract T getCopy(T object);
}
