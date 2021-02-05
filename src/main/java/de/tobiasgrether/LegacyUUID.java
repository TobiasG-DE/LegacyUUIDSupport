package de.tobiasgrether;

import com.google.gson.JsonElement;
import dev.waterdog.event.defaults.PreClientDataSetEvent;
import dev.waterdog.plugin.Plugin;

import java.util.UUID;

public class LegacyUUID extends Plugin {

    @Override
    public void onEnable() {
        this.getProxy().getEventManager().subscribe(PreClientDataSetEvent.class, this::handleClientDataSet);
    }

    public void handleClientDataSet(PreClientDataSetEvent evt) {
        JsonElement xuid = evt.getExtraData().get("XUID");
        if (xuid != null) {
            evt.getExtraData().remove("identity"); // Remove the previous UUID
            evt.getExtraData().addProperty("identity", new UUID(0, xuid.getAsLong()).toString()); // the most sig. bits are 0'ed, the least sig bits are the players XUID
        }
    }
}
