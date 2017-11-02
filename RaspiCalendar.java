package proto;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RaspiCalendar {

	private static final int WIDTH = 1300;
	private static final int HEIGHT = 650;

	private static JFrame raspiRevolutionaries = new JFrame();
	
	public static void main(String[] args){
		calendarSetup();
		controlSetup();
		
		raspiRevolutionaries.setTitle("Raspi Revolutionaries");
		raspiRevolutionaries.getContentPane().setLayout(new FlowLayout());
		raspiRevolutionaries.getContentPane().setBackground(Color.BLACK);
		raspiRevolutionaries.setSize(WIDTH, HEIGHT);
		raspiRevolutionaries.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		raspiRevolutionaries.setVisible(true);
		raspiRevolutionaries.pack();
		
	}
	
	private static void calendarSetup(){
		JPanel calendarPane = new JPanel();
		GregorianCalendar calendar = new GregorianCalendar();
		CalNavigationPanel calendarNavi = new CalNavigationPanel();
		CalendarView calendarView = new CalendarView(calendar, 450, 450);
		
		calendarPane.setLayout(new BoxLayout(calendarPane, BoxLayout.PAGE_AXIS));

		calendarNavi.setBackground(Color.BLACK);

		calendarView.setBackground(Color.BLACK);
		
		calendarNavi.getPrevButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				calendarView.prevDay();
			}
		});
		calendarNavi.getNextButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				calendarView.nextDay();
			}
		});
		calendarNavi.getViewButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				System.out.println(calendar.get(5));
			}
		});
		
		calendarPane.add(calendarView);
		calendarPane.add(calendarNavi);
		
		raspiRevolutionaries.add(calendarPane);
	}
	
	private static void controlSetup(){
		JPanel controlPane = new JPanel();
		controlPane.setLayout(new GridLayout(5, 1));
		controlPane.setBackground(Color.BLACK);
		
		JRadioButton morning = new JRadioButton();
		morning.setText("Morning");
		morning.setForeground(Color.green);
		morning.setBackground(Color.BLACK);
		
		JRadioButton afternoon = new JRadioButton();
		afternoon.setText("Afternoon");
		afternoon.setForeground(Color.green);
		afternoon.setBackground(Color.BLACK);
		
		JRadioButton evening = new JRadioButton();
		evening.setText("Evening");
		evening.setForeground(Color.green);
		evening.setBackground(Color.BLACK);
		
		JRadioButton night = new JRadioButton();
		night.setText("Night");
		night.setForeground(Color.green);
		night.setBackground(Color.BLACK);
		
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(RaspiCalendar.class.getResource("/images/piLogoGreenXS.png")));
		logo.setBackground(Color.green);
		
		controlPane.add(morning);
		controlPane.add(afternoon);
		controlPane.add(evening);
		controlPane.add(night);
		controlPane.add(logo);
		
		raspiRevolutionaries.add(controlPane);
	}
}