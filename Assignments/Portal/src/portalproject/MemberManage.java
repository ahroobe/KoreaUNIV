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
	JButton logout = new JButton("�α׾ƿ�");
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\�׸�\\dd.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel lblNewLabel = new JLabel(Variable.UserName + "("
				+ Variable.UserID + ")�� ȯ���մϴ�.[" + Variable.UserJob + "]");
		lblNewLabel.setBounds(32, 15, 531, 50);
		lblNewLabel.setIcon(new ImageIcon("C:\\�׸�\\\u314E\u314E\u314E.jpg"));
		contentPane.setLayout(null);
		logout.setBounds(562, 22, 110, 36);
		contentPane.add(logout);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel);
		setContentPane(contentPane);

		JLabel lblNewLabel_1 = new JLabel("\uD559\uC0DD\uBA85\uB2E8");
		lblNewLabel_1.setBounds(42, 75, 128, 24);
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("���� ���", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1);

		back.setBounds(293, 415, 98, 36);
		contentPane.add(back);

		JLabel lblNewLabel_2 = new JLabel("�л�ID(��ȣ ��):");
		lblNewLabel_2.setFont(new Font("���� ���", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(293, 139, 166, 36);
		contentPane.add(lblNewLabel_2);

		JLabel label_1 = new JLabel("�л�ID(��ȣ ��):");
		label_1.setFont(new Font("���� ���", Font.PLAIN, 14));
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
			read1 = new FileReader("�л���.txt");
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

			read1 = new FileReader("�л����.txt");
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
					if (Variable.Subject.equals("��ǻ�Ͱ��й׽ǽ�"))
						k = 3;
					else if (Variable.Subject.equals("�׸��� ���غ� �Ϻ��������а� ��ȭ"))
						k = 4;
					else if (Variable.Subject.equals("��ǻ�Ͱ��й׿���"))
						k = 5;
					else if (Variable.Subject.equals("�Ϲݹ����н���"))
						k = 6;
					else
						JOptionPane.showMessageDialog(null, "�������!");

					File f = new File(yyid + ".txt");
					if (!f.exists()) {
						JOptionPane.showMessageDialog(null, "���̵� �����ϴ�!");
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
									"���� ��ϵǾ����� ���� �л��Դϴ�!");
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

							JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�!");
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
					if (Variable.Subject.equals("��ǻ�Ͱ��й׽ǽ�"))
						k = 3;
					else if (Variable.Subject.equals("�׸��� ���غ� �Ϻ��������а� ��ȭ"))
						k = 4;
					else if (Variable.Subject.equals("��ǻ�Ͱ��й׿���"))
						k = 5;
					else if (Variable.Subject.equals("�Ϲݹ����н���"))
						k = 6;
					else
						JOptionPane.showMessageDialog(null, "�������!");

					File f = new File(yyid + ".txt");
					if (!f.exists()) {
						JOptionPane.showMessageDialog(null, "���̵� �����ϴ�!");
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
									"�̹� ��ϵǾ��ִ� �л��Դϴ�!");
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

							JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�!");
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
