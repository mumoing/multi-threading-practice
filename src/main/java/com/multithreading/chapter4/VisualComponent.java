package com.multithreading.chapter4;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 委托线程安全到多个底层的状态变量
 * CopyOnWriteArrayList:使用了一种叫写时复制的方法，当添加新元素时，先从原有的数组中拷贝一份出来，然后在新的数组做写操作，写完之后，再将原来的数组引用指向到新数组
 */
public class VisualComponent {

    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListener(KeyListener listener){
        keyListeners.add(listener);
    }

    public void addMouseListener(MouseListener listener){
        mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener){
        keyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener){
        mouseListeners.remove(listener);
    }
}
