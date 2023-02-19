import java.util.*;
import javax.swing.*; //contains jframe class for gui
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import java.net.*;
import java.io.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.*; //contains color class
import java.awt.event.*; //for action listener
import java.text.SimpleDateFormat;

public class chat_server implements ActionListener
{
    JTextField text;
    JPanel a1;
    static JFrame f=new JFrame();
    static DataOutputStream dout;
    static Box vertical=Box.createVerticalBox(); //ekta line sesh hole jate nextline ejaye
    chat_server()
    {
         f.setLayout(null); //setting the layout as null so that user can set layout
         
         f.setSize(450,700);   //gui size  
         f.setLocation(200,50); //to set the location of gui window in monitor 200 from left and 50 from top
         f.getContentPane().setBackground(Color.WHITE); //content pane for selection the whole content and setbackground for background colour
         
         
         //header
         JPanel p1=new JPanel(); //adding a panel for header
         p1.setBackground(new Color(7,94,80)); //set background color of header
         p1.setBounds(0,0,450,70); //0,0 for top left 450 length 70 height
         p1.setLayout(null); //setting panel=null so that we can set any layout according to user
         f.add(p1);//wait kor kichu koris na
         
         


         //image on header
         ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("image/back.png"));// image class object is created .classLoader is used to take any file from our pc
         Image i2=i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT); // cropping the image size as required
         ImageIcon i3=new ImageIcon(i2);
         JLabel back=new JLabel(i3);  
         back.setBounds(5,20,25,25);
         p1.add(back); //adding image inside panel(header)

         //action if clicked in back image
         back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae)
            {
               // System.out.print("hello");
                System.exit(0);

            }
         });

     //profile image
      /*    ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/shuv.jpg"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);
*/

         //profile name
         JLabel name = new JLabel("Shuv");
        name.setBounds(110, 25, 100, 18);
        name.setForeground(Color.WHITE);//change the colour of font
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

         //active now section
        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 45, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status); 
         
         //adding vc call button
         ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("image/vc.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);

         //adding call button
         ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("image/nc.png"));
         Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
         ImageIcon i12 = new ImageIcon(i11);
         JLabel phone = new JLabel(i12);
         phone.setBounds(350, 20, 35, 30);
         p1.add(phone);

         //attribute icon
         ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("image/at.png"));
         Image i14 = i13.getImage().getScaledInstance(20, 25, Image.SCALE_DEFAULT);
         ImageIcon i15 = new ImageIcon(i14);
         JLabel morevert = new JLabel(i15);
         morevert.setBounds(400, 20, 20, 25);
         p1.add(morevert);


        //jekhane texts gulo ashbe
        a1=new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);

        //jekhane message likhbo
        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);
        
        //send button
        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);

          

         f.setUndecorated(true); //uporer minimize cross oi part ta shore jabe
         f.setVisible(true);   //to set the frame visible 
       
    }

    public void actionPerformed(ActionEvent ae)
    {
        try
        {
        String out=text.getText();
        System.out.println(out); 
        
        JPanel p2=formatLabel(out);
      //  p2.add(output);//oi label ta panel e add holo
        
        a1.setLayout(new BorderLayout()); //top,buttom,left,right and a1 is jekhane message gulo store hochhe
        JPanel right=new JPanel(new BorderLayout());//oi chotto sender er every boc ta
        right.add(p2,BorderLayout.LINE_END);//panel i add kora jaye //line end mane right side
        vertical.add(right);             //vertical er right side e align kore     //vertical defined earlier object of Box class takes an argument right jeta ekta jpanel
        vertical.add(Box.createVerticalStrut(15)); //15 space between 2 message send howar por

        a1.add(vertical,BorderLayout.PAGE_START);

        text.setText("");

        dout.writeUTF(out);

        f.repaint();
        f.invalidate();
        f.validate();
    }catch(Exception e){}
        
    }

    //function for that box jar modhhey messages thakbe
    public static JPanel formatLabel(String out)
    {
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));//boxlayout diye kothay layout set korbo y axis 

        JLabel output=new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>"); //using html css tag to fix the message box size
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));//lekhar font change korlam
        output.setBackground(new Color(37, 211, 102));//background change korlam lekhar
        output.setOpaque(true); //opaque so that the writings are visisble
        output.setBorder(new EmptyBorder(15, 15, 15, 50));//padding oi green lekhar box gulo 
        

        panel.add(output);
        Calendar cal = Calendar.getInstance(); //time dekhanor jonno message er niche
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); //time dekhabe
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime())); //dynamically time set korar jonno
        
        panel.add(time);


        return panel;
    }
    
    public static void main(String args[])
    {
        new chat_server();       //main method call korlei jate gui ashe
        try {

            ServerSocket skt=new ServerSocket(6000);
            while(true)
            {
                Socket s=skt.accept();

                DataInputStream din=new DataInputStream(s.getInputStream());
                dout=new DataOutputStream(s.getOutputStream());
            
                while(true)
                {
                    //MIGHT DO IT LATER
                    /*String msg=din.readUTF();
                   

                    JPanel panel=new JPanel();
                    

                    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));//boxlayout diye kothay layout set korbo y axis 

                    JLabel ll=new JLabel("<html><p style=\"width: 150px\">" + msg + "</p></html>"); //using html css tag to fix the message box size
                    ll.setFont(new Font("Tahoma", Font.PLAIN, 16));//lekhar font change korlam
                    ll.setBackground(new Color(211, 211, 211));//background change korlam lekhar
                   ll.setOpaque(true); //opaque so that the writings are visisble
                   ll.setBorder(new EmptyBorder(15, 15, 25, 50));//padding oi green lekhar box gulo 
                   panel.add(ll);
                   panel.add(time);
                    */
                    
                   String msg=din.readUTF();
                    JPanel panel=formatLabel(msg); // server er chatbox take shape dichhi
                    
                    
                    
                    JPanel left=new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    vertical.add(left); //ektar niche ekta
                    f.validate();
                }

            }
        } catch (Exception e) {
           
        }
    }
}