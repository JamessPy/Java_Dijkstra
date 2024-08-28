import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.*;

public class combining {
    public static void main(String[] args) {
    	gui frame = new gui();
        Node now;
        
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<String, Integer> costMap = new HashMap<>();

       int num_visited = 0;
       Node obj = null;
       Node obj_final = null;
       List<String> list = new ArrayList<>();
       List<String>list_messages= new ArrayList<>();
       
       JFrame frame1 = new JFrame("Dijkstra Algorith Application");
       frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       JButton btnNewButton_1 = new JButton("Select text and press click here for show the Result");
       btnNewButton_1.setBounds(64, 36, 222, 96);
       btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
       btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       btnNewButton_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
       btnNewButton_1.setBackground(Color.LIGHT_GRAY);
       
       frame1.getContentPane().setPreferredSize(new Dimension(10, 10));
       frame1.getContentPane().setBounds(new Rectangle(200, 200, 200, 200));
       frame1.getContentPane().setFont(new Font("Imprint MT Shadow", Font.ITALIC, 16));
       frame1.getContentPane().setBackground(Color.DARK_GRAY);
       frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame1.setBounds(300, 300, 450, 250);
       frame1.getContentPane().add(btnNewButton_1);
       frame1.setVisible(true);
       

       try {
            File file = frame.openFile(false);
            BufferedReader b1 = new BufferedReader(new FileReader(file));
            String other_line;
            StringTokenizer s;
            while((other_line = b1.readLine())!=null) {
            	s = new StringTokenizer(other_line);
            	String s1 = s.nextToken();
            	list.add(s1);
            }
            b1.close();
            String choice_start = list.get(0);
            String choice_end = list.get(list.size()-1);
            BufferedReader b = new BufferedReader(new FileReader(file));
            String line;
            StringTokenizer st;
            
            b.readLine();
            while ((line = b.readLine()) != null) {          	
                st = new StringTokenizer(line);
                String name1 = st.nextToken();
                boolean name1_exists = false;
                String name2 = st.nextToken();
                boolean name2_exists = false; 
                int value = Integer.parseInt(st.nextToken());                
                for (int i = 0; i < nodes.size(); i++) {
                    if (nodes.get(i).name.equals(name1)) {
                        name1_exists = true;
                    }
                    if (nodes.get(i).name.equals(name2)) { 
                        name2_exists = true;
                    }
                }
                if (!name1_exists) { 
                    Node temp1;
                    boolean put_in_front = false;
                    
                    if (choice_start.equals(name1)) { 
                        temp1 = new Node(name1, true, false);
                        put_in_front = true;
                    } else if (choice_end.equals(name1)) { 
                        temp1 = new Node(name1, false, true);
                        obj_final = temp1;
                    } else {
                        temp1 = new Node(name1, false, false);
                    }
                    if (put_in_front) {
                        nodes.add(0, temp1);
                        obj = temp1;
                    } else {
                        nodes.add(temp1);
                    }                  
                }
                if (!name2_exists) { 
                    Node temp2;
                    boolean put_in_front = false;

                    if (choice_start.equals(name2)) { 
                        temp2 = new Node(name2, true, false);
                        put_in_front = true;
                    } else if (choice_end.equals(name2)) { 
                        temp2 = new Node(name2, false, true);
                        obj_final = temp2;
                    } else {
                        temp2 = new Node(name2, false, false);
                    }
                    if (put_in_front) {
                        nodes.add(0, temp2);
                        obj = temp2;
                    } else {
                        nodes.add(temp2);
                    }
                }
            }
            b.close();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line2;
            br.readLine();
            StringTokenizer stt;
            while ((line2 = br.readLine()) != null) {
                stt = new StringTokenizer(line2);
                String name1 = stt.nextToken();
                String name2 = stt.nextToken();
                int value = Integer.parseInt(stt.nextToken());

                costMap.put(name1 + " " + name2, value);
                costMap.put(name2 + " " + name1, value);

                Node obj1 = null;
                Node obj2 = null;

                for (int i = 0; i < nodes.size(); i++) { 
                    if (nodes.get(i).name.equals(name1)) {
                        obj1 = nodes.get(i); 
                    }
                    if (nodes.get(i).name.equals(name2)) {
                        obj2 = nodes.get(i);
                    }
                }

                if (!obj1.others.contains(obj2)) {
                    obj1.add_other(obj2);
                }
                if (!obj2.others.contains(obj1)) {
                    obj2.add_other(obj1);
                }
            }
            br.close();
            now = nodes.get(0);
            while (num_visited < nodes.size()) {
                int temp_min = 100000;
                Node temp_min_node = null;
                list_messages.add("--------->" + now.name+"   cost    " + now.start_cost);

                for (int j = 0; j < now.others.size(); j++) { 
                    int curr_other_cost = now.others.get(j).getstart_cost();
                    int curr_node_cost = now.getstart_cost();
                    int edge_to_other_cost = costMap.get(now.name + " " + now.others.get(j).name);
                    list_messages.add("Step "+j+"--->  "+now.name+"  "+"require cost->" + edge_to_other_cost+"    "+now.others.get(j).name);
                    
                    if (curr_other_cost > curr_node_cost + edge_to_other_cost && !now.others.get(j).isVisited()) { 
                        now.others.get(j).setstart_cost(curr_node_cost + edge_to_other_cost);
                        now.others.get(j).setPrev(now);
                    }
                }
                for (int i = 0; i < nodes.size(); i++) {
                    if (!now.equals(nodes.get(i))) {
                        if (nodes.get(i).start_cost < temp_min && !nodes.get(i).isVisited()) { 
                            temp_min = nodes.get(i).getstart_cost();
                            temp_min_node = nodes.get(i);
                        }
                    }
                }
                now.setVisited(true);
                num_visited++;
                if (temp_min_node != null) { 
                    now = temp_min_node; 
                }
            }
    		btnNewButton_1.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {

    				new gui(list_messages).setVisible(true);    				
    			}
    		});

        } catch (Exception x) {
        	JOptionPane.showMessageDialog(frame, "The text content is incorrect, please check again.");
            
        } 
    }
}
