import com.flowpowered.math.vector.Vector3d;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.event.game.GameRegistryEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.IOException;
import java.nio.file.Path;

@Plugin(id = "plugin", name = "plugin", description = "plugin" )
public class main {

    @Inject
    private PluginContainer container;

    @Inject
    public Logger logger;

    @Inject
    public Game game;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private Path configDir;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private Path configDir2;


    @Listener
    public void GameRegistryEventRegisterKey(GameRegistryEvent.Register<Key<?>> event) {
        logger.info("--");
        logger.info("");
        logger.info("GameRegistryEvent.Register<Key<?>>");
        logger.info("");
        logger.info("--");
        event.register(ToolKeys.VECTOR3D);
    }



    @Listener
    public void GamePreInitialization(GamePreInitializationEvent event) throws IOException {
        logger.info("--");
        logger.info("");
        logger.info("GamePreInitializationEvent!!!");
        logger.info("");
        logger.info("--");

        DataRegistration.builder()
                .id("vector3d_id")
                .name("Vector3dSav")
                .dataClass(MyVector3dData.class)
                .immutableClass(MyVector3dData.Immutable.class)
                .builder(new MyVector3dData.Builder())
                .build();
    }

        /**
         * Méthode qui est appelé quand le plugin est chargé .
         *
         * @param event Param de l'event
         */
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        Tool.getLoggerBarre("");
        Tool.getLogger().info(" ");
        logger.info("GameStartedServerEvent!!!");
        
        Tool.getGame().getEventManager().registerListeners(this, new EventLoader());
    }

}
