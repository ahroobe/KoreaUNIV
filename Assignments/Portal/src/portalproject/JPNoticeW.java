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

public class JPNoticeW extends JFrame {

	JTextArea rct = new JTextArea();
	private JPanel contentPane;
	FileWriter write2 = null;
	JButton comp = new JButton("\uC644\uB8CC");
	JButton logout = new JButton("�α׾ƿ�");
	FileReader read1 = null;
	FileWriter write1 = null;
	JButton back = new JButton("\uB4A4\uB85C");
	String body = "";
	private JTextField rtitle;

	/**
	 * Create the frame.
	 */
	public JPNoticeW() {
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\�׸�\\dd.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel(Variable.UserName + "("
				+ Variable.UserID + ")�� ȯ���մϴ�.[" + Variable.UserJob + "]");
		lblNewLabel.setIcon(new ImageIcon("C:\\�׸�\\\u314E\u314E\u314E.jpg"));
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(32, 15, 531, 50);
		contentPane.add(lblNewLabel);
		logout.setBounds(562, 22, 110, 36);
		contentPane.add(logout);

		back.setBounds(290, 608, 97, 43);
		contentPane.add(back);

		JLabel label = new JLabel("\uC791\uC131\uC790: " + Variable.UserName);
		label.setFont(new Font("���� ���", Font.PLAIN, 15));
		label.setBounds(32, 101, 611, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\uC81C\uBAA9:");
		label_1.setFont(new Font("���� ���", Font.PLAIN, 15));
		label_1.setBounds(32, 138, 57, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\uB0B4\uC6A9:");
		label_2.setFont(new Font("���� ���", Font.PLAIN, 15));
		label_2.setBounds(32, 174, 57, 15);
		contentPane.add(label_2);

		rtitle = new JTextField();
		rtitle.setBackground(SystemColor.control);
		rtitle.setBounds(91, 137, 552, 21);
		contentPane.add(rtitle);
		rtitle.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 172, 552, 389);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(rct);
		rct.setBackground(SystemColor.control);

		comp.setBounds(546, 569, 97, 43);
		contentPane.add(comp);

		actioncomp();
		actionlogout();
		actionback();
	}

	public void actioncomp() {
		comp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String num = "";

				String ynn = Variable.UserName;
				String ytitle = rtitle.getText();
				String yct = rct.getText();

				try {
					File k = new File(Variable.Subject + "�� ��������]�Խù� ��.txt");
					if (k.exists()) {
						read1 = new FileReader(Variable.Subject
								+ "�� ��������]�Խù� ��.txt");
						while (true) {
							int data = (int) read1.read();
							if (data == -1)
								break;
							num += (char) data;
						}
						read1.close();
					} else {
						write1 = new FileWriter(Variable.Subject
								+ "�� ��������]�Խù� ��.txt");
						write1.write("0");
						num = "0";
						write1.close();
					}

					int rno = Integer.parseInt(num);
					rno++;
					num = rno + "";
					write1 = new FileWriter(Variable.Subject
							+ "�� ��������]�Խù� ��.txt");
					write1.write(num);

					write1.close();

					write1 = new FileWriter(Variable.Subject
							+ "�� ��������]titles.txt", true);
					write1.write( rno + ") " + ytitle
							+ " (�ۼ���: " + ynn + ")" + "\n");
					write1.close();
					
					write2 = new FileWriter(Variable.Subject + "�� ��������]" + num
							+ ".txt");
					write2.write(yct);

					write2.close();
				} catch (Exception ex) {

				}

				JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�!");
				JPNotice brd = new JPNotice();
				brd.setVisible(true);
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
				// ��� ������ִ� �� �ʱ�ȭ��Ű��(�α׾ƿ�����)
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
