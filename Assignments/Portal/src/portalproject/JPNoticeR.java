package portalproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class JPNoticeR extends JFrame {
	private JPanel contentPane;
	FileWriter write2 = null;
	JButton logout = new JButton("로그아웃");
	FileReader read1 = null;
	FileWriter write1 = null;
	JButton back = new JButton("\uB4A4\uB85C");
	String body = "";
	JButton revise = new JButton("\uC218\uC815");
	JTextArea textArea = new JTextArea();

	String name="";
	/**
	 * Create the frame.
	 */
	public JPNoticeR() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("EKU");
		setSize(700, 700);
		Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		if (frameSize.height > monitorSize.height) {
			frameSize.height = monitorSize.height;
		}
		if (frameSize.width > monitorSize.width) {
			frameSize.width = monitorSize.width;
		}
		int locationX = (monitorSize.width - frameSize.width) / 2;
		int locationY = (monitorSize.height - frameSize.height) / 2;
		setLocation(locationX, locationY);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\그림\\dd.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel(Variable.UserName + "("
				+ Variable.UserID + ")님 환영합니다.[" + Variable.UserJob + "]");
		lblNewLabel.setIcon(new ImageIcon("C:\\그림\\\u314E\u314E\u314E.jpg"));
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(32, 15, 531, 50);
		contentPane.add(lblNewLabel);
		logout.setBounds(562, 22, 110, 36);
		contentPane.add(logout);

		back.setBounds(290, 608, 97, 43);
		contentPane.add(back);

	
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		if (!Variable.UserJob.equals("교직원")) {
			textArea.setEditable(false);
		}
		textArea.setBackground(SystemColor.control);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 102, 629, 437);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(textArea);
		try {

			
			read1 = new FileReader(Variable.Title);
			while (true) {
				int data = (int) read1.read();
				if (data == -1) {
					break;
				}
				
					body +=(char) data;
			}

		} catch (Exception eo) {

		}
		textArea.setText(body);

		if (Variable.UserJob.equals("교직원")) {
			revise.setBounds(562, 549, 97, 43);
			contentPane.add(revise);
		}

	
		actionrevise();
		
		actionlogout();
		actionback();
	}

	public void actionrevise() {
		revise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String str = textArea.getText();
				try{
					write1 = new FileWriter(Variable.Title);
					write1.write(str);
					write1.close();
				}
				catch(Exception eo){
					
				}
				JOptionPane.showMessageDialog(null, "수정되었습니다!");
				JPNotice jn = new JPNotice();
				jn.setVisible(true);
				dispose();
			}
		});
	}

	public void actionback() {
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				JPNotice b1 = new JPNotice();
				b1.setVisible(true);
				dispose();

			}
		});
	}

	public void actionlogout() {
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// 모든 저장되있는 것 초기화시키기(로그아웃전에)
				Variable.UserID = "";
				Variable.UserName = "";
				Variable.UserJob = "";
				Login log = new Login();
				log.setVisible(true);
				dispose();

			}
		});
	}
}
