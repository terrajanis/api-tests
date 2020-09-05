package models;

public class UserInfo {
    private String name;
    private String job;
    private String id;

    public String getName() {
        return name;
    }

    public UserInfo withName(String name) {
        this.name = name;
        return  this;
    }

    public String getJob() {
        return job;
    }

    public UserInfo withJob(String job) {
        this.job = job;
        return this;
    }

    public String getId() {
        return id;
    }

    public UserInfo withId(String id) {
        this.id = id;
        return this;
    }
}
