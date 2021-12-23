package software.daveturner.gametimejpa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ConferenceInfo implements Serializable {

    Conference conference;
    List<TeamInfo> teams = new ArrayList<>();

    public ConferenceInfo() { }

    public Conference getConference() {
        return conference;
    }
    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public List<TeamInfo> getTeams() {
        return teams;
    }
    public void setTeams(List<TeamInfo> teams) {
        this.teams = teams;
    }
}
