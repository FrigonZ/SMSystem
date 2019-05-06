package main.ui;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class FriendList extends JPanel{

    private static final long serialVersionUID = 1L;
    private static JTree tree;
    

    public static void main(String[] args) {
        FriendNode group = new FriendNode("group");
        FriendNode[] node = new FriendNode[15];
        for(int i = 0;i < 15;i++){
            node[i] = new FriendNode("name", i);
            group.add(node[i]);
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
                    new JFrame(fn.name).setVisible(true);
                }
            }
        });
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setViewportView(tree);
        JFrame frame = new JFrame("Test");
        frame.setSize(150, 600);
        frame.add(scrollPane);
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