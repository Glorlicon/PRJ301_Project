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
    
    public ArrayList<Product> GetProductid(String companyid){
        ArrayList<Product> pro = new ArrayList<>();
        try {
            String sql = "SELECT Product.ProductID, ProductName, Stock FROM Product INNER JOIN Company_Product \n"
                    + "ON Product.ProductID = Company_Product.ProductID \n"
                    + "WHERE CompanyID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, companyid);
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
}
