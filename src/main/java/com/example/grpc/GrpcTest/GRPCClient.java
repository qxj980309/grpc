package com.example.grpc.GrpcTest;

import com.example.grpc.api.RPCDateRequest;
import com.example.grpc.api.RPCDateResponse;
import com.example.grpc.api.RPCDateServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GRPCClient {
    private static final String host = "localhost";
    private static final int serverPort = 9999;
    public static void main(String[] args) {
        //1,拿到一个通信channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, serverPort).
                usePlaintext()//无需加密或认证
                .build();
        try {
            //2.拿到stub对象
            RPCDateServiceGrpc.RPCDateServiceBlockingStub rpcDateService  = RPCDateServiceGrpc.newBlockingStub(channel);
            RPCDateRequest rpcDateRequest = RPCDateRequest.newBuilder()
                    .setUserName("JACK")
                    .build();
            //3,请求
            RPCDateResponse rpcDateResponse = rpcDateService.getDate(rpcDateRequest);
            //4,输出结果
            System.out.println(rpcDateResponse.getServerDate());
        } finally {
            // 5.关闭channel, 释放资源.
            channel.shutdown();
        }

    }
}