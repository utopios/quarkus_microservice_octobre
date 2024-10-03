package com.example;


import com.example.grpc.BookServiceGrpc;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.grpc.GrpcService;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class GrpcBookClient {

    @GrpcClient("book-service")
    BookServiceGrpc.BookServiceBlockingStub bookServiceBlockingStub;

    //@GrpcClient("book-service")
    BookServiceGrpc.BookServiceStub bookServiceStub;

    public BookServiceGrpc.BookServiceBlockingStub getBlockingStub() {
        return bookServiceBlockingStub;
    }

    public BookServiceGrpc.BookServiceStub getStub() {
        return bookServiceStub;
    }
}