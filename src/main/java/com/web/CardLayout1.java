package com.web;

import java.awt.*;
import java.awt.event.*;
class Mycardlayout extends Frame implements ActionListener{
    Panel cardPanel =new Panel();//创建卡片面板
    Panel controlpaPanel =new Panel();//创建控制面板
    Button nextbutton,preButton;//创建控制按钮
    CardLayout cardlayout=new CardLayout();//定义布局对象
    public Mycardlayout(){
        System.setProperty("file.encoding", "UTF-8");
        this.setSize(300,200);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter(){//为窗口添加监听器
            public void windowClosing(WindowEvent e){
                Mycardlayout.this.dispose();
            }
        });
        cardPanel.setLayout(cardlayout);//设置cardPanel布局类型
        cardPanel.add(new Label("第一张界面", Label.CENTER));//在cardPanel面板中连续插入三个界面
        cardPanel.add(new Label("第二张界面", Label.CENTER));//Label对象是用于将文本放置在容器中的组件。 标签显示一行只读文本
        cardPanel.add(new Label("第三张界面", Label.CENTER));
        nextbutton=new Button("下一张卡片");//创建两个按钮
        preButton=new Button("上一张卡片");
        nextbutton.addActionListener(this);//为两个按钮注册监听器
        preButton.addActionListener(this);
        controlpaPanel.add(preButton);//将两个按钮添加到controlpaPanel（控制面板）中
        controlpaPanel.add(nextbutton);
        this.add(cardPanel, BorderLayout.CENTER);//将卡片面板添加到窗体中部
        this.add(controlpaPanel, BorderLayout.SOUTH);//将控制面板添加到窗体南部
        this.setTitle("CardLayout");
    }
    //下面的代码实现了按钮的监听触发，并对触发进行处理
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==nextbutton){//如果用户点击nextbutton则切换cardPanel之后的组件
            //getSource()的作用是获得事件最初发生的对象。
            cardlayout.next(cardPanel);
        }
        if(e.getSource()==preButton){//如果用户点击preButton咋切换cardPanel之前的组件
            cardlayout.previous(cardPanel);
        }
    }
}
public class CardLayout1 {
    public static void main(String[] args) {
        new Mycardlayout();
    }
}