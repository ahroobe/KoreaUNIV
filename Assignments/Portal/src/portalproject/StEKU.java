package portalproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StEKU extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	JButton logout = new JButton("로그아웃");
	JButton wat = new JButton("보기");// 제목으로 하고 현재 듣는 과목을 나타내고 오른쪽에 버튼으로 조회나 자세히
	// 버튼을 만듬.
	JButton wat1 = new JButton("보기");// 보기 버튼을 그륩화해도 될듯.
	JButton wat2 = new JButton("보기");
	JButton wat3 = new JButton("보기");
	FileReader read1 = null;
	FileWriter write1 = null;
	JButton back = new JButton("뒤로");

	/**
	 * Create the frame.
	 */
	public StEKU() {
		setTitle("EKU");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\그림\\dd.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(Variable.UserName + "("
				+ Variable.UserID + ")님 환영합니다.[" + Variable.UserJob + "]");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\그림\\\u314E\u314E\u314E.jpg"));
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(32, 15, 531, 50);
		contentPane.add(lblNewLabel);

		logout.setBounds(575, 23, 107, 34);
		contentPane.add(logout);

		JLabel lblNewLabel_1 = new JLabel("\uAC15\uC758 \uBAA9\uB85D");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("돋움", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(51, 85, 98, 34);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(
				"C:\\그림\\\uAC15\uC758\uBA85.jpg"));
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(214, 131, 328, 34);
		contentPane.add(lblNewLabel_2);

		JLabel label = new JLabel(
				"\uCEF4\uD4E8\uD130\uACFC\uD559\uBC0F\uC2E4\uC2B5");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("돋움", Font.PLAIN, 14));
		label.setBounds(51, 190, 199, 27);
		contentPane.add(label);

		JLabel label_1 = new JLabel(
				"\uADF8\uB9BC\uC744 \uD1B5\uD574\uBCF8 \uC77C\uBCF8\uACE0\uC804\uBB38\uD559\uACFC \uBB38\uD654");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("돋움", Font.PLAIN, 14));
		label_1.setBounds(51, 239, 246, 27);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel(
				"\uCEF4\uD4E8\uD130\uACFC\uD559\uBC0F\uC5F0\uC2B5");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("돋움", Font.PLAIN, 14));
		label_2.setBounds(51, 291, 199, 27);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel(
				"\uC77C\uBC18\uBB3C\uB9AC\uD559\uC2E4\uD5D8");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("돋움", Font.PLAIN, 14));
		label_3.setBounds(51, 339, 199, 27);
		contentPane.add(label_3);

		wat.setBounds(518, 187, 107, 34);
		contentPane.add(wat);

		wat1.setBounds(518, 236, 107, 34);
		contentPane.add(wat1);

		wat2.setBounds(518, 284, 107, 34);
		contentPane.add(wat2);

		wat3.setBounds(518, 332, 107, 34);
		contentPane.add(wat3);
		if(Variable.UserJob.equals("교직원")){
			back.setBounds(321, 397, 98, 36);
			contentPane.add(back);
			}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionlogout();
		actionwat();
		actionwat1();
		actionwat2();
		actionwat3();
		actionback();
	}

	public void actionback() {
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				PrEKU pr = new PrEKU();
				pr.setVisible(true);
				dispose();

			}
		});
	}

	// logout시 logout 시켜주는 리스너
	public void actionlogout() {
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				Variable.UserID = "";
				Variable.UserName = "";
				Variable.UserJob = "";

				Login log = new Login();
				log.setVisible(true);
				dispose();

			}
		});
	}

	// 보기 눌릴때 리스너
	public void actionwat() {

		wat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {

					char Jo1 = '9';
					read1 = new FileReader(Variable.UserID + ".txt");

					for (int i = 0; i < 3;) {
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

						Variable.Subject = "컴퓨터과학및실습";
						Japan jp = new Japan();
						jp.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "열람 권한이 없습니다!");

					}
				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "오류!");
				}
			}
		});

	}

	public void actionwat1() {

		wat1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {

					char Jo1 = '9';
					read1 = new FileReader(Variable.UserID + ".txt");

					for (int i = 0; i < 4;) {
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

						Variable.Subject = "그림을 통해본 일본고전문학과 문화";
						Japan jp = new Japan();
						jp.setVisible(true);
						dispose();
					}

					else if (Jo1 == '0') {
						JOptionPane.showMessageDialog(null, "열람 권한이 없습니다!");
					} else {
						JOptionPane.showMessageDialog(null, "오류!!");

					}
				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "오류!");
				}
			}
		});

	}

	public void actionwat2() {

		wat2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {

					char Jo1 = '9';
					read1 = new FileReader(Variable.UserID + ".txt");

					for (int i = 0; i < 5;) {
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

						Variable.Subject = "컴퓨터과학및연습";
						Japan jp = new Japan();
						jp.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "열람 권한이 없습니다!");

					}
				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "오류!");
				}
			}
		});

	}

	public void actionwat3() {

		wat3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {

					char Jo1 = '9';
					read1 = new FileReader(Variable.UserID + ".txt");

					for (int i = 0; i < 6;) {
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

						Variable.Subject = "일반물리학연습";
						Japan jp = new Japan();
						jp.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "열람 권한이 없습니다!");

					}
				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "오류!");
				}
			}
		});

	}
}
