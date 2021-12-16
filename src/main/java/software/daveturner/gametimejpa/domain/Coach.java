package software.daveturner.gametimejpa.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "coach")
public class Coach   {

  public Coach() { }

  private String firstName;
  private String lastName;


  @OneToOne(cascade = CascadeType.ALL)
  @JoinTable(name = "team_coach",
          joinColumns =
                  { @JoinColumn(name = "coach_id", referencedColumnName = "id") },
          inverseJoinColumns =
                  { @JoinColumn(name = "team_id", referencedColumnName = "id") })
  private Team team;

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }



  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coach coach = (Coach) o;
    return Objects.equals(id, coach.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }


  @Override
  public String toString() {
    return "Coach{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", id=" + id +
            '}';
  }
}

