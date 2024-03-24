package org.primeTimeHotel.Domain_Model_Objects;

public class AdminAccount extends Account {

    // Constructor for AdminAccount
    public AdminAccount(String username, String password, String firstName, String lastName, String phoneNumber, String email) {
        super(username, password, firstName, lastName, phoneNumber, email);
        setType(AccountType.ADMIN);
    }

    // You can add additional methods specific to AdminAccount here
}
