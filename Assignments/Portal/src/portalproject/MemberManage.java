package portalproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

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
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JTextField;

public class MemberManage extends JFrame {

	private JPanel contentPane;
	JButton logout = new JButton("로그아웃");
	private JTextField textField;
	private JTextField textField_1;
	JButton back = new JButton("\uB4A4\uB85C");
	JButton del = new JButton("\uC0AD\uC81C");
	JButton reg = new JButton("\uB4F1\uB85D");

	char Jo1 = '9';
	int number = 0;
	FileReader read1 = null;
	FileWriter write1 = null;
	String regfield = "";
	String delfield = "";

	public MemberManage() {
		super("EKU");
		setSize(700, 500);
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
		JLabel lblNewLabel = new JLabel(Variable.UserName + "("
				+ Variable.UserID + ")님 환영합니다.[" + Variable.UserJob + "]");
		lblNewLabel.setBounds(32, 15, 531, 50);
		lblNewLabel.setIcon(new ImageIcon("C:\\그림\\\u314E\u314E\u314E.jpg"));
		contentPane.setLayout(null);
		logout.setBounds(562, 22, 110, 36);
		contentPane.add(logout);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel);
		setContentPane(contentPane);

		JLabel lblNewLabel_1 = new JLabel("\uD559\uC0DD\uBA85\uB2E8");
		lblNewLabel_1.setBounds(42, 75, 128, 24);
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1);

		back.setBounds(293, 415, 98, 36);
		contentPane.add(back);

		JLabel lblNewLabel_2 = new JLabel("학생ID(괄호 안):");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(293, 139, 166, 36);
		contentPane.add(lblNewLabel_2);

		JLabel label_1 = new JLabel("학생ID(괄호 안):");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_1.setBounds(293, 270, 166, 36);
		contentPane.add(label_1);

		textField = new JTextField();
		textField.setBounds(473, 148, 110, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(473, 279, 110, 21);
		contentPane.add(textField_1);

		reg.setBounds(595, 146, 65, 30);
		contentPane.add(reg);

		del.setBounds(595, 277, 65, 30);
		contentPane.add(del);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 120, 198, 312);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.menu);
		scrollPane.setViewportView(textArea);

		try {
			read1 = new FileReader("학생수.txt");
			String s = "";
			while (true) {
				int data = (int) read1.read();
				if (data == -1)
					break;
				char ch = (char) data;
				s += ch;
			}
			read1.close();
			number = Integer.parseInt(s);
			s = "";

			read1 = new FileReader("학생명단.txt");
			while (true) {
				int data = (int) read1.read();
				if (data == -1)
					break;
				char ch = (char) data;
				s += ch;
			}

			textArea.setText(s);

		} catch (Exception eo) {

		}
		actiondel();
		actionreg();
		actionlogout();
		actionback();

	}

	public void actiondel() {
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String yyid = textField_1.getText();
				try {

					int i, k = 0;
					int j = 1;
					String s = "";
					if (Variable.Subject.equals("컴퓨터과학및실습"))
						k = 3;
					else if (Variable.Subject.equals("그림을 통해본 일본고전문학과 문화"))
						k = 4;
					else if (Variable.Subject.equals("컴퓨터과학및연습"))
						k = 5;
					else if (Variable.Subject.equals("일반물리학실험"))
						k = 6;
					else
						JOptionPane.showMessageDialog(null, "과목오류!");

					File f = new File(yyid + ".txt");
					if (!f.exists()) {
						JOptionPane.showMessageDialog(null, "아이디가 없습니다!");
						textField_1.setText("");

					} else {
						read1 = new FileReader(yyid + ".txt");
						for (i = 0; i < k;) {
							while (true) {
								int data = read1.read();
								if (data == ' ') {
									i++;
									break;
								}
							}
						}
						while (true) {
							int data = read1.read();
							if (data == ' ')
								break;
							char ch = (char) data;

							Jo1 = ch;
						}
						if (Jo1 == '0') {
							JOptionPane.showMessageDialog(null,
									"아직 등록되어있지 않은 학생입니다!");
							textField_1.setText("");

						}

						else if (Jo1 == '1') {
							read1.close();

							read1 = new FileReader(yyid + ".txt");

							for (i = 0; i < k;) {
								while (true) {
									int data = read1.read();
									if (data == ' ') {
										i++;
										j++;
										break;
									}
									j++;
								}
							}

							read1.close();
							i = 0;
							read1 = new FileReader(yyid + ".txt");
							while (true) {
								int data = read1.read();
								if (data == -1) {
									break;
								}
								i++;
								if (i == j) {
									s += '0';
								} else {
									s += (char) data;
								}
							}

							read1.close();
							write1 = new FileWriter(yyid + ".txt");
							write1.write(s);
							write1.close();

							JOptionPane.showMessageDialog(null, "삭제되었습니다!");
							textField_1.setText("");

						}
					}

				} catch (Exception eo) {

				}

			}
		});
	}

	public void actionreg() {
		reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String yyid = textField.getText();
				try {

					int i, k = 0;
					int j = 1;
					String s = "";
					if (Variable.Subject.equals("컴퓨터과학및실습"))
						k = 3;
					else if (Variable.Subject.equals("그림을 통해본 일본고전문학과 문화"))
						k = 4;
					else if (Variable.Subject.equals("컴퓨터과학및연습"))
						k = 5;
					else if (Variable.Subject.equals("일반물리학실험"))
						k = 6;
					else
						JOptionPane.showMessageDialog(null, "과목오류!");

					File f = new File(yyid + ".txt");
					if (!f.exists()) {
						JOptionPane.showMessageDialog(null, "아이디가 없습니다!");
						textField.setText("");

					} else {
						read1 = new FileReader(yyid + ".txt");
						for (i = 0; i < k;) {
							while (true) {
								int data = read1.read();
								if (data == ' ') {
									i++;
									break;
								}
							}
						}
						while (true) {
							int data = read1.read();
							if (data == ' ')
								break;
							char ch = (char) data;

							Jo1 = ch;
						}
						if (Jo1 == '1') {
							JOptionPane.showMessageDialog(null,
									"이미 등록되어있는 학생입니다!");
							textField.setText("");

						}

						else if (Jo1 == '0') {
							read1.close();
							read1 = new FileReader(yyid + ".txt");

							for (i = 0; i < k;) {
								while (true) {
									int data = read1.read();
									if (data == ' ') {
										i++;
										j++;
										break;
									}
									j++;
								}
							}

							read1.close();
							i = 0;
							read1 = new FileReader(yyid + ".txt");
							while (true) {
								int data = read1.read();
								if (data == -1) {
									break;
								}
								i++;
								if (i == j) {
									s += '1';
								} else {
									s += (char) data;
								}
							}

							read1.close();
							write1 = new FileWriter(yyid + ".txt");
							write1.write(s);
							write1.close();

							JOptionPane.showMessageDialog(null, "등록되었습니다!");
							textField.setText("");

						}
					}

				}

				catch (Exception eo) {

				}

			}
		});
	}

	public void actionlogout() {
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				Variable.UserID = "";
				Variable.UserJob = "";
				Variable.UserName = "";
				Login log = new Login();
				log.setVisible(true);
				dispose();

			}
		});
	}

	public void actionback() {
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				Japan pr = new Japan();
				pr.setVisible(true);
				dispose();

			}
		});
	}
}
