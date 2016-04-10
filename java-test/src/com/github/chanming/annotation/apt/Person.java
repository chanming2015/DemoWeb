package com.github.chanming.annotation.apt;

@Persistent(table = "person")
public class Person
{
    @IdProperty(column = "id", generator = "identity", type = "integer")
    private Integer id;

    @Property(column = "name", type = "string")
    private String name;

    public Person()
    {

    }

    public Person(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
