import java.io.*;
import org.alicebot.ab.*;
import org.alicebot.ab.utils.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Chatbot extends JFrame implements ActionListener{
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
public Chatbot()
{
	String resourcesPath = getResourcesPath();
    JPanel p=new JPanel(new GridLayout(3,2,75,75));
		b=new JButton("Send");
		l=new JLabel("");
		l2=new JLabel("You: ");
		l1=new JLabel("Alice: ");
		t=new JTextField("");
		p.add(l1);
		p.add(l);
		p.add(l2);
		p.add(t);
		p.add(b);
		setContentPane(p);
		b.addActionListener(this);
		bot = new Bot("super", resourcesPath);
		chatSession = new Chat(bot);
		bot.brain.nodeStats();
		MagicBooleans.trace_mode = TRACE_MODE;
}
public void actionPerformed(ActionEvent ee)
{
	if(ee.getSource()==b)
	{
		textLine = t.getText();
		System.out.println(textLine);
		if ((textLine == null) || (textLine.length() < 1))
			textLine = MagicStrings.null_input;
		if (textLine.equals("q")) {
			bot.writeQuit();
		} else if (textLine.equals("wq")) {
			bot.writeQuit();
			System.exit(0);
		} else {
			String request = textLine;
			if (MagicBooleans.trace_mode)
				System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
			response = chatSession.multisentenceRespond(request);
			l.setText(response);
}
}
}
	public static void main(String[] args)
	 {
		 LoginToChatBot cc=new LoginToChatBot();
		 cc.setSize(500,500);
 	   cc.setVisible(true);
     JFrame.setDefaultLookAndFeelDecorated(true);
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
