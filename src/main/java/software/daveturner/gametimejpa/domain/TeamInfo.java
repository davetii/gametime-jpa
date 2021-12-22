package software.daveturner.gametimejpa.domain;

import java.util.HashSet;
import java.util.Set;

public class TeamInfo {

    private Team team;
    private Conference conference;
    Set<Player> players = new HashSet<>();


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}


