import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

class Age
{
    public Integer Birth[],Today[];
    
    public Age(Integer bArr[], Integer pArr[])
    {
        Birth = new Integer[3];
        Today = new Integer[3];

        Birth = bArr;
        Today = pArr;
    }

    public int Year()
    {
        if (Today[1] < Birth[1])
        {
           	return (Today[2]-1)-Birth[2];
        }	 
        else if(Today[1] == Birth[1] && Today[0] < Birth[0])
        {
            return (Today[2] - 1) - Birth[2];
        }  
        else
        {
         	return Today[2] - Birth[2];
        }
    }

    public int Month()
    {
        if (Today[1] > Birth[1] && Today[0] > Birth[0])
        {
   	    	return Today[1] - Birth[1];
        }  
        else if(Today[1] < Birth[1] && Today[0] < Birth[0])
        {
   	    	return 12 - (Birth[1] - Today[1] + 1);
        }  
        else if(Today[1] > Birth[1] && Today[0] < Birth[0])
        {
           	return Today[1] - Birth[1] - 1;
        }  
        else if(Today[1] < Birth[1] && Today[0] > Birth[0])
        {
        	return 12 - (Birth[1] - Today[1]);
        }   
        else if(Today[1] == Birth[1] && Today[0] < Birth[0])
        {
   	    	return 11;
   	    }  
        else if(Today[1] < Birth[1] && Today[0] == Birth[0])
        {
   	    	return 12 - (Birth[1] - Today[1]);
   	    }
        else if(Today[1] > Birth[1] && Today[0] == Birth[0])
        {
   	    	return Today[1] - Birth[1];
    
        }  
        else 
        {
          	return Birth[1] - Today[1];
        } 
    }

    public int day()
    {
        if (Today[0] > Birth[0])
        {
  	    	return Today[0] - Birth[0];

        } 
        else if(Today[0] == Birth[0])
        {
   	    	return Today[0] - Birth[0];
        }  
        else 
        {
   	    	return 31 - (Birth[0] - Today[0]);
        }
    }

    public int NextMonth()
    {
        if (Today[1] < Birth[1] && Today[0] < Birth[0])
        {
   	    	return Birth[1] - Today[1];
        }	
        else if(Today[1] < Birth[1] && Today[0] > Birth[0])
        {
  	        return Birth[1] - Today[1] - 1;

        }  
        else if(Today[1] > Birth[1] && Today[0] > Birth[0])
        {
        	return 12 - (Today[1] - Birth[1] + 1);
        }    
        else if(Today[1] > Birth[1] && Today[0] < Birth[0])
        {
   	        return 12 - (Today[1] - Birth[1]);
        }	
        else if(Today[1] == Birth[1] && Today[0] > Birth[0])
        {
   	        return 11;
        }   
        else if (Today[1] > Birth[1] && Today[0] == Birth[0])
        {
   	        return 12 - (Today[1] - Birth[1]);
        }	
        else if(Today[1] < Birth[1] && Today[0] == Birth[0])
        {
   	        return Birth[1] - Today[1];
	    }	
        else 
        {
            return Birth[1] - Today[1];
        }

    }

    public int Nextday()
    {
        if(Today[0] < Birth[0])
        {
            return Birth[0] - Today[0];
        }	
        else if(Today[0] == Birth[0])
        {
   	        return Today[0] - Birth[0];
        }	
        else 
        {
            return 31 - (Today[0] - Birth[0]);
        } 
    }

}

class ActualFrame extends WindowAdapter implements ActionListener
{
    private JFrame mainFrame;
    private JButton bAge, nextB;
    private JTextField bDay,bMonth,bYear,pDay,pMonth,pYear;
    private JLabel Output, lBirth, lPresent;

    Integer iday,imonth,iyear,iNextday,iNextMonth;
    Integer bArr[] ,pArr[];
    
    

    public ActualFrame(int width, int hieght, String Title)
    {
        mainFrame = new JFrame(Title);
        mainFrame.setSize(width,hieght);
        mainFrame.setLayout(null);
        mainFrame.getContentPane().setBackground(Color.WHITE);
        mainFrame.addWindowListener(this);

        bAge = new JButton("Age");
        nextB = new JButton("Next Birthday");
        
        bAge.setBounds(50,220,150,40);
        nextB.setBounds(220,220,150,40);

        mainFrame.add(bAge);
        mainFrame.add(nextB);

        bAge.addActionListener(this);
        nextB.addActionListener(this);

        bDay = new JTextField();
        bMonth = new JTextField();
        bYear = new JTextField();
        pDay = new JTextField();
        pMonth = new JTextField();
        pYear = new JTextField();

        bDay.setBounds(170,70,40,30);
        bMonth.setBounds(220,70,40,30);
        bYear.setBounds(270,70,40,30);
        pDay.setBounds(170,130,40,30);
        pMonth.setBounds(220,130,40,30);
        pYear.setBounds(270,130,40,30);

        mainFrame.add(bDay);
        mainFrame.add(bMonth);
        mainFrame.add(bYear);
        mainFrame.add(pDay);
        mainFrame.add(pMonth);
        mainFrame.add(pYear);

        lBirth = new JLabel();
        lPresent = new JLabel();
        Output = new JLabel();

        lBirth.setBounds(10,70,180,40);
        lPresent.setBounds(10,130,180,40);
        Output.setBounds(100,300,300,40);

        mainFrame.add(lBirth);
        mainFrame.add(lPresent);
        mainFrame.add(Output);

        lBirth.setText("Birth date(dd/mm/yyyy):");
        lPresent.setText("Present date(dd/mm/yyyy):");
        
        mainFrame.setVisible(true);

    } 
    public void windowClosing(WindowEvent obj)
    {
        System.exit(0);
    }
    public void actionPerformed(ActionEvent obj)
    {
        try
        {
            bArr = new Integer[3];
            pArr = new Integer[3];

            bArr[0] = Integer.parseInt(bDay.getText());
            bArr[1] = Integer.parseInt(bMonth.getText());
            bArr[2] = Integer.parseInt(bYear.getText());

            pArr[0] = Integer.parseInt(pDay.getText());
            pArr[1] = Integer.parseInt(pMonth.getText());
            pArr[2] = Integer.parseInt(pYear.getText());

            Age ag = new Age(bArr,pArr);

            iyear = ag.Year();
            imonth = ag.Month();
            iday = ag.day();

            iNextMonth = ag.NextMonth();
            iNextday = ag.Nextday();
            

            if(obj.getSource() == bAge)
            {
                
                Output.setText("Your age : "+iyear+" years "+imonth+" months "+iday+" days");
            }
            else if(obj.getSource() == nextB)
            {
                Output.setText("Next Birthday on : "+iNextMonth+" months "+iNextday+" days");
            }
        }
        catch(Exception uNKnown)
        {}

    }
}

class Agecalculator
{
    public static void main(String arr[]) throws Exception
    {
        ActualFrame FrameSize = new ActualFrame(450,400,"Age calculator");
    }
}