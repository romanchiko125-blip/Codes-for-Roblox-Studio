package com.example.megavillages;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = MegaVillagesMod.MOD_ID, name = "Mega Villages", version = "1.0.2")
public class MegaVillagesMod {
    public static final String MOD_ID = "megavillages";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        if (event.getEntityLiving().world.isRemote) {
            return;
        }

        if (event.getEntityLiving() instanceof EntityMob
                || event.getEntityLiving() instanceof EntityAnimal
                || event.getEntityLiving() instanceof EntityWaterMob) {
            for (ItemStack drop : event.getEntityLiving().getArmorInventoryList()) {
                if (!drop.isEmpty()) {
                    event.getEntityLiving().entityDropItem(drop.copy(), 0.0F);
                }
            }
            for (ItemStack drop : event.getEntityLiving().getHeldEquipment()) {
                if (!drop.isEmpty()) {
                    event.getEntityLiving().entityDropItem(drop.copy(), 0.0F);
                }
            }
        }
    }
}
