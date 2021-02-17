package Services;
import Objects.User;

public class AccountService {

    public User login(String username, String password){
        //if the username = adam or betty and the password = password
        if(username.equals("adam") || username.equals("betty") && (password.equals("password"))){
            //return the username and make the password null
            return new User(username,null);
        }
        //else return null
        return null;
    }
}
