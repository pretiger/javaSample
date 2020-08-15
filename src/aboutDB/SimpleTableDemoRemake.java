package aboutDB;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class SimpleTableDemoRemake extends JPanel implements ActionListener {
	Vector data, columnNames;
	JTable table;
	
	public SimpleTableDemoRemake() {
//		super(new GridLayout());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		columnNames=new Vector();
		columnNames.add("성");
		columnNames.add("이름");
		columnNames.add("취미");
		columnNames.add("년수");
		columnNames.add("채식여부");
		data=new Vector();
		Object[][] objectData= {{"정","인수","야구",7,true},
				{"김","인호","포켓볼",8,false},
				{"이","수영","뜨게질",10,true},
				{"양","인영","체조",11,false},
				{"포","청천","우슈",20,true}};
		Vector subData=null;
		for(int i=0;i<5;i++) {
			subData=new Vector();
			for(int j=0;j<5;j++) {
				subData.add(objectData[i][j]);
			}
			data.add(subData);
		}

		MyTableModel model=new MyTableModel(data, columnNames);
		table=new JTable();
		table.setModel(model);
		table.setPreferredScrollableViewportSize(new Dimension(500,70));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane=new JScrollPane(table);
		
        initColumnSizes(table);
        setUpSportColumn(table, table.getColumnModel().getColumn(2));
		
		add(scrollPane);
		
		JButton printButton=new JButton("print");
		printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		printButton.addActionListener(this);  // Add to class ActionListener Inerface
		add(printButton);
	}
   
	public void actionPerformed(ActionEvent e) {  //about Print action
        MessageFormat header = new MessageFormat("Page {0,number,integer}");
        try {
            table.print(JTable.PrintMode.FIT_WIDTH, header, null);
        } catch (java.awt.print.PrinterException e1) {
            System.err.format("Cannot print %s%n", e1.getMessage());
        }
    }
    
    private void initColumnSizes(JTable table) {
        MyTableModel model = (MyTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();
 
        for (int i = 0; i < columnNames.size(); i++) {
            column = table.getColumnModel().getColumn(i);
 
            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;
 
            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, longValues[i],
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
 
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }
 
    public void setUpSportColumn(JTable table,
                                 TableColumn sportColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("야구");
        comboBox.addItem("포켓볼");
        comboBox.addItem("뜨게질");
        comboBox.addItem("체조");
        comboBox.addItem("우슈");
        comboBox.addItem("기타 등등");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
 
        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);
    }
	
	static void creadAndShowUI() {
		JFrame frame=new JFrame("SimpleTableDemoRemake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SimpleTableDemoRemake newContentPane=new SimpleTableDemoRemake();
		frame.setContentPane(newContentPane);
		newContentPane.setOpaque(true);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				creadAndShowUI();
			}
		});
	}
	
	class MyTableModel extends DefaultTableModel {
		
		MyTableModel(Vector data, Vector columnNames){
			super(data, columnNames);
		}
		
		public final Object[] longValues = {"김", "이",
				"None of the above",
				20, true};
		
		public boolean isCellEditable(int row, int col) {
			if (col < 2) {
				return false;
			} else {
				return true;
			}
		}
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
	}
}