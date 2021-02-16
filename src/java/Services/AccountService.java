package Services;
import Objects.User;

public class AccountService {

    public User login(String username, String password){
        
        if(username.equals("adam") || username.equals("betty")){
            if (password.equals("password")) {
                return new User(username,null);
            }
        }
        return null;
    }
}
