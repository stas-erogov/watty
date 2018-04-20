package ru.erogov.watty.server;

import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.erogov.watty.api.OfferList;
import ru.erogov.watty.agent.WattyResource;
import ru.erogov.watty.agent.WattyResourceHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PrototypeMessageHandler implements WebSocketMessageHandler{
    private static final Logger logger = LoggerFactory.getLogger(PrototypeMessageHandler.class);

    private static final List<WattyResource> agents;
    private volatile ChannelHandlerContext ctx;

    static {
        agents = WattyResourceHelper.getAgents();
        logger.debug("Registered agents: " + agents.toString());
    }

    @Override
    public void handleMessage(ChannelHandlerContext ctx, String message) {
        this.ctx = ctx;
        accept(message);
    }

    private void send(OfferList offerList) {
        ctx.channel().writeAndFlush(new TextWebSocketFrame(new Gson().toJson(offerList)));
    }

    private void accept(String query) {
        for (WattyResource<OfferList> service : agents) {
            CompletableFuture.supplyAsync(() -> service.apply(query))
                    .thenAccept(this::send);
        }
    }
}
