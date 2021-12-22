package software.daveturner.gametimejpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "team")
@Cacheable
public class TeamEntity {

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
  private ConferenceEntity conference;

  @OneToMany(fetch = FetchType.LAZY, mappedBy="team")
  private Set<PlayerEntity> players = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "coach_id", referencedColumnName = "id")
  private CoachEntity coach;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "gm_id", referencedColumnName = "id")
  private GMEntity gm;

  public CoachEntity getCoach() {
    return coach;
  }
  public void setCoach(CoachEntity coach) {
    this.coach = coach;
  }



  public TeamEntity() { }

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

  public GMEntity getGm() { return gm; }
  public void setGm(GMEntity gm) { this.gm = gm; }

  public ConferenceEntity getConference() {
    return conference;
  }

  public void setConference(ConferenceEntity conference) {
    this.conference = conference;
  }

  public Set<PlayerEntity> getPlayers() {
    return players;
  }

  public void setPlayers(Set<PlayerEntity> players) {
    this.players = players;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TeamEntity team = (TeamEntity) o;
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

