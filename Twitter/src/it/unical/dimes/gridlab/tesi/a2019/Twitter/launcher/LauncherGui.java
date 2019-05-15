package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.Saving;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.Searching;


public class LauncherGui extends JFrame {
	private static final long serialVersionUID = 1L;
	private String APIKey="hn3UBOFrP4WBLFVjqsCcYB3Dt";										//Application Settings/Consumer Key (API key)
	private String APISecret="ovCke2aWfdW6TvhZ0dggL5mO320mzMlLZwDgcvNVUqlUsZbp9J";	  //Application Settings/Consumer Secret (API Secret)
	private String AccessToken="1113747010694340608-csRTyytaX9TqM6zJLN3yPl5O0V2Yos";					 //Your Access Token/Access Token
	private String AccessTokenSecret="QB57vcKjJLXQbqQXKQ4I5n6wfvVFuUBkS3MfhOC81sjzA";			  //Your Access Token/Access Token Secret
	private String pathFile="";
	private String pathFileAvanzata="";
	private static Searching searching=null;
	private static Saving saving=new Saving();
	private ViewController controller=new ViewController();
	private JTextField hashtag=new JTextField("");
	private JTextField longitudine=new JTextField("");
	private JTextField latitudine=new JTextField("");
	private JTextField area=new JTextField("");
	private JTextField giorno=new JTextField("");
	private JTextField listHashtag=new JTextField(12);
	private JLabel hashtagLabel=new JLabel(" ");
	private JLabel checkLabel=new JLabel("                 Ricerca avanzata");
	private JLabel latitudineLabel=new JLabel("                 Latitudine    (Es. 39.3099931)");
	private JLabel longitudineLabel=new JLabel("                 Longitudine   (Es. 16.2501929)");
	private JLabel areaLabel=new JLabel("                 Area               (in miglia)");
	private JLabel pathFileLabel=new JLabel("                 Nessun file scelto");
	private JLabel pathFileLabelAvanzata=new JLabel("                 Nessun file scelto");
	private JLabel giornoLabel=new JLabel("                 Giorno            (yyyy/mm/dd)");
	private JLabel listHashtagLabel=new JLabel(" ");
	private JButton buttonFile=new JButton("",new ImageIcon(getClass().getResource("seleziona.png")));
	private JButton buttonFileAvanzata=new JButton("",new ImageIcon(getClass().getResource("seleziona.png")));
	private JButton search=new JButton("",new ImageIcon(getClass().getResource("button.png")));
	private JButton searchAvanzata=new JButton("",new ImageIcon(getClass().getResource("button.png")));
	private JButton stop=new JButton("",new ImageIcon(getClass().getResource("stopButton.png")));
	private JButton stopAvanzata=new JButton("",new ImageIcon(getClass().getResource("stopButton.png")));
	private JButton insertHashtagAvanzata=new JButton("",new ImageIcon(getClass().getResource("inserisciHashtag.png")));
	private JButton insertHashtag=new JButton("",new ImageIcon(getClass().getResource("inserisciHashtag.png")));
	private JButton removeHashtagAvanzata=new JButton("",new ImageIcon(getClass().getResource("svuotaHashtag.png")));
	private JButton removeHashtag=new JButton("",new ImageIcon(getClass().getResource("svuotaHashtag.png")));
	private JCheckBox check=new JCheckBox();
	private JPanel panelContainer=new JPanel();
	private JPanel containerTable=new JPanel();
	private JPanel containerDati=new JPanel();
	private JPanel normalResearch=new JPanel();
	private JPanel advanceResearch=new JPanel();
	private JTabbedPane tab=new JTabbedPane();
	private PanelTable panelTable=new PanelTable();
	private DrawGraph barChart=new DrawGraph(new LinkedList<Integer>());
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private DefaultListModel<String> listModelAvanzata = new DefaultListModel<>();
	private JMenu setting, informazioni;
	private JMenuItem aggiornaConfigurazione, esci, help,clock;
	private JMenuBar menuBar;
	public LauncherGui() {
		ImageIcon img = new ImageIcon(getClass().getResource("logoBiancoRosso.png"));
		setIconImage(img.getImage());
		setSize(1680,1020);
		setTitle("Quaketter");
		setVisible(true);
		setLocation(100,10);
	//	setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		giorno.setEditable(false);
		latitudine.setEditable(false);
		longitudine.setEditable(false);
		area.setEditable(false);
		listHashtag.setEditable(false);
		insertHashtagAvanzata.setEnabled(false);
		buttonFileAvanzata.setEnabled(false);
		removeHashtagAvanzata.setEnabled(false);
		searchAvanzata.setEnabled(false);
		removeHashtag.setEnabled(false);
		stop.setEnabled(false);
		stopAvanzata.setEnabled(false);
		searching=new Searching(APIKey, APISecret, AccessToken, AccessTokenSecret);
		menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		setting=new JMenu("Impostazioni");
		informazioni=new JMenu("Informazioni");
		aggiornaConfigurazione=new JMenuItem("Configura impostazioni",new ImageIcon(getClass().getResource("setting.png")));
		esci=new JMenuItem("Esci",new ImageIcon(getClass().getResource("close.png")));
		help=new JMenuItem("Aiuto",new ImageIcon(getClass().getResource("help.png")));
		clock=new JMenuItem("Tempo ricerca",new ImageIcon(getClass().getResource("clock.png")));
		menuBar.add(setting);menuBar.add(informazioni);
		setting.add(aggiornaConfigurazione);
		setting.add(clock);
		setting.add(esci);
		informazioni.add(help);
		esci.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int x=JOptionPane.showConfirmDialog(null, "Vuoi arrestare Quaketter?");
				if(x==0)
					System.exit(0);
			}
		});
		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String info="QUAKETTER\nIl programma permette di ricercare gli stati su Twitter tramite parole chiavi.\n"
						+ "Di default la ricerca si aggiorna ogni 15 secondi, per modificare cliccare"
						+ "su (Impostazioni/Tempo ricerca).\n"
						+ "Per maggiori informazioni sulla ricerca avanzata cliccare sul logo.";
	       		JOptionPane.showMessageDialog(null, info,"Informazioni", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		aggiornaConfigurazione.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConfigurationPanel();
			}
		});
		clock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String x=JOptionPane.showInputDialog("Inserire il tempo di ricerca in secondi (1 secondo =1000).");
				try {
					int c=Integer.parseInt(x);
					controller.setClock(c);
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Il valore inserito non è un numero!","Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(new InitialPanel());
		repaint();
		setVisible(true);
	}//Constructor
	
	class ConfigurationPanel extends JFrame{
		private static final long serialVersionUID = 1L;
		private JTextField APIKey=new JTextField(16);
		private JTextField APISecret=new JTextField(16);
		private JTextField AccessToken=new JTextField(16);
		private JTextField AccessTokenSecret=new JTextField(16);
		private JLabel APIKeyLabel=new JLabel("API Key");
		private JLabel APISecretLabel=new JLabel("API Secret");
		private JLabel AccessTokenLabel=new JLabel("Access Token");
		private JLabel AccessTokenSecretLabel=new JLabel("Access Token Secret");
		private JButton button=new JButton("MODIFICA PARAMETRI",new ImageIcon(getClass().getResource("setting.png")));
		public ConfigurationPanel() {
			ImageIcon img = new ImageIcon(getClass().getResource("icon.png"));
			setIconImage(img.getImage());
			setSize(400,200);
			setLocation(700,400);
			setTitle("Configurazione Impostazioni");
			setResizable(false);
			setVisible(true);
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        add(APIKeyLabel, gbc);
	        gbc.gridx = 1;
	        gbc.gridy = 0;
	        add(APIKey, gbc);
	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        add(APISecretLabel, gbc);
	        gbc.gridx = 1;
	        gbc.gridy = 1;
	        add(APISecret, gbc);
	        gbc.gridx = 0;
	        gbc.gridy = 2;
	        add(AccessTokenLabel, gbc);
	        gbc.gridx = 1;
	        gbc.gridy = 2;
	        add(AccessToken, gbc);
	        gbc.gridx = 0;
	        gbc.gridy = 3;
	        add(AccessTokenSecretLabel, gbc);
	        gbc.gridx = 1;
	        gbc.gridy = 3;
	        add(AccessTokenSecret, gbc);
	        gbc.gridx = 0;
	        gbc.gridy = 4;
		    gbc.fill = GridBagConstraints.HORIZONTAL;
	        gbc.gridwidth = 2;
	        add(button,gbc);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(APIKey.getText().equals("") || APISecret.getText().equals("") || AccessToken.getText().equals("") || AccessTokenSecret.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Attenzione i parametri forniti non sono corretti.\nControllare le chiavi su: https://developer.twitter.com/en/apps/","Parametri Errati", JOptionPane.ERROR_MESSAGE);
						else {
							searching=new Searching(APIKey.getText(), APISecret.getText(), AccessToken.getText(), AccessTokenSecret.getText());
							setVisible(false); //you can't see me!
							dispose(); //Destroy the JFrame object
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, "Attenzione i parametri forniti non sono corretti.\nControllare le chiavi su: https://developer.twitter.com/en/apps/16206932","Parametri Errati", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}//Constructor
		
		public void paint(Graphics g){
			super.paint(g);
			ImageIcon image=new ImageIcon(getClass().getResource("background.jpg"));
			Image img=image.getImage();
			Graphics2D g2d=(Graphics2D)g;
			g2d.drawImage(img,0,0,null);
		}//paint
		
	}//ConfigurationPanel
	
	
	class InitialPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		private GridBagConstraints gbcAR = new GridBagConstraints();
		private GridBagConstraints gbcNR = new GridBagConstraints();
		private String url="logoRossoBianco.png";
		private ImageIcon icone=new ImageIcon(getClass().getResource(url));
		private JLabel label=new JLabel(icone, JLabel.CENTER);
		private	JLabel normalLabel=new JLabel(new ImageIcon(getClass().getResource("ricercaStandard.png")));
		private	JLabel analisiIterazione=new JLabel(new ImageIcon(getClass().getResource("analisiIterazione.png")));
		private final JList<String> listKey =new JList<String>(listModel);
		private final JList<String> listKeyAvanzata =new JList<String>(listModelAvanzata);
		private JScrollPane scrollPane=new JScrollPane(listKey);
		private JScrollPane scrollPaneAvanzata=new JScrollPane(listKeyAvanzata);
		private final String info="GIORNO: yyyy/mm/dd\nLATITUDINE: Es. 39.3099931\nLONGITUDINE: Es. 16.2501929\nAREA: Area in Miglia";
		private JLabel labelAvanzata=new JLabel(new ImageIcon(getClass().getResource("ricercaAvanzata.png")));
		private Font font = new Font("Courier", Font.BOLD,22);
		private Font font2 = new Font("Courier", Font.ITALIC,12);
		private Font font3 = new Font("Courier", Font.BOLD,14);
		private Font font4 = new Font("Arial", Font.BOLD,12);
		
		public InitialPanel() {
			listener();

			//__________
			//Set Layout
			//__________
			panelContainer.setLayout(new GridLayout(1,2));
			setLayout(new GridLayout(1,2));
			normalResearch.setLayout(new GridBagLayout());
			containerDati.setLayout(new GridLayout(3,1));
			
			//__________
			//Set Border
			//__________
			normalLabel.setBorder(new LineBorder(Color.WHITE, 2, true));
			labelAvanzata.setBorder(new LineBorder(Color.WHITE, 2, true));
			advanceResearch.setBorder(new LineBorder(Color.WHITE, 1));
			analisiIterazione.setBorder(new LineBorder(Color.WHITE, 1));
			search.setBorder(new LineBorder(Color.WHITE, 3));	
			containerDati.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			normalResearch.setBorder(new LineBorder(Color.WHITE, 3, true));
			containerTable.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			
			//________
			//Set Font 
			//________
			giorno.setFont(font4);
			hashtag.setFont(font4);
			listHashtag.setFont(font4);
			latitudine.setFont(font4);
			longitudine.setFont(font4);
			area.setFont(font4);
			pathFileLabel.setFont(font2);
			pathFileLabelAvanzata.setFont(font2);
			normalLabel.setFont(font);
			checkLabel.setFont(font);
			giornoLabel.setFont(font3);
			areaLabel.setFont(font3);
			latitudineLabel.setFont(font3);
			longitudineLabel.setFont(font3);
			search.setFont(font2);
	        listKeyAvanzata.setFont(font4);
			labelAvanzata.setFont(font);
			
			//______________
			//Set Foreground
			//______________
			giorno.setForeground(Color.WHITE);
			hashtag.setForeground(Color.WHITE);
			listHashtag.setForeground(Color.WHITE);
			latitudine.setForeground(Color.WHITE);
			longitudine.setForeground(Color.WHITE);
			area.setForeground(Color.WHITE);
			listKey.setForeground(Color.WHITE);
			listKey.setFont(font4);
			pathFileLabel.setForeground(Color.WHITE);
			search.setForeground(new Color(29, 202, 255));
			searchAvanzata.setForeground(new Color(29, 202, 255));
			hashtagLabel.setForeground(Color.WHITE);
			giornoLabel.setForeground(Color.WHITE);
			areaLabel.setForeground(Color.WHITE);
			latitudineLabel.setForeground(Color.WHITE);
			longitudineLabel.setForeground(Color.WHITE);
			labelAvanzata.setForeground(Color.WHITE);
			listHashtagLabel.setForeground(Color.WHITE);
			pathFileLabelAvanzata.setForeground(Color.WHITE);
			checkLabel.setForeground(Color.WHITE);
			normalLabel.setForeground(Color.WHITE);
	        listKeyAvanzata.setForeground(Color.WHITE);
			search.setForeground(Color.WHITE);
			
			//______________
			//Set Background
			//______________
			area.setBackground(new Color(95,95,95));
			giorno.setBackground(new Color(95,95,95));
			hashtag.setBackground(new Color(95,95,95));
			listHashtag.setBackground(new Color(95,95,95));
			latitudine.setBackground(new Color(95,95,95));
			longitudine.setBackground(new Color(95,95,95));
			normalResearch.setBackground(Color.DARK_GRAY);
			advanceResearch.setBackground(Color.DARK_GRAY);
			containerDati.setBackground(new Color(95,95,95));
			check.setBackground(Color.DARK_GRAY);
			search.setBackground(new Color(20,20,20));
			scrollPane.getViewport().getView().setBackground(new Color(95,95,95));
			scrollPaneAvanzata.getViewport().getView().setBackground(new Color(95,95,95));

			//________________
			//Invisible button
			//________________
			search.setBorderPainted(false);
			search.setFocusPainted(false);
			search.setContentAreaFilled(false);
			stop.setBorderPainted(false);
			stop.setFocusPainted(false);
			stop.setContentAreaFilled(false);		
			stopAvanzata.setBorderPainted(false);
			stopAvanzata.setFocusPainted(false);
			stopAvanzata.setContentAreaFilled(false);			
			removeHashtagAvanzata.setBorderPainted(false);
			removeHashtagAvanzata.setFocusPainted(false);
			removeHashtagAvanzata.setContentAreaFilled(false);			
			removeHashtag.setBorderPainted(false);
			removeHashtag.setFocusPainted(false);
			removeHashtag.setContentAreaFilled(false);			
			searchAvanzata.setBorderPainted(false);
			searchAvanzata.setFocusPainted(false);
			searchAvanzata.setContentAreaFilled(false);			
			insertHashtag.setBorderPainted(false);
			insertHashtag.setFocusPainted(false);
			insertHashtag.setContentAreaFilled(false);			
			buttonFile.setBorderPainted(false);
			buttonFile.setFocusPainted(false);
			buttonFile.setContentAreaFilled(false);			
			buttonFileAvanzata.setBorderPainted(false);
			buttonFileAvanzata.setFocusPainted(false);
			buttonFileAvanzata.setContentAreaFilled(false);			
			insertHashtagAvanzata.setBorderPainted(false);
			insertHashtagAvanzata.setFocusPainted(false);
			insertHashtagAvanzata.setContentAreaFilled(false);
			
			//___________________________________
			//Disposizione Component Normal Panel
			//___________________________________
			gbcNR.fill = GridBagConstraints.HORIZONTAL;
	        gbcNR.gridx = 0;
	        gbcNR.gridy = 0;
			normalResearch.add(normalLabel, gbcNR);
	        gbcNR.gridx = 0;
	        gbcNR.gridy = 1;
			normalResearch.add(hashtagLabel, gbcNR);
			gbcNR.gridx = 0;
	        gbcNR.gridy = 2;
			normalResearch.add(hashtag, gbcNR);
			gbcNR.gridx = 1;
	        gbcNR.gridy = 2;
			normalResearch.add(insertHashtag, gbcNR);
			scrollPane.setPreferredSize(new Dimension(50,50));
	        gbcNR.gridx = 0;
	        gbcNR.gridy = 3;
	        normalResearch.add(scrollPane, gbcNR);
	        gbcNR.gridx = 1;
	        gbcNR.gridy = 3;
	        normalResearch.add(removeHashtag, gbcNR);      
			gbcNR.gridx = 0;
		    gbcNR.gridy = 4;
			normalResearch.add(buttonFile,gbcNR);
			gbcNR.gridx = 1;
		    gbcNR.gridy = 4;
			normalResearch.add(pathFileLabel, gbcNR);
			gbcNR.gridx = 0;
		    gbcNR.gridy = 5;
			normalResearch.add(search, gbcNR);
			gbcNR.gridx = 1;
		    gbcNR.gridy = 5;
			normalResearch.add(stop, gbcNR);
			gbcNR.gridx = 0;
		    gbcNR.gridy = 6;
			normalResearch.add(checkLabel, gbcNR);
			gbcNR.gridx = 1;
		    gbcNR.gridy = 6;
			normalResearch.add(check, gbcNR);	        
			advanceResearch.setLayout(new GridBagLayout());

			//____________________________________
			//Disposizione Component Advance Panel
			//____________________________________
			gbcAR.fill = GridBagConstraints.HORIZONTAL;
			gbcAR.gridx = 0;
	        gbcAR.gridy = 0;
	        advanceResearch.add(labelAvanzata, gbcAR);
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 1;
	        advanceResearch.add(giornoLabel, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 1;
	        advanceResearch.add(giorno, gbcAR);
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 2;
	        advanceResearch.add(latitudineLabel, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 2;
	        advanceResearch.add(latitudine, gbcAR);
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 3;
	        advanceResearch.add(longitudineLabel, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 3;
	        advanceResearch.add(longitudine, gbcAR);
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 4;
	        advanceResearch.add(areaLabel, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 4;
	        advanceResearch.add(area, gbcAR);
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 5;
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 6;
	        advanceResearch.add(listHashtag, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 6;
	        advanceResearch.add(insertHashtagAvanzata, gbcAR);
			scrollPaneAvanzata.setPreferredSize(new Dimension(50,50));
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 7;
	        advanceResearch.add(scrollPaneAvanzata, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 7;
	        advanceResearch.add(removeHashtagAvanzata, gbcAR); 
			gbcAR.gridx = 0;
		    gbcAR.gridy = 8;
	        advanceResearch.add(buttonFileAvanzata,gbcAR);
			gbcAR.gridx = 1;
		    gbcAR.gridy = 8;
	        advanceResearch.add(pathFileLabelAvanzata, gbcAR);
			gbcAR.gridx = 0;
		    gbcAR.gridy = 9;
	        advanceResearch.add(searchAvanzata, gbcAR);
			gbcAR.gridx = 1;
		    gbcAR.gridy = 9;
		    
		    //___________________________
		    //Aggiunta Component ai panel
		    //___________________________
	        advanceResearch.add(stopAvanzata, gbcAR);
			containerTable.add(panelTable, BorderLayout.NORTH);
			containerDati.add(normalResearch);
			containerDati.add(advanceResearch);
			containerDati.add(label);
			add(panelContainer);
			
			//______________________
			//Aggiornamento con tab	
			//______________________
			tab.setBackground(Color.DARK_GRAY);
			UIManager.put("TabbedPane.selected", new Color(95,95,95));
			UIManager.put("TabbedPane.focus ", new Color(95,95,95));			
			UIManager.put("TabbedPane.contentAreaColor", new Color(95,95,95));
			UIManager.put("TabbedPane.highlight", Color.WHITE);
			UIManager.put("TabbedPane.shadow", new Color(95,95,95));
			UIManager.put("TabbedPane.darkShadow", Color.WHITE);
			UIManager.put("TabbedPane.borderHightlightColor", new Color(95,95,95));
			tab.setOpaque(true);
			tab.setUI(new BasicTabbedPaneUI());
			
			tab.addTab("RISULTATI", panelTable);

			JPanel panelAnalisiTotale=new JPanel();
			JPanel panelAnalisiIterazione=new JPanel();
			panelAnalisiIterazione.setLayout(new GridLayout(2,0));
			panelAnalisiIterazione.setBackground(new Color(95,95,95));
			JPanel tmp=new JPanel();
			tmp.setBackground(new Color(95,95,95));
			JLabel labelTmp=new JLabel("HASHTAG ANALIZZATI AD OGNI ITERAZIONE");
			labelTmp.setFont(new Font("Arial", Font.BOLD,18));
			labelTmp.setForeground(Color.WHITE);
			tmp.add(labelTmp);
			tmp.add(analisiIterazione);
			barChart.setBorder(new LineBorder(Color.WHITE, 1));
			panelAnalisiIterazione.add(tmp);
			panelAnalisiIterazione.add(barChart);
			tab.addTab("ANALISI ITERAZIONE",panelAnalisiIterazione);
			tab.addTab("ANALISI TOTALE", panelAnalisiTotale);
			for (int i = 0; i < tab.getComponentCount(); ++i){
			  tab.setBackgroundAt(i,Color.DARK_GRAY);
			  tab.setForegroundAt(i, Color.WHITE); 
			}
			UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource( Color.RED ));
			UIManager.put("TabbedPane.darkShadow", new ColorUIResource( Color.RED ));
			panelContainer.add(containerDati);
			panelContainer.add(tab);
			repaint();
			setVisible(true);
		}//Constructor
		
		private void listener() {
			 insertHashtag.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(insertHashtag.isEnabled()){
							if(!hashtag.getText().equals("")) {
								listModel.addElement(hashtag.getText());
								hashtag.setText("");	
								removeHashtag.setEnabled(true);
							}
							else {
								JOptionPane.showMessageDialog(null, "Nessun hashtag inserito","Errore", JOptionPane.ERROR_MESSAGE);
							}
						}
					}});
			 stop.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(stop.isEnabled()){
							controller.setStopTrue();
							stop.setEnabled(false);
							search.setText("");
							search.setEnabled(true);
							check.setEnabled(true);
						}
					}});
		        stopAvanzata.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(stopAvanzata.isEnabled()){
							controller.setStopTrue();
							stopAvanzata.setEnabled(false);
							searchAvanzata.setText("");
							searchAvanzata.setEnabled(true);
							check.setEnabled(true);
						}
					}});
		        insertHashtagAvanzata.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(insertHashtagAvanzata.isEnabled()){
							if(!listHashtag.getText().equals("")) {
								listModelAvanzata.addElement(listHashtag.getText());
								listHashtag.setText("");	
								removeHashtagAvanzata.setEnabled(true);
							}
							else {
								JOptionPane.showMessageDialog(null, "Nessun hashtag inserito","Errore", JOptionPane.ERROR_MESSAGE);
							}
						}
					}});				
		        removeHashtagAvanzata.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(removeHashtagAvanzata.isEnabled()){
							listModelAvanzata.removeAllElements();
							removeHashtagAvanzata.setEnabled(false);
						}
						}});
		        removeHashtag.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(removeHashtag.isEnabled()){
							listModel.removeAllElements();
							removeHashtag.setEnabled(false);
						}
						}});
				label.addMouseListener(new MouseAdapter() {
			       	@Override
			       	public void mouseClicked(MouseEvent evt) {
			       		JOptionPane.showMessageDialog(null, info,"Informazioni Ricerca Avanzata", JOptionPane.INFORMATION_MESSAGE);
			       	}
		        });
				check.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(check.isEnabled()) {
							if(check.isSelected()) {
								giorno.setEditable(true);
								latitudine.setEditable(true);
								longitudine.setEditable(true);
								area.setEditable(true);
								listHashtag.setEditable(true);
								insertHashtagAvanzata.setEnabled(true);
								hashtag.setEditable(false);
								buttonFileAvanzata.setEnabled(true);
								buttonFile.setEnabled(false);
							//	removeHashtagAvanzata.setEnabled(true);
								search.setEnabled(false);
								searchAvanzata.setEnabled(true);
								insertHashtag.setEnabled(false);
								removeHashtag.setEnabled(false);
								if(listModelAvanzata.isEmpty())
									removeHashtagAvanzata.setEnabled(false);
								else
									removeHashtagAvanzata.setEnabled(true);
							}
							else if(!check.isSelected()) {
						//		giorno.setText("");
						//		latitudine.setText("");
						//		longitudine.setText("");
						//		area.setText("");
								hashtag.setEditable(true);
								giorno.setEditable(false);
								latitudine.setEditable(false);
								longitudine.setEditable(false);
								area.setEditable(false);
								listHashtag.setEditable(false);
								insertHashtagAvanzata.setEnabled(false);
								buttonFileAvanzata.setEnabled(false);
								buttonFile.setEnabled(true);
								removeHashtagAvanzata.setEnabled(false);
								search.setEnabled(true);
								searchAvanzata.setEnabled(false);
								insertHashtag.setEnabled(true);
							//	removeHashtag.setEnabled(true);
								if(listModel.isEmpty())
									removeHashtag.setEnabled(false);
								else
									removeHashtag.setEnabled(true);
							}
						}
					}});
				hashtag.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(hashtag.isEnabled()) {
							hashtag.setText("");	
						}
					}});
				latitudine.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(latitudine.isEnabled())
							latitudine.setText("");						
					}});
				longitudine.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(longitudine.isEnabled())
							longitudine.setText("");						
					}});
				area.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(area.isEnabled())
							area.setText("");						
					}});
				giorno.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						if(giorno.isEnabled())
							giorno.setText("");						
					}});
				buttonFile.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(buttonFile.isEnabled()) {
							JFileChooser fileChooser=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
							fileChooser.setDialogTitle("Seleziona un file");
							fileChooser.setAcceptAllFileFilterUsed(false);
							FileNameExtensionFilter filter = new FileNameExtensionFilter("Tutti i file di tipo (*.xls)", "xls");
							fileChooser.addChoosableFileFilter(filter);
							int returnValue = fileChooser.showOpenDialog(null);
							if (returnValue == JFileChooser.APPROVE_OPTION) {
								File selectedFile = fileChooser.getSelectedFile();
								pathFile=selectedFile.getPath();
								pathFileLabel.setText(pathFile);
							}
						}
					}
				});
				buttonFileAvanzata.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(buttonFileAvanzata.isEnabled()) {
							JFileChooser fileChooser=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
							fileChooser.setDialogTitle("Seleziona un file");
							fileChooser.setAcceptAllFileFilterUsed(false);
							FileNameExtensionFilter filter = new FileNameExtensionFilter("Tutti i file di tipo (*.xls)", "xls");
							fileChooser.addChoosableFileFilter(filter);
							int returnValue = fileChooser.showOpenDialog(null);
							if (returnValue == JFileChooser.APPROVE_OPTION) {
								File selectedFile = fileChooser.getSelectedFile();
								pathFileAvanzata=selectedFile.getPath();
								pathFileLabelAvanzata.setText(pathFileAvanzata);
							}
						}
					}
				});
				search.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(search.isEnabled()) {
							if(listModel.size()==0) {
								JOptionPane.showMessageDialog(null, "Nessuna parola chiave inserita. Ripetere","Errore", JOptionPane.ERROR_MESSAGE);
							}
							else if(pathFile.equals("")) {
								JOptionPane.showMessageDialog(null, "Nessun file selezionato su cui salvare.","Attenzione", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								controller.setStopFalse();
								stop.setEnabled(true);
								check.setEnabled(false);
								controller.normalSearch(searching, listModel, pathFile, panelTable, barChart, saving, search);
							}
						}
					}});
				searchAvanzata.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(searchAvanzata.isEnabled()) {
							if(listModelAvanzata.size()==0) {
								JOptionPane.showMessageDialog(null, "Nessuna parola chiave inserita. Ripetere","Errore", JOptionPane.ERROR_MESSAGE);
							}
							else if(pathFileAvanzata.equals("")) {
								JOptionPane.showMessageDialog(null, "Nessun file selezionato su cui salvare.","Attenzione", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								controller.setStopFalse();
								stopAvanzata.setEnabled(true);
								check.setEnabled(false);
								controller.advanceSearch(searching, listModelAvanzata, latitudine, longitudine, area, giorno, pathFileAvanzata, panelTable, barChart, saving, searchAvanzata);
							}
						}
					}});
		}//listener
 
	}//InitialPanel

	public static void main(String...args) throws IOException {
		LauncherGui launcher=new LauncherGui();
	}//main
	
}//LauncherGui