package proto;

import java.awt.Color;
import java.util.GregorianCalendar;

import javax.swing.JButton;

public class DefaultColorStrat implements ButtonColorStrat
{
  public void color(JButton button, int date, GregorianCalendar cal, boolean selected)
  {
	button.setForeground(Color.green);
	//If date has data--
	//	if (cal.getEventsOnDate(date, cal.get(2), cal.get(1)).size() > 0) {
//	   button.setBackground(Color.green);
//	   button.setForeground(Color.black);
//	}
    button.setBackground(selected ? Color.WHITE : null);
  }
  
  public void color(JButton button, int date, GregorianCalendar cal, boolean selected, Color bgColor)
  {
	button.setForeground(Color.green);
	//If date has data--
//    if () {
//      button.setBackground(Color.green);
//      button.setForeground(Color.black);
//    }
    button.setBackground(selected ? Color.WHITE : bgColor);
  }
}