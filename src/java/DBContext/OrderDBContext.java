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
public class OrderDBContext extends DBContext {

    public ArrayList<Order> OrderPaging(int page, int record, String ivd) {
        ArrayList<Order> Orders = new ArrayList<>();
        try {
            String sql = "SELECT Invoice_ID, CompanyID, ProductID, Amount, Cost, ImportDate FROM [Order]\n";
            if (!ivd.equalsIgnoreCase("0")) {
                sql += "WHERE Invoice_ID = ?\n";
            }

            sql += "ORDER BY Invoice_ID \n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY\n";

            PreparedStatement stm = connection.prepareStatement(sql);
            if (!ivd.equalsIgnoreCase("0")) {
                stm.setString(1, ivd);
                stm.setInt(2, (page - 1) * 10);
                stm.setInt(3, record);
            } else {
                stm.setInt(1, (page - 1) * 10);
                stm.setInt(2, record);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order Or = new Order();
                Company Cp = new Company();
                Product Pp = new Product();
                Or.setInvoice_id(rs.getString("Invoice_ID"));
                Cp.setCompanyid(rs.getString("CompanyID"));
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

    public int GetNoOfRecord() {
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) 'Total' FROM [Order]";
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

    public ArrayList<Order> GetSerperateInvoice() {
        ArrayList<Order> Orders = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT Invoice_ID FROM [Order]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                Order Or = new Order();
                Or.setInvoice_id(rs.getString("Invoice_ID"));
                Orders.add(Or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Orders;
    }

    public int GetNoOfInvoice() {
        int total = 0;
        try {
            String sql = "SELECT COUNT(DISTINCT Invoice_ID) AS 'Total' FROM [Order]";
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

    public Order AddOrder(String invoiceid, String companyid, String productid, int amount, float cost, Date importdate) {
        try {
            String sql = "INSERT INTO [Order] (Invoice_ID, CompanyID, ProductID, Amount, Cost, ImportDate)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
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

    public Order getOrder(String invoiceid, String companyid, String productid, int amount, float cost, String importdate) {
        try {
            String sql = "SELECT FROM [Order]\n"
                    + "WHERE Invoice_ID = ? AND CompanyID = ? AND ProductID = ? AND Amount = ? AND Cost = ? AND ImportDate = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, invoiceid);
            stm.setString(2, companyid);
            stm.setString(3, productid);
            stm.setInt(4, amount);
            stm.setFloat(5, cost);
            stm.setString(6, importdate);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Order Or = new Order();
                Company Cp = new Company();
                Product Pp = new Product();
                Or.setInvoice_id(rs.getString("Invoice_ID"));
                Cp.setCompanyid(rs.getString("CompanyID"));
                Or.setC(Cp);
                Pp.setProductid(rs.getString("ProductID"));
                Or.setP(Pp);
                Or.setAmount(rs.getInt("Amount"));
                Or.setCost(rs.getFloat("Cost"));
                Or.setImportDate(rs.getDate("ImportDate"));
                return Or;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateOrder(Order o) {
        String sql = "UPDATE [Order]\n"
                + "SET [CompanyID] = ?,\n"
                + "	[ProductID] = ?,\n"
                + "	[Amount] = ?,\n"
                + "	[Cost] = ?,\n"
                + "	[ImportDate] = ?\n"
                + "	WHERE [Invoice_ID] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, o.getC().getCompanyid());
            stm.setString(2, o.getP().getProductid());
            stm.setInt(3, o.getAmount());
            stm.setFloat(4, o.getCost());
            stm.setString(5, o.getInvoice_id());
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

}
