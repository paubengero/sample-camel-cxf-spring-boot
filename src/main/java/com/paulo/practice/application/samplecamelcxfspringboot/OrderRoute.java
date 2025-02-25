package com.paulo.practice.application.samplecamelcxfspringboot;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OrderRoute extends RouteBuilder {

    @Bean(name = "jsonProvider")
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

    @Override
    public void configure() throws Exception {
        // use CXF-RS to setup the REST web service using the resource class
        // and use the simple binding style which is recommended to use
        from("cxfrs:bean:cxfEndpoint?performInvocation=true&bindingStyle=SimpleConsumer&providers=#jsonProvider")
            .routeId("cxf-springboot-route")
            // call the route based on the operation invoked on the REST web service
            .toD("direct:${header.operationName}");

        // routes that implement the REST services
        from("direct:createOrder")
            .bean("orderService", "createOrder");

        from("direct:getOrder")
            .bean("orderService", "getOrder(${header.id})");

        from("direct:updateOrder")
            .bean("orderService", "updateOrder");

        from("direct:cancelOrder")
            .bean("orderService", "cancelOrder(${header.id})");
    }
}
