package com.feroov.frv.init;

import com.feroov.frv.Frv;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles
{
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Frv.MOD_ID);

    public static final RegistryObject<SimpleParticleType> COAL_PARTICLES = PARTICLE_TYPES.register("coal_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> IRON_PARTICLES = PARTICLE_TYPES.register("iron_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> GOLD_PARTICLES = PARTICLE_TYPES.register("gold_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> LAPIS_PARTICLES = PARTICLE_TYPES.register("lapis_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> EMERALD_PARTICLES = PARTICLE_TYPES.register("emerald_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> REDSTONE_PARTICLES = PARTICLE_TYPES.register("redstone_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> DIAMOND_PARTICLES = PARTICLE_TYPES.register("diamond_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> NETHERITE_PARTICLES = PARTICLE_TYPES.register("netherite_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CORRUPT_PARTICLES = PARTICLE_TYPES.register("corrupt_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> MUSHROOM_PARTICLES = PARTICLE_TYPES.register("mushroom_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
