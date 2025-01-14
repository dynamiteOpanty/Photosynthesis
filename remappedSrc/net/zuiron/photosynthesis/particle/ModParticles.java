package net.zuiron.photosynthesis.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.zuiron.photosynthesis.Photosynthesis;

public class ModParticles {
    public static final DefaultParticleType BOILING_BUBBLES_PARTICLES =
            registerParticle("boiling_bubbles_particles", FabricParticleTypes.simple());
    private static DefaultParticleType registerParticle(String name, DefaultParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(Photosynthesis.MOD_ID, name), particleType);
    }
    public static void registerParticles() {
        Photosynthesis.LOGGER.info("Registering particles for: " + Photosynthesis.MOD_ID);
    }
}
