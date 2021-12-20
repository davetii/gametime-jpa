package software.daveturner.gametimejpa.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team   {

  @Column(nullable = false)
  private String locale;
  @Column(nullable = false)
  private String name;

  @Id
  @Column(name = "id", nullable = false)
  private String id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name="conference_id", nullable=true)
  private Conference conference;

  @OneToMany(fetch = FetchType.LAZY, mappedBy="team")
  private Set<Player> players = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "coach_id", referencedColumnName = "id")
  private Coach coach;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "gm_id", referencedColumnName = "id")
  private GM gm;

  public Coach getCoach() {
    return coach;
  }
  public void setCoach(Coach coach) {
    this.coach = coach;
  }



  public Team() { }

  public void setId(String id) {
    this.id = id;
  }
  public String getId() {
    return id;
  }

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

  public GM getGm() { return gm; }
  public void setGm(GM gm) { this.gm = gm; }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Team team = (Team) o;
    return id.equals(team.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Team{" +
            "locale='" + locale + '\'' +
            ", name='" + name + '\'' +
            ", id='" + id + '\'' +
            ", coach=" + coach +
            '}';
  }
}

