/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.Account;

/**
 *
 * @author Bk
 */
public class AccountDBContext extends DBContext {

    public Account getAccount(String username, String password) {
        try {
            String sql = "SELECT username,password FROM Account \n"
                    + "WHERE username = ? AND password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getPermission(String username, String url) {
        try {
            String sql = "SELECT COUNT(*) as Total FROM Account a INNER JOIN GroupAccount ga \n"
                    + "ON a.username = ga.username\n"
                    + "INNER JOIN [Group] g ON g.gid = ga.gid\n"
                    + "INNER JOIN GroupPermission gp ON gp.gid = g.gid\n"
                    + "INNER JOIN Permission p ON p.pid = gp.pid\n"
                    + "WHERE a.username = ? AND p.url = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Account addAccountOrder(String username, String invoiceid) {
        try {
            String sql = "INSERT INTO Account_Invoice (username, Invoice_ID)\n"
                    + " VALUES (?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, invoiceid);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
