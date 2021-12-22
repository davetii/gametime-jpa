package software.daveturner.gametimejpa.domain;

import java.util.HashSet;
import java.util.Set;

public class ConferenceInfo {

    Conference conference;
    Set<TeamInfo> teams = new HashSet<>();

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Set<TeamInfo> getTeams() {
        return teams;
    }

    public void setTeams(Set<TeamInfo> teams) {
        this.teams = teams;
    }
}
