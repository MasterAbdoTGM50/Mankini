package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniCreeperSwell;
import matgm50.mankini.init.ModItems;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;

public class EntityMankiniCreeper extends EntityCreeper
{
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 30;
    private int explosionRadius = 3;
    private int droppedSkulls;

    public EntityMankiniCreeper(World worldIn)
    {
        super(worldIn);
    }
    
    public static void registerFixesMankiniCreeper(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, "MankiniCreeper");
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMankiniCreeperSwell(this));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

    protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_CREEPER_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_CREEPER_DEATH;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        if (this.isEntityAlive())
        {
            this.lastActiveTime = this.timeSinceIgnited;

            if (this.hasIgnited())
            {
                this.setCreeperState(1);
            }

            int i = this.getCreeperState();

            if (i > 0 && this.timeSinceIgnited == 0)
            {
                this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
            }

            this.timeSinceIgnited += i;

            if (this.timeSinceIgnited < 0)
            {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime)
            {
                this.timeSinceIgnited = this.fuseTime;
                this.explode();
            }
        }

        super.onUpdate();
    }

    /**
     * Creates an explosion as determined by this creeper's power and explosion radius.
     */
    private void explode()
    {   		
    	if(this.getAttackTarget() instanceof EntityPlayer)
    	{
    		EntityPlayer hitPlayer = (EntityPlayer) this.getAttackTarget();
        	ItemStack creeperKini = new ItemStack(ModItems.itemDyeableMankini);

        	if (!this.worldObj.isRemote)
            {
                boolean flag = this.worldObj.getGameRules().getBoolean("mobGriefing");

                    this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)0.0, flag);
                    if(hitPlayer.posX == (int) this.posX || hitPlayer.posY == (int) this.posY || hitPlayer.posZ == this.posZ){
                    	
                    	if(hitPlayer.inventory.armorItemInSlot(3) != null) {

                            ItemStack toSpawn = hitPlayer.inventory.armorItemInSlot(3);
                           // EntityItem spawned = new EntityItem(hitPlayer.worldObj, hitPlayer.posX, hitPlayer.posY, hitPlayer.posZ, toSpawn);
                            hitPlayer.inventory.addItemStackToInventory(toSpawn);
                           // worldObj.spawnEntityInWorld(spawned);

                        }

                        hitPlayer.setItemStackToSlot(EntityEquipmentSlot.CHEST, creeperKini);
                        ItemStack toSpawn = hitPlayer.inventory.armorItemInSlot(3);
                        hitPlayer.inventory.addItemStackToInventory(toSpawn);
                    	
                    }
                }
    	}
            this.setDead();
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);

        if (this.worldObj.getGameRules().getBoolean("doMobLoot"))
        {
            if (cause.getEntity() instanceof EntitySkeleton)
            {
                int i = Item.getIdFromItem(Items.RECORD_13);
                int j = Item.getIdFromItem(Items.RECORD_WAIT);
                int k = i + this.rand.nextInt(j - i + 1);
                this.dropItem(Item.getItemById(k), 1);
            }
        }
    }
    
    @Override
    public void onStruckByLightning(EntityLightningBolt lightningBolt)
    {
        super.onStruckByLightning(lightningBolt);
    }
    
    @Override
    public boolean getPowered()
    {
        return false;
    }
}