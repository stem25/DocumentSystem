package model;

import javax.xml.bind.annotation.XmlAnyElement;
import java.util.ArrayList;
import java.util.List;

public class Wrapper<T> {

    private List<T> items;

    public Wrapper(){
        this.items = new ArrayList<>();
    }

    public List<T> getItems() {
        return items;
    }

    @XmlAnyElement(lax = true)
    public void setItems(List<T> items) {
        this.items = items;
    }
}
