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
public class CompanyDBContext extends DBContext{
    public ArrayList<Company> GetCompany(){
        ArrayList<Company> com = new ArrayList<>();
        try {
            String sql = "SELECT CompanyID, Company FROM Company";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
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
}
