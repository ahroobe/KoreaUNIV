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

public class Board1 extends JFrame {

	private JPanel contentPane;

	JButton logout = new JButton("로그아웃");
	FileReader read1 = null;
	FileWriter write1 = null;
	JButton write = new JButton("\uAE00\uC4F0\uAE30");
	JButton back = new JButton("\uB4A4\uB85C");
	JButton find = new JButton("\uBCF4\uAE30");
	String body = "";
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public Board1() {
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 124, 600, 415);
		contentPane.add(scrollPane);

		JTextArea Titles = new JTextArea();
		Titles.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Titles.setEditable(false);
		Titles.setBackground(SystemColor.menu);
		scrollPane.setViewportView(Titles);

		write.setBounds(547, 548, 97, 36);
		contentPane.add(write);

		back.setBounds(290, 608, 97, 43);
		contentPane.add(back);

		JLabel lblNewLabel_1 = new JLabel("\uAC8C\uC2DC\uBB3C \uBC88\uD638:");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(42, 559, 132, 15);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(146, 556, 42, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		find.setBounds(200, 555, 66, 23);
		contentPane.add(find);

		try {
			File f = new File(Variable.Subject + "의 게시판]titles.txt");
			if (f.exists()) {

				read1 = new FileReader(Variable.Subject + "의 게시판]titles.txt");

				while (true) {
					int data = read1.read();
					if (data == -1)
						break;

					char ch = (char) data;
					body += ch;
				}

				Titles.setText(body);
				read1.close();

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "오류발생!");
			System.exit(1);
		}

		JLabel label = new JLabel("\uC790\uC720\uAC8C\uC2DC\uD310");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label.setBounds(42, 88, 132, 15);
		contentPane.add(label);
		actionwrite();
		actionfind();
		actionlogout();
		actionback();
	}

	public void actionwrite() {
		write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				BoardW bd = new BoardW();
				bd.setVisible(true);
				dispose();

			}
		});
	}

	public void actionfind() {
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				int number = Integer.parseInt(textField.getText());

				Variable.Title = Variable.Subject + "의 게시판]" + number + ".txt";
				File f = new File(Variable.Subject + "의 게시판]" + number + ".txt");
				if (!f.exists()) {
					JOptionPane.showMessageDialog(null, "없는 번호입니다!");
				} else {

					BoardR br = new BoardR();
					br.setVisible(true);
					dispose();

				}
			}
		});
	}

	public void actionback() {
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				Japan jb = new Japan();
				jb.setVisible(true);
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
