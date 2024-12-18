package com.thesis.serverfurnitureecommerce.internal.services.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.serverfurnitureecommerce.domain.request.ProductSearchRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.ImageRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.ProductRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.ReviewRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.UserRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.custom.product.IProductRepositoryCustom;
import com.thesis.serverfurnitureecommerce.model.dto.ImageDTO;
import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import com.thesis.serverfurnitureecommerce.model.dto.ReviewDTO;
import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.pkg.mapper.ImageMapper;
import com.thesis.serverfurnitureecommerce.pkg.mapper.ProductMapper;
import com.thesis.serverfurnitureecommerce.pkg.mapper.ReviewMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    IProductRepositoryCustom productRepositoryCustom;
    ProductMapper productMapper;
    ImageRepository imageRepository;
    ReviewRepository reviewRepository;
    ImageMapper imageMapper;
    ObjectMapper objectMapper;
    UserRepository userRepository;
    ReviewMapper reviewMapper;


    @Override
    public List<ProductDTO> findAll() {
        log.info("Invoke to service find all product");
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductDTO> productDTOS = productEntities.stream()
                .map(productMapper::convertToDTO)
                .collect(Collectors.toList());
        productDTOS.forEach(productDTO -> productDTO.setImages(getImagesByProductID(productDTO.getId())));
        return productDTOS;
    }

    @Override
    public List<ProductDTO> findByMultiFields(Map<String, Object> productSearchRequest) {
        ProductSearchRequest searchRequest = objectMapper.convertValue(productSearchRequest, ProductSearchRequest.class);
        List<ProductEntity> productEntities = productRepositoryCustom.findAllMultiField(searchRequest);
        List<ProductDTO> productDTOS = productEntities.stream()
                .map(productMapper::convertToDTO)
                .collect(Collectors.toList());
        productDTOS.forEach(productDTO -> productDTO.setImages(getImagesByProductID(productDTO.getId())));
        return productDTOS;
    }


    @Override
    public ProductDTO findByProductID(int productID) {
        log.info("Invoke to service find product by id");
        ProductEntity productEntity = productRepository.findById(productID)
                .filter(product -> product.getIsActive() == 1)
                .orElse(null);
        List<ImageDTO> imageDTOS = getImagesByProductID(productID);
        List<ReviewDTO> reviewDTOS = getReviewByProductID(productID);
        ProductDTO productDTO = productMapper.convertToDTO(productEntity);
        productDTO.setImages(imageDTOS);
        productDTO.setReviewDTO(reviewDTOS);
        return productDTO;
    }


    private List<ImageDTO> getImagesByProductID(Integer productID) {
        return imageRepository.getImagesByProductID(productID)
                .stream()
                .map(imageMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    private List<ReviewDTO> getReviewByProductID(Integer productID) {
        return reviewRepository.getReviewByProductID(productID)
                .stream()
                .map(reviewMapper::convertToDTO)
                .collect(Collectors.toList());
    }

}
