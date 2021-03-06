package joshie.harvest.calendar.command;

import joshie.harvest.calendar.CalendarHelper;
import joshie.harvest.core.commands.AbstractHFCommand;
import joshie.harvest.core.commands.HFCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import static joshie.harvest.calendar.HFCalendar.TICKS_PER_DAY;

@HFCommand
@SuppressWarnings("unused")
public class HFCommandNewDay extends AbstractHFCommand {
    @Override
    public String getCommandName() {
        return "newDay";
    }

    @Override
    public String getUsage() {
        return "/hf newDay";
    }

    @Override
    public boolean execute(MinecraftServer server, ICommandSender sender, String[] parameters) {
        long i = sender.getEntityWorld().getWorldTime() + TICKS_PER_DAY;
        CalendarHelper.setWorldTime(server, (i - i % TICKS_PER_DAY) - 1);
        return true;
    }
}