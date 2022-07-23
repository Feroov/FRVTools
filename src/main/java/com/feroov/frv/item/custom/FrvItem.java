package com.feroov.frv.item.custom;


import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;



public class FrvItem extends Item implements IAnimatable, ISyncable
{
    public AnimationFactory factory = new AnimationFactory(this);
    public String controllerName = "controller";
    public static final int STATE = 0;

    public FrvItem(Properties p_41383_) {
        super(p_41383_);
        GeckoLibNetwork.registerSyncable(this);
    }


    public <P extends ProjectileWeaponItem & IAnimatable> PlayState predicate(AnimationEvent<P> event)
    {
        return PlayState.CONTINUE;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, controllerName, 1, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    @Override
    public void onAnimationSync(int id, int state)
    {
        if (state == STATE) {
            final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, controllerName);
            if (controller.getAnimationState() == AnimationState.Stopped)
            {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("attack", false));
            }
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        return false;
    }

    public static void removeAmmo(Item ammo, Player playerEntity) {

        if (!playerEntity.isCreative())
        {
            for (ItemStack item : playerEntity.getInventory().offhand)
            {
                if (item.getItem() == ammo)
                {
                    item.shrink(1);
                    break;
                }
                for (ItemStack item1 : playerEntity.getInventory().items)
                {
                    if (item1.getItem() == ammo)
                    {
                        item1.shrink(1);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public boolean isFoil(ItemStack stack) { return false; }

    @Override
    public int getUseDuration(ItemStack stack) { return 7200; }
}
