package com.example;

import com.example.grpc.*;
import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BookClientService {

    @Inject
    GrpcBookClient grpcBookClient;


    public Uni<Void> addBook(Long id, String title, String author) {
        BookRequest request = BookRequest.newBuilder()
                .setId(id)
                .setTitle(title)
                .setAuthor(author)
                .build();

        return Uni.createFrom().emitter(emitter -> {
            grpcBookClient.getBlockingStub().addBook(request);
            /*grpcBookClient.getStub().addBook(request, new StreamObserver<Empty>() {
                @Override
                public void onNext(Empty empty) {
                    emitter.complete(null);
                }

                @Override
                public void onError(Throwable t) {
                    emitter.fail(t);
                }

                @Override
                public void onCompleted() {

                }
            });*/
        });
    }

    public Uni<Void> streamBooks() {
        BookStreamRequest request = BookStreamRequest.newBuilder()
                .setOffset(0)
                .build();

        return Uni.createFrom().emitter(emitter -> {
            grpcBookClient.getBlockingStub().streamBooks(request);
            /*grpcBookClient.getStub().streamBooks(request, new StreamObserver<Book>() {
                @Override
                public void onNext(Book book) {
                    System.out.println("Received book: " + book.getTitle());
                }

                @Override
                public void onError(Throwable t) {
                    emitter.fail(t);
                }

                @Override
                public void onCompleted() {
                    emitter.complete(null);
                }
            });*/
        });
    }
}