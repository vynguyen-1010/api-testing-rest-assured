package com.api.models;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phone;
    private String bloodGroup;
    private String gender;

    public User() {}

    public User(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public int getId() { return id;}
    public void setId(int id) { this.id = id;}

    public String getFirstName() { return firstName;}
    public void setFirstName(String firstname) { this.firstName = firstname;}

    public String getLastName() { return lastName;}
    public void setLastName(String lastName) { this.lastName = lastName;}

    public int getAge() { return age;}
    public void setAge(int age) { this.age = age;}

    public String getEmail() { return email;}
    public void setEmail(String email) { this.email = email;}

    public String getPhone() { return phone;}
    public void setPhone(String phone) { this.phone = phone;}

    public String getBloodGroup() { return bloodGroup;}
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup;}

    public String getGender() { return gender;}
    public void setGender(String gender) { this.gender = gender;}
}
