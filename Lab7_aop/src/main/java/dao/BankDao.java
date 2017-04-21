package dao;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Daria on 04.03.2017.
 */
public class BankDao {

    private Integer accountsCount = 0;
    private HashMap<String, Integer> accountID = new HashMap<String, Integer>();
    private HashMap<Integer, Integer> moneys = new HashMap<Integer, Integer>();

    public Integer getAccountID(String name) throws IOException {
        if (accountID.containsKey(name)) {
            return accountID.get(name);
        } else {
            throw new IOException("cant find user " + name);
        }
    }

    public Integer getMoney(Integer accountID) throws IOException {
        if (moneys.containsKey(accountID)) {
            return moneys.get(accountID);
        } else {
            throw new IOException("cant find account " + accountID);
        }

    }

    public Integer addUser(String name) {
        accountID.put(name, accountsCount);
        moneys.put(accountsCount, 0);
        accountsCount++;
        return accountsCount - 1;
    }

    public void sendMoney(Integer ID1, Integer ID2, Integer amount) throws IOException {
        if (moneys.get(ID1) < amount) {
            throw new IOException("User " + ID1 + " doesn't have enough money");
        }
        moneys.put(ID1, moneys.get(ID1) - amount);
        moneys.put(ID2, moneys.get(ID2) + amount);
    }

    public Integer addMoney(Integer ID1, Integer amount) {
        moneys.put(ID1, moneys.get(ID1) + amount);
        return moneys.get(ID1);
    }


}
