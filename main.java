import com.google.inject.Inject;
import fr.simonbhb.animatedmagic.Event.EventLoader;
import fr.simonbhb.animatedmagic.Task.TaskLoader;
import fr.simonbhb.animatedmagic.Utils.Animation.AnimationCraft;
import fr.simonbhb.animatedmagic.Utils.MyKeys.*;
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

@Plugin(id = "tool", name = "Tool", description = "tool" )
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

        new Tool(this, container, game, logger, configDir); // Obligatoire

        DataRegistration.builder()
                .id("standard_data") // prefix is added for you and you can't add it yourself
                .name("My Standard Data")
                .dataClass(MyStandardData.class)
                .immutableClass(MyImmutableStandardData.class)
                .builder(new MyStandardDataBuilder());

        DataRegistration.builder()
                .id("standard_data") // prefix is added for you and you can't add it yourself
                .name("My Standard Data")
                .dataClass(MyStandardData.class)
                .immutableClass(MyImmutableStandardData.class)
                .builder(new MyStandardDataBuilder());
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
        Tool.getLogger().info(" ");
    }

}
