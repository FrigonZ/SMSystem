package main.ui;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import main.Main;
import main.mysql.MySQL;

public class FriendList extends JPanel{

    private static final long serialVersionUID = 1L;
    private static JTree tree;
    
    public FriendList(){
        
    }

	public void getList(String account) {
        FriendNode group = new FriendNode("group");
        int n = new MySQL().getRow("select * from user");
        int id = new MySQL().getId(account);
        FriendNode[] node = new FriendNode[n];
        for(int i = 1;i<=n;i++){
            String str = new MySQL().getData(i);
            String[] info = str.split("&");
            node[i-1] = new FriendNode(info[0], i);
            if(i != id){
                group.add(node[i-1]);
                Main.map.put(info[0], new ChatFrame(info[0]));
            }
        }

        tree = new JTree(group);
        tree.putClientProperty("JTree.lineStyle", "Horizontal");
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        tree.setRowHeight(60);
        tree.setCellRenderer(new FriendRenderer());
        tree.addTreeSelectionListener(new TreeSelectionListener(){
        
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                FriendNode fn = (FriendNode) tree.getLastSelectedPathComponent();
                if(fn.isLeaf()){
                    Main.map.get(fn.name).setVisible(true);
                }
            }
        });
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setViewportView(tree);
        add(scrollPane);
    }

    public static void main(String[] args) {
        FriendList fl = new FriendList();
        fl.getList("2018004");
        fl.setSize(150, 300);
        JFrame frame = new JFrame();
        frame.add(fl);
        frame.setVisible(true);
    }
}

class FriendNode extends DefaultMutableTreeNode{
    
    private static final long serialVersionUID = 1L;
    protected ImageIcon icon;
    protected String name;
    protected int id;

    public FriendNode(){

    }
    
    public FriendNode(String group){
        super();
        name = group;
    }

    public FriendNode(String name,int id){
        super();
        this.name = name;
        this.id = id;
        icon = new ImageIcon("src//main//res//icons//user.jpg");
    }

    public int getId(){
        return id;
    }

    public ImageIcon getIcon(){
        return icon;
    }
}

class FriendRenderer extends DefaultTreeCellRenderer{

    private static final long serialVersionUID = 1L;
    
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,boolean leaf, int row, boolean hasFocus){
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        FriendNode friendNode = (FriendNode) value;
        ImageIcon icon=new ImageIcon(friendNode.getIcon()+"");
        icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
        String text = friendNode.name;
        setIcon(icon);
        setText(text);
        setIconTextGap(15);
        return this;
    }
}