package software.daveturner.gametimejpa.domain;

import java.io.Serializable;

public class PlayerInfo implements Serializable {

    Player player;
    Team team;
    Conference conference;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

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
}
