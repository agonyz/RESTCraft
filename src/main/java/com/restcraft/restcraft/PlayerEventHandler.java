package com.restcraft.restcraft;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.server.level.ServerPlayer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerEventHandler {

    private static final HttpClient client = HttpClient.newHttpClient();
    public static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        LOGGER.info("RestCraft player joined!");
        if (event.getEntity() instanceof ServerPlayer player) {
            sendToBackend("join", player.getName().getString());
        }
    }

    @SubscribeEvent
    public void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        LOGGER.info("RestCraft player left!");
        if (event.getEntity() instanceof ServerPlayer player) {
            sendToBackend("leave", player.getName().getString());
        }
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        LOGGER.info("RestCraft player died!");
        if (event.getEntity() instanceof ServerPlayer player) {
            sendToBackend("death", player.getName().getString());
        }
    }

    private void sendToBackend(String type, String player) {
        try {
            String url = switch (type) {
                case "join" -> Config.BACKEND_URL.get() + "/join";
                case "leave" -> Config.BACKEND_URL.get() + "/leave";
                case "death" -> Config.BACKEND_URL.get() + "/death";
                default -> throw new IllegalArgumentException("Unknown event type: " + type);
            };

            String json = String.format(
                    "{\"player\":\"%s\",\"timestamp\":%d}",
                    player, System.currentTimeMillis()
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                  .thenAccept(response -> LOGGER.info("Event sent: {} -> {}", type, response.statusCode()));

        } catch (Exception e) {
            LOGGER.error("Failed to send {} event to backend", type, e);
        }
    }
}
