import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ProiectPOO {
    public static void main(String[] args) {
        ProiectPOO proiectPOO = new ProiectPOO();
        String streamersFile = System.getProperty("user.dir") + "/src/main/resources/";
        String streamsFile = System.getProperty("user.dir") + "/src/main/resources/";
        String usersFile = System.getProperty("user.dir") + "/src/main/resources/";
        String commandsFile = System.getProperty("user.dir") + "/src/main/resources/";
        if(args == null) {
            System.out.println("Nothing to read here");
        } else {
            streamersFile += args[0];
            streamsFile += args[1];
            usersFile += args[2];
            commandsFile += args[3];
            EntityFactory entityFactory = new EntityFactory();
            HashMap<Integer, Entity> users = entityFactory.createEntity("USERS", usersFile);
            HashMap<Integer, Entity> streamers = entityFactory.createEntity("STREAMERS", streamersFile);
            StreamsFacade streamsFacade = new StreamsFacade();
            streamsFacade.users = users;
            streamsFacade.streamers = streamers;
            streamsFacade.streams = streamsFacade.constructStreams(streamsFile);
            proiectPOO.processComands(commandsFile, streamsFacade);
        }
    }
    public void processComands(String commandsFile, StreamsFacade streamsFacade) {
        try(BufferedReader reader = new BufferedReader(new FileReader(commandsFile))) {
            String line;
            CommandInvoker invoker = new CommandInvoker();
            while((line = reader.readLine()) != null) {
                String[] command = line.split(" ");
                if(command[1].equals("LIST")) {
                    invoker.execute("list", line, streamsFacade);
                }else if(command[1].equals("ADD")) {
                    invoker.execute("add", line, streamsFacade);
                }else if(command[1].equals("DELETE")) {
                    invoker.execute("delete", line, streamsFacade);
                } else if(command[1].equals("LISTEN")) {
                    invoker.execute("listen", line, streamsFacade);
                } else if(command[1].equals("RECOMMEND")) {
                    invoker.execute("recommend", line, streamsFacade);
                } else if(command[1].equals("SURPRISE")) {
                    invoker.execute("surprise", line, streamsFacade);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
