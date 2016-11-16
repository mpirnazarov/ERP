package com.lgcns.erp.tapps.viewModel.HR;

/**
 * Created by Dell on 27-Oct-16.
 */
public class HrUserslistViewModel {
    private String firstName;
    private String lastName;

    public HrUserslistViewModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
}
