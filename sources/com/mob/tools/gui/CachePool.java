package com.mob.tools.gui;

import com.mob.tools.MobLog;
import java.util.Iterator;
import java.util.LinkedList;

public class CachePool<K, V> {
    private int capacity;
    private OnRemoveListener<K, V> listener;
    private LinkedList<CachePool<K, V>.Node<K, V>> pool = new LinkedList<>();
    private int poolSize;

    public interface OnRemoveListener<K, V> {
        void onRemove(K k, V v);
    }

    public CachePool(int i) {
        this.capacity = i;
    }

    public void setOnRemoveListener(OnRemoveListener<K, V> onRemoveListener) {
        this.listener = onRemoveListener;
    }

    public synchronized boolean put(K k, V v, int i) {
        if (this.pool != null && this.capacity > 0) {
            try {
                Node node = new Node();
                node.key = k;
                node.value = v;
                long unused = node.cacheTime = System.currentTimeMillis();
                int unused2 = node.size = i;
                this.pool.add(0, node);
                this.poolSize += i;
                while (this.poolSize > this.capacity) {
                    Node removeLast = this.pool.removeLast();
                    if (removeLast != null) {
                        this.poolSize -= removeLast.size;
                        if (this.listener != null) {
                            this.listener.onRemove(removeLast.key, removeLast.value);
                        }
                    }
                }
                return true;
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
                return false;
            }
        }
    }

    public synchronized boolean put(K k, V v) {
        return put(k, v, 1);
    }

    public synchronized V get(K k) {
        Node node;
        if (this.pool != null && this.capacity > 0) {
            while (this.poolSize > this.capacity) {
                try {
                    Node removeLast = this.pool.removeLast();
                    if (removeLast != null) {
                        this.poolSize -= removeLast.size;
                        if (this.listener != null) {
                            this.listener.onRemove(removeLast.key, removeLast.value);
                        }
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().mo29787w(th);
                }
            }
            Iterator it = this.pool.iterator();
            while (true) {
                if (!it.hasNext()) {
                    node = null;
                    break;
                }
                node = (Node) it.next();
                if (node != null) {
                    if (k != null || node.key != null) {
                        if (k != null && k.equals(node.key)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (node != null) {
                this.pool.set(0, node);
                long unused = node.cacheTime = System.currentTimeMillis();
                return node.value;
            }
        }
        return null;
    }

    public synchronized void clear() {
        if (this.pool != null && this.capacity > 0) {
            if (this.listener == null) {
                this.pool.clear();
            } else {
                while (this.pool.size() > 0) {
                    Node removeLast = this.pool.removeLast();
                    if (removeLast != null) {
                        this.poolSize -= removeLast.size;
                        if (this.listener != null) {
                            this.listener.onRemove(removeLast.key, removeLast.value);
                        }
                    }
                }
            }
            this.poolSize = 0;
        }
    }

    public synchronized void trimBeforeTime(long j) {
        if (this.pool != null && this.capacity > 0) {
            int size = this.pool.size() - 1;
            while (size >= 0) {
                if (this.pool.get(size).cacheTime < j) {
                    Node remove = this.pool.remove(size);
                    if (remove != null) {
                        this.poolSize -= remove.size;
                        if (this.listener != null) {
                            this.listener.onRemove(remove.key, remove.value);
                        }
                    }
                } else {
                    size--;
                }
            }
            while (this.poolSize > this.capacity) {
                Node removeLast = this.pool.removeLast();
                if (removeLast != null) {
                    this.poolSize -= removeLast.size;
                    if (this.listener != null) {
                        this.listener.onRemove(removeLast.key, removeLast.value);
                    }
                }
            }
        }
    }

    public synchronized int size() {
        return this.poolSize;
    }

    private class Node<K, V> {
        /* access modifiers changed from: private */
        public long cacheTime;
        public K key;
        /* access modifiers changed from: private */
        public int size;
        public V value;

        private Node() {
        }
    }
}
