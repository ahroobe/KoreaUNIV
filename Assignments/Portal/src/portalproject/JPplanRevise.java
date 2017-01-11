package portalproject;

import java.awt.BorderLayout;
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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class JPplanRevise extends JFrame {

	private JPanel contentPane;
	String title = "강의계획서]" + Variable.Subject;
	String body = "";
	FileReader read1 = null;
	FileWriter write1 = null;
	JButton back = new JButton("\uB4A4\uB85C");
	JTextArea planstory = new JTextArea();
	JButton revise = new JButton("\uC218\uC815");
	JButton logout = new JButton("로그아웃");

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JPplanRevise() {
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\그림\\dd.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			File f = new File(title + ".txt");
			if (f.exists()) {

				read1 = new FileReader(title + ".txt");
				while (true) {
					int data = read1.read();
					if (data == -1)
						break;

					char ch = (char) data;
					body += ch;
				}

				planstory.setText(body);
				read1.close();

			} else {

				JOptionPane.showMessageDialog(null, "아직 내용이 없습니다!");

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "오류발생!");
			System.exit(1);
		}
		JLabel lblNewLabel = new JLabel(Variable.UserName + "("
				+ Variable.UserID + ")님 환영합니다.[" + Variable.UserJob + "]");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\그림\\\u314E\u314E\u314E.jpg"));
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(32, 15, 531, 50);
		contentPane.add(lblNewLabel);
		logout.setBounds(562, 22, 110, 36);
		contentPane.add(logout);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 108, 622, 452);
		contentPane.add(scrollPane);

		planstory.setEditable(true);
		if (Variable.UserJob.equals("학생")) {
			planstory.setEditable(false);
		}
		scrollPane.setViewportView(planstory);

		back.setFont(new Font("굴림", Font.PLAIN, 15));
		back.setBounds(225, 590, 101, 41);
		contentPane.add(back);

		if (Variable.UserJob.equals("교직원")) {
			revise.setFont(new Font("굴림", Font.PLAIN, 15));
			revise.setBounds(351, 590, 101, 41);
			contentPane.add(revise);
		}

		JLabel lblNewLabel_1 = new JLabel(
				title);
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(42, 75, 410, 23);
		contentPane.add(lblNewLabel_1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionback();
		actionlogout();
		actionrevise();

	}

	public void actionrevise() {
		revise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {

					write1 = new FileWriter(title + ".txt");
					write1.write(planstory.getText());

					write1.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "오류발생!");
					System.exit(1);
				}

				JOptionPane.showMessageDialog(null, "완료!");
				Japan jp = new Japan();
				jp.setVisible(true);
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

	public void actionback() {
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				Japan st = new Japan();
				st.setVisible(true);
				dispose();

			}
		});
	}
}
