package software.daveturner.gametimejpa.domain;

import software.daveturner.gametimejpa.entity.ConferenceEntity;

import java.util.HashSet;
import java.util.Set;

public class Conference {

    private String id;
    private String name;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

}
