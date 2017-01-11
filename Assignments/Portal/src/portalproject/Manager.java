package portalproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Manager extends JFrame {

	FileReader read1 = null;
	FileWriter write1 = null;

	String s = "";
	int i = 0, j = 1;


	JButton reg1 = new JButton("\uB4F1\uB85D");
	JButton del1 = new JButton("\uC0AD\uC81C");
	JButton reg2 = new JButton("\uB4F1\uB85D");
	JButton del2 = new JButton("\uC0AD\uC81C");
	JButton reg3 = new JButton("\uB4F1\uB85D");
	JButton del3 = new JButton("\uC0AD\uC81C");
	JButton reg4 = new JButton("\uB4F1\uB85D");
	JButton del4 = new JButton("\uC0AD\uC81C");

	char Jo1;
	char Jo2;
	char Jo3;
	char Jo4;
	private JPanel contentPane;
	JButton back = new JButton("\uB4A4\uB85C");
	JButton logout = new JButton("로그아웃");

	/**
	 * Launch the application.



	/**
	 * Create the frame.
	 */
	public Manager() {
		setTitle("EKU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\그림\\dd.jpg"));
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
		JLabel lblNewLabel = new JLabel(Variable.UserName + "("
				+ Variable.UserID + ")님 환영합니다.[" + Variable.UserJob + "]");
		lblNewLabel.setIcon(new ImageIcon("C:\\그림\\\u314E\u314E\u314E.jpg"));
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(32, 15, 531, 50);
		contentPane.add(lblNewLabel);
		logout.setBounds(562, 22, 110, 36);
		contentPane.add(logout);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("\uB4F1\uB85D\uB41C \uACFC\uBAA9");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(32, 126, 206, 36);
		contentPane.add(lblNewLabel_1);

		JLabel label = new JLabel(
				"\uB4F1\uB85D\uB418\uC5B4 \uC788\uC9C0 \uC54A\uC740 \uACFC\uBAA9");
		label.setForeground(Color.GRAY);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label.setBounds(363, 126, 206, 36);
		contentPane.add(label);

		JPanel panel1 = new JPanel();
		panel1.setBounds(32, 172, 298, 278);
		contentPane.add(panel1);
		panel1.setLayout(null);
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(363, 172, 298, 278);
		contentPane.add(panel2);
		
		back.setBounds(574, 79, 98, 50);
		contentPane.add(back);
		// ////////////////////////
		try {

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

				Jo1 += ch;
			}
			if (Jo1 == '0') {
				JLabel 없는과목1 = new JLabel("컴퓨터과학및실습");
				없는과목1.setBounds(13, 24, 164, 40);
				panel2.add(없는과목1);
				reg1.setBounds(189, 24, 97, 40);
				panel2.add(reg1);

			} else {

				JLabel 등록과목1 = new JLabel("컴퓨터과학및실습");
				등록과목1.setBounds(12, 26, 164, 40);
				panel1.add(등록과목1);
				del1.setBounds(189, 26, 97, 40);
				panel1.add(del1);

			}
			read1.close();

		} catch (Exception eo) {

		}

		try {

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

				Jo2 += ch;
			}
			if (Jo2 == '0') {

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(13, 74, 164, 40);
				panel2.add(scrollPane);
				JLabel 없는과목2 = new JLabel("그림을 통해본 일본고전문학과 문화");
				panel2.add(없는과목2);
				scrollPane.setViewportView(없는과목2);
				
				reg2.setBounds(189, 74, 97, 40);
				panel2.add(reg2);

			} else {

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 76, 164, 40);
				panel1.add(scrollPane);
				JLabel 등록과목2 = new JLabel("그림을 통해본 일본고전문학과 문화");
				panel1.add(등록과목2);
				scrollPane.setViewportView(등록과목2);
				
				del2.setBounds(189, 76, 97, 40);
				panel1.add(del2);
			}
			read1.close();

		} catch (Exception eo) {

		}
		try {
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

				Jo3 += ch;
			}
			if (Jo3 == '0') {
				JLabel 없는과목3 = new JLabel("컴퓨터과학및연습");
				없는과목3.setBounds(13, 124, 164, 40);
				panel2.add(없는과목3);

				
				reg3.setBounds(189, 124, 97, 40);
				panel2.add(reg3);
			} else {
				JLabel 등록과목3 = new JLabel("컴퓨터과학및연습");
				등록과목3.setBounds(12, 126, 164, 40);
				panel1.add(등록과목3);
			
				del3.setBounds(189, 126, 97, 40);
				panel1.add(del3);

			}

			read1.close();
		} catch (Exception eo) {

		}
		try {

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

				Jo4 += ch;
			}
			if (Jo4 == '0') {

				JLabel 없는과목4 = new JLabel("일반물리학실험");
				없는과목4.setBounds(13, 174, 164, 40);
				panel2.add(없는과목4);

			
				reg4.setBounds(189, 174, 97, 40);
				panel2.add(reg4);

			} else {

				del4.setBounds(189, 176, 97, 40);
				panel1.add(del4);

				JLabel 등록과목4 = new JLabel("일반물리학실험");
				등록과목4.setBounds(12, 176, 164, 40);
				panel1.add(등록과목4);

			}

			read1.close();
		} catch (Exception eo) {

		}

		setVisible(true);
		actionlogout();
		actionback();
		actionreg1();
		actiondel1();
		actionreg2();
		actiondel2();
		actionreg3();
		actiondel3();
		actionreg4();
		actiondel4();

	}

	public void actionreg1() {
		reg1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {
					char ch = '9';

					File f = new File("상태]컴퓨터과학및실습.txt");

					if (!f.exists()) {
						write1 = new FileWriter("상태]컴퓨터과학및실습.txt");
						write1.write("0");
						write1.close();
					}

					read1 = new FileReader("상태]컴퓨터과학및실습.txt");
					while (true) {
						int data = read1.read();
						if (data == -1)
							break;
						ch = (char) data;

					}

					read1.close();
					if (ch == '0') {
						// 등록 수행
						write1 = new FileWriter("상태]컴퓨터과학및실습.txt");

						write1.write("1");
						write1.close();

						read1 = new FileReader(Variable.UserID + ".txt");
						for (i = 0; i < 3;) {
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
						read1 = new FileReader(Variable.UserID + ".txt");
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

						write1 = new FileWriter(Variable.UserID + ".txt");
						write1.write(s);
						write1.close();

						JOptionPane.showMessageDialog(null, "등록되었습니다!");
						PrEKU pe = new PrEKU();
						pe.setVisible(true);
						dispose();
					} else if (ch == '1') {
						JOptionPane.showMessageDialog(null,
								"이미 누군가에 의해 등록된 과목입니다!");
					} else {
						// d오류!!
						JOptionPane.showMessageDialog(null, "오류!");
					}
				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "예외처리기가 머야!!!!!!!");
				}
			}
		});
	}

	public void actionreg2() {
		reg2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {
					char ch = '9';

					File f = new File("상태]그림을 통해본 일본고전문학과 문화.txt");

					if (!f.exists()) {
						write1 = new FileWriter("상태]그림을 통해본 일본고전문학과 문화.txt");
						write1.write("0");
						write1.close();
					}

					read1 = new FileReader("상태]그림을 통해본 일본고전문학과 문화.txt");
					while (true) {
						int data = read1.read();
						if (data == -1)
							break;
						ch = (char) data;

					}

					read1.close();
					if (ch == '0') {
						// 등록 수행
						write1 = new FileWriter("상태]그림을 통해본 일본고전문학과 문화.txt");

						write1.write("1");
						write1.close();

						read1 = new FileReader(Variable.UserID + ".txt");
						for (i = 0; i < 4;) {
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
						read1 = new FileReader(Variable.UserID + ".txt");
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

						write1 = new FileWriter(Variable.UserID + ".txt");
						write1.write(s);
						write1.close();

						JOptionPane.showMessageDialog(null, "등록되었습니다!");
						PrEKU pe = new PrEKU();
						pe.setVisible(true);
						dispose();
					} else if (ch == '1') {
						JOptionPane.showMessageDialog(null,
								"이미 누군가에 의해 등록된 과목입니다!");
					} else {
						// d오류!!
						JOptionPane.showMessageDialog(null, "오류!");
					}
				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "예외처리기가 머야!!!!!!!");
				}
			}
		});
	}

	public void actionreg3() {
		reg3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {
					char ch = '9';

					File f = new File("상태]컴퓨터과학및연습.txt");

					if (!f.exists()) {
						write1 = new FileWriter("상태]컴퓨터과학및연습.txt");
						write1.write("0");
						write1.close();
					}

					read1 = new FileReader("상태]컴퓨터과학및연습.txt");
					while (true) {
						int data = read1.read();
						if (data == -1)
							break;
						ch = (char) data;

					}

					read1.close();
					if (ch == '0') {
						// 등록 수행
						write1 = new FileWriter("상태]컴퓨터과학및연습.txt");

						write1.write("1");
						write1.close();

						read1 = new FileReader(Variable.UserID + ".txt");
						for (i = 0; i < 5;) {
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
						read1 = new FileReader(Variable.UserID + ".txt");
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

						write1 = new FileWriter(Variable.UserID + ".txt");
						write1.write(s);
						write1.close();

						JOptionPane.showMessageDialog(null, "등록되었습니다!");
						PrEKU pe = new PrEKU();
						pe.setVisible(true);
						dispose();
					} else if (ch == '1') {
						JOptionPane.showMessageDialog(null,
								"이미 누군가에 의해 등록된 과목입니다!");
					} else {
						// d오류!!
						JOptionPane.showMessageDialog(null, "오류!");
					}
				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "예외처리기가 머야!!!!!!!");
				}
			}
		});
	}

	public void actionreg4() {
		reg4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {
					char ch = '9';

					File f = new File("상태]일반물리학실험.txt");

					if (!f.exists()) {
						write1 = new FileWriter("상태]일반물리학실험.txt");
						write1.write("0");
						write1.close();
					}

					read1 = new FileReader("상태]일반물리학실험.txt");
					while (true) {
						int data = read1.read();
						if (data == -1)
							break;
						ch = (char) data;

					}

					read1.close();
					if (ch == '0') {
						// 등록 수행
						write1 = new FileWriter("상태]일반물리학실험.txt");

						write1.write("1");
						write1.close();

						read1 = new FileReader(Variable.UserID + ".txt");
						for (i = 0; i < 6;) {
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
						read1 = new FileReader(Variable.UserID + ".txt");
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

						write1 = new FileWriter(Variable.UserID + ".txt");
						write1.write(s);
						write1.close();

						JOptionPane.showMessageDialog(null, "등록되었습니다!");
						PrEKU pe = new PrEKU();
						pe.setVisible(true);
						dispose();
					} else if (ch == '1') {
						JOptionPane.showMessageDialog(null,
								"이미 누군가에 의해 등록된 과목입니다!");
					} else {
						// d오류!!
						JOptionPane.showMessageDialog(null, "오류!");
					}
				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "예외처리기가 머야!!!!!!!");
				}
			}
		});
	}

	public void actiondel1() {
		del1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {
					char ch = '9';

					write1 = new FileWriter("상태]컴퓨터과학및실습.txt");

					write1.write("0");
					write1.close();

					read1 = new FileReader(Variable.UserID + ".txt");
					for (i = 0; i < 3;) {
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
					read1 = new FileReader(Variable.UserID + ".txt");
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

					write1 = new FileWriter(Variable.UserID + ".txt");
					write1.write(s);
					write1.close();

					JOptionPane.showMessageDialog(null, "삭제되었습니다!");
					PrEKU pe = new PrEKU();
					pe.setVisible(true);
					dispose();

				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "예외처리기가 머야!!!!!!!");
				}
			}
		});
	}

	public void actiondel2() {
		del2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {
					char ch = '9';

					write1 = new FileWriter("상태]그림을 통해본 일본고전문학과 문화.txt");

					write1.write("0");
					write1.close();

					read1 = new FileReader(Variable.UserID + ".txt");
					for (i = 0; i < 4;) {
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
					read1 = new FileReader(Variable.UserID + ".txt");
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

					write1 = new FileWriter(Variable.UserID + ".txt");
					write1.write(s);
					write1.close();

					JOptionPane.showMessageDialog(null, "삭제되었습니다!");
					PrEKU pe = new PrEKU();
					pe.setVisible(true);
					dispose();

				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "예외처리기가 머야!!!!!!!");
				}
			}
		});
	}

	public void actiondel3() {
		del3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {
					char ch = '9';

					write1 = new FileWriter("상태]컴퓨터과학및연습.txt");

					write1.write("0");
					write1.close();

					read1 = new FileReader(Variable.UserID + ".txt");
					for (i = 0; i < 5;) {
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
					read1 = new FileReader(Variable.UserID + ".txt");
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

					write1 = new FileWriter(Variable.UserID + ".txt");
					write1.write(s);
					write1.close();

					JOptionPane.showMessageDialog(null, "삭제되었습니다!");
					PrEKU pe = new PrEKU();
					pe.setVisible(true);
					dispose();

				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "예외처리기가 머야!!!!!!!");
				}
			}
		});
	}

	public void actiondel4() {
		del4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {
					char ch = '9';

					write1 = new FileWriter("상태]일반물리학실험.txt");

					write1.write("0");
					write1.close();

					read1 = new FileReader(Variable.UserID + ".txt");
					for (i = 0; i < 6;) {
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
					read1 = new FileReader(Variable.UserID + ".txt");
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

					write1 = new FileWriter(Variable.UserID + ".txt");
					write1.write(s);
					write1.close();

					JOptionPane.showMessageDialog(null, "삭제되었습니다!");
					PrEKU pe = new PrEKU();
					pe.setVisible(true);
					dispose();

				} catch (Exception eo) {

					JOptionPane.showMessageDialog(null, "예외처리기가 머야!!!!!!!");
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

				PrEKU pr = new PrEKU();
				pr.setVisible(true);
				dispose();

			}
		});
	}
}
