package org.primeTimeHotel.Domain_Model_Objects;

import java.util.Objects;

public class GuestAccount extends Account {

    // Constructor for GuestAccount
    public GuestAccount(String username, String password, String firstName, String lastName, String phoneNumber, String email) {
        super(username, password, firstName, lastName, phoneNumber, email);
    }

    // You can add additional methods specific to GuestAccount here
}
