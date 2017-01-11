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
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Japan extends JFrame {

	private JPanel contentPane;
	JButton back = new JButton("\uB4A4\uB85C");
	JButton membermanage = new JButton("");
	JButton notice = new JButton("");
	JButton plan = new JButton("");
	/**
	 * Launch the application.
	 */
	JButton logout = new JButton("로그아웃");

	JButton free = new JButton("");

	/**
	 * Create the frame.
	 */
	public Japan() {
		setTitle("EKU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\그림\\dd.jpg"));
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
		JLabel lblNewLabel = new JLabel(Variable.UserName + "("
				+ Variable.UserID + ")님 환영합니다.[" + Variable.UserJob + "]");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\그림\\\u314E\u314E\u314E.jpg"));
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(32, 15, 531, 50);
		logout.setBounds(562, 22, 110, 36);
		contentPane.add(logout);
		contentPane.add(lblNewLabel);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		plan.setIcon(new ImageIcon(
				"C:\\그림\\\uAC15\uC758\uACC4\uD68D\uC11C\uC870\uD68C.png"));
		plan.setBounds(512, 114, 142, 36);
		contentPane.add(plan);

		JLabel lblNewLabel_2 = new JLabel(
				"\uAC15\uC758\uACF5\uC9C0\uC0AC\uD56D");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(53, 182, 184, 50);
		contentPane.add(lblNewLabel_2);

		notice.setIcon(new ImageIcon(
				"C:\\그림\\\uBAA8\uC5B4.jpg"));
		notice.setBounds(280, 196, 65, 23);
		contentPane.add(notice);

		free.setIcon(new ImageIcon(
				"C:\\그림\\\uBAA8\uC5B4.jpg"));
		free.setBounds(577, 194, 65, 23);
		contentPane.add(free);

		if (Variable.UserJob.equals("교직원")) {
			JLabel 학생관리 = new JLabel("\uD559\uC0DD\uAD00\uB9AC");
			학생관리.setForeground(Color.DARK_GRAY);
			학생관리.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			학생관리.setBounds(53, 286, 184, 50);
			membermanage.setIcon(new ImageIcon(
					"C:\\그림\\\uBAA8\uC5B4.jpg"));
			membermanage.setBounds(280, 301, 65, 23);
			contentPane.add(membermanage);
			contentPane.add(학생관리);
		}

		JLabel label_1 = new JLabel("\uC790\uC720\uAC8C\uC2DC\uD310");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_1.setBounds(379, 182, 184, 50);
		contentPane.add(label_1);

		back.setBounds(321, 397, 98, 36);
		contentPane.add(back);
		JLabel label_2 = new JLabel(Variable.Subject);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		label_2.setBounds(42, 100, 390, 50);
		contentPane.add(label_2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionmembermanage();
		actionback();
		actionlogout();
		actionplan();
		actionnotice();
		actionfree();
	}

	// 로그아웃
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

	// 뒤로버튼
	public void actionback() {
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				Variable.Subject = "";
				StEKU st = new StEKU();
				st.setVisible(true);
				dispose();
			}

		});
	}

	// 강의계획서

	public void actionplan() {
		plan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// 강의계획서로 이동

				JPplanRevise jr = new JPplanRevise();
				jr.setVisible(true);
				dispose();

			}
		});

	}



	public void actionmembermanage() {
		membermanage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				MemberManage mm = new MemberManage();
				mm.setVisible(true);
				dispose();
			}
		});
	}

	// 공지사항

	public void actionnotice() {
		notice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				JPNotice jn = new JPNotice();
				jn.setVisible(true);
				dispose();
			}
		});

	}

	// 자유게시판

	public void actionfree() {
		free.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				Board1 b1 = new Board1();
				b1.setVisible(true);
				dispose();
			}
		});
	}

}
