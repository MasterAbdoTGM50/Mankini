package matgm50.mankini.entity.hostile;

import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import matgm50.mankini.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityMankiniSpider extends EntitySpider {
    public EntityMankiniSpider(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.MANKINI_SPIDER;
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingData, @Nullable NBTTagCompound itemNbt) {
        livingData = super.onInitialSpawn(difficulty, livingData, itemNbt);
        ItemStack creeperKini = new ItemStack(ModItems.dyeable_mankini);

        if (this.world.rand.nextInt(100) == 0) {
            EntitySkeleton entityskeleton = new EntitySkeleton(this.world);

            if(this.world.rand.nextInt(20) < 5) {
                entityskeleton = new EntityMankiniSkeleton(this.world);
            }

            entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entityskeleton.onInitialSpawn(difficulty, (IEntityLivingData)null, (NBTTagCompound)null);
            entityskeleton.setItemStackToSlot(EntityEquipmentSlot.CHEST, creeperKini);
            this.world.spawnEntity(entityskeleton);
            entityskeleton.startRiding(this);
        }
        else if (this.world.rand.nextInt(100) < 10)
        {

            EntityMankiniCreeper mankinicreeper = new EntityMankiniCreeper(this.world);
            mankinicreeper.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            mankinicreeper.onInitialSpawn(difficulty, (IEntityLivingData)null, null);
            mankinicreeper.setItemStackToSlot(EntityEquipmentSlot.CHEST, creeperKini);
            this.world.spawnEntity(mankinicreeper);
            mankinicreeper.startRiding(this);
        }

        if (livingData == null) {
            livingData = new EntitySpider.GroupData();
            if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty()) {
                ((EntitySpider.GroupData)livingData).setRandomEffect(this.world.rand);
            }
        }

        if (livingData instanceof EntitySpider.GroupData) {
            Potion potion = ((EntitySpider.GroupData)livingData).effect;
            if (potion != null) {
                this.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE));
            }
        }

        return livingData;
    }

    @Override
    public boolean canSpawn(IWorld worldIn, boolean p_205020_2_) {
        if(MankiniConfig.COMMON.MankiniSpiderSpawn.get())
        {
            return super.canSpawn(worldIn, p_205020_2_);
        }
        else
        {
            return false;
        }
    }
}
