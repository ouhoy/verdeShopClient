package com.dahmou.verdeShop.repository;

import com.dahmou.verdeShop.model.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.aspectj.weaver.ast.Or;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderCollectionRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderCollectionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Order mapRow(ResultSet rs, int rowNum) throws SQLException {


        ObjectMapper objectMapper = new ObjectMapper();


        try {
//            JsonNode products = objectMapper.readTree(rs.getString("products"));
            JsonNode address = objectMapper.readTree(rs.getString("address"));

            JsonNode products = objectMapper.readTree(rs.getString("products"));
            if (products.isArray()) {
                List<JsonNode> productNodes = objectMapper.convertValue(products, new TypeReference<List<JsonNode>>() {});
                return new Order(rs.getInt("id"), productNodes, address, rs.getString("date"),rs.getInt("total"), rs.getInt("user_id"));
            } else {
                // Handle string data (e.g., log a warning or store as a single string)
                return new Order(rs.getInt("id"), List.of(products), address, rs.getString("date"), rs.getInt("total"),rs.getInt("user_id"));
            }


        } catch (Exception e) {
            throw new SQLException("Error mapping row to Order", e);
        }


    }


    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM Orders";
        return jdbcTemplate.query(sql, OrderCollectionRepository::mapRow);
    }

    public void createOrder(Order order) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String sql = "INSERT INTO Orders (products, address, date,total, user_id) VALUES (?,?, ?, ?, ?)";
            jdbcTemplate.update(sql, objectMapper.writeValueAsString(order.products()), objectMapper.writeValueAsString(order.address()), order.date(),order.total(), order.userId());

        } catch (Exception e) {
            // Handle the exception (log it or throw a specific exception)
            e.printStackTrace();
        }
    }

    public void updateOrder(int id, Order order) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String sql = "UPDATE Orders SET  products=?, address=?, date=?,total=?, user_id=? WHERE id=?";
            jdbcTemplate.update(sql, objectMapper.writeValueAsString(order.products()), objectMapper.writeValueAsString(order.address()), order.date(), order.total(), order.userId(), id);

        } catch (Exception e) {
            // Handle the exception (log it or throw a specific exception)
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        String sql = "DELETE FROM Orders WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Order getOrder(int id) {
        try {
            String sql = "SELECT * FROM Orders WHERE id=?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, OrderCollectionRepository::mapRow);
        }catch (EmptyResultDataAccessException e) {
            // Handle empty result - throw a custom exception or return null
            return null; // Or throw a custom exception
        }
    }




}
