package net.zuiron.photosynthesis.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.zuiron.photosynthesis.util.IEntityDataSaver;
import net.zuiron.photosynthesis.util.ThirstData;

import java.util.Random;

/*public class PlayerTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            if(new Random().nextFloat() <= 0.005f) {
                IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
                //ThirstData.removeThirst(dataPlayer,1);
                int Sat = ThirstData.removeThirstSaturation(dataPlayer,50);
                player.sendMessage(Text.literal("Removed Thirst Saturation: "+Sat));
            }
        }
    }
}*/
public class PlayerTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            float probability = 0.001f; //0.1%chance
            int amount = 20;
            if (player.isSprinting()) {
                probability = 0.01f;    //0.5% chance
                amount = 60;
            }
            if (new Random().nextFloat() <= probability) {
                IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);

                if(ThirstData.getThirstSat(dataPlayer) >= 450) { amount = amount / 8; }
                else if(ThirstData.getThirstSat(dataPlayer) >= 300) { amount = amount / 6; }
                else if(ThirstData.getThirstSat(dataPlayer) >= 150) { amount = amount / 2; }

                int Sat = ThirstData.removeThirstSaturation(dataPlayer, amount);
                player.sendMessage(Text.literal("Removed Thirst Saturation: " + Sat + " - amount: "+amount));
            }
        }
    }
}