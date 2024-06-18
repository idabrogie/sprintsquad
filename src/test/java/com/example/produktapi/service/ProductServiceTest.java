package com.example.produktapi.service;

import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import com.example.produktapi.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.produktapi.exception.BadRequestException;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;


    @Test
    public void testAddProductWhenTitleExists() {

        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);

        Product existingProduct = new Product("Existing Title", 450.0, "Action", "babla", "picture.jpg");
        Product newProduct = new Product("Existing Title", 450.0, "Action", "babla", "picture.jpg");

        when(productRepository.findByTitle("Existing Title")).thenReturn(Optional.of(existingProduct));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            productService.addProduct(newProduct);
        });
        System.out.println("Caught exception: " + exception.getMessage());
        assertEquals("En produkt med titeln: Existing Title finns redan", exception.getMessage());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void testAddProductWhenTitleDoesNotExist() {

        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);

        Product newProduct = new Product("New Title", 450.0, "Action", "babla", "picture.jpg");

        when(productRepository.findByTitle("New Title")).thenReturn(Optional.empty());

        when(productRepository.save(newProduct)).thenReturn(newProduct);

        Product result = productService.addProduct(newProduct);

        assertEquals(newProduct, result);

        verify(productRepository, times(1)).save(newProduct);
    }

    @Test
    void testUpdateProductSuccess() {
        product = new Product();
        product.setId(1);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product, 1);

        assertNotNull(updatedProduct);
        verify(productRepository).findById(1);
        verify(productRepository).save(product);
    }

    @Test
    void testUpdateProductNotFound() {
        product = new Product();
        product.setId(1);
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            productService.updateProduct(product, 1);
        });

        verify(productRepository).findById(1);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testDeleteProductSuccess() {
        product = new Product();
        product.setId(1);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        productService.deleteProduct(1);

        verify(productRepository).findById(1);
        verify(productRepository).deleteById(1);
    }

    @Test
    void testDeleteProductNotFound() {
        product = new Product();
        product.setId(1);
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            productService.deleteProduct(1);
        });

        verify(productRepository).findById(1);
        verify(productRepository, never()).deleteById(1);
    }
}