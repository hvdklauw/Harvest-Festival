package joshie.harvest.core.network.quests;

import io.netty.buffer.ByteBuf;
import joshie.harvest.api.HFApi;
import joshie.harvest.api.quest.IQuest;
import joshie.harvest.core.helpers.QuestHelper;
import joshie.harvest.core.helpers.generic.MCClientHelper;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketQuestSetStage implements IMessage, IMessageHandler<PacketQuestSetStage, IMessage> {
    private IQuest quest;
    private boolean isSenderClient;
    private int stage;

    public PacketQuestSetStage() {}

    public PacketQuestSetStage(IQuest quest, boolean isSenderClient, int stage) {
        this.quest = quest;
        this.isSenderClient = isSenderClient;
        this.stage = stage;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isSenderClient);
        buf.writeShort(stage);
        ByteBufUtils.writeUTF8String(buf, quest.getUniqueName());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        isSenderClient = buf.readBoolean();
        stage = buf.readShort();
        quest = HFApi.QUESTS.get(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public IMessage onMessage(PacketQuestSetStage message, MessageContext ctx) {
        if (message.isSenderClient) {
            QuestHelper.setQuestStage(ctx.getServerHandler().playerEntity, message.quest, message.stage);
        } else {
            QuestHelper.setQuestStage(MCClientHelper.getPlayer(), message.quest, message.stage);
        }

        return null;
    }
}