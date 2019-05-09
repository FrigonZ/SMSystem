package main.ui;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import main.tcp.client.RateClient;

public class VoteList extends JPanel{

    private static final long serialVersionUID = 1L;
    private static JTree tree;
    JScrollPane scrollPane;
    private static Map<String ,Rate> map;
    
    public VoteList(){
        scrollPane=new JScrollPane();
        add(scrollPane);
    }

	public void getList() {
        map = new HashMap<String,Rate>();
        VoteNode group = new VoteNode("投票");
        String data = new RateClient("4#0").sendMsg();
        if(data.equals("0")){

        }else{
            String[] str = data.split("&");
            int n = str.length;
            VoteNode[] node = new VoteNode[n];
            for(int i = 0;i<n;i++){
                String[] name = str[i].split("#");
                node[i] = new VoteNode(name[0], i);
                group.add(node[i]);
            }
            for(int i = 0;i<n;i++){
                Rate rate = new Rate(str[i]);
                System.out.println(rate.name);
                map.put(rate.name, rate);
            }
        }
        

        tree = new JTree(group);
        tree.putClientProperty("JTree.lineStyle", "Horizontal");
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        tree.setRowHeight(60);
        tree.setCellRenderer(new VoteRenderer());
        tree.addTreeSelectionListener(new TreeSelectionListener(){
        
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                VoteNode fn = (VoteNode) tree.getLastSelectedPathComponent();
                if(fn.isLeaf()){
                    System.out.println(map.toString());
                    Rate r = map.get(fn.name);
                    new RateFrame(r).setVisible(true);
                }
            }
        });
        scrollPane.setViewportView(tree);
    }

    public static void main(String[] args) {
        VoteList fl = new VoteList();
        fl.getList();
        fl.setSize(150, 300);
        JFrame frame = new JFrame();
        frame.add(fl);
        frame.setVisible(true);
    }

    public static void test() {
        VoteList fl = new VoteList();
        fl.getList();
        fl.setSize(150, 300);
        JFrame frame = new JFrame();
        frame.add(fl);
        frame.setVisible(true);
    }
}

class VoteNode extends DefaultMutableTreeNode{
    
    private static final long serialVersionUID = 1L;
    protected ImageIcon icon;
    protected String name;
    protected int id;

    public VoteNode(){

    }
    
    public VoteNode(String group){
        super();
        name = group;
    }

    public VoteNode(String name,int id){
        super();
        this.name = name;
        this.id = id;
        icon = new ImageIcon("src//main//res//icons//vote.png");
    }

    public int getId(){
        return id;
    }

    public ImageIcon getIcon(){
        return icon;
    }
}

class VoteRenderer extends DefaultTreeCellRenderer{

    private static final long serialVersionUID = 1L;
    
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,boolean leaf, int row, boolean hasFocus){
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        VoteNode voteNode = (VoteNode) value;
        ImageIcon icon=new ImageIcon(voteNode.getIcon()+"");
        icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
        String text = voteNode.name;
        setIcon(icon);
        setText(text);
        setIconTextGap(15);
        return this;
    }
}

class Rate{

    String name;
    String str;
    String[] choice;

    public Rate(String msg){
        String[] info = msg.split("#");
        int n = info.length;
        name = info[0];
        str = info[1];
        choice = new String[n-2];
        for(int i = 0;i<n-2;i++){
            choice[i] = info[i+2];
        }
    }

}