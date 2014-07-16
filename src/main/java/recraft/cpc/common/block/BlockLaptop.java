package recraft.cpc.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import recraft.cpc.CPC;
import recraft.cpc.common.tileentity.TileEntityLaptop;
import recraft.cpc.init.CPCBlocks;

import java.util.Random;

public class BlockLaptop extends BlockContainer {
	public BlockLaptop() {
		super(Material.iron);
		setCreativeTab(CPC.tabCPC);
		setBlockTextureName("cpc:laptop");
		setBlockName("cpc:laptop");
	}

	public Item getBlockDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(CPCBlocks.laptop);
	}

	public int quantityDropped(Random random) {
		return 1;
	}

	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public void breakBlock(World par1world, int par2, int par3, int par4, Block par5Block, int par6) {
		par1world.func_147453_f(par2, par3, par4, par5Block);
		super.breakBlock(par1world, par2, par3, par4, par5Block, par6);
	}

	public boolean onBlockActivated(World world, int x, int y, int z,
									EntityPlayer entityPlayer, int par6,
									float par7, float par8, float par9) {
		TileEntity tileEntity = world.getTileEntity(x, y ,z);
		if (tileEntity instanceof TileEntityLaptop) {
			entityPlayer.openGui(CPC.instance, 0, world, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World par1, int par2) {
		return new TileEntityLaptop();
	}

	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		byte b0 = 0;
		int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			b0 = 2;
		}

		if (l == 1) {
			b0 = 5;
		}

		if (l == 2) {
			b0 = 3;
		}

		if (l == 3) {
			b0 = 4;
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
	}
}
