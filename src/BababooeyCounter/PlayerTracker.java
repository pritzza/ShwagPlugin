package me.pritzza.swagplugin.BababooeyCounter;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.HashMap;

public class PlayerTracker implements Listener {

    static final String PLAYER_TRACKER_FILENAME = "playerCounter.txt";
    static final int PLAYER_NAME_COL = 0;
    static final int PLAYER_COUNT_COL = 1;
    static HashMap<String, Integer> counter = new HashMap<String, Integer>();

    public PlayerTracker()
    {
        try {
            File file = new File(PLAYER_TRACKER_FILENAME);
            file.createNewFile();

        } catch (IOException e) {
            System.out.println("An error occurred in working with playerCounter file.");
            e.printStackTrace();
        }
    }

    private int getCount(final String playerName) throws IOException
    {
        BufferedReader fileReader = null;
        String line;

        int count = 0;

        try
        {
            fileReader = new BufferedReader(new FileReader(PLAYER_TRACKER_FILENAME));

            while ((line = fileReader.readLine()) != null) {
                String[] data = line.split(" ");

                if (data[PLAYER_NAME_COL].equals(playerName)) {
                    count = Integer.parseInt(data[PLAYER_COUNT_COL]);
                    break;
                }
            }

            fileReader.close();
        } finally { }

        return count;
    }

    private void writeCount(final String playerName, final int count) throws IOException
    {
        File fileToBeModified = null;
        BufferedReader reader = null;
        FileWriter writer = null;

        try
        {
            fileToBeModified = new File(PLAYER_TRACKER_FILENAME);
            reader = new BufferedReader(new FileReader(fileToBeModified));

            String oldContent = "";
            int oldCount = -1;

            boolean isPlayerRegistered = false;

            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] data = line.split(" ");

                if (data[PLAYER_NAME_COL].equals(playerName))
                {
                    isPlayerRegistered = true;
                    oldCount = Integer.parseInt(data[PLAYER_COUNT_COL]);
                }

                oldContent += line + System.lineSeparator();
            }

            String newContent;

            if (isPlayerRegistered)
            {
                // replace old existing record
                String oldString = playerName + " " + oldCount;
                String newString = playerName + " " + count;

                newContent = oldContent.replaceAll(oldString, newString);
            }
            else
            {
                // make new record
                newContent = oldContent + playerName + " " + count;
            }

            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);

            reader.close();
            writer.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event)
    {
        final String message = event.getMessage();
        final String playerName = event.getPlayer().getName();

        final String COUNTING_WORD = "bababooey";

        if (message.equals(COUNTING_WORD))
        {
            counter.put(playerName, counter.get(playerName) + 1);

            event.getPlayer().sendMessage("counter:" + counter.get(playerName));
        }
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        String playerName = event.getPlayer().getName();

        int playerCount = 0;

        try {
            playerCount = getCount(playerName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        counter.put(playerName, playerCount);
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event)
    {
        String playerName = event.getPlayer().getName();

        final int count = counter.get(playerName);

        try {
            writeCount(playerName, count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
