package net.nevermine.mob.entity.night.ghostly;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.event.dimensional.overworld.SoulScurryEvent;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;

public class EntityGhostlyCyclops extends EntityMob {
	public EntityGhostlyCyclops(final World par1World) {
		super(par1World);
		setSize(0.6f, 2.5f);
	}

	protected String getLivingSound() {
		return "nevermine:CyclopsLiving";
	}

	protected String getDeathSound() {
		return "nevermine:CyclopsHit";
	}

	protected String getHurtSound() {
		return "nevermine:CyclopsHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(3) == 1) {
			dropItem(Itemizer.GhostStone, 4);
		}
		if (rand.nextInt(5) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.GhostlyBanner), 1);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && SoulScurryEvent.isScurry() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox) && !worldObj.isDaytime();
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(75.0);
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		if (entityToAttack != null) {
			if (entityToAttack instanceof EntityPlayer && ((EntityPlayer)entityToAttack).getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue() != 1.0) {
				entityToAttack.addVelocity(motionX * 10.5, motionY * 0.5, motionZ * 22.0);
			}
			if (entityToAttack instanceof EntityLiving) {
				((EntityLiving)entityToAttack).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 150, 1));
			}
			return true;
		}
		return false;
	}
}