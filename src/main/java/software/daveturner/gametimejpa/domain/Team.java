package software.daveturner.gametimejpa.domain;

import java.io.Serializable;

public class Team implements Serializable {

    private String locale;
    private String name;
    private String id;
    private Coach coach;
    private GM gm;



    public String getLocale() {
        return locale;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Coach getCoach() {
        return coach;
    }
    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public GM getGm() {
        return gm;
    }
    public void setGm(GM gm) {
        this.gm = gm;
    }

   }
