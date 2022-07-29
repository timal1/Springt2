package com.timlad.springweb.service;

import com.timlad.springweb.dto.ProductDto;
import com.timlad.springweb.entities.CategoryProduct;
import com.timlad.springweb.entities.Product;
import com.timlad.springweb.exeptions.ResourceNotFoundException;
import com.timlad.springweb.repository.ProductRepository;
import com.timlad.springweb.repository.specifications.CategoriesProductsSpecifications;
import com.timlad.springweb.repository.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public static final Function<Product, com.timlad.springweb.soap.products.Product> functionEntityToSoap = se -> {
        com.timlad.springweb.soap.products.Product s = new com.timlad.springweb.soap.products.Product();
        s.setId(se.getId());
        s.setTitle(se.getTitle());
        s.setPrice(se.getPrice());
        return s;
    };

    public Page<Product> find(Double minPrice, Double maxPrice, String titlePart, Integer page, String categoryTitle) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(ProductsSpecifications.titleLike(titlePart));
        }
        if (categoryTitle != null) {
            spec = spec.and(ProductsSpecifications.categoryLike(categoryTitle));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Unable to update, the product is not found in the database, id: "
                        + productDto.getId()));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return product;
    }


    public List<com.timlad.springweb.soap.products.Product> getAllStudentsSoap() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public com.timlad.springweb.soap.products.Product getByIdSoap(Long Id) {
        return productRepository.findById(Id).map(functionEntityToSoap).get();
    }
}
