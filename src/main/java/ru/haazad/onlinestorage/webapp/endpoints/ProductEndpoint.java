package ru.haazad.onlinestorage.webapp.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.haazad.onlinestorage.webapp.services.ProductService;
import ru.haazad.onlinestorage.webapp.soap.products.GetAllProductsRequest;
import ru.haazad.onlinestorage.webapp.soap.products.GetAllProductsResponse;
import ru.haazad.onlinestorage.webapp.soap.products.GetProductByIdRequest;
import ru.haazad.onlinestorage.webapp.soap.products.GetProductByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.haazad.ru/soap/products";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productService.getProductById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProduct().forEach(response.getProducts()::add);
        return response;
    }

}
