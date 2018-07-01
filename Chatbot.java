import java.io.*;
import org.alicebot.ab.*;
import org.alicebot.ab.utils.*;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
public class Chatbot extends TelegramLongPollingBot
 {
	private static final boolean TRACE_MODE = false;
	static String botName = "super";
	JButton b;
	JLabel l1,l2,l;
	JTextField t;
	String response;
	String textLine;
	Bot bot;
	Chat chatSession;
	String resourcesPath;
	public void onUpdateReceived(Update update) 
	{
		if (update.hasMessage() && update.getMessage().hasText()) 
		{
			String textLine = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			if ((textLine == null) || (textLine.length() < 1))
			textLine = MagicStrings.null_input;
			String request = textLine;
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
}
	public static void main(String[] args) 
	{
		        String resourcesPath = getResourcesPath();
			ApiContextInitializer.init();
			TelegramBotsApi botsApi = new TelegramBotsApi();
			try 
			{
				botsApi.registerBot(new MyAmazingBot());
			} catch (TelegramApiException e) 
			{
				e.printStackTrace();
			}
		}
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
