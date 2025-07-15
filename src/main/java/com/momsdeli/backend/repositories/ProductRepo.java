package com.momsdeli.backend.repositories;

import com.momsdeli.backend.dto.ProductDTO;
import com.momsdeli.backend.model.Category;
import com.momsdeli.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    List<Product> findByCategory(Category category);
}
