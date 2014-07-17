package recraft.cpc.common.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import recraft.cpc.common.entity.monster.CPEntity;

public class EntityMutahar extends EntityAnimal {
	public EntityMutahar(World world) {
		super(world);
		float moveSpeed = 0.2F;
		setSize(1.0F,0.5F);
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAvoidEntity(this, CPEntity.class, 6.0F, 0.25F, 0.3F));
		tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		tasks.addTask(2, new EntityAIBreakDoor(this));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, moveSpeed));
		tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
		tasks.addTask(6, new EntityAIWander(this, 0.6D));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	@Override
	public boolean getCanSpawnHere() {
        if (worldObj.villageCollectionObj.getVillageList().iterator().hasNext()) {
            if (worldObj.villageCollectionObj.findNearestVillage((int) posX, (int) posY, (int) posZ, 10) == null) {
                return false;
            }
        }
		return true;
	}

	protected boolean canDespawn() {
		return true;
	}
	
	public void onKillEntity(EntityLiving entity) {}

	public EntityAgeable createChild(EntityAgeable entity) {
		return entity;
	}
}
