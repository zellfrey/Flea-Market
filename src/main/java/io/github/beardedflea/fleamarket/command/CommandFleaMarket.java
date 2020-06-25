package io.github.beardedflea.fleamarket.command;

import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.server.MinecraftServer;

import io.github.beardedflea.fleamarket.store.*;
import static io.github.beardedflea.fleamarket.utils.TextUtils.*;


import java.util.ArrayList;
import java.util.List;

public class CommandFleaMarket extends CommandBase{


    @Override
    public String getName() {
        return "fleamarket";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/fleamarket [help:check:sell]";
    }


    @Override
    public List<String> getAliases()
    {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("fm");
        aliases.add("fleamkt");
        return aliases;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    private static ITextComponent getHelpUsage(){
        ITextComponent comp1 = getModTextBorder();
        ITextComponent comp2 = TransformModLanguage(modLanguageMap.get("fmhelp"));
        ITextComponent comp3 = TransformModLanguage(modLanguageMap.get("fmcheck"));
        ITextComponent comp4 = TransformModLanguage(modLanguageMap.get("fmsell"));
        ITextComponent comp5 = getModTextBorder();

        comp1.appendSibling(comp2).appendSibling(comp3).appendSibling(comp4).appendSibling(comp5);

        return comp1;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        boolean isItemOn;
        if(args.length > 1){
            throw new SyntaxErrorException("Too many arguments");
        }
        if(args.length == 1){
            switch(args[0].toLowerCase()){
                case "help":
                    sender.sendMessage(getHelpUsage());
                break;

                case "check":
                     isItemOn = checkItemOffer(sender);

                    if(isItemOn){
                        sender.sendMessage(TransformModLanguage(ItemOfferList.currentItemOffer.getBroadcastMsg()));
                    }
                break;

                case "sell":
                    isItemOn = checkItemOffer(sender);

                    if(isItemOn){
                        EntityPlayerMP playerMP = getCommandSenderAsPlayer(sender);
                        ItemOfferList.sellItemOffer(playerMP);
                    }
                break;

                default:
                    throw new WrongUsageException(getUsage(sender));
            }
        }
        else{
            throw new WrongUsageException(getUsage(sender));
        }
    }

    private static boolean checkItemOffer(ICommandSender sender){
        if(ItemOfferList.currentItemOffer == null){
            sender.sendMessage(TransformModLanguage(modLanguageMap.get("itemOfferNoneMsg")));

            return false;
        }
        else if(ItemOfferList.itemOfferUptime <= 0){
            sender.sendMessage(TransformModLanguage(modLanguageMap.get("itemOfferFindingMsg")));

            return false;

        }else{
            return true;
        }
    }

}