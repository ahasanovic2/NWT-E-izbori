package ba.nwt.tim3.notificationmicroservices.management.models;


import javax.persistence.*;

import java.util.List;

@Entity
public class PollingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "pollingStation")
    private List<Result> resultList;

    @OneToMany(mappedBy = "pollingStation")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser (User user) {
        this.users.add(user);
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public void addResult (Result result) {
        this.resultList.add(result);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
