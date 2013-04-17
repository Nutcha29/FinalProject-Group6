package example;

import iceworld.given.ICEWorldImmigration;
import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import iceworld.given.ICEWorldImmigration;
import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

//import view.EngineWindow;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class LoginScreen1 extends JFrame
{	/**
 * 
 */
	private static final long serialVersionUID = 1L;
	
	//Prinn start
	BufferedImage sky=null;
	ImageIcon skyIcon;
			private		JPanel		panel1;
			private		JPanel		panel2;
			private		JPanel		panel3;
			private		JPanel		panel4;
			private		JPanel		panel5;
			private		JPanel		panel6;
			private		JFrame		frame1;
			private JLabel author_image;
			private JDialog helpJDialog;
			private JDialog HTMLJDialog;
			private JEditorPane jep;
			Font font = new Font("TimesRoman",Font.BOLD,18);
			//Prinn end
	public JTextField userField; 
	public JPasswordField passwordField; 
	JButton b1;
	static JMenuBar menuBar;
	static JMenu file,about;
	static JMenuItem exitItem,helpItem,aboutItem,customizationItem;

	static String result;
	static char quotationMark = '"';
	static char colon = ':';
	static char comma = ',';
	static char endBracket = '}';
	IcetizenLook look = new IcetizenLook();
	Myicetz tester = new Myicetz();
	//check ninja[]= new check[200];
	int uid;
	String accessToken;
	JScrollPane scrollpane;
	public ICEWorldImmigration immigration;

	public LoginScreen1() throws FileNotFoundException
	{
		setSize(1000,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setJMenuBar(createMenuBar());
		setLayout(new BorderLayout());

		  try {
		   sky = ImageIO.read(new File("Sky_Splash.jpg"));
		  } catch (IOException e1) {
		   // TODO Auto-generated catch block
		   e1.printStackTrace();
		  }

		  sky = resize(sky,1000,700);
		  skyIcon = new ImageIcon(sky);
		  setContentPane(new JLabel(skyIcon));
		
		setLayout(new FlowLayout());
		addListeners();


		JLabel userLabel = new JLabel("USERNAME : "); 
		userLabel.setForeground(Color.WHITE); 
		userField = new JTextField(); 




		JLabel passLabel = new JLabel("PASSWORD : "); 
		passLabel.setForeground(Color.WHITE); 
		passwordField = new JPasswordField();




		b1=new JButton("Login");
		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){

				{	tester = new Myicetz();
				URL a;
				String result="";

				tester.setIcePortID(255);
				tester.setListeningPort(10011);

				immigration = new ICEWorldImmigration((iceworld.given.MyIcetizen) tester);	
				String username = userField.getText();
				tester.setUsername(username);
				String password = new String(passwordField.getPassword());
				try {
					a = new URL("http://iceworld.sls-atl.com/api/immigration?cmd=login&username="+username+"&code="+password+"&pid=255&listeningport=0");
					URLConnection yc = a.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
					String inputLine;

					while ((inputLine = in.readLine()) != null) {
						result += inputLine+"\n";
					}
					in.close();		
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(result);
				int index = result.lastIndexOf(",");
				int uid=Integer.parseInt(result.substring(27,index-1));


				
				if (immigration.login(password))
				{
					System.out.println("Login OK");//new IcePort("MY ICE PORT DELTA!");
					//if(immigration.)
					//boolean a =((tester.getIcetizenLook().gidB)==null);
					//System.out.println(a);
					//Map json = (Map)this.parser.parse(inputLine, this.containerFactory);
					// Map jsonData = (Map)json.get("data");
					//System.out.println(Integer.parseInt(jsonData.get("uid").toString()));
					String[] exampleLookList=getGlist(uid);
					System.out.println(uid);
					look = new IcetizenLook();
					System.out.println("hello");
					System.out.println(exampleLookList[0]);
					String b ="One or more gids are invalid.";

					//System.out.println(b);
					immigration.walk(85, 85);
					//if(tester.getIcetizenLook().gidB==null){
					if(exampleLookList[0].equals("null")){
						System.out.println("can get in this");
						look.gidB = "B001";
						look.gidS = "S020";
						look.gidH = "H009";
						look.gidW = "W045";
						tester.setIcetizenLook(look);
						immigration.customization(look);

						System.out.println("Completely set");
					} else {
						String[] fLook = getGlist(uid);
						look.gidB = fLook[0];
						look.gidS = fLook[1];
						look.gidH = fLook[2];
						look.gidW = fLook[3];
						tester.setIcetizenLook(look);
						System.out.println("Loading current look");
					}
					/*
				System.out.println(tester.getIcetizenLook().gidB);
				System.out.println(tester.getIcetizenLook().gidH);
				System.out.println(tester.getIcetizenLook().gidS);
				System.out.println(tester.getIcetizenLook().gidW);
					 */
					//TestEngineWindow te = new TestEngineWindow(tester,uid);
					/*Custimization custom = new Custimization();
				custom.customizedScreen(tester,immigration);*/
					//	BufferedWriter bufferedWriter = null;

					/* try {

		            //Construct the BufferedWriter object
		           bufferedWriter = new BufferedWriter(new FileWriter("Username.txt"));

		            //Start writing to the output stream
		            Scanner scanner1 = new Scanner(new File("Username.txt"));
		            while (scanner1.hasNext()){
		            	scanner1.nextLine();
		            	bufferedWriter.newLine();
		            }


		            bufferedWriter.write(username);
		            //bufferedWriter.write("Writing line two to file");

		        } catch (FileNotFoundException ex) {
		            ex.printStackTrace();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        } finally {
		            //Close the BufferedWriter
		            try {
		                if (bufferedWriter != null) {
		                    bufferedWriter.flush();
		                    bufferedWriter.close();
		                }
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }*/

					File log = new File("Username.txt");

					try{
						if(!log.exists()){
							System.out.println("We had to make a new file.");
							//log.createNewFile();
							 Writer writer = null;

						        try {
						            String text = "";

						            File file = new File("Username.txt");
						            writer = new BufferedWriter(new FileWriter(file));
						            writer.write(text);
						        } catch (FileNotFoundException e2) {
						            e2.printStackTrace();
						        } catch (IOException e2) {
						            e2.printStackTrace();
						        } finally {
						            try {
						                if (writer != null) {
						                    writer.close();
						                }
						            } catch (IOException ee) {
						                ee.printStackTrace();
						            }
						        }
						    }
						

						FileWriter fileWriter = new FileWriter(log, true);

						BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
						if(checking(username)){
							bufferedWriter.newLine();
							bufferedWriter.write(username);
							bufferedWriter.close();}

						System.out.println("Remember this user");
					} catch(IOException e1) {
						System.out.println("COULD NOT LOG!!");
					}




/*
					araikordai ror = new araikordai(tester,immigration);
					ror.setVisible(true);
					dispose();
					*/
					TestEngineWindow te = new TestEngineWindow();
					te.setIcetizen(tester);
					te.setPlayerUID(uid);
					te.setImmigration(immigration);
					System.out.println(":::::::::::"+uid+":::::::::::");
					dispose();
				}else{	
					Scanner scanner;
					try {
						scanner = new Scanner(new File("Username.txt"));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					/*while (scanner.hasNextLine()) {
						for(int i = 0; i<user.length; i++){
						user[i]= scanner.nextLine();
						if(ninja[i].getUsername() == line && ninja[i].count()==3){
						ninja[i].count();

						}
						}

						}*/

				}

				System.out.print("Bye");

				//System.out.println(tester.getIcetizenLook().gidB);



				//EngineWindow ew = new EngineWindow(te);
				/*
			sc1 = new IcePort("MY ICE PORT DELTA!");
			s1 = new Thread(sc1);
			s1.start();
				 */



				}
				{

				}

			}

		});


		Scanner scanner = new Scanner(new File("Username.txt"));
		int count=0;
		while (scanner.hasNextLine()) {
			count++;

			String line = scanner.nextLine();
			System.out.println(line);

		}
		String []user = new String[count];
		Scanner scanner1 = new Scanner(new File("Username.txt"));
		while (scanner1.hasNextLine()) {
			for(int i = 0; i<user.length; i++){
				user[i]= scanner1.nextLine();
			}

			//String line = scanner.nextLine();
			//System.out.println(line);

		}



		setLayout(null);
		userLabel.setBounds(395, 502, 87, 25);
		add(userLabel);
		userField.setBounds(509, 500, 134, 28);
		add(userField);
		boolean sc = false;
		userField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				scrollpane.setVisible(true);
			}
		});

		passLabel.setBounds(395, 543, 87, 16);
		add(passLabel);
		passwordField.setBounds(509, 540, 134, 22);
		add(passwordField);
		passwordField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				scrollpane.setVisible(false);
			}
		});




		final JList list = new JList(user);
		scrollpane = new JScrollPane(list);
		add(scrollpane);
		scrollpane.setVisible(sc);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {


					String selectedItem = (String) list.getSelectedValue();
					userField.setText(selectedItem);
					scrollpane.setVisible(false);}
			}
		});


		scrollpane.setBounds(640,503,134,100);

		JButton btnLoginAsAlien = new JButton("Login as Alien");
		btnLoginAsAlien.setBounds(388, 575, 161, 25);
		add(btnLoginAsAlien);
		btnLoginAsAlien.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){

				{	Myicetz tester = new Myicetz();
				tester.setIcePortID(255);
				tester.setListeningPort(10011);
				//tester.setUsername("Rockyear");
				immigration = new ICEWorldImmigration((iceworld.given.MyIcetizen) tester);	
				//if (immigration.loginAlien()){
					//System.out.print(immigration.loginAlien());
					System.out.println();
					//immigration.loginAlien();
					System.out.println("Login Alien OK");
					/*
					araikordai ror = new araikordai(tester,immigration);
					ror.setVisible(true);
					*/
					/*
			sc1 = new IcePort("MY ICE PORT DELTA!");
			s1 = new Thread(sc1);
			s1.start();
					 */
					URL a;
					String result="";
					/*
					this.uid = Integer.parseInt(jsonData.get("uid").toString());
				    this.accessToken = jsonData.get("access_token").toString();
				    */
					try {
						a = new URL("http://iceworld.sls-atl.com/api/immigration?cmd=login&alien=true&pid=255");
						URLConnection yc = a.openConnection();
						BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
						String inputLine;

						while ((inputLine = in.readLine()) != null) {
							result += inputLine+"\n";
						}
						in.close();		
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(result);
					int index = result.lastIndexOf(",");
					int uid=Integer.parseInt(result.substring(27,index-1));
					
					TestEngineWindow te = new TestEngineWindow();
					te.setIcetizen(tester);
					te.setPlayerUID(uid);
					te.setImmigration(immigration);
					System.out.println(":::::::::::"+uid+":::::::::::");
					dispose();
					//dispose();
				//}

				}
			}
		});



		b1.setBounds(550, 573, 97, 28);
		add(b1);



		SwingUtilities.updateComponentTreeUI(this);
	}
	public void getInAlien(){
		
	}
	protected JMenuBar createMenuBar() {
		menuBar = new JMenuBar();
		//menuBar.setBackground(Color.BLACK);
		file = new JMenu("File");
		//file.setBackground(Color.black);
		about = new JMenu("Help");
		//about.setBackground(Color.BLACK);
		exitItem = new JMenuItem("Exit");
		helpItem = new JMenuItem("View Help");
		aboutItem = new JMenuItem("About");
		//customizationItem = new JMenuItem("IceTizen Customization");

		file.add(exitItem);
		about.add(helpItem);
		about.add(aboutItem);
		menuBar.add(file);
		menuBar.add(about);
		//about.add(customizationItem);
		add(menuBar);
		return menuBar;
	}
	private void addListeners()
	{ exitItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			int confirmed = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to exit?", "User Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (confirmed == JOptionPane.YES_OPTION)
				//immigration.logout();
				System.exit(0);

		}
	});

	/*exitItem.addActionListener(new ActionListener()
	  {
	  public void actionPerformed(ActionEvent e)
	  {
	  System.exit(0);
	  }
	  });*/
	helpItem.setMnemonic(KeyEvent.VK_F1);
	helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
	helpItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame helpJFrame = new JFrame("Help");
			helpJDialog = new JDialog(helpJFrame,"Help");
			//exit when press escape key
			/*
	  helpJDialog.addKeyListener(new KeyAdapter(){
	  public void keyPressed(KeyEvent ke) {
	  int key = ke.getKeyCode();
	  if(key == KeyEvent.VK_ESCAPE){
	  //System.out.println("escape pressed on about");
	  helpJDialog.setVisible(false);
	  helpJDialog.dispose();
	  }
	  }
	  });
			 */
			JTabbedPane helpTab = new JTabbedPane();
			JPanel helpPanel = new JPanel();
			//help.add(helpPanel, BorderLayout.CENTER);
			//helpPanel.setBackground(Color.BLUE);
			//helpPanel.add(helpTab);
			helpJDialog.setLayout(new BorderLayout());
			helpPanel.add(helpTab, BorderLayout.CENTER);
			//helpTab.addTab(title, component)
			// Create the tab pages
			
			createPage1();
			createPage2();
			createPage3();
			createPage4();
			createPage5();
			createPage6();
			
			// Create a tabbed pane

			helpTab.addTab( "Logging In/Out", panel1 );
			helpTab.addTab( "The look of things", panel2 );
			helpTab.addTab( "Customization", panel3 );
			helpTab.addTab( "Sounds", panel4 );
			helpTab.addTab( "File Transferring", panel5 );
			helpTab.addTab( "Don't know SHIT!", panel6 );

			/*
	  JPanel helpPanel = new JPanel();
	  help.add(helpPanel, BorderLayout.CENTER);
	  JTabbedPane helpTab = new JTabbedPane();
	  JPanel helpPage1 = new JPanel();
	  JPanel helpPage2 = new JPanel();
	  JPanel helpPage3 = new JPanel();
	  ImageIcon icon = new ImageIcon("mog.JPG");
	  helpPage1.setBackground(Color.BLACK);
	  helpTab.addTab("page1", icon, helpPage1, "Tab1" );
	  helpPanel.add(helpTab, BorderLayout.CENTER);
	  helpTab.addTab( "Page 1", helpPage1 );
	  helpTab.addTab( "Page 2", helpPage2 );
	  helpTab.addTab( "Page 3", helpPage3 );
	  //JPanel panel = new JPanel();
	  //JLabel help_text = new JLabel();
	  //help_text.setFont(font);
	  //help_text.setText("I can't help u");
	  help.setLayout(new BorderLayout());
	  //panel.add(help_text);
	  //help_text.setAlignmentX(JComponent.CENTER_ALIGNMENT);
	  //help.add(panel);
			 */
			//helpJDialog.setModal(true);
			helpJDialog.add(helpPanel,BorderLayout.CENTER);
			helpJDialog.setPreferredSize(new Dimension(1000,600));
			helpJDialog.pack();
			helpJDialog.setVisible(true);
			helpJDialog.setModal(false);
		}
	});
	aboutItem.setMnemonic(KeyEvent.VK_F2);
	aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
	aboutItem.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame aboutJFrame = new JFrame("About");
			/*
			aboutJFrame.addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent ke) {
					int key = ke.getKeyCode();
					if(key == KeyEvent.VK_ESCAPE){
						//System.out.println("escape pressed on about");
						aboutJFrame.setVisible(false);
						aboutJFrame.dispose();
					}
				}
			});
			 */
			BufferedImage myImage = null;
			try {
				myImage = ImageIO.read(new File("Pian.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				//System.out.println("Cannot find image");
			}
			myImage = resize(myImage,500,700);

			ImageIcon imageIcon = new ImageIcon(myImage);

			//resizedIMage = imageIcon.getImage();

			JPanel imagePanel = new JPanel(new GridLayout(1,1,10,10));
			//image = ImageIO.read(new File("D:\\Project pictures\\Ja.jpg"));
			author_image = new JLabel(imageIcon);
			aboutJFrame.setLayout(new BorderLayout());
			imagePanel.add(author_image);
			//author_image.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			//author_image.setFont(font);
			aboutJFrame.add(imagePanel);
			aboutJFrame.setPreferredSize(new Dimension(500,700));
			aboutJFrame.pack();
			aboutJFrame.setVisible(true);
		} 
	}
			);
	//customizationItem.setMnemonic(KeyEvent.VK_F3);
	//customizationItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
	//customizationItem.addActionListener(new ActionListener());
	}

	public static boolean checking(String s) throws FileNotFoundException{
		boolean c =true;
		Scanner scanner1 = new Scanner(new File("Username.txt"));
		while (scanner1.hasNextLine()) {

			if (s.equals (scanner1.nextLine())){
				c=false;
			}


		}
		return c;
	}

	public static String ProcessReq(String req) throws MalformedURLException, IOException{

		String result = "";

		if (req.indexOf(' ') == -1){
			URL a = new URL("http://iceworld.sls-atl.com/api/&cmd="+req);
			URLConnection yc = a.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				result += inputLine+"\n";
			}
			in.close();
		}else{
			result = ProcessReq(req.substring(0,req.indexOf(' ')),req.substring(req.indexOf(' ')+1,req.length()));
		}
		return result;
	}

	public static String ProcessReq(String req1, String req2) throws MalformedURLException, IOException{
		String result="";
		String s = "";
		if(req1.equalsIgnoreCase("actions")){
			s=req1+"&from="+req2;
		} else if(req1.equalsIgnoreCase("gresources")){
			s=req1+"&uid="+req2;
		} else if(req1.equalsIgnoreCase("gurl")){
			s=req1+"&gid="+req2;
		}

		URL a = new URL("http://iceworld.sls-atl.com/api/&cmd="+s);
		URLConnection yc = a.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			result += inputLine+"\n";
		}
		in.close();
		return result;
	}
	public String[] subArrayString(String[] a, int s,int e){
		String[] r = new String[e-s];
		for (int i=0;i<e-s;i++){
			r[i]=a[i+s];
		}
		return a;
	}

	//resize picture
	public static BufferedImage resize(BufferedImage image, int width, int height) {
		int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	} 

	public static String[] getGlist(int n){
		String[] lookList = null;
		try {
			lookList = refineGraphicResouces(ProcessReq("gresources "+n));
			for (int i=0;i<lookList.length;i++){
				if(lookList[i]!="null"){
					lookList[i]=removeQuotationMark(lookList[i]);
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lookList;
	}
	public static String[] refineGraphicResouces(String s){
		String [] iLook = new String [4];
		int cCnt;
		int itr;

		cCnt=0;
		itr=0;
		for (int i=0;i<s.length();i++){
			if (s.charAt(i) == colon){
				cCnt += 1;
				switch (cCnt) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					itr=i+1;
					break;
				case 4:
					//username
					iLook[0]= s.substring(itr,i-4);
					itr=i+1;
					break;
				case 5:
					//type
					iLook[1]= s.substring(itr,i-4);
					itr=i+1;
					break;
				case 6:
					//ip
					iLook[2]= s.substring(itr,i-4);
					itr=i+1;
					iLook[3]= s.substring(itr,s.length()-4);
					break;
				default:
					iLook=null;
					break;
				}
			}
		}
		return iLook;
	}
	public static String[] refineUserData(String[] s){
		String[] r = new String[s.length];
		for(int i = 0;i<s.length;i++){
			r[i]=removeQuotationMark(s[i]);
		}
		for(int i = 0;i<s.length;i++){
			if(s[i].equalsIgnoreCase("null")){
				r[i]=null;
			}
		}
		return r;
	}
	public static String removeQuotationMark(String s){
		String r="";
		for (int i=0;i<s.length();i++){
			if (s.charAt(i) != quotationMark){
				r += s.charAt(i);
			}
		}
		return r;
	}
	public static String[] processUserAttr(String u) throws MalformedURLException, IOException{
		String [] attrList = new String [8];
		int cCnt;
		int itr;

		cCnt=0;
		itr=0;
		for (int i=0;i<u.length();i++){
			if (u.charAt(i) == colon){
				cCnt += 1;
				switch (cCnt) {
				case 1:
					//number
					attrList[0]= u.substring(0,i);
					break;
				case 2:
					break;
				case 3:
					itr=i+1;
					break;
				case 4:
					//username
					attrList[1]= u.substring(itr,i-7);
					itr=i+1;
					break;
				case 5:
					//type
					attrList[2]= u.substring(itr,i-5);
					itr=i+1;
					break;
				case 6:
					//ip
					attrList[3]= u.substring(itr,i-7);
					itr=i+1;
					break;
				case 7:
					//port
					attrList[4]= u.substring(itr,i-6);
					itr=i+1;
					break;
				case 8:
					attrList[5]= u.substring(itr,i-26);
					break;
				case 9:
					itr=i+1;
					break;
				case 10:
					attrList[6]= u.substring(itr,i-12);
					attrList[7]= u.substring(i+1,u.lastIndexOf("}")-1);
					break;
				default:
					attrList=null;
					break;
				}
			}
		}
		String a = ProcessReq("gresources "+removeQuotationMark(attrList[0]));
		System.out.println(a);
		return attrList;
	}
	public static String[] getUserList() throws MalformedURLException, IOException{
		int firstUserPivot=0;
		int qCnt;
		int cCnt;
		int userAmount;
		int [][] pivot;
		int pCnt=0;
		int pIndex=0;
		int [][] uPivot;
		int counter;
		int uIndex=0;
		result = ProcessReq("states");

		//backup 367	
		//result="{\"status\":1,\"data\":{\"weather\":{\"condition\":\"Snowing\",\"last_change\":1365444420},\"icetizen\":{\"1083\":{\"user\":{\"username\":\"Aerodualfried\",\"type\":0,\"ip\":\"58.11.37.3\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1084\":{\"user\":{\"username\":\"Licor\",\"type\":0,\"ip\":\"171.99.86.41\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1085\":{\"user\":{\"username\":\"Robspace\",\"type\":0,\"ip\":\"27.55.129.52\",\"port\":\"0\",\"pid\":\"250\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1086\":{\"user\":{\"username\":\"Mobilematchashad\",\"type\":0,\"ip\":\"27.55.129.52\",\"port\":\"0\",\"pid\":\"250\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"37\":{\"user\":{\"username\":\"Veeriya.R\",\"type\":1,\"ip\":\"27.55.129.52\",\"port\":\"0\",\"pid\":\"250\"},\"last_known_destination\":{\"position\":\"(40,30)\",\"timestamp\":\"1365415348\"}},\"1087\":{\"user\":{\"username\":\"Crazybust\",\"type\":0,\"ip\":\"171.99.84.136\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1088\":{\"user\":{\"username\":\"Galaxyboxcentury\",\"type\":0,\"ip\":\"171.99.84.136\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"20\":{\"user\":{\"username\":\"Prinn.P\",\"type\":1,\"ip\":\"180.183.47.219\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":\"(105,50)\",\"timestamp\":\"1365422475\"}},\"28\":{\"user\":{\"username\":\"Putti.O\",\"type\":1,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":\"(-5,40)\",\"timestamp\":\"1365420539\"}},\"1089\":{\"user\":{\"username\":\"Melonmetal\",\"type\":0,\"ip\":\"180.183.47.219\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1090\":{\"user\":{\"username\":\"Trihungryweib\",\"type\":0,\"ip\":\"180.183.47.219\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1091\":{\"user\":{\"username\":\"Jutsunuclear\",\"type\":0,\"ip\":\"180.183.47.219\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1092\":{\"user\":{\"username\":\"Megalu\",\"type\":0,\"ip\":\"180.183.47.219\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"35\":{\"user\":{\"username\":\"Waris-sara.B\",\"type\":1,\"ip\":\"180.183.40.180\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":\"(80,80)\",\"timestamp\":\"1365424696\"}},\"1093\":{\"user\":{\"username\":\"Visionholo\",\"type\":0,\"ip\":\"171.99.87.188\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1094\":{\"user\":{\"username\":\"Kakalijohn\",\"type\":0,\"ip\":\"171.99.87.188\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1095\":{\"user\":{\"username\":\"Hazardpeegiant\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"41\":{\"user\":{\"username\":\"Harin.S\",\"type\":1,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":\"(5,13)\",\"timestamp\":\"1365343928\"}},\"1096\":{\"user\":{\"username\":\"Waterracervodka\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1097\":{\"user\":{\"username\":\"Wickwallet\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1098\":{\"user\":{\"username\":\"Slayereightyhell\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1099\":{\"user\":{\"username\":\"Suzramasixty\",\"type\":0,\"ip\":\"27.55.13.192\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1100\":{\"user\":{\"username\":\"Jokerduke\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"7\":{\"user\":{\"username\":\"Johmphot.T\",\"type\":1,\"ip\":\"27.55.13.192\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1101\":{\"user\":{\"username\":\"Squalleighty\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1102\":{\"user\":{\"username\":\"Megaradio\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1103\":{\"user\":{\"username\":\"Eightyposok\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1104\":{\"user\":{\"username\":\"Hazardliquidhunter\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1105\":{\"user\":{\"username\":\"Lightsha\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1106\":{\"user\":{\"username\":\"-asstankmech\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1107\":{\"user\":{\"username\":\"Binaryjohn\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1108\":{\"user\":{\"username\":\"Heinz-sanjoey\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1109\":{\"user\":{\"username\":\"Boyecho\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1110\":{\"user\":{\"username\":\"Tankyeahbox\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1111\":{\"user\":{\"username\":\"Eaglestrong\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1112\":{\"user\":{\"username\":\"Waterstar\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1113\":{\"user\":{\"username\":\"Dohspace\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1114\":{\"user\":{\"username\":\"Pinkdrinker\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1115\":{\"user\":{\"username\":\"Xamega\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1116\":{\"user\":{\"username\":\"Robnew\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1117\":{\"user\":{\"username\":\"Deltadinomick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1118\":{\"user\":{\"username\":\"Geniusboot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1119\":{\"user\":{\"username\":\"Bootsuz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1120\":{\"user\":{\"username\":\"Shockvick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1121\":{\"user\":{\"username\":\"Nanodualpee\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1122\":{\"user\":{\"username\":\"Jutsumech\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1123\":{\"user\":{\"username\":\"Pimpdiamond\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1124\":{\"user\":{\"username\":\"Crimsonger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1125\":{\"user\":{\"username\":\"Handsomefuji\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1126\":{\"user\":{\"username\":\"Boxninbabel\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1127\":{\"user\":{\"username\":\"Yeahlabeckham\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1128\":{\"user\":{\"username\":\"Holospeed\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1129\":{\"user\":{\"username\":\"Chictarocurry\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1130\":{\"user\":{\"username\":\"Hunternotbeckham\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1131\":{\"user\":{\"username\":\"Sabersam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1132\":{\"user\":{\"username\":\"Laglion\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1133\":{\"user\":{\"username\":\"Tarussmiley\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1134\":{\"user\":{\"username\":\"Deltaavengerwallet\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1135\":{\"user\":{\"username\":\"-F-taro\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1136\":{\"user\":{\"username\":\"Jokerseinroos\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1137\":{\"user\":{\"username\":\"Hawkmidnight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1138\":{\"user\":{\"username\":\"Gigadiamond\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1139\":{\"user\":{\"username\":\"Footultimatesquall\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1140\":{\"user\":{\"username\":\"Stonetiny\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1141\":{\"user\":{\"username\":\"Foolterran\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1142\":{\"user\":{\"username\":\"Efffoot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1143\":{\"user\":{\"username\":\"Fluffycrazycurry\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1144\":{\"user\":{\"username\":\"Poosmileyfluffy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1145\":{\"user\":{\"username\":\"Golu\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1146\":{\"user\":{\"username\":\"Aerothunder\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1147\":{\"user\":{\"username\":\"Godrock\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1148\":{\"user\":{\"username\":\"Towergate\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1149\":{\"user\":{\"username\":\"Clearburger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1150\":{\"user\":{\"username\":\"Saurusrobo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1151\":{\"user\":{\"username\":\"Waterhell\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1152\":{\"user\":{\"username\":\"Zonezionnin\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1153\":{\"user\":{\"username\":\"Fluffyplatinumchic\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1154\":{\"user\":{\"username\":\"Queenavengergeish\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1155\":{\"user\":{\"username\":\"Dragoncurryprince\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1156\":{\"user\":{\"username\":\"Dualliquid\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1157\":{\"user\":{\"username\":\"Friedandroid\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1158\":{\"user\":{\"username\":\"Waterrobhulk\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1159\":{\"user\":{\"username\":\"Shachocoshady\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1160\":{\"user\":{\"username\":\"Moonhandsomesphere\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1161\":{\"user\":{\"username\":\"Dohzawa\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1162\":{\"user\":{\"username\":\"Dudeshady\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1163\":{\"user\":{\"username\":\"Metalvoltage\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1164\":{\"user\":{\"username\":\"Tarusray\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1165\":{\"user\":{\"username\":\"Strongphobia\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1166\":{\"user\":{\"username\":\"Ironyeahlight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1167\":{\"user\":{\"username\":\"Burgerpoo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1168\":{\"user\":{\"username\":\"Ninjafried\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1169\":{\"user\":{\"username\":\"Beckyziz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1170\":{\"user\":{\"username\":\"Greenman\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1171\":{\"user\":{\"username\":\"Latobi\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1172\":{\"user\":{\"username\":\"Shadylunar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1173\":{\"user\":{\"username\":\"Madbeebeast\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1174\":{\"user\":{\"username\":\"Byzonefried\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1175\":{\"user\":{\"username\":\"Dragonradio\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1176\":{\"user\":{\"username\":\"-F-avengerroon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1177\":{\"user\":{\"username\":\"Magiceagledoh\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1178\":{\"user\":{\"username\":\"Logyfujibottom\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1179\":{\"user\":{\"username\":\"Phobiatriblue\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1180\":{\"user\":{\"username\":\"Friedmad\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1181\":{\"user\":{\"username\":\"Keeper-F-\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1182\":{\"user\":{\"username\":\"Twentyking\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1183\":{\"user\":{\"username\":\"Hunterdragmetal\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1184\":{\"user\":{\"username\":\"Draggate\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1185\":{\"user\":{\"username\":\"Joeybad\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1186\":{\"user\":{\"username\":\"Corewimpy-ass\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1187\":{\"user\":{\"username\":\"Truebox\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1188\":{\"user\":{\"username\":\"Ultimateecho\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1189\":{\"user\":{\"username\":\"Solidboy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1190\":{\"user\":{\"username\":\"Goddragon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1191\":{\"user\":{\"username\":\"Pizzasuper\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1192\":{\"user\":{\"username\":\"Speedsilly\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1193\":{\"user\":{\"username\":\"Lightvodkamech\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1194\":{\"user\":{\"username\":\"Newlightduke\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1195\":{\"user\":{\"username\":\"Boyankee\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1196\":{\"user\":{\"username\":\"Zizcobratough\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1197\":{\"user\":{\"username\":\"Nikephon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1198\":{\"user\":{\"username\":\"Boxfluffyheinz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1199\":{\"user\":{\"username\":\"Handsometough\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1200\":{\"user\":{\"username\":\"Beeviperspirit\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1201\":{\"user\":{\"username\":\"Liquidnin\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1202\":{\"user\":{\"username\":\"Protossphobia\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1203\":{\"user\":{\"username\":\"-sanmaru\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1204\":{\"user\":{\"username\":\"Coreapplemotor\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1205\":{\"user\":{\"username\":\"Yankeebeam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1206\":{\"user\":{\"username\":\"Hungrystein\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1207\":{\"user\":{\"username\":\"Kingshockduck\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1208\":{\"user\":{\"username\":\"Bottomrobometal\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1209\":{\"user\":{\"username\":\"Freezerphobia\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1210\":{\"user\":{\"username\":\"Hunterstonetank\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1211\":{\"user\":{\"username\":\"Mobileboydof\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1212\":{\"user\":{\"username\":\"Betamickmoon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1213\":{\"user\":{\"username\":\"Metalrick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1214\":{\"user\":{\"username\":\"Shadymobile\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1215\":{\"user\":{\"username\":\"Holosmileylogy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1216\":{\"user\":{\"username\":\"Newviperpink\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1217\":{\"user\":{\"username\":\"Slayerbluehungry\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1218\":{\"user\":{\"username\":\"Eagleninjabecky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1219\":{\"user\":{\"username\":\"Frankybullywimpy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1220\":{\"user\":{\"username\":\"Shadeagle-san\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1221\":{\"user\":{\"username\":\"Footyama\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1222\":{\"user\":{\"username\":\"Puppyzoonstone\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1223\":{\"user\":{\"username\":\"Boxdustshiny\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1224\":{\"user\":{\"username\":\"Lavalight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1225\":{\"user\":{\"username\":\"Crimsoniron\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1226\":{\"user\":{\"username\":\"Dragprotosstarus\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1227\":{\"user\":{\"username\":\"Heinzthunder\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1228\":{\"user\":{\"username\":\"Geishstrongbeckham\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1229\":{\"user\":{\"username\":\"Kingbulletjohn\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1230\":{\"user\":{\"username\":\"Squallmetal\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1231\":{\"user\":{\"username\":\"Princenot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1232\":{\"user\":{\"username\":\"Xafried\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1233\":{\"user\":{\"username\":\"Wuzzeightyhulk\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1234\":{\"user\":{\"username\":\"Browniedustecho\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1235\":{\"user\":{\"username\":\"Notsixtyapple\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1236\":{\"user\":{\"username\":\"Weibshady\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1237\":{\"user\":{\"username\":\"Slayervision\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1238\":{\"user\":{\"username\":\"Lagridervick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1239\":{\"user\":{\"username\":\"Motordrinker\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1240\":{\"user\":{\"username\":\"Dudegayza\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1241\":{\"user\":{\"username\":\"Corger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1242\":{\"user\":{\"username\":\"Firesaurusiron\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1243\":{\"user\":{\"username\":\"Quaroos\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1244\":{\"user\":{\"username\":\"Visionrobo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1245\":{\"user\":{\"username\":\"Spiritwickstone\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1246\":{\"user\":{\"username\":\"Solarsteelslayer\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1247\":{\"user\":{\"username\":\"Voltsteeltarus\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1248\":{\"user\":{\"username\":\"Nationbox\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1249\":{\"user\":{\"username\":\"Spherestar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1250\":{\"user\":{\"username\":\"Diamondbrew\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1251\":{\"user\":{\"username\":\"Rayfootxy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1252\":{\"user\":{\"username\":\"Hitsquallmetal\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1253\":{\"user\":{\"username\":\"Solidholo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1254\":{\"user\":{\"username\":\"Docktri\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1255\":{\"user\":{\"username\":\"Thunderlunull\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1256\":{\"user\":{\"username\":\"Axaman\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1257\":{\"user\":{\"username\":\"Starpuppynot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1258\":{\"user\":{\"username\":\"Dukefootbot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1259\":{\"user\":{\"username\":\"Lunarnot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1260\":{\"user\":{\"username\":\"Gonlucky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1261\":{\"user\":{\"username\":\"Zaeighty\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1262\":{\"user\":{\"username\":\"Keeperaxa\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1263\":{\"user\":{\"username\":\"Jokersteinkeeper\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1264\":{\"user\":{\"username\":\"Godbulletfreak\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1265\":{\"user\":{\"username\":\"Gonrider\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1266\":{\"user\":{\"username\":\"Tinytri\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1267\":{\"user\":{\"username\":\"Peevick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1268\":{\"user\":{\"username\":\"Gigalunarval\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1269\":{\"user\":{\"username\":\"Puppydudedoh\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1270\":{\"user\":{\"username\":\"Magicfluffy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1271\":{\"user\":{\"username\":\"Queenraynostril\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1272\":{\"user\":{\"username\":\"Cleardualbabel\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1273\":{\"user\":{\"username\":\"Towerholopimp\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1274\":{\"user\":{\"username\":\"Browniealphapizza\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1275\":{\"user\":{\"username\":\"-O-kaka\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1276\":{\"user\":{\"username\":\"Eaglexy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1277\":{\"user\":{\"username\":\"Lightfranky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1278\":{\"user\":{\"username\":\"Strongguitar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1279\":{\"user\":{\"username\":\"Beenuclearrama\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1280\":{\"user\":{\"username\":\"Mooncrimson\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1281\":{\"user\":{\"username\":\"Crazylight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1282\":{\"user\":{\"username\":\"Bitchbitch\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1283\":{\"user\":{\"username\":\"Hitquick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1284\":{\"user\":{\"username\":\"Lagmad\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1285\":{\"user\":{\"username\":\"Lightger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1286\":{\"user\":{\"username\":\"-sanwimpy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1287\":{\"user\":{\"username\":\"Zawanight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1288\":{\"user\":{\"username\":\"Corediamondzone\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1289\":{\"user\":{\"username\":\"Steinvisionkeeper\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1290\":{\"user\":{\"username\":\"Bigdrinkeryankee\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1291\":{\"user\":{\"username\":\"Ninskinny\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1292\":{\"user\":{\"username\":\"Gigapink\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1293\":{\"user\":{\"username\":\"Gatesphere\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1294\":{\"user\":{\"username\":\"Freezercobra\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1295\":{\"user\":{\"username\":\"Voltsamgay\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1296\":{\"user\":{\"username\":\"Bulletcorelu\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1297\":{\"user\":{\"username\":\"Marumechabee\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1298\":{\"user\":{\"username\":\"Gerhdragon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1299\":{\"user\":{\"username\":\"Sixtyvision-F-\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1300\":{\"user\":{\"username\":\"Raypepsicentury\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1301\":{\"user\":{\"username\":\"Zonegerhdragon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1302\":{\"user\":{\"username\":\"Greensam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1303\":{\"user\":{\"username\":\"Bitchcute\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1304\":{\"user\":{\"username\":\"Boyhandsome\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1305\":{\"user\":{\"username\":\"Bervoltagefranky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1306\":{\"user\":{\"username\":\"Snakefreakza\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1307\":{\"user\":{\"username\":\"Coretruehunter\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1308\":{\"user\":{\"username\":\"Lightbadda\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1309\":{\"user\":{\"username\":\"Phondino\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1310\":{\"user\":{\"username\":\"Vickmidnight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1311\":{\"user\":{\"username\":\"Yoaxajutsu\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1312\":{\"user\":{\"username\":\"Jokertiger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1313\":{\"user\":{\"username\":\"Phobiasmiley\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1314\":{\"user\":{\"username\":\"Tigertough\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1315\":{\"user\":{\"username\":\"Beckyful\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1316\":{\"user\":{\"username\":\"Diamondgerjuno\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1317\":{\"user\":{\"username\":\"Beckyful\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1318\":{\"user\":{\"username\":\"Princeseinbully\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1319\":{\"user\":{\"username\":\"Pinktiny\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1320\":{\"user\":{\"username\":\"Ultimatecatkaz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1321\":{\"user\":{\"username\":\"Quafool\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1322\":{\"user\":{\"username\":\"Seinskinny\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1323\":{\"user\":{\"username\":\"Fulboxbee\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1324\":{\"user\":{\"username\":\"Dodgesnake\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1325\":{\"user\":{\"username\":\"Xayamajuice\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1326\":{\"user\":{\"username\":\"Currydohlight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1327\":{\"user\":{\"username\":\"Steinroon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1328\":{\"user\":{\"username\":\"Visionphobia\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1329\":{\"user\":{\"username\":\"Shopdofyama\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1330\":{\"user\":{\"username\":\"Speedfriedgeish\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1331\":{\"user\":{\"username\":\"Androidpoo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1332\":{\"user\":{\"username\":\"Weibcute\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1333\":{\"user\":{\"username\":\"Weibhungry\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1334\":{\"user\":{\"username\":\"Irondakeeper\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1335\":{\"user\":{\"username\":\"Zawabeckycat\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1336\":{\"user\":{\"username\":\"Goerbox\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1337\":{\"user\":{\"username\":\"Foolmobile\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1338\":{\"user\":{\"username\":\"Applemotor\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1339\":{\"user\":{\"username\":\"Sillydukezoon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1340\":{\"user\":{\"username\":\"Nightvalwallet\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1341\":{\"user\":{\"username\":\"Poodual\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1342\":{\"user\":{\"username\":\"Appleroos\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1343\":{\"user\":{\"username\":\"Lavamecheagle\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1344\":{\"user\":{\"username\":\"Pooboxrob\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1345\":{\"user\":{\"username\":\"Zeoncentury\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1346\":{\"user\":{\"username\":\"Bootstar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1347\":{\"user\":{\"username\":\"Handmechabox\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1348\":{\"user\":{\"username\":\"Gerstar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1349\":{\"user\":{\"username\":\"Princedrag\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1350\":{\"user\":{\"username\":\"Greenspirit\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1351\":{\"user\":{\"username\":\"Pooya\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1352\":{\"user\":{\"username\":\"Iceskinval\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1353\":{\"user\":{\"username\":\"Crazyful\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1354\":{\"user\":{\"username\":\"Sillyzonebrownie\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1355\":{\"user\":{\"username\":\"Gonduck\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1356\":{\"user\":{\"username\":\"Mech-F-\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1357\":{\"user\":{\"username\":\"Galaxybully\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1358\":{\"user\":{\"username\":\"Terranmidnightdoh\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1359\":{\"user\":{\"username\":\"Zizzerg\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1360\":{\"user\":{\"username\":\"Aeroskinnyavenger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1361\":{\"user\":{\"username\":\"Yoirondoh\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1362\":{\"user\":{\"username\":\"Tokyosnake\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1363\":{\"user\":{\"username\":\"Dragonfriedblood\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1364\":{\"user\":{\"username\":\"Quacrazysuz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1365\":{\"user\":{\"username\":\"Nuclearzawa\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1366\":{\"user\":{\"username\":\"Suzbox\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1367\":{\"user\":{\"username\":\"Bysam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1368\":{\"user\":{\"username\":\"Gonmidnight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1369\":{\"user\":{\"username\":\"Steingoerapple\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1370\":{\"user\":{\"username\":\"Phondual\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1371\":{\"user\":{\"username\":\"Dazionlucky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1372\":{\"user\":{\"username\":\"Zatobi\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1373\":{\"user\":{\"username\":\"Eightymickiron\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1374\":{\"user\":{\"username\":\"Greendago\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1375\":{\"user\":{\"username\":\"Tabbeambeam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1376\":{\"user\":{\"username\":\"Tabgalaxysphere\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1377\":{\"user\":{\"username\":\"Beastroblucky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1378\":{\"user\":{\"username\":\"Joeygatehot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1379\":{\"user\":{\"username\":\"Geishmega\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1380\":{\"user\":{\"username\":\"Hazardyeah\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1381\":{\"user\":{\"username\":\"Lionpoosuper\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1382\":{\"user\":{\"username\":\"Smileytar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1383\":{\"user\":{\"username\":\"Quahellnano\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1384\":{\"user\":{\"username\":\"Corlu\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1385\":{\"user\":{\"username\":\"Guitarstarla\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1386\":{\"user\":{\"username\":\"Godnight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1387\":{\"user\":{\"username\":\"Snake-san\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1388\":{\"user\":{\"username\":\"Seinboyby\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1389\":{\"user\":{\"username\":\"Holospace\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1390\":{\"user\":{\"username\":\"Midnightninja\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1391\":{\"user\":{\"username\":\"Raygate\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1392\":{\"user\":{\"username\":\"Hawkdohhawk\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1393\":{\"user\":{\"username\":\"Rockbabelroon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1394\":{\"user\":{\"username\":\"Tabfreak\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1395\":{\"user\":{\"username\":\"Xyheinz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1396\":{\"user\":{\"username\":\"Bootmick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1397\":{\"user\":{\"username\":\"Rockdanation\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1398\":{\"user\":{\"username\":\"Roondelta\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1399\":{\"user\":{\"username\":\"Bluepoo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1400\":{\"user\":{\"username\":\"Quickwaterwater\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1401\":{\"user\":{\"username\":\"Handsomemetalfool\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1402\":{\"user\":{\"username\":\"Hitgalaxynuclear\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1403\":{\"user\":{\"username\":\"Ninavengerstar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1404\":{\"user\":{\"username\":\"Twentyhulksam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1405\":{\"user\":{\"username\":\"Dohtarbio\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1406\":{\"user\":{\"username\":\"Fluffyhitdust\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1407\":{\"user\":{\"username\":\"Geishiron\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1408\":{\"user\":{\"username\":\"Liontrue\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1409\":{\"user\":{\"username\":\"Frankytwenty\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1410\":{\"user\":{\"username\":\"Chocoman\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1411\":{\"user\":{\"username\":\"Wimpyroos\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1412\":{\"user\":{\"username\":\"Beastgeish\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1413\":{\"user\":{\"username\":\"Boxtokyo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1414\":{\"user\":{\"username\":\"Clearposokplatinum\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1415\":{\"user\":{\"username\":\"Wuzzgianthazard\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1416\":{\"user\":{\"username\":\"Pepsifluffyspirit\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1417\":{\"user\":{\"username\":\"Roostobixy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1418\":{\"user\":{\"username\":\"Rickstrongsnake\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1419\":{\"user\":{\"username\":\"Steelgiant\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1420\":{\"user\":{\"username\":\"Midnightgreen\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1421\":{\"user\":{\"username\":\"Metalbrew\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1422\":{\"user\":{\"username\":\"Queenphon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1423\":{\"user\":{\"username\":\"Lightdukerick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1424\":{\"user\":{\"username\":\"Maru-sanmagic\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1425\":{\"user\":{\"username\":\"Hungryfranky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1426\":{\"user\":{\"username\":\"Bersmileynot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1427\":{\"user\":{\"username\":\"Steelsambeast\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1428\":{\"user\":{\"username\":\"Steelcobra\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1429\":{\"user\":{\"username\":\"Ultimatezeontaro\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1430\":{\"user\":{\"username\":\"Hitzerg\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1431\":{\"user\":{\"username\":\"Echotarus\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1432\":{\"user\":{\"username\":\"Peepoo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1433\":{\"user\":{\"username\":\"Handsomedoh\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1434\":{\"user\":{\"username\":\"Gaybitchsha\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1435\":{\"user\":{\"username\":\"Suzbrownieheinz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1436\":{\"user\":{\"username\":\"-O-sixty\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1437\":{\"user\":{\"username\":\"Sillygreenmega\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1438\":{\"user\":{\"username\":\"Riderzerg\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1439\":{\"user\":{\"username\":\"Betafuji\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1440\":{\"user\":{\"username\":\"Valby\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1441\":{\"user\":{\"username\":\"Bottombottomspace\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"66\":{\"user\":{\"username\":\"SivaGod\",\"type\":1,\"ip\":\"Heavenly IP\",\"port\":0,\"pid\":0},\"last_known_destination\":{\"position\":\"(48,48)\",\"timestamp\":1365444432}},\"77\":{\"user\":{\"username\":\"EtherealProgrammer\",\"type\":1,\"ip\":\"Heavenly IP\",\"port\":0,\"pid\":0},\"last_known_destination\":{\"position\":\"(15,30)\",\"timestamp\":1365444430}}}}}";

		//backup 373
		//result="{\"status\":1,\"data\":{\"weather\":{\"condition\":\"Raining\",\"last_change\":1365453240},\"icetizen\":{\"1083\":{\"user\":{\"username\":\"Aerodualfried\",\"type\":0,\"ip\":\"58.11.37.3\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1084\":{\"user\":{\"username\":\"Licor\",\"type\":0,\"ip\":\"171.99.86.41\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1085\":{\"user\":{\"username\":\"Robspace\",\"type\":0,\"ip\":\"27.55.129.52\",\"port\":\"0\",\"pid\":\"250\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1086\":{\"user\":{\"username\":\"Mobilematchashad\",\"type\":0,\"ip\":\"27.55.129.52\",\"port\":\"0\",\"pid\":\"250\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"37\":{\"user\":{\"username\":\"Veeriya.R\",\"type\":1,\"ip\":\"27.55.129.52\",\"port\":\"0\",\"pid\":\"250\"},\"last_known_destination\":{\"position\":\"(40,30)\",\"timestamp\":\"1365415348\"}},\"1087\":{\"user\":{\"username\":\"Crazybust\",\"type\":0,\"ip\":\"171.99.84.136\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1088\":{\"user\":{\"username\":\"Galaxyboxcentury\",\"type\":0,\"ip\":\"171.99.84.136\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"20\":{\"user\":{\"username\":\"Prinn.P\",\"type\":1,\"ip\":\"180.183.47.219\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":\"(105,50)\",\"timestamp\":\"1365422475\"}},\"1089\":{\"user\":{\"username\":\"Melonmetal\",\"type\":0,\"ip\":\"180.183.47.219\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1090\":{\"user\":{\"username\":\"Trihungryweib\",\"type\":0,\"ip\":\"180.183.47.219\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1091\":{\"user\":{\"username\":\"Jutsunuclear\",\"type\":0,\"ip\":\"180.183.47.219\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1092\":{\"user\":{\"username\":\"Megalu\",\"type\":0,\"ip\":\"180.183.47.219\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"35\":{\"user\":{\"username\":\"Waris-sara.B\",\"type\":1,\"ip\":\"180.183.40.180\",\"port\":\"0\",\"pid\":\"255\"},\"last_known_destination\":{\"position\":\"(80,80)\",\"timestamp\":\"1365424696\"}},\"1093\":{\"user\":{\"username\":\"Visionholo\",\"type\":0,\"ip\":\"171.99.87.188\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1094\":{\"user\":{\"username\":\"Kakalijohn\",\"type\":0,\"ip\":\"171.99.87.188\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1095\":{\"user\":{\"username\":\"Hazardpeegiant\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"41\":{\"user\":{\"username\":\"Harin.S\",\"type\":1,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":\"(5,13)\",\"timestamp\":\"1365343928\"}},\"1096\":{\"user\":{\"username\":\"Waterracervodka\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1097\":{\"user\":{\"username\":\"Wickwallet\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1098\":{\"user\":{\"username\":\"Slayereightyhell\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1099\":{\"user\":{\"username\":\"Suzramasixty\",\"type\":0,\"ip\":\"27.55.13.192\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1100\":{\"user\":{\"username\":\"Jokerduke\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"7\":{\"user\":{\"username\":\"Johmphot.T\",\"type\":1,\"ip\":\"27.55.13.192\",\"port\":\"0\",\"pid\":\"253\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1101\":{\"user\":{\"username\":\"Squalleighty\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1102\":{\"user\":{\"username\":\"Megaradio\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1103\":{\"user\":{\"username\":\"Eightyposok\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1104\":{\"user\":{\"username\":\"Hazardliquidhunter\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1105\":{\"user\":{\"username\":\"Lightsha\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1106\":{\"user\":{\"username\":\"-asstankmech\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1107\":{\"user\":{\"username\":\"Binaryjohn\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1108\":{\"user\":{\"username\":\"Heinz-sanjoey\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1109\":{\"user\":{\"username\":\"Boyecho\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1110\":{\"user\":{\"username\":\"Tankyeahbox\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1111\":{\"user\":{\"username\":\"Eaglestrong\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1112\":{\"user\":{\"username\":\"Waterstar\",\"type\":0,\"ip\":\"171.101.218.171\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1113\":{\"user\":{\"username\":\"Dohspace\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1114\":{\"user\":{\"username\":\"Pinkdrinker\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1115\":{\"user\":{\"username\":\"Xamega\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1116\":{\"user\":{\"username\":\"Robnew\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1117\":{\"user\":{\"username\":\"Deltadinomick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1118\":{\"user\":{\"username\":\"Geniusboot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1119\":{\"user\":{\"username\":\"Bootsuz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1120\":{\"user\":{\"username\":\"Shockvick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1121\":{\"user\":{\"username\":\"Nanodualpee\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1122\":{\"user\":{\"username\":\"Jutsumech\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1123\":{\"user\":{\"username\":\"Pimpdiamond\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1124\":{\"user\":{\"username\":\"Crimsonger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1125\":{\"user\":{\"username\":\"Handsomefuji\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1126\":{\"user\":{\"username\":\"Boxninbabel\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1127\":{\"user\":{\"username\":\"Yeahlabeckham\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1128\":{\"user\":{\"username\":\"Holospeed\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1129\":{\"user\":{\"username\":\"Chictarocurry\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1130\":{\"user\":{\"username\":\"Hunternotbeckham\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1131\":{\"user\":{\"username\":\"Sabersam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1132\":{\"user\":{\"username\":\"Laglion\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1133\":{\"user\":{\"username\":\"Tarussmiley\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1134\":{\"user\":{\"username\":\"Deltaavengerwallet\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1135\":{\"user\":{\"username\":\"-F-taro\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1136\":{\"user\":{\"username\":\"Jokerseinroos\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1137\":{\"user\":{\"username\":\"Hawkmidnight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1138\":{\"user\":{\"username\":\"Gigadiamond\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1139\":{\"user\":{\"username\":\"Footultimatesquall\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1140\":{\"user\":{\"username\":\"Stonetiny\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1141\":{\"user\":{\"username\":\"Foolterran\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1142\":{\"user\":{\"username\":\"Efffoot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1143\":{\"user\":{\"username\":\"Fluffycrazycurry\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1144\":{\"user\":{\"username\":\"Poosmileyfluffy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1145\":{\"user\":{\"username\":\"Golu\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1146\":{\"user\":{\"username\":\"Aerothunder\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1147\":{\"user\":{\"username\":\"Godrock\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1148\":{\"user\":{\"username\":\"Towergate\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1149\":{\"user\":{\"username\":\"Clearburger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1150\":{\"user\":{\"username\":\"Saurusrobo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1151\":{\"user\":{\"username\":\"Waterhell\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1152\":{\"user\":{\"username\":\"Zonezionnin\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1153\":{\"user\":{\"username\":\"Fluffyplatinumchic\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1154\":{\"user\":{\"username\":\"Queenavengergeish\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1155\":{\"user\":{\"username\":\"Dragoncurryprince\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1156\":{\"user\":{\"username\":\"Dualliquid\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1157\":{\"user\":{\"username\":\"Friedandroid\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1158\":{\"user\":{\"username\":\"Waterrobhulk\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1159\":{\"user\":{\"username\":\"Shachocoshady\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1160\":{\"user\":{\"username\":\"Moonhandsomesphere\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1161\":{\"user\":{\"username\":\"Dohzawa\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1162\":{\"user\":{\"username\":\"Dudeshady\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1163\":{\"user\":{\"username\":\"Metalvoltage\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1164\":{\"user\":{\"username\":\"Tarusray\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1165\":{\"user\":{\"username\":\"Strongphobia\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1166\":{\"user\":{\"username\":\"Ironyeahlight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1167\":{\"user\":{\"username\":\"Burgerpoo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1168\":{\"user\":{\"username\":\"Ninjafried\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1169\":{\"user\":{\"username\":\"Beckyziz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1170\":{\"user\":{\"username\":\"Greenman\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1171\":{\"user\":{\"username\":\"Latobi\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1172\":{\"user\":{\"username\":\"Shadylunar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1173\":{\"user\":{\"username\":\"Madbeebeast\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1174\":{\"user\":{\"username\":\"Byzonefried\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1175\":{\"user\":{\"username\":\"Dragonradio\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1176\":{\"user\":{\"username\":\"-F-avengerroon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1177\":{\"user\":{\"username\":\"Magiceagledoh\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1178\":{\"user\":{\"username\":\"Logyfujibottom\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1179\":{\"user\":{\"username\":\"Phobiatriblue\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1180\":{\"user\":{\"username\":\"Friedmad\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1181\":{\"user\":{\"username\":\"Keeper-F-\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1182\":{\"user\":{\"username\":\"Twentyking\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1183\":{\"user\":{\"username\":\"Hunterdragmetal\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1184\":{\"user\":{\"username\":\"Draggate\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1185\":{\"user\":{\"username\":\"Joeybad\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1186\":{\"user\":{\"username\":\"Corewimpy-ass\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1187\":{\"user\":{\"username\":\"Truebox\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1188\":{\"user\":{\"username\":\"Ultimateecho\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1189\":{\"user\":{\"username\":\"Solidboy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1190\":{\"user\":{\"username\":\"Goddragon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1191\":{\"user\":{\"username\":\"Pizzasuper\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1192\":{\"user\":{\"username\":\"Speedsilly\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1193\":{\"user\":{\"username\":\"Lightvodkamech\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1194\":{\"user\":{\"username\":\"Newlightduke\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1195\":{\"user\":{\"username\":\"Boyankee\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1196\":{\"user\":{\"username\":\"Zizcobratough\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1197\":{\"user\":{\"username\":\"Nikephon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1198\":{\"user\":{\"username\":\"Boxfluffyheinz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1199\":{\"user\":{\"username\":\"Handsometough\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1200\":{\"user\":{\"username\":\"Beeviperspirit\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1201\":{\"user\":{\"username\":\"Liquidnin\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1202\":{\"user\":{\"username\":\"Protossphobia\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1203\":{\"user\":{\"username\":\"-sanmaru\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1204\":{\"user\":{\"username\":\"Coreapplemotor\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1205\":{\"user\":{\"username\":\"Yankeebeam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1206\":{\"user\":{\"username\":\"Hungrystein\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1207\":{\"user\":{\"username\":\"Kingshockduck\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1208\":{\"user\":{\"username\":\"Bottomrobometal\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1209\":{\"user\":{\"username\":\"Freezerphobia\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1210\":{\"user\":{\"username\":\"Hunterstonetank\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1211\":{\"user\":{\"username\":\"Mobileboydof\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1212\":{\"user\":{\"username\":\"Betamickmoon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1213\":{\"user\":{\"username\":\"Metalrick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1214\":{\"user\":{\"username\":\"Shadymobile\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1215\":{\"user\":{\"username\":\"Holosmileylogy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1216\":{\"user\":{\"username\":\"Newviperpink\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1217\":{\"user\":{\"username\":\"Slayerbluehungry\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1218\":{\"user\":{\"username\":\"Eagleninjabecky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1219\":{\"user\":{\"username\":\"Frankybullywimpy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1220\":{\"user\":{\"username\":\"Shadeagle-san\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1221\":{\"user\":{\"username\":\"Footyama\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1222\":{\"user\":{\"username\":\"Puppyzoonstone\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1223\":{\"user\":{\"username\":\"Boxdustshiny\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1224\":{\"user\":{\"username\":\"Lavalight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1225\":{\"user\":{\"username\":\"Crimsoniron\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1226\":{\"user\":{\"username\":\"Dragprotosstarus\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1227\":{\"user\":{\"username\":\"Heinzthunder\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1228\":{\"user\":{\"username\":\"Geishstrongbeckham\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1229\":{\"user\":{\"username\":\"Kingbulletjohn\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1230\":{\"user\":{\"username\":\"Squallmetal\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1231\":{\"user\":{\"username\":\"Princenot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1232\":{\"user\":{\"username\":\"Xafried\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1233\":{\"user\":{\"username\":\"Wuzzeightyhulk\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1234\":{\"user\":{\"username\":\"Browniedustecho\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1235\":{\"user\":{\"username\":\"Notsixtyapple\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1236\":{\"user\":{\"username\":\"Weibshady\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1237\":{\"user\":{\"username\":\"Slayervision\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1238\":{\"user\":{\"username\":\"Lagridervick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1239\":{\"user\":{\"username\":\"Motordrinker\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1240\":{\"user\":{\"username\":\"Dudegayza\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1241\":{\"user\":{\"username\":\"Corger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1242\":{\"user\":{\"username\":\"Firesaurusiron\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1243\":{\"user\":{\"username\":\"Quaroos\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1244\":{\"user\":{\"username\":\"Visionrobo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1245\":{\"user\":{\"username\":\"Spiritwickstone\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1246\":{\"user\":{\"username\":\"Solarsteelslayer\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1247\":{\"user\":{\"username\":\"Voltsteeltarus\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1248\":{\"user\":{\"username\":\"Nationbox\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1249\":{\"user\":{\"username\":\"Spherestar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1250\":{\"user\":{\"username\":\"Diamondbrew\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1251\":{\"user\":{\"username\":\"Rayfootxy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1252\":{\"user\":{\"username\":\"Hitsquallmetal\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1253\":{\"user\":{\"username\":\"Solidholo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1254\":{\"user\":{\"username\":\"Docktri\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1255\":{\"user\":{\"username\":\"Thunderlunull\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1256\":{\"user\":{\"username\":\"Axaman\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1257\":{\"user\":{\"username\":\"Starpuppynot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1258\":{\"user\":{\"username\":\"Dukefootbot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1259\":{\"user\":{\"username\":\"Lunarnot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1260\":{\"user\":{\"username\":\"Gonlucky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1261\":{\"user\":{\"username\":\"Zaeighty\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1262\":{\"user\":{\"username\":\"Keeperaxa\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1263\":{\"user\":{\"username\":\"Jokersteinkeeper\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1264\":{\"user\":{\"username\":\"Godbulletfreak\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1265\":{\"user\":{\"username\":\"Gonrider\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1266\":{\"user\":{\"username\":\"Tinytri\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1267\":{\"user\":{\"username\":\"Peevick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1268\":{\"user\":{\"username\":\"Gigalunarval\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1269\":{\"user\":{\"username\":\"Puppydudedoh\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1270\":{\"user\":{\"username\":\"Magicfluffy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1271\":{\"user\":{\"username\":\"Queenraynostril\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1272\":{\"user\":{\"username\":\"Cleardualbabel\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1273\":{\"user\":{\"username\":\"Towerholopimp\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1274\":{\"user\":{\"username\":\"Browniealphapizza\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1275\":{\"user\":{\"username\":\"-O-kaka\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1276\":{\"user\":{\"username\":\"Eaglexy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1277\":{\"user\":{\"username\":\"Lightfranky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1278\":{\"user\":{\"username\":\"Strongguitar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1279\":{\"user\":{\"username\":\"Beenuclearrama\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1280\":{\"user\":{\"username\":\"Mooncrimson\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1281\":{\"user\":{\"username\":\"Crazylight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1282\":{\"user\":{\"username\":\"Bitchbitch\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1283\":{\"user\":{\"username\":\"Hitquick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1284\":{\"user\":{\"username\":\"Lagmad\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1285\":{\"user\":{\"username\":\"Lightger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1286\":{\"user\":{\"username\":\"-sanwimpy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1287\":{\"user\":{\"username\":\"Zawanight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1288\":{\"user\":{\"username\":\"Corediamondzone\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1289\":{\"user\":{\"username\":\"Steinvisionkeeper\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1290\":{\"user\":{\"username\":\"Bigdrinkeryankee\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1291\":{\"user\":{\"username\":\"Ninskinny\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1292\":{\"user\":{\"username\":\"Gigapink\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1293\":{\"user\":{\"username\":\"Gatesphere\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1294\":{\"user\":{\"username\":\"Freezercobra\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1295\":{\"user\":{\"username\":\"Voltsamgay\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1296\":{\"user\":{\"username\":\"Bulletcorelu\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1297\":{\"user\":{\"username\":\"Marumechabee\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1298\":{\"user\":{\"username\":\"Gerhdragon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1299\":{\"user\":{\"username\":\"Sixtyvision-F-\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1300\":{\"user\":{\"username\":\"Raypepsicentury\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1301\":{\"user\":{\"username\":\"Zonegerhdragon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1302\":{\"user\":{\"username\":\"Greensam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1303\":{\"user\":{\"username\":\"Bitchcute\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1304\":{\"user\":{\"username\":\"Boyhandsome\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1305\":{\"user\":{\"username\":\"Bervoltagefranky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1306\":{\"user\":{\"username\":\"Snakefreakza\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1307\":{\"user\":{\"username\":\"Coretruehunter\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1308\":{\"user\":{\"username\":\"Lightbadda\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1309\":{\"user\":{\"username\":\"Phondino\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1310\":{\"user\":{\"username\":\"Vickmidnight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1311\":{\"user\":{\"username\":\"Yoaxajutsu\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1312\":{\"user\":{\"username\":\"Jokertiger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1313\":{\"user\":{\"username\":\"Phobiasmiley\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1314\":{\"user\":{\"username\":\"Tigertough\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1315\":{\"user\":{\"username\":\"Beckyful\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1316\":{\"user\":{\"username\":\"Diamondgerjuno\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1317\":{\"user\":{\"username\":\"Beckyful\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1318\":{\"user\":{\"username\":\"Princeseinbully\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1319\":{\"user\":{\"username\":\"Pinktiny\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1320\":{\"user\":{\"username\":\"Ultimatecatkaz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1321\":{\"user\":{\"username\":\"Quafool\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1322\":{\"user\":{\"username\":\"Seinskinny\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1323\":{\"user\":{\"username\":\"Fulboxbee\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1324\":{\"user\":{\"username\":\"Dodgesnake\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1325\":{\"user\":{\"username\":\"Xayamajuice\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1326\":{\"user\":{\"username\":\"Currydohlight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1327\":{\"user\":{\"username\":\"Steinroon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1328\":{\"user\":{\"username\":\"Visionphobia\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1329\":{\"user\":{\"username\":\"Shopdofyama\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1330\":{\"user\":{\"username\":\"Speedfriedgeish\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1331\":{\"user\":{\"username\":\"Androidpoo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1332\":{\"user\":{\"username\":\"Weibcute\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1333\":{\"user\":{\"username\":\"Weibhungry\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1334\":{\"user\":{\"username\":\"Irondakeeper\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1335\":{\"user\":{\"username\":\"Zawabeckycat\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1336\":{\"user\":{\"username\":\"Goerbox\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1337\":{\"user\":{\"username\":\"Foolmobile\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1338\":{\"user\":{\"username\":\"Applemotor\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1339\":{\"user\":{\"username\":\"Sillydukezoon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1340\":{\"user\":{\"username\":\"Nightvalwallet\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1341\":{\"user\":{\"username\":\"Poodual\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1342\":{\"user\":{\"username\":\"Appleroos\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1343\":{\"user\":{\"username\":\"Lavamecheagle\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1344\":{\"user\":{\"username\":\"Pooboxrob\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1345\":{\"user\":{\"username\":\"Zeoncentury\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1346\":{\"user\":{\"username\":\"Bootstar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1347\":{\"user\":{\"username\":\"Handmechabox\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1348\":{\"user\":{\"username\":\"Gerstar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1349\":{\"user\":{\"username\":\"Princedrag\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1350\":{\"user\":{\"username\":\"Greenspirit\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1351\":{\"user\":{\"username\":\"Pooya\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1352\":{\"user\":{\"username\":\"Iceskinval\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1353\":{\"user\":{\"username\":\"Crazyful\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1354\":{\"user\":{\"username\":\"Sillyzonebrownie\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1355\":{\"user\":{\"username\":\"Gonduck\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1356\":{\"user\":{\"username\":\"Mech-F-\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1357\":{\"user\":{\"username\":\"Galaxybully\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1358\":{\"user\":{\"username\":\"Terranmidnightdoh\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1359\":{\"user\":{\"username\":\"Zizzerg\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1360\":{\"user\":{\"username\":\"Aeroskinnyavenger\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1361\":{\"user\":{\"username\":\"Yoirondoh\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1362\":{\"user\":{\"username\":\"Tokyosnake\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1363\":{\"user\":{\"username\":\"Dragonfriedblood\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1364\":{\"user\":{\"username\":\"Quacrazysuz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1365\":{\"user\":{\"username\":\"Nuclearzawa\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1366\":{\"user\":{\"username\":\"Suzbox\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1367\":{\"user\":{\"username\":\"Bysam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1368\":{\"user\":{\"username\":\"Gonmidnight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1369\":{\"user\":{\"username\":\"Steingoerapple\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1370\":{\"user\":{\"username\":\"Phondual\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1371\":{\"user\":{\"username\":\"Dazionlucky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1372\":{\"user\":{\"username\":\"Zatobi\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1373\":{\"user\":{\"username\":\"Eightymickiron\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1374\":{\"user\":{\"username\":\"Greendago\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1375\":{\"user\":{\"username\":\"Tabbeambeam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1376\":{\"user\":{\"username\":\"Tabgalaxysphere\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1377\":{\"user\":{\"username\":\"Beastroblucky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1378\":{\"user\":{\"username\":\"Joeygatehot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1379\":{\"user\":{\"username\":\"Geishmega\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1380\":{\"user\":{\"username\":\"Hazardyeah\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1381\":{\"user\":{\"username\":\"Lionpoosuper\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1382\":{\"user\":{\"username\":\"Smileytar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1383\":{\"user\":{\"username\":\"Quahellnano\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1384\":{\"user\":{\"username\":\"Corlu\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1385\":{\"user\":{\"username\":\"Guitarstarla\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1386\":{\"user\":{\"username\":\"Godnight\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1387\":{\"user\":{\"username\":\"Snake-san\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1388\":{\"user\":{\"username\":\"Seinboyby\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1389\":{\"user\":{\"username\":\"Holospace\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1390\":{\"user\":{\"username\":\"Midnightninja\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1391\":{\"user\":{\"username\":\"Raygate\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1392\":{\"user\":{\"username\":\"Hawkdohhawk\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1393\":{\"user\":{\"username\":\"Rockbabelroon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1394\":{\"user\":{\"username\":\"Tabfreak\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1395\":{\"user\":{\"username\":\"Xyheinz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1396\":{\"user\":{\"username\":\"Bootmick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1397\":{\"user\":{\"username\":\"Rockdanation\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1398\":{\"user\":{\"username\":\"Roondelta\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1399\":{\"user\":{\"username\":\"Bluepoo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1400\":{\"user\":{\"username\":\"Quickwaterwater\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1401\":{\"user\":{\"username\":\"Handsomemetalfool\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1402\":{\"user\":{\"username\":\"Hitgalaxynuclear\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1403\":{\"user\":{\"username\":\"Ninavengerstar\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1404\":{\"user\":{\"username\":\"Twentyhulksam\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1405\":{\"user\":{\"username\":\"Dohtarbio\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1406\":{\"user\":{\"username\":\"Fluffyhitdust\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1407\":{\"user\":{\"username\":\"Geishiron\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1408\":{\"user\":{\"username\":\"Liontrue\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1409\":{\"user\":{\"username\":\"Frankytwenty\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1410\":{\"user\":{\"username\":\"Chocoman\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1411\":{\"user\":{\"username\":\"Wimpyroos\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1412\":{\"user\":{\"username\":\"Beastgeish\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1413\":{\"user\":{\"username\":\"Boxtokyo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1414\":{\"user\":{\"username\":\"Clearposokplatinum\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1415\":{\"user\":{\"username\":\"Wuzzgianthazard\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1416\":{\"user\":{\"username\":\"Pepsifluffyspirit\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1417\":{\"user\":{\"username\":\"Roostobixy\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1418\":{\"user\":{\"username\":\"Rickstrongsnake\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1419\":{\"user\":{\"username\":\"Steelgiant\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1420\":{\"user\":{\"username\":\"Midnightgreen\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1421\":{\"user\":{\"username\":\"Metalbrew\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1422\":{\"user\":{\"username\":\"Queenphon\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1423\":{\"user\":{\"username\":\"Lightdukerick\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1424\":{\"user\":{\"username\":\"Maru-sanmagic\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1425\":{\"user\":{\"username\":\"Hungryfranky\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1426\":{\"user\":{\"username\":\"Bersmileynot\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1427\":{\"user\":{\"username\":\"Steelsambeast\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1428\":{\"user\":{\"username\":\"Steelcobra\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1429\":{\"user\":{\"username\":\"Ultimatezeontaro\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1430\":{\"user\":{\"username\":\"Hitzerg\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1431\":{\"user\":{\"username\":\"Echotarus\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1432\":{\"user\":{\"username\":\"Peepoo\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1433\":{\"user\":{\"username\":\"Handsomedoh\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1434\":{\"user\":{\"username\":\"Gaybitchsha\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1435\":{\"user\":{\"username\":\"Suzbrownieheinz\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1436\":{\"user\":{\"username\":\"-O-sixty\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1437\":{\"user\":{\"username\":\"Sillygreenmega\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1438\":{\"user\":{\"username\":\"Riderzerg\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1439\":{\"user\":{\"username\":\"Betafuji\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1440\":{\"user\":{\"username\":\"Valby\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1441\":{\"user\":{\"username\":\"Bottombottomspace\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"666\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1442\":{\"user\":{\"username\":\"Viperrobolu\",\"type\":0,\"ip\":\"58.8.137.49\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1443\":{\"user\":{\"username\":\"Zonesein\",\"type\":0,\"ip\":\"58.8.137.49\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1444\":{\"user\":{\"username\":\"Stronghitxy\",\"type\":0,\"ip\":\"58.8.137.49\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"1445\":{\"user\":{\"username\":\"Gonboltyo\",\"type\":0,\"ip\":\"58.8.137.49\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"9\":{\"user\":{\"username\":\"Chatchanart.C\",\"type\":1,\"ip\":\"58.8.137.49\",\"port\":\"0\",\"pid\":\"246\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"28\":{\"user\":{\"username\":\"Putti.O\",\"type\":1,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":\"(-5,40)\",\"timestamp\":\"1365420539\"}},\"1446\":{\"user\":{\"username\":\"Tobitwenty\",\"type\":0,\"ip\":\"124.121.184.135\",\"port\":\"0\",\"pid\":\"0\"},\"last_known_destination\":{\"position\":null,\"timestamp\":null}},\"66\":{\"user\":{\"username\":\"SivaGod\",\"type\":1,\"ip\":\"Heavenly IP\",\"port\":0,\"pid\":0},\"last_known_destination\":{\"position\":\"(48,52)\",\"timestamp\":1365453276}},\"77\":{\"user\":{\"username\":\"EtherealProgrammer\",\"type\":1,\"ip\":\"Heavenly IP\",\"port\":0,\"pid\":0},\"last_known_destination\":{\"position\":\"(22,30)\",\"timestamp\":1365453270}}}}}";

		qCnt=0;
		cCnt=0;
		for (int i=0;i<result.length();i++){
			if (result.charAt(i) == quotationMark){
				qCnt += 1;
				if (qCnt==15){
					firstUserPivot = i;
				}
			}
			if (result.charAt(i) == colon){
				cCnt += 1;
			}
		}
		userAmount=(cCnt-6)/10;
		pivot = new int[2][userAmount];
		String newData = result.substring(firstUserPivot,result.length());
		pCnt=0;
		pIndex=0;
		for (int i=0;i<newData.length();i++){
			if (newData.charAt(i) == colon){
				pCnt += 1;
				if ((double)pCnt%10==1){
					pivot[0][pIndex]=i;
				} else if ((double)pCnt%10==0){
					pivot[1][pIndex]=i;
					pIndex += 1;
				}
			}
		}
		uPivot=new int[2][userAmount];
		uIndex=0;
		for (int i=0;i<userAmount;i++){
			counter=0;
			for (int j=pivot[0][i];j>=0;j--){
				if (newData.charAt(j) == quotationMark){
					counter += 1;
					if (counter == 2){
						uPivot[0][uIndex]=j;
						break;
					}
				}
			}
			for (int j=pivot[1][i];j<newData.length();j++){
				if (i==(userAmount-1)){
					if (newData.charAt(j) == endBracket){
						uPivot[1][uIndex]=j+2;
						break;
					}
				} else if (newData.charAt(j) == comma){
					uPivot[1][uIndex]=j;
					break;
				}
			}
			uIndex += 1;
		}
		String[] userList = new String[userAmount];
		for(int i=0;i<userAmount;i++){
			userList[i]=newData.substring(uPivot[0][i],uPivot[1][i]);
		}
		return userList;
	}
	public static void main(String args[])
	{
		try {
			new LoginScreen1();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createPage1()
	{

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(Color.CYAN);
		JLabel link = new JLabel("<html><u>Logging in/out</u></html>");
		link.setBounds(40,30,125,25);
		panel1.add(link);
		panel1.setPreferredSize(new Dimension(900,500));
		link.setForeground(Color.BLUE);
		link.setFont(font);
		
		BufferedImage charImage = null;
		try {
			charImage = ImageIO.read(new File("1.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//System.out.println("Cannot find image");
		}
		charImage = resize(charImage,200,300);

		ImageIcon nattIcon = new ImageIcon(charImage);
		JLabel natt =new JLabel(nattIcon);
		natt.setBounds(480,100,200,300);
		panel1.add(natt);
		
		link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		link.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				jep = new JEditorPane();
				jep.setEditable(false);
				HTMLJDialog = new JDialog(frame1,"Help HTML5");
				try {
					jep.setPage(new File("1Logging InOut.html").toURI().toURL());
					jep.addHyperlinkListener(new HyperlinkListener(){
						public void hyperlinkUpdate(HyperlinkEvent event) {
							if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
								try {
									jep.setPage(event.getURL());
									//urlField.setText(event.getURL().toExternalForm());
								} catch(IOException ioe) {
									jep.setContentType("text/html");
									jep.setText("<html>Could not load</html>");
								}
							}	
						}

					});
				}catch (IOException e1) {
					jep.setContentType("text/html");
					jep.setText("<html>Could not load</html>");
				} 
				JScrollPane scrollPane = new JScrollPane(jep); 
				HTMLJDialog.getContentPane().add(scrollPane);
				HTMLJDialog.setPreferredSize(new Dimension(800,400));
				HTMLJDialog.pack();
				HTMLJDialog.setVisible(true);
				HTMLJDialog.setModal(false);
			}
		});


	}

	public void createPage2()
	{
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBackground(Color.CYAN);
		JLabel link = new JLabel("<html><u>The look of things</u></html>");
		link.setBounds(40,30,160,25);
		panel2.add(link);
		panel2.setPreferredSize(new Dimension(900,500));
		link.setForeground(Color.BLUE);
		link.setFont(font);
		
		BufferedImage charImage = null;
		try {
			charImage = ImageIO.read(new File("2.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//System.out.println("Cannot find image");
		}
		charImage = resize(charImage,200,300);

		ImageIcon nattIcon = new ImageIcon(charImage);
		JLabel natt =new JLabel(nattIcon);
		natt.setBounds(480,100,200,300);
		panel2.add(natt);
		
		link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		link.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				jep = new JEditorPane();
				jep.setEditable(false);
				HTMLJDialog = new JDialog(frame1,"Help HTML5");
				try {
					jep.setPage(new File("2The look of things.html").toURI().toURL());
					jep.addHyperlinkListener(new HyperlinkListener(){
						public void hyperlinkUpdate(HyperlinkEvent event) {
							if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
								try {
									jep.setPage(event.getURL());
									//urlField.setText(event.getURL().toExternalForm());
								} catch(IOException ioe) {
									jep.setContentType("text/html");
									jep.setText("<html>Could not load</html>");
								}
							}	
						}

					});
				}catch (IOException e1) {
					jep.setContentType("text/html");
					jep.setText("<html>Could not load</html>");
				} 
				JScrollPane scrollPane = new JScrollPane(jep);     
				HTMLJDialog.getContentPane().add(scrollPane);
				HTMLJDialog.setPreferredSize(new Dimension(800,400));
				HTMLJDialog.pack();
				HTMLJDialog.setVisible(true);
				HTMLJDialog.setModal(false);
			}
		});


	}

	public void createPage3()
	{
		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBackground(Color.CYAN);
		JLabel link = new JLabel("<html><u>Customization</u></html>");
		link.setBounds(40,30,125,25);
		panel3.add(link);
		panel3.setPreferredSize(new Dimension(900,500));
		link.setForeground(Color.BLUE);
		link.setFont(font);
		
		BufferedImage charImage = null;
		try {
			charImage = ImageIO.read(new File("3.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//System.out.println("Cannot find image");
		}
		charImage = resize(charImage,200,300);

		ImageIcon nattIcon = new ImageIcon(charImage);
		JLabel natt =new JLabel(nattIcon);
		natt.setBounds(480,100,200,300);
		panel3.add(natt);
		
		link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		link.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				jep = new JEditorPane();
				jep.setEditable(false);
				HTMLJDialog = new JDialog(frame1,"Help HTML5");
				try {
					jep.setPage(new File("3Customization.html").toURI().toURL());
					jep.addHyperlinkListener(new HyperlinkListener(){
						public void hyperlinkUpdate(HyperlinkEvent event) {
							if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
								try {
									jep.setPage(event.getURL());
									//urlField.setText(event.getURL().toExternalForm());
								} catch(IOException ioe) {
									jep.setContentType("text/html");
									jep.setText("<html>Could not load</html>");
								}
							}	
						}

					});
				}catch (IOException e1) {
					jep.setContentType("text/html");
					jep.setText("<html>Could not load</html>");
				} 
				JScrollPane scrollPane = new JScrollPane(jep);     
				HTMLJDialog.getContentPane().add(scrollPane);
				HTMLJDialog.setPreferredSize(new Dimension(800,400));
				HTMLJDialog.pack();
				HTMLJDialog.setVisible(true);
				HTMLJDialog.setModal(false);
			}
		});

	}
	public void createPage4()
	{
		panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBackground(Color.CYAN);
		JLabel link = new JLabel("<html><u>Sounds</u></html>");
		link.setBounds(40,30,75,25);
		panel4.add(link);
		panel4.setPreferredSize(new Dimension(900,500));
		link.setForeground(Color.BLUE);
		link.setFont(font);
		
		BufferedImage charImage = null;
		try {
			charImage = ImageIO.read(new File("4.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//System.out.println("Cannot find image");
		}
		charImage = resize(charImage,200,300);

		ImageIcon nattIcon = new ImageIcon(charImage);
		JLabel natt =new JLabel(nattIcon);
		natt.setBounds(480,100,200,300);
		panel4.add(natt);
		
		link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		link.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				jep = new JEditorPane();
				jep.setEditable(false);
				HTMLJDialog = new JDialog(frame1,"Help HTML5");
				try {
					jep.setPage(new File("4Sounds.html").toURI().toURL());
					jep.addHyperlinkListener(new HyperlinkListener(){
						public void hyperlinkUpdate(HyperlinkEvent event) {
							if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
								try {
									jep.setPage(event.getURL());
									//urlField.setText(event.getURL().toExternalForm());
								} catch(IOException ioe) {
									jep.setContentType("text/html");
									jep.setText("<html>Could not load</html>");
								}
							}	
						}

					});
				}catch (IOException e1) {
					jep.setContentType("text/html");
					jep.setText("<html>Could not load</html>");
				} 
				JScrollPane scrollPane = new JScrollPane(jep);     
				HTMLJDialog.getContentPane().add(scrollPane);
				HTMLJDialog.setPreferredSize(new Dimension(800,400));
				HTMLJDialog.pack();
				HTMLJDialog.setVisible(true);
				HTMLJDialog.setModal(false);
			}
		});


	}
	public void createPage5()
	{
		panel5 = new JPanel();
		panel5.setLayout(null);
		panel5.setBackground(Color.CYAN);
		JLabel link = new JLabel("<html><u>File Transferring</u></html>");
		link.setBounds(40,30,150,25);
		panel5.add(link);
		panel5.setPreferredSize(new Dimension(900,500));
		link.setForeground(Color.BLUE);
		link.setFont(font);
		
		BufferedImage charImage = null;
		try {
			charImage = ImageIO.read(new File("5.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//System.out.println("Cannot find image");
		}
		charImage = resize(charImage,200,300);

		ImageIcon nattIcon = new ImageIcon(charImage);
		JLabel natt =new JLabel(nattIcon);
		natt.setBounds(480,100,200,300);
		panel5.add(natt);
		
		link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		link.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				jep = new JEditorPane();
				jep.setEditable(false);
				HTMLJDialog = new JDialog(frame1,"Help HTML5");
				try {
					jep.setPage(new File("5File Transferring.html").toURI().toURL());
					jep.addHyperlinkListener(new HyperlinkListener(){
						public void hyperlinkUpdate(HyperlinkEvent event) {
							if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
								try {
									jep.setPage(event.getURL());
									//urlField.setText(event.getURL().toExternalForm());
								} catch(IOException ioe) {
									jep.setContentType("text/html");
									jep.setText("<html>Could not load</html>");
								}
							}	
						}

					});
				}catch (IOException e1) {
					jep.setContentType("text/html");
					jep.setText("<html>Could not load</html>");
				} 
				JScrollPane scrollPane = new JScrollPane(jep);     
				HTMLJDialog.getContentPane().add(scrollPane);
				HTMLJDialog.setPreferredSize(new Dimension(800,400));
				HTMLJDialog.pack();
				HTMLJDialog.setVisible(true);
				HTMLJDialog.setModal(false);
			}
		});
	}
	public void createPage6()
	{
		panel6 = new JPanel();
		panel6.setLayout(null);
		panel6.setBackground(Color.CYAN);
		
		JLabel link = new JLabel("<html><u>Don't know SHIT!</u></html>");
		link.setBounds(40,30,150,25);
		panel6.add(link);
		link.setForeground(Color.BLUE);
		link.setFont(font);
		
		JLabel gog = new JLabel("<html><u>Prevents you from failing</u></html>");
		gog.setBounds(40,200,225,25);
		panel6.add(gog);
		gog.setForeground(Color.BLUE);
		gog.setFont(font);
		
		JLabel nattw = new JLabel("<html><u>Contact the GOD Directly!</u></html>");
		nattw.setBounds(480,360,225,25);
		panel6.add(nattw);
		nattw.setForeground(Color.BLUE);
		nattw.setFont(font);
		
		
		
		BufferedImage nattImage = null;
		try {
			nattImage = ImageIO.read(new File("Natt.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//System.out.println("Cannot find image");
		}
		nattImage = resize(nattImage,200,300);

		ImageIcon nattIcon = new ImageIcon(nattImage);
		JLabel natt =new JLabel(nattIcon);
		natt.setBounds(480,30,200,300);
		panel6.add(natt);
		JLabel nattLabel = new JLabel("The image of our GOD");
		nattLabel.setBounds(520,200,200,300);
		panel6.add(nattLabel);
		
		gog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gog.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI("https://www.google.com"));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		nattw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nattw.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI("https://www.facebook.com/executor.natt.wara"));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		panel6.setPreferredSize(new Dimension(900,500));
		
		link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		link.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				jep = new JEditorPane();
				jep.setEditable(false);

				HTMLJDialog = new JDialog(frame1,"Help HTML5");
				try {
					jep.setPage(new File("6Don't know woi.html").toURI().toURL());
					jep.addHyperlinkListener(new HyperlinkListener(){
						public void hyperlinkUpdate(HyperlinkEvent event) {
							if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
								try {
									jep.setPage(event.getURL());
									//urlField.setText(event.getURL().toExternalForm());
								} catch(IOException ioe) {
									jep.setContentType("text/html");
									jep.setText("<html>Could not load</html>");
								}
							}	
						}

					});
				}catch (IOException e1) {
					jep.setContentType("text/html");
					jep.setText("<html>Could not load</html>");
				}
				JScrollPane scrollPane = new JScrollPane(jep); 
				
				HTMLJDialog.getContentPane().add(scrollPane);
				HTMLJDialog.setPreferredSize(new Dimension(800,400));
				HTMLJDialog.pack();
				HTMLJDialog.setVisible(true);
				HTMLJDialog.setModal(false);
			}
		});

	}

}
