package software.daveturner.gametimejpa.domain;

import javax.persistence.*;


@Entity
public class Player {

    public Player() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String height;
    private Integer weight;
    private Integer yearsPro;
    private String origin;
    private String originDetails;

    private Integer athleticism;
    private Integer charisma;
    private Integer cohesion;
    private Integer determination;
    private Integer ego;
    private Integer endurance;
    private Integer energy;
    private Integer handle;
    private Integer health;
    private Integer intelligence;
    private Integer luck;
    private Integer shotSelection;
    private Integer shotSkill;
    private Integer size;
    private Integer speed;
    private Integer strength;



    @ManyToOne
    @JoinColumn(name="team_id", nullable=true)
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getYearsPro() {
        return yearsPro;
    }

    public void setYearsPro(Integer yearsPro) {
        this.yearsPro = yearsPro;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginDetails() { return originDetails; }
    public void setOriginDetails(String originDetails) { this.originDetails = originDetails; }

    public Integer getAthleticism() {
        return athleticism;
    }

    public void setAthleticism(Integer athleticism) {
        this.athleticism = athleticism;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }

    public Integer getCohesion() {
        return cohesion;
    }

    public void setCohesion(Integer cohesion) {
        this.cohesion = cohesion;
    }

    public Integer getDetermination() {
        return determination;
    }

    public void setDetermination(Integer determination) {
        this.determination = determination;
    }

    public Integer getEgo() {
        return ego;
    }

    public void setEgo(Integer ego) {
        this.ego = ego;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getHandle() {
        return handle;
    }

    public void setHandle(Integer handle) {
        this.handle = handle;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getLuck() {
        return luck;
    }

    public void setLuck(Integer luck) {
        this.luck = luck;
    }

    public Integer getShotSelection() {
        return shotSelection;
    }

    public void setShotSelection(Integer shotSelection) {
        this.shotSelection = shotSelection;
    }

    public Integer getShotSkill() {
        return shotSkill;
    }

    public void setShotSkill(Integer shotSkill) {
        this.shotSkill = shotSkill;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSpeed() {
        return speed;
    }
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getStrength() {
        return strength;
    }
    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return id != null ? id.equals(player.id) : player.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", role='" + role + '\'' +
                ", height='" + height + '\'' +
                ", weight=" + weight +
                ", yearsPro=" + yearsPro +
                ", origin='" + origin + '\'' +
                ", originDetails='" + originDetails + '\'' +
                ", athleticism=" + athleticism +
                ", charisma=" + charisma +
                ", cohesion=" + cohesion +
                ", determination=" + determination +
                ", ego=" + ego +
                ", endurance=" + endurance +
                ", energy=" + energy +
                ", handle=" + handle +
                ", health=" + health +
                ", intelligence=" + intelligence +
                ", luck=" + luck +
                ", shotSelection=" + shotSelection +
                ", shotSkill=" + shotSkill +
                ", size=" + size +
                ", speed=" + speed +
                ", strength=" + strength +
                ", team=" + team +
                '}';
    }
}
