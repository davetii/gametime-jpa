package software.daveturner.gametimejpa.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "coach")
public class Coach   {

  public Coach() { }

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @JsonBackReference
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "coach")
  private Team team;

  public Team getTeam() {
    return team;
  }
  public void setTeam(Team team) {
    this.team = team;
  }


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

