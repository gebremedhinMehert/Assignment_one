package Main;

import java.sql.Timestamp;

/**
 * Customer.java
 *
 * @author g106108
 * @since 9/11/18
 */
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Address add;
    private Timestamp updateTime;

    public Customer(Address add) {
        this.add = add;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAdd() {
        return add;
    }

    public void setAdd(Address add) {
        this.add = add;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Customer))
            return false;

        return (id == ((Customer) o).getId());
    }

    @Override
    public int hashCode() {


        return id;
    }


    @Override
    public String toString() { return String.format("Customer id #" + id +"\n" +
                 lastName + " " +firstName + "\n" +
                 email + "\n" +
                 add.getAddress() + "\n" +
                 add.getCity() + " " + add.getState() +" " + add.getZipCode() + "\n" + "\n");
    }
}