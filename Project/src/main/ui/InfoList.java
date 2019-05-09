package main.ui;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import main.mysql.MySQL;

public class InfoList extends JPanel{

    private static final long serialVersionUID = 1L;
    private static JTree tree;
    JScrollPane scrollPane;
    
    public InfoList(){
        scrollPane = new JScrollPane();
        add(scrollPane);
    }

	public void getList() {
        InfoNode group = new InfoNode("通知");
        int n = new MySQL().getRow("select * from notice");
        InfoNode[] node = new InfoNode[n];
        for(int i = 1;i<=n;i++){
            String str = new MySQL().getNotice(i);
            String[] info = str.split("&");
            node[i-1] = new InfoNode(info[1], i);
            group.add(node[i-1]);
        }

        tree = new JTree(group);
        tree.putClientProperty("JTree.lineStyle", "Horizontal");
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        tree.setRowHeight(60);
        tree.setCellRenderer(new InfoRenderer());
        tree.addTreeSelectionListener(new TreeSelectionListener(){
        
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                InfoNode in = (InfoNode) tree.getLastSelectedPathComponent();
                if(in.isLeaf()){
                    new JFrame(in.notice).setVisible(true);
                }
            }
        });
        scrollPane.setViewportView(tree);
    }

    public static void main(String[] args) {
        InfoList fl = new InfoList();
        fl.getList();
        fl.setSize(150, 300);
        JFrame frame = new JFrame();
        frame.add(fl);
        frame.setVisible(true);
    }
}

class InfoNode extends DefaultMutableTreeNode{
    
    private static final long serialVersionUID = 1L;
    protected ImageIcon icon;
    protected String notice;
    protected int id;

    public InfoNode(){

    }
    
    public InfoNode(String group){
        super();
        notice = group;
    }

    public InfoNode(String notice,int id){
        super();
        this.notice = notice;
        this.id = id;
        icon = new ImageIcon("src//main//res//icons//info.png");
    }

    public int getId(){
        return id;
    }

    public ImageIcon getIcon(){
        return icon;
    }
}

class InfoRenderer extends DefaultTreeCellRenderer{

    private static final long serialVersionUID = 1L;
    
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,boolean leaf, int row, boolean hasFocus){
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        InfoNode infoNode = (InfoNode) value;
        ImageIcon icon=new ImageIcon(infoNode.getIcon()+"");
        icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
        String text = infoNode.notice;
        setIcon(icon);
        setText(text);
        setIconTextGap(15);
        return this;
    }
}