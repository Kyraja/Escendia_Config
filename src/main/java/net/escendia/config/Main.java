package net.escendia.config;


import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.*;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = PluginInfo.PLUGIN_ID, name = PluginInfo.PLUGIN_NAME, version = PluginInfo.PLUGIN_VERSION, authors = {"FPetersen"}, description = PluginInfo.PLUGIN_DESCRIPTION)
public class Main {

    @Inject
    private Logger logger;

    @Listener
    public void preInit(GamePreInitializationEvent event) {
    }

    @Listener
    public void init(GameInitializationEvent event) {
    }

    @Listener
    public void onServerStarting(GameStartingServerEvent event) {

    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Escendia ConfigPlugin is started!");

        logger.info("Escendia ConfigPlugin is finished!");
    }

    @Listener
    public void onServerStopping(GameStoppingEvent event) {

    }

    @Listener
    public void onServerStopped(GameStoppedServerEvent event) {
        logger.info("Escendia ConfigPlugin has stopped!");

    }


}


