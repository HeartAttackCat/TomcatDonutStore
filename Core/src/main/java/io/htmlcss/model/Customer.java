/**
 * A container for customer information.
 */
package io.htmlcss.model;

public class Customer {
    
    private String firstName = "";
    private String lastName = "";
    private String address = "";
    private String phoneNumber = "";
    private String email = "";
    private String cardID = ""; // card used to purchase. Int was definitely the wrong data type.
    private int zipCode = 0;

    /**
     * Basic constructor for class.
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param email
     * @param cardID
     * @param zipCode
     */
    public Customer(String firstName, String lastName, String address, String phoneNumber, String email, String cardID, int zipCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cardID = cardID;
        this.zipCode = zipCode;

    }

    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }

    public String getAddress(){
        return this.address;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getEmail(){
        return this.email;
    }

    public int getZipCode(){
        return this.zipCode;
    }
    
    public String getCardID(){
        return this.cardID;
    }
}
