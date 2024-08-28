import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class gui extends JFrame {
	private JButton btnNewButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);;
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}
	
	public java.io.File openFile(boolean open) {
	    JFileChooser fc = new JFileChooser("Open an Txt");
	    
	    javax.swing.filechooser.FileFilter ff = new javax.swing.filechooser.FileFilter() {
	       public boolean accept(java.io.File f) {
	          String name = f.getName().toLowerCase();
	          if(open)
	             return f.isDirectory() || name.endsWith(".txt") ;
	          return f.isDirectory() || name.endsWith(".txt");
	          }
	       public String getDescription() {
	          if(open)
	             return "Txt (*.txt)";
	          return "Txt (*.txt)";
	          
	          }
	       };
	    fc.setAcceptAllFileFilterUsed(false);
	    fc.addChoosableFileFilter(ff);
	 
	    java.io.File f = null;
	   if(open && fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
	       f = fc.getSelectedFile();
	    else if(!open && fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
	       f = fc.getSelectedFile();

	    return f;
	    }

	public gui() {	
		getContentPane().setPreferredSize(new Dimension(10, 10));
		getContentPane().setBounds(new Rectangle(200, 200, 200, 200));
		getContentPane().setFont(new Font("Imprint MT Shadow", Font.ITALIC, 16));
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 800, 500);
		getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Read TXT");
		btnNewButton.setBounds(239, 125, 259, 84);
		getContentPane().add(btnNewButton);

		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 20));
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(239, 333, 259, 49);
		getContentPane().add(btnNewButton_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.GRAY);
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setFont(new Font("Dialog", Font.BOLD, 14));
		textArea.setEditable(false);
		textArea.setBounds(187, 47, 355, 49);
		getContentPane().add(textArea);
		textArea.setText("        Welcome the Dijkstra algorithm  application "+"\n"+"       	  Please select a txt file");
		textArea.setDisabledTextColor(Color.darkGray);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isShowing())
					removeNotify();
				combining.main(null);		
			}});
	}
	//Overload
	public gui(List<String> message) {
		getContentPane().setPreferredSize(new Dimension(10, 10));
		getContentPane().setBounds(new Rectangle(200, 200, 200, 200));
		getContentPane().setFont(new Font("Imprint MT Shadow", Font.ITALIC, 16));
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 800, 500);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 784, 461);
		getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setColumns(10);
		textArea.setBounds(0, 0, 434, 211);

		for (int i = 0; i < message.size()-1; i++) {
        	textArea.setText(textArea.getText()+message.get(i)+"\n");
		}

	}
}
