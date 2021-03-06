package joshie.harvest.quests.packet;

import io.netty.buffer.ByteBuf;
import joshie.harvest.core.HFTrackers;
import joshie.harvest.core.network.Packet;
import joshie.harvest.core.network.Packet.Side;
import joshie.harvest.core.network.PenguinPacket;
import joshie.harvest.knowledge.letter.LetterData;
import joshie.harvest.quests.data.QuestData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import java.util.UUID;

@Packet(Side.CLIENT)
public class PacketSharedSync extends PenguinPacket {
    protected UUID uuid;

    public PacketSharedSync() {}
    public PacketSharedSync setUUID(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(uuid != null);
        if (uuid != null) {
            ByteBufUtils.writeUTF8String(buf, uuid.toString());
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        if (buf.readBoolean()) {
            uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        }
    }

    @SuppressWarnings("unchecked")
   public <Q extends QuestData> Q getQuestDataFromPlayer(EntityPlayer player) {
        if (uuid == null) return (Q) HFTrackers.getPlayerTrackerFromPlayer(player).getQuests();
        else return (Q) HFTrackers.getTownTracker(player.worldObj).getTownByID(uuid).getQuests();
    }

    @SuppressWarnings("unchecked")
    public <L extends LetterData> L getLetterDataFromPlayer(EntityPlayer player) {
        if (uuid == null) return (L) HFTrackers.getPlayerTrackerFromPlayer(player).getLetters();
        else return (L) HFTrackers.getTownTracker(player.worldObj).getTownByID(uuid).getLetters();
    }
}