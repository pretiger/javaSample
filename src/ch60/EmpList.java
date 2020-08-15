package ch60;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class EmpList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	//변수 추가
	private Vector data, col;
	private EmpDAO empDao;
	private DeptDAO deptDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpList frame = new EmpList();
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
	public EmpList() {
		empDao=new EmpDAO();
		table = new JTable();
		col=new Vector();
		
		col.add("사원번호");
		col.add("사원이름");
		col.add("직급");
		col.add("입사일자");
		col.add("급여");
		col.add("소속부서");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JComboBox cbDept = new JComboBox();
		cbDept.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					String dname=cbDept.getSelectedItem().toString();
					DefaultTableModel model=
							new DefaultTableModel(empDao.listEmp(dname), col);
					table.setModel(model);
				}
			}
		});
		contentPane.add(cbDept, BorderLayout.NORTH);
		
		//JComboBox에 부서명 출력
		deptDao=new DeptDAO();
		ArrayList<DeptDTO> deptList=deptDao.listDept();
		cbDept.addItem("전체부서");
		for(DeptDTO dto : deptList) {
			cbDept.addItem(dto.getDname());
		}
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
//		//JTable에 출력할 모델 생성
//		DefaultTableModel model=new DefaultTableModel(empDao.listEmp("%"), col);
//		//JTable에 모델을 입력(화면에 출력)
//		table.setModel(model);
		scrollPane.setViewportView(table);
	}

}
