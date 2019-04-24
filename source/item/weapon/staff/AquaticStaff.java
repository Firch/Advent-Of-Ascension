package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityAquaticShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class AquaticStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeWind, 2);
		runes.put(ItemRegister.runeWater, 4);
		runes.put(ItemRegister.runeKinetic, 1);
	}

	public AquaticStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("AquaticStaff");
		setRegistryName("aoa3:aquatic_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityAquaticShot(caster, this, 60));
	}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		EntityUtil.dealMagicDamage(shot, shooter, target, target.isInWater() ? getDmg() * 2 : getDmg(), false);
	}

	@Override
	public float getDmg() {
		return 14;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.AquaticStaff.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.AquaticStaff.desc.2", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}