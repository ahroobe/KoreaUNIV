package portalproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PrEKU extends JFrame {


	private JPanel contentPane;
	JButton logout = new JButton("�α׾ƿ�");
	JButton wat = new JButton("����");// �������� �ϰ� ���� ��� ������ ��Ÿ���� �����ʿ� ��ư���� ��ȸ�� �ڼ���
	// ��ư�� ����.
	JButton wat1 = new JButton("����");
	/**
	 * Launch the application.
	

	/**
	 * Create the frame.
	 */
	public PrEKU() {
		setTitle("EKU");
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
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel(Variable.UserName + "("
				+ Variable.UserID + ")�� ȯ���մϴ�.[" + Variable.UserJob + "]");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\�׸�\\\u314E\u314E\u314E.jpg"));
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(32, 15, 531, 50);
		contentPane.add(lblNewLabel);
		setContentPane(contentPane);
		
		logout.setBounds(562, 22, 110, 36);
		contentPane.add(logout);
		
		JLabel lblR = new JLabel("\uACFC\uBAA9\uB4F1\uB85D/\uC0AD\uC81C");
		lblR.setForeground(Color.BLACK);
		lblR.setFont(new Font("����", Font.PLAIN, 15));
		lblR.setBounds(136, 131, 141, 57);
		contentPane.add(lblR);
		
		JLabel label = new JLabel("\uACFC\uBAA9\uC870\uD68C");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("����", Font.PLAIN, 15));
		label.setBounds(466, 131, 141, 57);
		contentPane.add(label);
		
		wat.setBounds(125, 239, 104, 36);
		contentPane.add(wat);
		
		wat1.setBounds(440, 239, 104, 36);
		contentPane.add(wat1);
		actionlogout();
		actionwat();
		actionwat1();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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

	// ���� ������ ������
	public void actionwat() {

		wat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				Manager mn = new Manager();
				mn.setVisible(true);
				dispose();

			}
		});

	}

	public void actionwat1() {

		wat1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				StEKU st = new StEKU();
				st.setVisible(true);
				dispose();

			}
		});

	}
}
