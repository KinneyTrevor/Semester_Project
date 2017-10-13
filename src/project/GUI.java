package project;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI
{
	// fields
	private JFrame frmWeatherStation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GUI window = new GUI();
					window.frmWeatherStation.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmWeatherStation = new JFrame();
		frmWeatherStation.setTitle("Weather Station");
		frmWeatherStation.setIconImage(
				Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/imgs/weatherStationIcon.png")));
		frmWeatherStation.getContentPane().setBackground(new Color(0, 0, 0));
		frmWeatherStation.getContentPane().setLayout(null);

		// TODO
		URL url = GUI.class.getResource("/imgs/piLogoGreen.png");
		ImageIcon icon = new ImageIcon(url);
		Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon resizedIcon = new ImageIcon(scaledImage);

		JLabel lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBackground(Color.GREEN);
		lblImage.setForeground(Color.GREEN);
		lblImage.setBounds(1237, 1037, 135, 168);
		lblImage.setIcon(new ImageIcon(GUI.class.getResource("/imgs/piLogoGreen.png")));
		frmWeatherStation.getContentPane().add(lblImage);

		JLabel lblWind = new JLabel("WIND");
		lblWind.setFont(new Font("Noto Sans", Font.PLAIN, 40));
		lblWind.setForeground(new Color(0, 255, 0));
		lblWind.setBounds(22, 38, 115, 35);
		frmWeatherStation.getContentPane().add(lblWind);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(0, 255, 0));
		separator_2.setBounds(22, 24, 1528, 2);
		frmWeatherStation.getContentPane().add(separator_2);

		JLabel label = new JLabel("000.0");
		label.setFont(new Font("Digital-7 Mono", Font.PLAIN, 99));
		label.setForeground(new Color(0, 255, 0));
		label.setBounds(22, 138, 225, 98);
		frmWeatherStation.getContentPane().add(label);

		JLabel lblMph = new JLabel("MPH");
		lblMph.setFont(new Font("Noto Sans", Font.PLAIN, 30));
		lblMph.setHorizontalAlignment(SwingConstants.CENTER);
		lblMph.setForeground(Color.GREEN);
		lblMph.setBounds(96, 249, 88, 29);
		frmWeatherStation.getContentPane().add(lblMph);

		JLabel lblDate = new JLabel("DATE");
		lblDate.setFont(new Font("Noto Sans", Font.PLAIN, 40));
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setForeground(Color.GREEN);
		lblDate.setBounds(1446, 42, 104, 35);
		frmWeatherStation.getContentPane().add(lblDate);

		JLabel lblTime = new JLabel("TIME");
		lblTime.setFont(new Font("Noto Sans", Font.PLAIN, 40));
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setForeground(Color.GREEN);
		lblTime.setBounds(1446, 313, 104, 35);
		frmWeatherStation.getContentPane().add(lblTime);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.GREEN);
		separator_3.setBounds(22, 295, 802, 2);
		frmWeatherStation.getContentPane().add(separator_3);

		JLabel lblTemperature = new JLabel("TEMPERATURE");
		lblTemperature.setForeground(Color.GREEN);
		lblTemperature.setFont(new Font("Noto Sans", Font.PLAIN, 40));
		lblTemperature.setBounds(22, 313, 282, 35);
		frmWeatherStation.getContentPane().add(lblTemperature);

		JLabel label_1 = new JLabel("000");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Digital-7 Mono", Font.PLAIN, 99));
		label_1.setForeground(Color.GREEN);
		label_1.setBounds(22, 425, 135, 98);
		frmWeatherStation.getContentPane().add(label_1);

		JLabel lblNewLabel = new JLabel("°F");
		lblNewLabel.setFont(new Font("Noto Sans", Font.PLAIN, 30));
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setBounds(72, 523, 43, 42);
		frmWeatherStation.getContentPane().add(lblNewLabel);

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.GREEN);
		separator_4.setBounds(22, 579, 542, 2);
		frmWeatherStation.getContentPane().add(separator_4);

		JLabel lblIpsum = new JLabel("RAINFALL");
		lblIpsum.setForeground(Color.GREEN);
		lblIpsum.setFont(new Font("Noto Sans", Font.PLAIN, 40));
		lblIpsum.setBounds(22, 595, 191, 35);
		frmWeatherStation.getContentPane().add(lblIpsum);

		JLabel label_2 = new JLabel("00");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Digital-7 Mono", Font.PLAIN, 99));
		label_2.setForeground(Color.GREEN);
		label_2.setBounds(22, 701, 93, 98);
		frmWeatherStation.getContentPane().add(label_2);

		JLabel lblNewLabel_1 = new JLabel("FT");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Noto Sans", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(50, 803, 33, 42);
		frmWeatherStation.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("00");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Digital-7 Mono", Font.PLAIN, 99));
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setBounds(149, 701, 98, 98);
		frmWeatherStation.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("IN");
		lblNewLabel_3.setFont(new Font("Noto Sans", Font.PLAIN, 30));
		lblNewLabel_3.setForeground(Color.GREEN);
		lblNewLabel_3.setBounds(187, 803, 33, 42);
		frmWeatherStation.getContentPane().add(lblNewLabel_3);

		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.GREEN);
		separator_5.setBounds(22, 854, 338, 2);
		frmWeatherStation.getContentPane().add(separator_5);

		JLabel lblHumidity = new JLabel("HUMIDITY");
		lblHumidity.setFont(new Font("Noto Sans", Font.PLAIN, 40));
		lblHumidity.setForeground(Color.GREEN);
		lblHumidity.setBounds(22, 871, 202, 35);
		frmWeatherStation.getContentPane().add(lblHumidity);

		JLabel label_3 = new JLabel("%");
		label_3.setFont(new Font("Noto Sans", Font.PLAIN, 30));
		label_3.setForeground(Color.GREEN);
		label_3.setBounds(76, 1077, 25, 42);
		frmWeatherStation.getContentPane().add(label_3);

		JLabel label_4 = new JLabel("000");
		label_4.setFont(new Font("Digital-7 Mono", Font.PLAIN, 99));
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setForeground(Color.GREEN);
		label_4.setBounds(22, 975, 135, 98);
		frmWeatherStation.getContentPane().add(label_4);

		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.GREEN);
		separator_6.setBounds(22, 1129, 225, 2);
		frmWeatherStation.getContentPane().add(separator_6);

		JLabel label_5 = new JLabel("00/00/0000");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Digital-7 Mono", Font.PLAIN, 99));
		label_5.setForeground(Color.GREEN);
		label_5.setBounds(1070, 138, 480, 98);
		frmWeatherStation.getContentPane().add(label_5);

		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.GREEN);
		separator_7.setBounds(1190, 579, 360, 2);
		frmWeatherStation.getContentPane().add(separator_7);

		JLabel lblNewLabel_4 = new JLabel("00:00:00");
		lblNewLabel_4.setFont(new Font("Digital-7 Mono", Font.PLAIN, 99));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setForeground(Color.GREEN);
		lblNewLabel_4.setBounds(1190, 439, 360, 98);
		frmWeatherStation.getContentPane().add(lblNewLabel_4);

		JSeparator separator_8 = new JSeparator();
		separator_8.setForeground(Color.GREEN);
		separator_8.setBounds(1096, 295, 454, 2);
		frmWeatherStation.getContentPane().add(separator_8);

		JLabel lblMonth = new JLabel("MONTH");
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonth.setFont(new Font("Noto Sans", Font.PLAIN, 24));
		lblMonth.setForeground(Color.GREEN);
		lblMonth.setBounds(1100, 251, 93, 29);
		frmWeatherStation.getContentPane().add(lblMonth);

		JLabel lblDay = new JLabel("DAY");
		lblDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay.setFont(new Font("Noto Sans", Font.PLAIN, 24));
		lblDay.setForeground(Color.GREEN);
		lblDay.setBounds(1257, 251, 47, 29);
		frmWeatherStation.getContentPane().add(lblDay);

		JLabel lblYear = new JLabel("YEAR");
		lblYear.setFont(new Font("Noto Sans", Font.PLAIN, 24));
		lblYear.setForeground(Color.GREEN);
		lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYear.setBounds(1409, 251, 104, 29);
		frmWeatherStation.getContentPane().add(lblYear);

		JLabel lblRaspi = new JLabel("RasPi");
		lblRaspi.setFont(new Font("Futurism", Font.PLAIN, 99));
		lblRaspi.setForeground(Color.GREEN);
		lblRaspi.setBounds(725, 1089, 505, 116);
		frmWeatherStation.getContentPane().add(lblRaspi);

		JLabel lblRevolutionaries = new JLabel("Revolutionaries");
		lblRevolutionaries.setFont(new Font("Digital-7 Italic", Font.BOLD, 99));
		lblRevolutionaries.setForeground(Color.GREEN);
		lblRevolutionaries.setBounds(761, 1181, 710, 109);
		frmWeatherStation.getContentPane().add(lblRevolutionaries);

		frmWeatherStation.setBounds(0, 0, 1600, 1600);
		frmWeatherStation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWeatherStation.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		frmWeatherStation.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Noto Sans", Font.PLAIN, 35));
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setIcon(new ImageIcon(GUI.class.getResource("/imgs/open_black_icon.png")));
		mntmOpen.setHorizontalAlignment(SwingConstants.LEFT);
		mntmOpen.setFont(new Font("Noto Sans", Font.PLAIN, 30));
		mnFile.add(mntmOpen);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setIcon(new ImageIcon(GUI.class.getResource("/imgs/save_black_icon.png")));
		mntmSave.setHorizontalAlignment(SwingConstants.LEFT);
		mntmSave.setFont(new Font("Noto Sans", Font.PLAIN, 30));
		mnFile.add(mntmSave);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		mntmExit.setIcon(new ImageIcon(GUI.class.getResource("/imgs/close_red_icon.png")));
		mntmExit.setHorizontalAlignment(SwingConstants.LEFT);
		mntmExit.setFont(new Font("Noto Sans", Font.PLAIN, 30));
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Noto Sans", Font.PLAIN, 35));
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setIcon(new ImageIcon(GUI.class.getResource("/imgs/about_black_icon.png")));
		mntmAbout.setHorizontalAlignment(SwingConstants.LEFT);
		mntmAbout.setFont(new Font("Noto Sans", Font.PLAIN, 30));
		mnHelp.add(mntmAbout);
	}
}
