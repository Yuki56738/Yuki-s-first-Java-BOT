import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

import static java.lang.System.*;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, InterruptedException {
        Dotenv dotenv = Dotenv.load();
        JDA jda = JDABuilder.createDefault(dotenv.get("DISCORD_TOKEN"))
                .addEventListeners(new Main())
                .setActivity(Activity.playing(".yuki"))
                .build();
        jda.awaitReady();
        out.println("Built JDA.");
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        out.println("Message received.");
        if (event.isFromType(ChannelType.TEXT)) {
            User author = event.getAuthor();
            String name = author.getName();
            Message message = event.getMessage();
            String msg = message.getContentDisplay();
            TextChannel textChannel = event.getTextChannel();
            MessageChannel channel = event.getChannel();
            if (author.isBot() == false) {
                out.println(msg);
                if (msg.equals(".yuki")) {
                    channel.sendMessage("Pong!").queue();
                }
            }
        }
    }
}
