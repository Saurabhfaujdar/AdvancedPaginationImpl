package org.example.DAO;

import org.example.Model.PageURL;
import org.example.Model.ProblemModel;
import org.example.Response.Response;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDAO {
    static final String JDBC = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/Problems?sslMode=DISABLED";
    static final String uname = "root";
    static final String pass = "";
    static final int limit = 10;

    public Response getProblems(int pageNumber) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC);
        Response response = new Response();
        List<ProblemModel> problemModels = new ArrayList<>();
        PageURL nextPage = new PageURL(),previousPage = new PageURL();
        try (Connection conn = DriverManager.getConnection(url, uname, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT id from Problems Where id >= ? ORDER BY id LIMIT 4")) {
            stmt.setInt(1, pageNumber);
            List<Integer> temp = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                int i=1;
                while (rs.next()) {
                    if(i<=2) {
                        temp.add(rs.getInt(1));
                    }
                    if(i==3) {
                        nextPage.setId(rs.getInt(1));
                    }
                    i++;
                }
            }
            StringBuilder builder = new StringBuilder();
            for(int i=0;i<2;i++) {
                builder.append("?,");
            }
                try(PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from Problems Where id IN (" + builder.deleteCharAt( builder.length() -1 ).toString() + ") ORDER BY id LIMIT 2")) {
                   for(int i=0;i<2;i++) {
                       preparedStatement.setInt(i+1, temp.get(i));
                   }
                    try(ResultSet rs = preparedStatement.executeQuery()) {
                        while (rs.next()) {
                            problemModels.add(new ProblemModel(rs.getInt("id"), rs.getString("authorName"), rs.getString("problemName")));
                        }
                    }
                }
            }
        try (Connection conn = DriverManager.getConnection(url, uname, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT id from Problems Where id < ? ORDER BY id DESC LIMIT 2")) {
            stmt.setInt(1, pageNumber);
            List<Integer> temp = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                int i = 1;
                while (rs.next()) {
                    System.out.println(rs.getInt("id"));
                    if (i == 2) {
                        previousPage.setId(rs.getInt("id"));
                    }
                    i++;
                }
            }
        }
        response.setProblemModels(problemModels);
        response.setNextPage(nextPage);
        response.setPreviousPage(previousPage);
        return response;
    }
}

