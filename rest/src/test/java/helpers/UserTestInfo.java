package helpers;

import com.nntu.containers.info.UserRole;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserTestInfo {
    private String name;
    private String lastName;
    private String email;
    private UserRole role;
    private String password;

    public String authorizationString() {
        return "?email=" + email + '&' +
                "password=" + password;
    }

    @Override
    public String toString() {
        return "?name='" + name + '&' +
                "lastName='" + lastName + '&' +
                "email='" + email + '&' +
                "role=" + role + '&' +
                "password='" + password;
    }
}
