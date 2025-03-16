package de.dhbw.karlsruhe.getraenkeabrechnung;

import de.dhbw.karlsruhe.getraenkeabrechnung.commands.CommandRegistry;
import de.dhbw.karlsruhe.getraenkeabrechnung.commands.HelpCommand;
import de.dhbw.karlsruhe.getraenkeabrechnung.commands.LoginCommand;
import de.dhbw.karlsruhe.getraenkeabrechnung.commands.LogoutCommand;
import de.dhbw.karlsruhe.getraenkeabrechnung.io.cli.Cli;

public class Getraenkeabrechnung {
    private User user;

    public Getraenkeabrechnung() {
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
        this.user = user;
    }

    public void logout() {
        user = null;
    }

    public User getUser() {
        return user;
    }
}
