package bo.entities;

import java.util.UUID;

public class Item {
    private String id;
    private String descr;
    private String name;

    public Item(String descr, String name){
        this.id = UUID.randomUUID().toString();
        this.descr = descr;
        this.name = name;
    }
    public Item(String id, String descr, String name){
        this.id = id;
        this.descr = descr;
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle(){
        return this.descr;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", descr='" + descr + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
