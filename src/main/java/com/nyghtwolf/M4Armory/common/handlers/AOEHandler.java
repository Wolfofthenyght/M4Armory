package com.nyghtwolf.m4armory.common.handlers;

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import net.minecraft.block.Block;
        import net.minecraft.block.material.Material;
        import net.minecraft.client.Minecraft;
        import net.minecraft.client.network.NetHandlerPlayClient;
        import net.minecraft.enchantment.EnchantmentHelper;
        import net.minecraft.entity.EntityLivingBase;
        import net.minecraft.entity.SharedMonsterAttributes;
        import net.minecraft.entity.player.EntityPlayer;
        import net.minecraft.item.Item;
        import net.minecraft.item.ItemAxe;
        import net.minecraft.item.ItemPickaxe;
        import net.minecraft.item.ItemSpade;
        import net.minecraft.item.ItemStack;
        import net.minecraft.item.ItemTool;
        import net.minecraft.network.INetHandler;
        import net.minecraft.network.play.client.C07PacketPlayerDigging;
        import net.minecraft.util.AxisAlignedBB;
        import net.minecraft.world.World;
        import net.minecraft.world.WorldServer;
        import net.minecraftforge.common.util.FakePlayerFactory;
        import net.minecraftforge.event.entity.player.AttackEntityEvent;
        import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
        import net.minecraftforge.event.world.BlockEvent.BreakEvent;
        import com.nyghtwolf.m4armory.common.util.DirectionHelper;
        import com.mojang.authlib.GameProfile;
        import cpw.mods.fml.client.FMLClientHandler;
        import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AOEHandler
{
    private void do3x3Hoe(BreakEvent event)
    {
        if (canHoeHarvest(event.block))
        {
            mineGrass(DirectionHelper.mineArrayTop, event);
        }
    }

    public void mineOutEverything(int[][] locations, BreakEvent event)
    {
        EntityPlayer player = event.getPlayer();
        ItemStack current = player.getCurrentEquippedItem();

        for (int i = 0; i < locations.length; i++)
        {
            int curX = event.x + locations[i][0];
            int curY = event.y + locations[i][1];
            int curZ = event.z + locations[i][2];

            Block miningBlock = event.world.getBlock(curX, curY, curZ);
            int meta = event.world.getBlockMetadata(curX, curY, curZ);
            if (canHarvestBlock(player, event.block, miningBlock, meta, curX, curY, curZ))
            {
                if (!((ItemTool) current.getItem()).onBlockStartBreak(current, curX, curY, curZ, player))
                {
                    mineBlock(event.world, curX, curY, curZ, meta, player, miningBlock);
                    ((ItemTool) current.getItem()).onBlockDestroyed(current, event.world, miningBlock, curX, curY, curZ, player);
                    player.addExhaustion((float) 0.025);
                }
            }
        }
    }



    public void mineBlock(World world, int x, int y, int z, int meta, EntityPlayer player, Block block)
    {
        // Workaround for dropping experience
        boolean silktouch = EnchantmentHelper.getSilkTouchModifier(player);
        int fortune = EnchantmentHelper.getFortuneModifier(player);
        int exp = block.getExpDrop(world, meta, fortune);

        block.onBlockHarvested(world, x, y, z, meta, player);
        if (block.removedByPlayer(world, player, x, y, z, true))
        {
            block.onBlockDestroyedByPlayer(world, x, y, z, meta);
            block.harvestBlock(world, player, x, y, z, meta);
            // Workaround for dropping experience
            if (!silktouch)
                block.dropXpOnBlockBreak(world, x, y, z, exp);

            if (world.isRemote)
            {
                INetHandler handler = FMLClientHandler.instance().getClientPlayHandler();
                if (handler != null && handler instanceof NetHandlerPlayClient)
                {
                    NetHandlerPlayClient handlerClient = (NetHandlerPlayClient) handler;
                    handlerClient.addToSendQueue(new C07PacketPlayerDigging(0, x, y, z, Minecraft.getMinecraft().objectMouseOver.sideHit));
                    handlerClient.addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, Minecraft.getMinecraft().objectMouseOver.sideHit));
                }
            }
        }
    }

    private static Map<Class<? extends ItemTool>, String> toolClasses = new HashMap<Class<? extends ItemTool>, String>();
    static
    {
        toolClasses.put(ItemPickaxe.class, "pickaxe");
        toolClasses.put(ItemSpade.class, "shovel");
        toolClasses.put(ItemAxe.class, "axe");
    }

    private static String getToolClass(Class<? extends Item> clazz)
    {
        for (Class<? extends ItemTool> toolClass : toolClasses.keySet())
        {
            if (toolClass.isAssignableFrom(clazz))
            {
                return toolClasses.get(toolClass);
            }
        }
        return null;
    }

    private boolean canHarvestBlock(EntityPlayer player, Block origBlock, Block block, int meta, int x, int y, int z)
    {
        ItemStack current = player.getCurrentEquippedItem();
        if (current == null)
            return false;

        String toolClass = getToolClass(current.getItem().getClass());
        if (toolClass == null)
            return false;

        float hardness = block.getBlockHardness(player.worldObj, x, y, z);
        float digSpeed = ((ItemTool) current.getItem()).getDigSpeed(current, block, meta);

        // It works. It just does.
        return (digSpeed > 1.0F && block.getHarvestLevel(meta) <= ((ItemTool) current.getItem()).getHarvestLevel(current, toolClass) && hardness >= 0 && origBlock
                .getBlockHardness(player.worldObj, x, y, z) >= hardness - 1.5);
    }

    public void mineGrass(int[][] blocks, BreakEvent event)
    {
        EntityPlayer player = event.getPlayer();
        ItemStack current = player.getCurrentEquippedItem();

        for (int i = 0; i < blocks.length; i++)
        {
            Block currentBlock = event.world.getBlock(event.x + blocks[i][0], event.y + blocks[i][1], event.z + blocks[i][2]);
            int currentMeta = event.world.getBlockMetadata(event.x + blocks[i][0], event.y + blocks[i][1], event.z + blocks[i][2]);
            if (canHoeHarvest(currentBlock))
            {
                mineBlock(event.world, event.x + blocks[i][0], event.y + blocks[i][1], event.z + blocks[i][2], currentMeta, player, currentBlock);
                current.damageItem(1, player);
                if (current.getItemDamage() >= current.getMaxDamage())
                {
                    return;
                }
            }
        }
    }

    public static Material[] validHoeMaterials = { Material.plants, Material.vine };

    public boolean canHoeHarvest(Block block)
    {
        for (Material i : validHoeMaterials)
        {
            if (i == block.getMaterial())
            {
                return true;
            }
        }
        return false;
    }

    /* ==== Sword ==== */

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event)
    {
        if (!event.entityPlayer.worldObj.isRemote)
        {
            EntityPlayer fakePlayer = FakePlayerFactory.get((WorldServer) event.entityPlayer.worldObj, new GameProfile(null, "M4Armory-ScytheDummy"));
            if (event.entityPlayer == fakePlayer) return;
            ItemStack current = event.entityPlayer.getCurrentEquippedItem();
            {
                copy(event.entityPlayer, fakePlayer);
                AxisAlignedBB bb = event.target.boundingBox;
                bb = bb.expand(2, 2, 2);
                List<EntityLivingBase> list = event.entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, bb);
                for (EntityLivingBase entity : list)
                {
                    if (entity != event.target && entity != event.entityPlayer)
                    {
                        fakePlayer.attackTargetEntityWithCurrentItem(entity);
                        current.getItem().hitEntity(current, entity, event.entityPlayer);
                    }
                }

                fakePlayer.setLocationAndAngles(0, 0, 0, 0, 0);
            }
        }
    }

    private static void copy(EntityPlayer from, EntityPlayer to)
    {
        to.setSprinting(from.isSprinting());
        to.setLocationAndAngles(from.posX, from.posY, from.posZ, from.rotationYaw, from.rotationPitch);
        to.fallDistance = from.fallDistance;
        to.onGround = from.onGround;
        to.inventory.mainInventory[to.inventory.currentItem] = from.getCurrentEquippedItem();
        to.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(from.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
    }
}