package de.dhbw.karlsruhe.getraenkeabrechnung.state;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;

public class ApplicationState {

    private User loggedInUser;

    public ApplicationState() {

    }

    public void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void clearLoggedInUser() {
        loggedInUser = null;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

}
