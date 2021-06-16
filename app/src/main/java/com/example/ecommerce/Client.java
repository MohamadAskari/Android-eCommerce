package com.example.ecommerce;

public class Client {
    private String UserName;
    private String FirstName;
    private String LastName;
    private String Email;
    private String PhoneNumber;
    private String Password;
    private boolean IsSeller;
    private int Login_count;

    public Client(String userName, String firstName, String lastName, String email, String phoneNumber, String password, boolean is_Seller) {
        UserName = userName;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        PhoneNumber = phoneNumber;
        Password = password;
        IsSeller = is_Seller;
        Login_count = 1;
    }

    public Client(String userName, String firstName, String lastName, String email, String phoneNumber, String password, boolean isSeller,  int login_count) {
        UserName = userName;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        PhoneNumber = phoneNumber;
        Password = password;
        IsSeller = isSeller;
        Login_count = login_count;
    }

    public Client() {
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isSeller() {
        return IsSeller;
    }

    public void setSeller(boolean seller) {
        IsSeller = seller;
    }

    public int getLogin_count() {
        return Login_count;
    }

    public void setLogin_count(int login_count) {
        this.Login_count = login_count;
    }

    @Override
    public String toString() {
        return "Client{" +
                "UserName='" + UserName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Password='" + Password + '\'' +
                ", IsSeller=" + IsSeller +
                '}';
    }
}
