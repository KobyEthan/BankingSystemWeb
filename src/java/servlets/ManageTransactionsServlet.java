/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.BankingSystemEM;
import database.TransactionDA;
import domain.Transaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ManageTransactionsServlet extends HttpServlet {
private EntityManagerFactory emf;

public ManageTransactionsServlet(){
    emf = BankingSystemEM.getEmFactory();
}
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            EntityManager em = emf.createEntityManager();
    ArrayList<Transaction> allTransactions = TransactionDA.getAllTransactions(em);
    request.getSession().setAttribute("allTransactions", allTransactions);
    request.getRequestDispatcher("manage_transactions.jsp").forward(request, response);
}

}