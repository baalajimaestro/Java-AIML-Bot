import java.io.*;
import org.alicebot.ab.*;
import org.alicebot.ab.utils.*;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.TelegramBotsApi;
import java.util.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
class BotHandler extends TelegramLongPollingBot
 {
	private static final boolean TRACE_MODE = false;
	static String botName = "super";
	String response;
	String textLine;
	Bot bot;
	Chat chatSession;
	String resourcesPath;
	public BotHandler()
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
			
			if(request.equals("/start"))
			{
				Random randseed=new Random();
				int briseed=randseed.nextInt(5);
				String[] welcomemsg=new String[5];
				welcomemsg[0]="Hello, Nice to meet you!";
				welcomemsg[1]="Hope you enjoy your time chatting with me!";
				welcomemsg[2]="Some issue with Bot Protocol. Fixing up..............Okay Done!! I must say Hello now!";
				welcomemsg[3]="Virus Detected! Please uninstall Telegram to continue..... Doing self cleanup.........Done............Virus Eliminated........Yaay!!";
				welcomemsg[4]="Hello! Mic Testing 1----2----3-----$echo Working";
				response = welcomemsg[briseed];
			}
			else if(request.contains("Call")||request.contains("Send message"))
			{
				response="Abe jaa na! Yeh to telegram bot hai. Tera google assistant se yeh poocho";
			}
			else if(response.equals("/cmds"))
				{
					response="Just try hitting upon a random chat with me or Type Wordplay, DrawGrid, LuckySlots, Knock Knock, Blackjack, Zbert, Hangman, Horoscope, TicTacToe to play with me. Much more games are inside. Discover them ;)";
				}
			else
			{
				response = chatSession.multisentenceRespond(request);
				if(response.contains("<"))
				{
					response="Some Internal Error! Report with screenshot to t.me/baalajimaestro";
				}
				
			}
			SendMessage message = new SendMessage().setChatId(chat_id).setText(response);
			try {
				execute(message); 
			} 
			catch (TelegramApiException e)
		    {
				e.printStackTrace();
			}
		}
	}
	public String getBotUsername() 
	{
        return BOT_USERNAME;
    }

    @Override
	public String getBotToken()
	 {
        return BOT_TOKEN;
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
				botsApi.registerBot(new BotHandler());
			} catch (TelegramApiException e) 
			{
				e.printStackTrace();
			}
	}
 }
