package sabria.demo.annotationdemo.runtime;

/**
 * Created by xiongwei,An Android project Engineer.
 * Date:2016-01-11  14:30
 * Base on Meilimei.com (PHP Service)
 * Describe:
 * Version:1.0
 * Open source
 */
public class PasswordUtils {

    @UserInfo(id = "47", description = "Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }
}
