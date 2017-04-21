import domain.AccountManager;
import domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daria on 04.03.2017.
 */
public class Main {
    //простой банк. вкладчики и их счета, можно пополнять счет, переводить деньги со счета на счет

    static final int N = 10000;
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        AccountManager account = ctx.getBean(AccountManager.class);

            List<User> users = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                User user = new User(new Integer((int)(Math.random() * 10000)).toString());
                users.add(user);
                account.addUser(user);
            }
            for (User user: users) {
                try {
                    account.addMoney(user, (int)(Math.random() * 100000));
                } catch (IOException ignored) {
                }
            }
            for (int i = 0; i < 2 * N; i++) {
                int id1 = (int)(Math.random() * 100000) % N;
                int id2 = (int)(Math.random() * 100000) % N;
                try {
                    account.sendMoney(users.get(id1), users.get(id2), (int)(Math.random() * 10000));
                } catch (IOException ignored) {
                }
            }
    }

}
