package com.example.grpc.GrpcTest;

import com.example.grpc.api.RPCDateRequest;
import com.example.grpc.api.RPCDateResponse;
import com.example.grpc.api.RPCDateServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RPCDateServiceImpl  extends RPCDateServiceGrpc.RPCDateServiceImplBase {
    @Override
    public void getDate(RPCDateRequest request, StreamObserver<RPCDateResponse> responseObserver) {
        //请求结果，我们定义的
        RPCDateResponse rpcDateResponse = null;
        //
        String userName = request.getUserName();
        String response = String.format("你好:%s,今天是%s.", userName, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        try {
            // 定义响应,是一个builder构造器.
            rpcDateResponse = RPCDateResponse.newBuilder()
                    .setServerDate(response)
                    .build();
            //int i = 10/0;
        } catch (Exception e) {
            responseObserver.onError(e);
        } finally {

            responseObserver.onNext(rpcDateResponse);
        }

        responseObserver.onCompleted();

    }
}