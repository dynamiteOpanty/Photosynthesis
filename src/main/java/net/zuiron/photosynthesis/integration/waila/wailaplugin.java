package net.zuiron.photosynthesis.integration.waila;

import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import net.minecraft.block.CropBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.passive.*;
import net.zuiron.photosynthesis.block.custom.CropSticksBlock;
import net.zuiron.photosynthesis.integration.waila.components.BerryTreeGrowthComponent;
import net.zuiron.photosynthesis.integration.waila.components.EntityDataStatsComponent;
import net.zuiron.photosynthesis.integration.waila.components.FertPestComponent;

import static mcp.mobius.waila.api.TooltipPosition.BODY;

public class wailaplugin implements IWailaPlugin {

    @Override
    public void register(IRegistrar registrar) {
        registrar.addComponent(FertPestComponent.INSTANCE, BODY, CropSticksBlock.class);
        registrar.addComponent(FertPestComponent.INSTANCE, BODY, CropBlock.class);

        registrar.addComponent(BerryTreeGrowthComponent.INSTANCE, BODY, SweetBerryBushBlock.class);

        registrar.addComponent(EntityDataStatsComponent.INSTANCE, BODY, CowEntity.class);
        registrar.addComponent(EntityDataStatsComponent.INSTANCE, BODY, GoatEntity.class);
        registrar.addComponent(EntityDataStatsComponent.INSTANCE, BODY, ChickenEntity.class);
        registrar.addComponent(EntityDataStatsComponent.INSTANCE, BODY, HorseEntity.class);
        registrar.addComponent(EntityDataStatsComponent.INSTANCE, BODY, PigEntity.class);
        registrar.addComponent(EntityDataStatsComponent.INSTANCE, BODY, SheepEntity.class);

        registrar.addEntityData(EntityDataStatsComponent.INSTANCE, PassiveEntity.class);
    }
}
