package domain;

import java.io.IOException;

/**
 * Created by Daria on 04.03.2017.
 */
public interface AccountManager {

    Integer addUser(User user);
    Integer getAccountID(User user) throws IOException;
    Integer getMoney(User user) throws IOException;
    Integer addMoney(User user, Integer amount) throws IOException;
    void sendMoney(User user1, User user2, Integer amount) throws IOException;

}
