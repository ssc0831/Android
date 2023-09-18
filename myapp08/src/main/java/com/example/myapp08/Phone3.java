package com.example.myapp08;

public class Phone3 {
    private long id;
    private String name;
    private String tel;

    public Phone3(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
