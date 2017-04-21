package domain;

import dao.BankDao;

import java.io.IOException;

/**
 * Created by Daria on 04.03.2017.
 */

public class AccountManagerImpl implements AccountManager {

    private BankDao bank = new BankDao();

    public AccountManagerImpl(BankDao bank) {
        this.bank = bank;
    }

    @Override
    public Integer addUser(User user) {
        return bank.addUser(user.getName());
    }

    @Override
    public Integer getAccountID(User user) throws IOException {
        return bank.getAccountID(user.getName());
    }

    @Override
    public Integer getMoney(User user) throws IOException {
        return bank.getMoney(getAccountID(user));
    }

    @Override
    public Integer addMoney(User user, Integer amount) throws IOException {
        return bank.addMoney(getAccountID(user), amount);
    }

    @Override
    public void sendMoney(User user1, User user2, Integer amount) throws IOException {
        bank.sendMoney(getAccountID(user1), getAccountID(user2), amount);
    }
}
