/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBContext;

import Entity.Company;
import Entity.Order;
import Entity.Product;
import java.sql.Date;
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
public class OrderDBContext extends DBContext{
    public ArrayList<Order> OrderPaging(int page, int record){
        ArrayList<Order> Orders = new ArrayList<>();
        try {
            String sql = "SELECT Invoice_ID, CompanyID, ProductID, Amount, Cost, ImportDate FROM [Order]" +
                         "ORDER BY Invoice_ID \n" +
                         "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (page-1)*10);
            stm.setInt(2, record);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Order Or = new Order();
                Company Cp = new Company();
                Product Pp = new Product();
                Or.setInvoice_id(rs.getString("Invoice_ID"));
                Cp.setCompany(rs.getString("CompanyID"));
                Or.setC(Cp);
                Pp.setProductid(rs.getString("ProductID"));
                Or.setP(Pp);
                Or.setAmount(rs.getInt("Amount"));
                Or.setCost(rs.getFloat("Cost"));
                Or.setImportDate(rs.getDate("ImportDate"));
                Orders.add(Or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Orders;
    }
    
    public int GetNoOfRecord(){
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) 'Total' FROM [Order]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            if(rs.next())
            {
              total = rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public Order AddOrder(String invoiceid, String companyid, String productid, int amount, float cost, Date importdate){
        try{
        String sql = "INSERT INTO [Order] (Invoice_ID, CompanyID, ProductID, Amount, Cost, ImportDate)\n" +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, invoiceid);
            stm.setString(2, companyid);
            stm.setString(3, productid);
            stm.setInt(4, amount);
            stm.setFloat(5, cost);
            stm.setDate(6, importdate);
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 

}
