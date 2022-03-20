/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBContext;

import Entity.Company;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BK
 */
public class CompanyDBContext extends DBContext {

    public ArrayList<Company> GetCompany() {
        ArrayList<Company> com = new ArrayList<>();
        try {
            String sql = "SELECT CompanyID, Company FROM Company";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Company c = new Company();
                c.setCompany(rs.getString("Company"));
                c.setCompanyid(rs.getString("CompanyID"));
                com.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return com;
    }

    public ArrayList<Company> CompanyPaging(int page, int record) {
        ArrayList<Company> coms = new ArrayList<>();
        try {
            String sql = "SELECT CompanyID, Company FROM Company\n"
                    + "ORDER BY CompanyID \n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY\n";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, (page - 1) * 10);
            stm.setInt(2, record);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Company cp = new Company();
                cp.setCompanyid(rs.getString("CompanyID"));
                cp.setCompany(rs.getString("Company"));
                coms.add(cp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coms;
    }
    
    public int GetNoOfRecord() {
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) 'Total' FROM [Company]";
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
    
    public void deleteCompany(String cid) {
        String sql = "DELETE FROM [Company]\n"
                + "WHERE [CompanyID] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, cid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void updateCompany(Company c) {
        String sql = "UPDATE [Company]\n"
                + "SET [CompanyID] = ?,\n"
                + "	[Company] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, c.getCompanyid());
            stm.setString(2, c.getCompany());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
    
    public Company addCompany(String companyid, String companyname) {
        try {
            String sql = "INSERT INTO COMPANY (CompanyID, Company)\n"
                    + "VALUES (?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, companyid);
            stm.setString(2, companyname);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
