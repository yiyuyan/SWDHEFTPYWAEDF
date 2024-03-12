package cn.ksmcbrigade.swdheftpywaedf;

import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod("swdheftpywaedf")
@Mod.EventBusSubscriber
public class SWDHEFTPYWAEDF {

    public SWDHEFTPYWAEDF() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void killEnderDragon(EntityJoinWorldEvent event){
        if(!event.getWorld().isClientSide && event.getWorld().dimension().equals(Level.END) && (event.getEntity() instanceof EnderDragon dragon)){
            EndDragonFight dragonFight = dragon.getDragonFight();
            if(dragonFight!=null && !dragonFight.hasPreviouslyKilledDragon()){
                List<EndCrystal> list = event.getWorld().getEntitiesOfClass(EndCrystal.class, dragon.getBoundingBox().inflate(10000.0D));
                for (EndCrystal enderCrystal : list){
                    enderCrystal.kill();
                }
                dragon.setHealth(-1);
                dragonFight.setDragonKilled(dragon);
            }
        }
    }
}
