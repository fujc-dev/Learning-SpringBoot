package com.zc58s.springaopdemo.pojo;

/*
 * springaop-demo
 * com.zc58s.springaopdemo.pojo;
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/24 14:41
 */


public class User {
    private long id;
    private String username;
    private String node;

    public User() {
    }

    public User(long id, String username, String node) {
        this.id = id;
        this.username = username;
        this.node = node;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", node='" + node + '\'' +
                '}';
    }
}
