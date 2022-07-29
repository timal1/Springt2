package com.timlad.springweb.endpoints;

import com.timlad.springweb.soap.products.*;
import com.timlad.springweb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.timlad.com/springweb/ws/products";
    private final ProductService productService;

    /*
     Пример запроса по id: POST http://localhost:8189/app/ws
        Header -> Content-Type: text/xml
     <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:t="http://www.timlad.com/springweb/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <t:getProductByIdRequest>
                   <t:id>1</t:id>
                </t:getProductByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productService.getByIdSoap(request.getId()));
        return response;
    }

    /*
        Пример запроса всех товаров: POST http://localhost:8189/app/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:t="http://www.timlad.com/springweb/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <t:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllStudentsSoap().forEach(response.getProducts()::add);
        return response;
    }
}
