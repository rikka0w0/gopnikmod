package gopnikmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs{
	public static CreativeTab instance;
	
	public CreativeTab() {
		super(GopnikMod.MODID);
		instance = this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return new ItemStack(BlockRegistry.blockFlag, 1 , 0);
	}
}
