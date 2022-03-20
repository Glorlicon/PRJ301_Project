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
import java.util.ArrayList;

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

    public ArrayList<Account> getaccounts() {
        ArrayList<Account> acc = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT a.username, gname FROM Account a INNER JOIN GroupAccount ga\n"
                    + "ON a.username = ga.username\n"
                    + "INNER JOIN [Group] g ON g.gid = ga.gid\n"
                    + "INNER JOIN GroupPermission gp ON gp.gid = g.gid\n"
                    + "INNER JOIN Permission p ON p.pid = gp.pid";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUsername(rs.getString("username"));
                a.setPermission(rs.getString("gname"));
                acc.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
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

    public Account addpermission(String username, int gid) {
        try {
            String sql = "INSERT INTO [GroupAccount] (username, gid)\n"
                    + " VALUES (?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setInt(2, gid);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    
    public Account addAccount(String username, String password) {
        try {
            String sql = "INSERT INTO Account (username, password)\n"
                    + " VALUES (?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int GetNoOfRecord() {
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) 'Total' FROM [Account]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                total = rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
}
}
