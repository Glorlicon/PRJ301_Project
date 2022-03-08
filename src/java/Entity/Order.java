/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;



/**
 *
 * @author BK
 */
public class Order {
    private String Invoice_ID;
    private Company c;
    private Product p;
    private int Amount;
    private float Cost;
    private Date ImportDate;

    public String getInvoice_ID() {
        return Invoice_ID;
    }

    public void setInvoice_ID(String Invoice_ID) {
        this.Invoice_ID = Invoice_ID;
    }

    public Company getC() {
        return c;
    }

    public void setC(Company c) {
        this.c = c;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public float getCost() {
        return Cost;
    }

    public void setCost(float Cost) {
        this.Cost = Cost;
    }

    public Date getImportDate() {
        return ImportDate;
    }

    public void setImportDate(Date ImportDate) {
        this.ImportDate = ImportDate;
    }


    
}
