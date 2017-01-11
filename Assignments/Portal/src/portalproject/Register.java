package portalproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class Register extends JFrame {

	FileReader read1 = null;
	FileWriter write1 = null;

	ButtonGroup group = new ButtonGroup();
	String justjob = "";
	private JPanel contentPane;
	private JTextField rn;
	private JTextField rid;
	JRadioButton st = new JRadioButton("\uD559\uC0DD");
	JRadioButton pr = new JRadioButton("\uAD50\uC9C1\uC6D0");
	JButton canc = new JButton("\uCDE8\uC18C");
	JButton comp = new JButton("\uC644\uB8CC");
	JLabel NNAAA = new JLabel("\uC774\uB984");
	JLabel lblNewLabel = new JLabel("");
	JLabel IDID = new JLabel("\uC544\uC774\uB514");
	JLabel PASS = new JLabel("\uBE44\uBC00\uBC88\uD638");
	JLabel YYYYYYYYY = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
	private JPasswordField rps;
	private JPasswordField rrps;
	int check = 0;

	/**
	 * Create the frame.
	 */
	public Register() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\그림\\dd.jpg"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(368, 472);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\그림\\\u314E\u314E\u314E.jpg"));
		lblNewLabel.setBounds(27, 30, 325, 55);
		contentPane.add(lblNewLabel);

		group.add(st);
		group.add(pr);
		NNAAA.setHorizontalAlignment(SwingConstants.RIGHT);
		NNAAA.setFont(new Font("굴림", Font.PLAIN, 15));
		NNAAA.setBounds(79, 132, 79, 28);
		contentPane.add(NNAAA);

		IDID.setHorizontalAlignment(SwingConstants.RIGHT);
		IDID.setFont(new Font("굴림", Font.PLAIN, 15));
		IDID.setBounds(66, 174, 92, 38);
		contentPane.add(IDID);

		PASS.setHorizontalAlignment(SwingConstants.RIGHT);
		PASS.setFont(new Font("굴림", Font.PLAIN, 15));
		PASS.setBounds(66, 222, 92, 34);
		contentPane.add(PASS);

		YYYYYYYYY.setHorizontalAlignment(SwingConstants.RIGHT);
		YYYYYYYYY.setFont(new Font("굴림", Font.PLAIN, 15));
		YYYYYYYYY.setBounds(27, 266, 131, 38);
		contentPane.add(YYYYYYYYY);

		rn = new JTextField();
		rn.setBounds(170, 133, 116, 28);
		contentPane.add(rn);
		rn.setColumns(10);

		rid = new JTextField();
		rid.setBounds(170, 179, 116, 29);
		contentPane.add(rid);
		rid.setColumns(10);

		st.setHorizontalAlignment(SwingConstants.CENTER);
		st.setFont(new Font("굴림", Font.PLAIN, 15));
		st.setBackground(Color.WHITE);
		st.setBounds(48, 328, 121, 23);
		contentPane.add(st);

		pr.setHorizontalAlignment(SwingConstants.CENTER);
		pr.setBackground(Color.WHITE);
		pr.setFont(new Font("굴림", Font.PLAIN, 15));
		pr.setBounds(170, 328, 121, 23);
		contentPane.add(pr);

		canc.setBounds(48, 374, 98, 38);
		contentPane.add(canc);

		comp.setBounds(188, 374, 98, 38);
		contentPane.add(comp);

		rps = new JPasswordField();
		rps.setBounds(170, 226, 116, 28);
		contentPane.add(rps);

		rrps = new JPasswordField();
		rrps.setBounds(170, 275, 116, 28);
		contentPane.add(rrps);
		actioncanc();
		actionreg();
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		st.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				justjob = "학생";
				check = 1;

			}
		});
		pr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				justjob = "교직원";
				check = 1;
			}
		});

	}

	public void actioncanc() {
		canc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Login log = new Login();
				log.setVisible(true);
				dispose();
			}
		});
	}

	public void actionreg() {
		comp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String yn = rn.getText();
				String yid = rid.getText();
				String yps = rps.getText();
				String yyps = rrps.getText();

				File f = new File(yid + ".txt");
				if (check == 0) {
					JOptionPane.showMessageDialog(null, "직업을 선택하세요!");
				} else {
					if (!yps.equals(yyps)) {

						JOptionPane.showMessageDialog(null, "비밀번호가 서로 다릅니다.");
						rid.setText("");
						rps.setText("");
						rn.setText("");
						rrps.setText("");
						rn.requestFocus();

					} else if (f.exists()) {// 같은 아이디 있는지 찾는 기능 넣어야 함.

						JOptionPane.showMessageDialog(null, "아이디가 이미 존재합니다.");
						rid.setText("");
						rps.setText("");
						rn.setText("");
						rrps.setText("");
						rn.requestFocus();

					} else {

						try {
							int number=0;
							String pl = "";
							if (justjob == "학생") {

								
								File f1 = new File("학생수.txt");
								if(f1.exists()){
									
									String realnum = "";
									read1 = new FileReader("학생수.txt");
									while (true) {
										int data = read1.read();

										if (data == -1)
											break;

										char ch = (char) data;
										realnum += ch;

									}
									number=Integer.parseInt(realnum);
									
									read1.close();
									number++;
									write1 = new FileWriter("학생수.txt");
									pl = number+"";
									write1.write(pl);
									write1.close();
									
									
								}
								else{
									write1 = new FileWriter("학생수.txt");
									write1.write("1");
									write1.close();
									number=1;
									
								}
								write1 = new FileWriter("학생명단.txt", true);
								pl = number+"";
								write1.write(pl);
								write1.write(" ");
								write1.write(yn+" ("+yid+")");
								write1.write("\n");
								write1.close();

							}

							write1 = new FileWriter(yid + ".txt", true);

							write1.write(yps);
							write1.write(" ");
							write1.write(yn);
							write1.write(" ");
							write1.write(justjob);
							write1.write(" ");
							write1.write("0");
							write1.write(" ");
							write1.write("0");
							write1.write(" ");
							write1.write("0");
							write1.write(" ");
							write1.write("0");
							write1.write(" ");
							write1.close();

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "오류발생!");
							System.exit(1);
						}
						JOptionPane.showMessageDialog(null, "완료되었습니다!");
						Login log = new Login();
						log.setVisible(true);
						dispose();
					}
				}
			}
		});
	}
}
