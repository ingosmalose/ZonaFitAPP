package com.zonafitApp.data;

import com.zonafitApp.conection.Conection;
import com.zonafitApp.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO implements IClienteDAO {

    @Override
    public List<Customer> listCustomer() {
        List<Customer> customers = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conection.getConection();
        var sql = "SELECT id, firstName, lastName, member FROM customers ORDER BY id ";
        try {
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setMenber(rs.getInt("member"));
                customers.add(customer);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        } finally {
            try {
                Conection.closeConection(con);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return customers;
    }

    @Override
    public boolean searchCustomerById(Customer customer) {
        PreparedStatement ps;
        ResultSet rs;
        var con = Conection.getConection();
        var sql = "SELECT * FROM customers WHERE  id = ?";
        var info = new Customer();
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                customer.setFirstName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setMenber(rs.getInt("member"));
                return true;
            }

        }catch (Exception e){
            System.out.println("Error Detail : "+e.getMessage());
        }
        finally {
            try {
                Conection.closeConection(con);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        PreparedStatement ps;
        Connection con = Conection.getConection();
        var sql = "INSERT INTO customers (firstName, lastName, member) VALUES(?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setInt(3, customer.getMember());
            ps.execute();
            return true;
        }
        catch (Exception e){
            System.out.println("Error adding customer : "+ sql + "\n* "+e.getMessage());
        }
        finally {
            try {
                Conection.closeConection(con);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        Connection con = Conection.getConection();
        PreparedStatement ps;
        var sql = "UPDATE customers SET firstName =?, lastName =?, member =? WHERE id =?";
        try{
           ps=con.prepareStatement(sql);
           ps.setString(1, customer.getFirstName());
           ps.setString(2, customer.getLastName());
           ps.setInt(3,customer.getMember());
           ps.setInt(4,customer.getId());
           ps.execute();
           return true;
        }catch(Exception e){
            System.out.println("Error updating customer information : "+e);
        }finally {
            try {
                Conection.closeConection(con);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        Connection con = Conection.getConection();
        PreparedStatement ps;
        var sql = "DELETE FROM customers WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error deleted : "+e.getMessage());
        }finally {
            try {
                Conection.closeConection(con);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        return false;
    }
    
    public static void main(String[] args){
        //  Testing list customers
        IClienteDAO customerDao = new CustomerDAO();
//        System.out.println("List customer");
//        var customer = customerDao.listCustomer();
//        customer.forEach(System.out::println);

        //  Search by Id
//        var customer = new Customer(1);
//        System.out.println("Searching customer::.");
//        System.out.println(customer);
//        var find = customerDao.searchCustomerById(customer);
//        if(find){
//            System.out.println("Customer find sucessfull.");
//            System.out.println(customer);
//        }
//        else{
//            System.out.println("d");
//        }
        //  Testing adding new customer
//        var customer = new Customer("Manuel", "Jaramillo", 6);
//        var result = customerDao.addCustomer(customer);
//        if(result){
//            System.out.println("Customer adding successfully");
//        }
//        else{
//            System.out.println("Information typed in has some error. Please try it later, thank you.");
//        }
        //  testing update customer
        var customers = customerDao.listCustomer();
        customers.forEach(System.out::println);

//        var customer = new Customer(1,"Oscar Mauricio", "Lopez Segura", 1);
//        var result = customerDao.updateCustomer(customer);
//        if(result){
//            System.out.println("Customer updating successfully");
//        }
        //  Delete customer
        var customer = new Customer(8);
        var result = customerDao.deleteCustomer(customer);
        if(result){
            System.out.println("User deleted successfully.");
        }else{
            System.out.println("User is not existing in our record.");
        }

        //  listing customer
        customers = customerDao.listCustomer();
        customers.forEach(System.out::println);

    }
}
