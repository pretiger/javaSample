package ch60;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ComboExam extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JList list;
	//변수 추가
	private DeptDAO deptDao;
	private ArrayList<DeptDTO> deptList;
	private EmpDAO empDao;
	private ArrayList<EmpDTO> empList;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboExam frame = new ComboExam();
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
	public ComboExam() {
		//부서목록 출력을 위한 객체 생성
		deptDao=new DeptDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		list = new JList();
		empDao=new EmpDAO();
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					int idx=comboBox.getSelectedIndex();
					if(idx != -1) {
						refreshList(idx);
					}
				}
			}
		});
		contentPane.add(comboBox, BorderLayout.NORTH);
		//콤보박스에 부서목록을 binding
		deptList=deptDao.listDept();//부서목록을 리스트로 받아옴
		for(DeptDTO dto : deptList) {
			comboBox.addItem(dto.getDname());//콤보박스에 부서이름 추가
		}
		
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		//사원목록을 가져오는 코드
		
		empList=empDao.listEmp(10);//처음 실행시 10번 부터 표시

		
		refreshList(0);
			
		scrollPane.setViewportView(list);
	}
	
	void refreshList(int idx) {
		list.removeAll();
		int deptno=deptList.get(idx).getDeptno();
		empList=empDao.listEmp(deptno);
		String[] names=new String[empList.size()];
		for(int i=0; i<names.length; i++) {
			names[i] = empList.get(i).getEname();
		}
		DefaultListModel<String> model=new DefaultListModel<>();
		for(String val : names) {
			model.addElement(val);
		}
		list.setModel(model);
	}

}
