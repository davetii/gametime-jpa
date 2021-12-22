package software.daveturner.gametimejpa.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class PlayerSkills implements Serializable {

    private BigDecimal acumen;
    private BigDecimal ballSecurity;
    private BigDecimal passing;
    private BigDecimal teamOffense;
    private BigDecimal drive;
    private BigDecimal freeThrows;
    private BigDecimal longRange;
    private BigDecimal perimeter;
    private BigDecimal post;
    private BigDecimal individualDefense;
    private BigDecimal teamDefense;
    private BigDecimal offenseRebound;
    private BigDecimal defenseRebound;

    public PlayerSkills() { }

    public PlayerSkills(Player player) {
    }

    public BigDecimal getAcumen() {
        return acumen;
    }

    public void setAcumen(BigDecimal acumen) {
        this.acumen = acumen;
    }

    public BigDecimal getBallSecurity() {
        return ballSecurity;
    }

    public void setBallSecurity(BigDecimal ballSecurity) {
        this.ballSecurity = ballSecurity;
    }

    public BigDecimal getPassing() {
        return passing;
    }

    public void setPassing(BigDecimal passing) {
        this.passing = passing;
    }

    public BigDecimal getTeamOffense() {
        return teamOffense;
    }

    public void setTeamOffense(BigDecimal teamOffense) {
        this.teamOffense = teamOffense;
    }

    public BigDecimal getDrive() {
        return drive;
    }

    public void setDrive(BigDecimal drive) {
        this.drive = drive;
    }

    public BigDecimal getFreeThrows() {
        return freeThrows;
    }

    public void setFreeThrows(BigDecimal freeThrows) {
        this.freeThrows = freeThrows;
    }

    public BigDecimal getLongRange() {
        return longRange;
    }

    public void setLongRange(BigDecimal longRange) {
        this.longRange = longRange;
    }

    public BigDecimal getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(BigDecimal perimeter) {
        this.perimeter = perimeter;
    }

    public BigDecimal getPost() {
        return post;
    }

    public void setPost(BigDecimal post) {
        this.post = post;
    }

    public BigDecimal getIndividualDefense() {
        return individualDefense;
    }

    public void setIndividualDefense(BigDecimal individualDefense) {
        this.individualDefense = individualDefense;
    }

    public BigDecimal getTeamDefense() {
        return teamDefense;
    }

    public void setTeamDefense(BigDecimal teamDefense) {
        this.teamDefense = teamDefense;
    }

    public BigDecimal getOffenseRebound() {
        return offenseRebound;
    }

    public void setOffenseRebound(BigDecimal offenseRebound) {
        this.offenseRebound = offenseRebound;
    }

    public BigDecimal getDefenseRebound() {
        return defenseRebound;
    }

    public void setDefenseRebound(BigDecimal defenseRebound) {
        this.defenseRebound = defenseRebound;
    }
}
