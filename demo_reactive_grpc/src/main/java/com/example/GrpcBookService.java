package com.example;


import com.example.grpc.Book;
import com.example.BookStreamRequest;
import com.example.service.LogicBookService;
import io.grpc.stub.StreamObserver;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.concurrent.atomic.AtomicBoolean;

@ApplicationScoped
public class GrpcBookService extends BookServiceGrpc.BookServiceImplBase {

    @Inject
    LogicBookService logicBookService;
    @Override
    public void addBook(BookRequest request, StreamObserver<Empty> responseObserver) {
        //Logique Métier de l'ajout
        logicBookService.addBook(request.getTitle(), request.getAuthor(), request.getId())
                .subscribe().with(ignored -> {
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        },error -> responseObserver.onError(error));
    }

    @Override
    public void streamBooks(BookStreamRequest request, StreamObserver<Book> responseObserver) {
        //AtomicBoolean cancelFlag = new AtomicBoolean(false);

        logicBookService.streamBooks()
                .subscribe().with(
                        gBook -> {
                            //if (!cancelFlag.get()) {
                                Book grpcBook = Book.newBuilder()
                                        .setId(gBook.id)
                                        .setTitle(gBook.title)
                                        .setAuthor(gBook.author)
                                        .build();
                                responseObserver.onNext(grpcBook);
                            //}
                        },
                        throwable -> {
                            responseObserver.onError(throwable);
                        },
                        responseObserver::onCompleted
                );
    }
}