import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.CraftItemEvent;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.event.item.inventory.UpdateAnvilEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.transaction.SlotTransaction;
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public class EventLoader {
    @Listener
    public void CraftItemCraft(CraftItemEvent event) {
        if(event.getCause().first(Player.class).isPresent()) {
            Player player = event.getCause().first(Player.class).get();
            for(SlotTransaction slotTransaction : event.getTransactions()) {
                ItemStack peek = slotTransaction.getFinal().createStack();
                if(peek.getType().equals(ItemTypes.CLOCK)) {
                    peek.offer(Keys.DISPLAY_NAME, Text.of("AnimatedMagic"));
                    
                    peek.offer(new MyVector3dData(player.getPosition(), ToolKeys.VECTOR3D));

                    ItemStack peek2 = AnimationItem.loreDefaut(peek, player);
                    slotTransaction.setCustom(peek2);
                }
            }
        }
    }
}
