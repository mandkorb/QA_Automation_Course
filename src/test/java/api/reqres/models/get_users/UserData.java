package api.reqres.models.get_users;

import lombok.Getter;

@Getter
public class UserData {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
