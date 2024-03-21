package org.primeTimeHotel.Domain_Model_Objects;

import java.util.Objects;

public class ClerkAccount extends Account {

    // Constructor for ClerkAccount
    public ClerkAccount(String username, String password, String firstName, String lastName, String phoneNumber, String email) {
        super(username, password, firstName, lastName, phoneNumber, email);
    }

    // You can add additional methods specific to GuestAccount here
}

