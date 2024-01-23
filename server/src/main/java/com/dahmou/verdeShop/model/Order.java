package com.dahmou.verdeShop.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public record Order(int id, List<JsonNode> products, JsonNode address, String date,int total, int userId) {
}
