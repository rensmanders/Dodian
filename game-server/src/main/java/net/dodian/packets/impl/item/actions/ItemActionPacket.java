package net.dodian.packets.impl.item.actions;

import lombok.Getter;
import net.dodian.old.net.packet.Packet;
import net.dodian.old.world.entity.impl.player.Player;
import net.dodian.packets.GamePacket;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static net.dodian.packets.PacketConstants.*;

@Component
@Getter
public class ItemActionPacket extends GamePacket {
    private int itemId;
    private int slot;
    private Action action;

    @Override
    public ItemActionPacket createFrom(Packet packet, Player player) {
        this.player = player;
        this.itemId = packet.readShort();
        this.slot = packet.readShort();
        this.action = Arrays.stream(Action.values())
                .filter(value -> value.getOpcode() == packet.getOpcode())
                .findFirst()
                .orElse(Action.FIRST);
        return this;
    }

    public enum Action {
        FIRST(FIRST_ITEM_ACTION_OPCODE),
        SECOND(SECOND_ITEM_ACTION_OPCODE),
        THIRD(THIRD_ITEM_ACTION_OPCODE);

        private int opcode;

        Action(int opcode) {
            this.opcode = opcode;
        }

        public int getOpcode() {
            return opcode;
        }
    }
}
