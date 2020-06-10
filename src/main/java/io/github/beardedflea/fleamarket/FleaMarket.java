package io.github.beardedflea.fleamarket;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.beardedflea.fleamarket.command.*;
import io.github.beardedflea.fleamarket.config.*;


@Mod(modid = FleaMarket.MODID, name = FleaMarket.NAME, version = FleaMarket.VERSION, acceptedMinecraftVersions = FleaMarket.MCVERSIONS, acceptableRemoteVersions = "*", serverSideOnly = true)
public class FleaMarket
{
    public static final String MODID = "fleamarket";
    public static final String NAME = "Flea Market";
    public static final String MCVERSIONS = "[1.12, 1.13)";
    public static final String VERSION = "1.02";

    private static final Logger log = LogManager.getLogger(MODID);

    public static Logger getLogger() {
        return log;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        log.info("Pre int of Flea market, creating folders");
        LanguageParser.init(event);
        ItemOfferParser.init(event);
        CurrentItemOfferParser.init(event);
    }

    public static boolean isDebugMode(){
        return FleaMarketConfig.debugMode;
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        log.info("Flea market test");
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandFleaMarket());
        event.registerServerCommand(new CommandOPFleaMarket());

        CurrentItemOfferParser.loadCurrentItemOffer();
        ItemOfferParser.loadItemOfferData();
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        log.info("saving currentItemOffer data...");
        CurrentItemOfferParser.saveCurrentItemOffer();
    }
}
