/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBContext;

import Entity.Product;
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
public class ProductDBContext extends DBContext{
    public ArrayList<Product> GetProduct(){
        ArrayList<Product> pro = new ArrayList<>();
        try {
            String sql = "SELECT ProductID, ProductName, Stock FROM Product";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Product p = new Product();
                p.setProductname(rs.getString("ProductName"));
                p.setProductid(rs.getString("ProductID"));
                p.setStock(rs.getInt("Stock"));
                pro.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }
    
    public ArrayList<Product> getproduct(String companyid){
        ArrayList<Product> pro = new ArrayList<>();
        try {
            String sql = "SELECT Product.ProductID, ProductName, Stock FROM Product INNER JOIN Company_Product \n"
                    + "ON Product.ProductID = Company_Product.ProductID \n";
            if (!companyid.equalsIgnoreCase("-1")){
                sql += "WHERE CompanyID = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (!companyid.equalsIgnoreCase("-1")){
                stm.setString(1, companyid);
            }
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Product p = new Product();
                p.setProductname(rs.getString("ProductName"));
                p.setProductid(rs.getString("ProductID"));
                p.setStock(rs.getInt("Stock"));
                pro.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }
    
    public ArrayList<Product> ProductPaging(int page, int record) {
        ArrayList<Product> prods = new ArrayList<>();
        try {
            String sql = "SELECT ProductID, ProductName, Stock FROM Product\n"
                    + "ORDER BY ProductID \n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY\n";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, (page - 1) * 10);
            stm.setInt(2, record);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product pd = new Product();
                pd.setProductid(rs.getString("ProductID"));
                pd.setProductname(rs.getString("ProductName"));
                pd.setStock(rs.getInt("Stock"));
                prods.add(pd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prods;
    }
    
    public int GetNoOfRecord() {
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) 'Total' FROM [Product]";
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
    
    public Product addProduct(String productid, String productname) {
        try {
            String sql = "INSERT INTO Product (ProductID, ProductName, Stock)\n"
                    + "VALUES (?, ?, 0)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, productid);
            stm.setString(2, productname);
            stm.setInt(3, 0);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
