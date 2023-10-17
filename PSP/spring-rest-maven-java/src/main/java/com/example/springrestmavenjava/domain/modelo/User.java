package com.example.springrestmavenjava.domain.modelo;

import lombok.Data;

import java.util.List;


public record User(int id, String username, String password, List<Moto> motos) {

}
