package io.github.beardedflea.fleamarket.utils;

import io.github.beardedflea.fleamarket.FleaMarket;
import net.minecraft.util.text.*;

public class TextUtils {

    public static void printDebugStrConsole(String... sArgs){
        for(String line : sArgs){
            FleaMarket.getLogger().info(line);
        }
    }

    public static ITextComponent getTextBorder(){
        ITextComponent borderLeft = new TextComponentString(TextFormatting.BLUE + "==================");
        ITextComponent modNameMid = new TextComponentString(TextFormatting.LIGHT_PURPLE + " " + FleaMarket.NAME + " ");
        ITextComponent borderRight = new TextComponentString(TextFormatting.BLUE + "==================");

        borderLeft.appendSibling(modNameMid).appendSibling(borderRight);
        //"================== Flea Market =================="
        return borderLeft;
    }

    //Will implement with version 1.1
//    public static ITextComponent getModTextTag(){
//        ITextComponent bracketLeft = new TextComponentString(TextFormatting.BLUE + "[");
//        ITextComponent bracketRight = new TextComponentString(TextFormatting.BLUE + "]");
//        ITextComponent modNameMid = new TextComponentString(TextFormatting.LIGHT_PURPLE + " " + FleaMarket.NAME + " ");
//        bracketLeft.appendSibling(modNameMid).appendSibling(bracketRight);
//        // [ Flea Market ]
//        return bracketLeft;
//    }

    public static String replacePlayerPlaceHolder(String line, String playerName){
        return line.replace("%playerName%", playerName);
    }

    public static String replaceItemAndAmount(String line, int itemAmount, String itemName){
        return line.replace("%item%", itemName).replace("%amount%", Integer.toString(itemAmount));
    }
}