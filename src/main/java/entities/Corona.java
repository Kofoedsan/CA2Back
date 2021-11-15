package entities;

import javax.persistence.*;

@Table(name = "corona")
@Entity
public class Corona {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Id
    private int Id;
    @Column(name = "status", nullable = false)
    private boolean covidstatus;

    @ManyToOne
    private User user;

    public int getId() {
        return Id;
    }

    public int getCoronaId() {
        return Id;
    }


    public Corona() {
    }

    public Corona(boolean covidstatus, User user) {
        this.covidstatus = covidstatus;
        this.user = user;
    }

    public void setId(int id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Corona(boolean covidstatus) {
        this.covidstatus = covidstatus;
    }


    public boolean isCovidstatus() {
        return covidstatus;
    }

    public void setCovidstatus(boolean covidstatus) {
        this.covidstatus = covidstatus;
    }


}