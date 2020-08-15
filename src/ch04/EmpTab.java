package ch04;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class EmpTab extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfEmpno;
	private JTextField tfHiredate;
	private JTextField tfSal;
	private JTable table;
	private JComboBox cboDeptno;
	private JButton btnSave;
	private JButton btnImage;
	private JLabel lblImage;
	private EmpDTO empDto;
	private EmpDAO empDao;
	private DeptDTO deptDto;
	private DeptDAO deptDao;
	private ArrayList<DeptDTO> items;
	private String img_path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpTab frame = new EmpTab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmpTab() {
		empDao=new EmpDAO();
		deptDao=new DeptDAO();
		ArrayList<String> empList=empDao.listEmp2();
		items=new ArrayList<>();
		items=deptDao.listDept();
		
		table = new JTable();
		Vector col=new Vector();
		col.add("사번");
		col.add("이름");
		col.add("직위");
		col.add("입사일");
		col.add("급여");
		col.add("부서");
		col.add("사진위치");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 159, 206);
		contentPane.add(scrollPane);
		
		JList list = new JList(empList.toArray());
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					String str=list.getSelectedValue().toString();
					String arr[]=str.split(" ");
					int empno=Integer.parseInt(arr[0]);
					DefaultTableModel model=new DefaultTableModel(empDao.listVectorEmp(empno), col);
					table.setModel(model);
					
					empDto=empDao.listEmp1(empno);
					if(empDto!=null) {
						tfName.setText(empDto.getEname());
						tfEmpno.setText(empDto.getEmpno()+"");
						tfHiredate.setText(empDto.getHiredate()+"");
						tfSal.setText(empDto.getSal()+"");
						
						int count=0, cboIndex=0;
						cboDeptno.removeAll();
						for(DeptDTO arr1 : items) {
							cboDeptno.addItem(arr1.getDname());
							if(empDto.getDname().equals(arr1.getDname())) {
								cboIndex=count;
							}
							count++;
						}
						cboDeptno.setSelectedIndex(cboIndex);
					}else {
						tfName.setText("");
						tfEmpno.setText("");
						tfHiredate.setText("");
						tfSal.setText("");
					}
				}
			}
		});
		scrollPane.setViewportView(list);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(183, 10, 499, 242);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("사원정보", null, panel, null);
		panel.setLayout(null);
		
		lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBounds(12, 10, 123, 134);
		panel.add(lblImage);
		
		btnImage = new JButton("사진등록");
		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc=new JFileChooser();
				int result=fc.showOpenDialog(EmpTab.this);
				if(result==JFileChooser.APPROVE_OPTION) {
					File file=fc.getSelectedFile();
					img_path=fc.getSelectedFile().getAbsolutePath();
					try {
						ImageIcon icon=new ImageIcon(ImageIO.read(file));
						Image imageSrc=icon.getImage();
						BufferedImage bi=ImageIO.read(file);
						File copyFile=new File("test.jpg");
						ImageIO.write(bi, "jpg", copyFile);
						Image imageNew=imageSrc.getScaledInstance(100, 120, Image.SCALE_AREA_AVERAGING);
						icon=new ImageIcon(imageNew);
						lblImage.setIcon(icon);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnImage.setBounds(26, 164, 97, 23);
		panel.add(btnImage);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(147, 10, 57, 15);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("사번");
		label.setBounds(147, 39, 57, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("부서");
		label_1.setBounds(147, 70, 57, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("입사일");
		label_2.setBounds(147, 95, 57, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("급여");
		label_3.setBounds(147, 120, 57, 15);
		panel.add(label_3);
		
		tfName = new JTextField();
		tfName.setBounds(216, 7, 83, 21);
		panel.add(tfName);
		tfName.setColumns(10);
		
		tfEmpno = new JTextField();
		tfEmpno.setColumns(10);
		tfEmpno.setBounds(216, 36, 83, 21);
		panel.add(tfEmpno);
		
		tfHiredate = new JTextField();
		tfHiredate.setColumns(10);
		tfHiredate.setBounds(216, 92, 83, 21);
		panel.add(tfHiredate);
		
		tfSal = new JTextField();
		tfSal.setColumns(10);
		tfSal.setBounds(216, 117, 83, 21);
		panel.add(tfSal);
		
		cboDeptno = new JComboBox();
		cboDeptno.setBounds(216, 66, 83, 19);
		panel.add(cboDeptno);
		
		btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empDto=new EmpDTO();
				empDto.setEname(tfName.getText());
				empDto.setEmpno(Integer.valueOf(tfEmpno.getText()+""));
				empDto.setHiredate(Date.valueOf(tfHiredate.getText()));
				empDto.setSal(Integer.valueOf(tfSal.getText()));
				empDto.setImg_path(img_path);
			}
		});
		btnSave.setBounds(177, 164, 97, 23);
		panel.add(btnSave);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("상세정보", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		
		scrollPane_1.setViewportView(table);
	}
}
