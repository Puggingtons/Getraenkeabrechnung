package de.dhbw.karlsruhe.getraenkeabrechnung;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.UserDatabase;

public class ThirstyCalc {
    private User loggedInUser;

    private UserDatabase userDatabase;

    public ThirstyCalc() {
        loggedInUser = null;
        userDatabase = new UserDatabase();
        greet();
    }

    private void greet() {
        System.out.println("""
                
                  _____    _   _      ___      ____     ____     _____   __   __   ____     _       _        ____  
                 |_ \" _|  |'| |'|    | \"_|  U |  _\"\\ u / __\"| u |_ \" _|  \\ \\ / /U /\"___|U  /\"\\  u  |\"|    U /\"___| 
                   | |   /| |_| |\\    | |    \\| |_) |/<\\___ \\/    | |     \\ V / \\| | u   \\/ _ \\/ U | | u  \\| | u   
                  /| |\\  U|  _  |u    | |     |  _ <   u___) |   /| |\\   U_|\"|_u | |/__  / ___ \\  \\| |/__  | |/__  
                 u |_|U   |_| |_|   U/| |\\u   |_| \\_\\  |____/>> u |_|U     |_|    \\____|/_/   \\_\\  |_____|  \\____| 
                 _// \\\\_  //   \\\\.-,_|___|_,-.//   \\\\_  )(  (__)_// \\\\_.-,//|(_  _// \\\\  \\\\    >>  //  \\\\  _// \\\\  
                (__) (__)(_\" )(\"_)\\_)-' '-(_/(__)  (__)(__)    (__) (__)\\_) (__)(__)(__)(__)  (__)(_\" )(\"_)(__)(__) 
                
                """);
    }

    public void login(User user) {
        if (!userDatabase.userExists(user.getUsername())) {
            // todo handle user does not exist
            System.out.println("user does not exist");
            return;
        }

        if (loggedInUser != null) {
            // todo handle user is already logged in
            System.out.println("user already logged in");
            return;
        }

        this.loggedInUser = user;
    }

    public void logout() {
        loggedInUser = null;
    }

    public void createNewUser(User user) {
        userDatabase.registerNewUser(user);
    }

    public User getUser() {
        return loggedInUser;
    }

    UserDatabase getUserDatabase() {
        return userDatabase;
    }
}
