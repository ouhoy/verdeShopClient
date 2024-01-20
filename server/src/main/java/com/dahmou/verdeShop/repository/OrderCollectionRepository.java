package com.dahmou.verdeShop.repository;

import com.dahmou.verdeShop.model.Address;
import com.dahmou.verdeShop.model.Order;
import com.dahmou.verdeShop.model.Product;
import com.dahmou.verdeShop.util.ResultSetHelperEnum;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.postgresql.util.PGobject;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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
                return new Order(rs.getInt("id"), productNodes, address, rs.getString("date"), rs.getInt("user_id"));
            } else {
                // Handle string data (e.g., log a warning or store as a single string)
                return new Order(rs.getInt("id"), List.of(products), address, rs.getString("date"), rs.getInt("user_id"));
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
            String sql = "INSERT INTO Orders (products, address, date, user_id) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, objectMapper.writeValueAsString(order.products()), objectMapper.writeValueAsString(order.address()), order.date(), order.userId());

        } catch (Exception e) {
            // Handle the exception (log it or throw a specific exception)
            e.printStackTrace();
        }
    }




}
