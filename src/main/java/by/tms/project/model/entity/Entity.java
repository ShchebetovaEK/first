package by.tms.project.model.entity;

import java.io.Serializable;

public abstract class Entity <T> implements Serializable,Cloneable {

    public Entity(){}

    public abstract T clone(T object);
}
