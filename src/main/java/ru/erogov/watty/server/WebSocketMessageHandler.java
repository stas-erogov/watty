package ru.erogov.watty.server;

import io.netty.channel.ChannelHandlerContext;

public interface WebSocketMessageHandler {
    void handleMessage(ChannelHandlerContext ctx, String message);
}
