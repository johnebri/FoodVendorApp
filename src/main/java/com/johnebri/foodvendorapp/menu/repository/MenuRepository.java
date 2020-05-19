package com.johnebri.foodvendorapp.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnebri.foodvendorapp.menu.data.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
