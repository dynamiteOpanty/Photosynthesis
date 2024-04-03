package net.zuiron.photosynthesis.integration.waila.components;

import mcp.mobius.waila.api.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.zuiron.photosynthesis.Photosynthesis;
import net.zuiron.photosynthesis.util.getCustomVarsPassiveEntity;

public enum EntityDataStatsComponent implements IEntityComponentProvider, IDataProvider<PassiveEntity> {
    INSTANCE;

    @Override
    public void appendBody(ITooltip tooltip, IEntityAccessor accessor, IPluginConfig config) {
        if (accessor.getEntity() instanceof AnimalEntity living) {
            NbtCompound data = accessor.getData().raw();
            long living_ticks = data.getLong("photosynthesis_livingticks");

            tooltip.addLine(Text.literal("living ticks: "+living_ticks));
        }
    }

    @Override
    public void appendData(IDataWriter data, IServerAccessor<PassiveEntity> accessor, IPluginConfig config) {
        var mob = accessor.getTarget();
        long mod_LivingTicks = ((getCustomVarsPassiveEntity) mob).getMod_LivingTicks();
        data.raw().putLong("photosynthesis_livingticks", mod_LivingTicks);

        Photosynthesis.LOGGER.info("submitting??? "+mod_LivingTicks);
    }
}
