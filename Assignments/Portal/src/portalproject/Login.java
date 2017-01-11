package portalproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	JTextField id = new JTextField(10);
	JPasswordField password = new JPasswordField(10);
	JButton bsign = new JButton("\uD68C\uC6D0\uAC00\uC785");
	JButton blogin = new JButton("\uB85C\uADF8\uC778");
	FileReader read1 = null;
	FileReader read2 = null;
	FileReader read3 = null;
	FileWriter write1 = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Login lg = new Login();
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);

		setFont(new Font("Adobe ��� Std B", Font.PLAIN, 12));
		
		setTitle("Login to EKU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(317, 239);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\�׸�\\dd.jpg"));
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
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		id = new JTextField();
		id.setBounds(90, 80, 127, 29);
		contentPane.add(id);
		id.setColumns(10);

		password = new JPasswordField();
		password.setBounds(90, 119, 127, 28);
		contentPane.add(password);

		blogin.setBounds(222, 80, 77, 67);
		contentPane.add(blogin);

		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setBackground(new Color(205, 92, 92));
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 81, 66, 26);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 118, 66, 28);
		contentPane.add(lblNewLabel_1);

		bsign.setBounds(187, 162, 112, 38);
		contentPane.add(bsign);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(
				"C:\\�׸�\\\u314E\u314E\u314E.jpg"));
		lblNewLabel_2.setBounds(26, 10, 252, 50);
		contentPane.add(lblNewLabel_2);
		actionlogin();
		actionregister();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionlogin() {

		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String pid = id.getText();
				String pps = password.getText();
				File f = new File(pid + ".txt");
				if (!f.exists()) {

					JOptionPane.showMessageDialog(null, "���� ���̵��Դϴ�!");
					id.setText("");
					password.setText("");
					id.requestFocus();
				} else {

					try {
						String psps = "";
						read1 = new FileReader(pid + ".txt");
						while (true) {
							int data = read1.read();

							if (data == ' ')
								break;

							char ch = (char) data;
							psps += ch;

						}
						read1.close();

						if (psps.equals(pps)) {

							Variable.UserID = pid;
							try {
								read2 = new FileReader(pid + ".txt");
								for (int i = 0; i < 1;) {
									while (true) {
										int data = read2.read();
										if (data == ' ') {
											i++;
											break;
										}
									}
								}
								while (true) {
									int data = read2.read();

									if (data == ' ')
										break;

									char ch = (char) data;
									Variable.UserName += ch;

								}
								read2.close();
								read3 = new FileReader(pid + ".txt");
								for (int i = 0; i < 2;) {
									while (true) {
										int data = read3.read();
										if (data == ' ') {
											i++;
											break;
										}
									}
								}
								while (true) {
									int data = read3.read();

									if (data == ' ')
										break;

									char ch = (char) data;
									Variable.UserJob += ch;

								}

								read3.close();

							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "�����߻�!");
								System.exit(1);
							}

							// ���� ��.
							if (Variable.UserJob.equals("�л�")) {
								StEKU st = new StEKU();
								st.setVisible(true);
								dispose();
							}

							else if (Variable.UserJob.equals("������")) {
								PrEKU pr = new PrEKU();
								pr.setVisible(true);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "����!");
							}
						} else {

							JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٸ��ϴ�!");
							id.setText("");
							password.setText("");
							id.requestFocus();
						}

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "�����߻�!");
						System.exit(1);
					}

				}
			}
		});

	}

	// ȸ������ ��ư������ ȸ���������� �̵�
	public void actionregister() {

		bsign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Register reg = new Register();
				reg.setVisible(true);
				dispose();
			}
		});

	}
}
