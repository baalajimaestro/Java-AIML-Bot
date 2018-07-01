import java.io.*;
import org.alicebot.ab.*;
import org.alicebot.ab.utils.*;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
class Thanos extends TelegramLongPollingBot
 {
	private static final boolean TRACE_MODE = false;
	static String botName = "super";
	String response;
	String textLine;
	Bot bot;
	Chat chatSession;
	String resourcesPath;
	public Thanos()
	{
		String resourcesPath = getResourcesPath();
		bot = new Bot("super", resourcesPath);
		chatSession = new Chat(bot);
		bot.brain.nodeStats();
		MagicBooleans.trace_mode = TRACE_MODE;
	}
	public void onUpdateReceived(Update update) 
	{
		if (update.hasMessage() && update.getMessage().hasText()) 
		{

			String textLine = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			if ((textLine == null) || (textLine.length() < 1))
			textLine = MagicStrings.null_input;
			String request = textLine;
			///////Just for Log memes :p Disabled by default
			if (MagicBooleans.trace_mode)
		    System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
			response = chatSession.multisentenceRespond(request);
			SendMessage message = new SendMessage().setChatId(chat_id).setText(response);
			try {
				execute(message); 
			} catch (TelegramApiException e)
		    {
				e.printStackTrace();
			}
		}
	}
	public String getBotUsername() 
	{
        return "baalajimaestro_bot";
    }

    @Override
	public String getBotToken()
	 {
        return "499214987:AAEuP5ppYgQdGQ9YEGi6xcdcglSMLoDbDuw";
	}
	private static String getResourcesPath()
	{
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		System.out.println(path);
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}
 }
 public class Chatbot 
 {
	public static void main(String[] args) 
	{
			
		ApiContextInitializer.init();
			TelegramBotsApi botsApi = new TelegramBotsApi();
			try 
			{
				botsApi.registerBot(new Thanos());
			} catch (TelegramApiException e) 
			{
				e.printStackTrace();
			}
	}
 }

	
