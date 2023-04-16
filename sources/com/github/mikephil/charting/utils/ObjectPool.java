package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import java.util.List;

public class ObjectPool<T extends Poolable> {
    private static int ids;
    private int desiredCapacity;
    private T modelObject;
    private Object[] objects;
    private int objectsPointer;
    private int poolId;
    private float replenishPercentage;

    public static abstract class Poolable {
        public static int NO_OWNER = -1;
        int currentOwnerId = NO_OWNER;

        /* access modifiers changed from: protected */
        public abstract Poolable instantiate();
    }

    public int getPoolId() {
        return this.poolId;
    }

    public static synchronized ObjectPool create(int i, Poolable poolable) {
        ObjectPool objectPool;
        synchronized (ObjectPool.class) {
            objectPool = new ObjectPool(i, poolable);
            objectPool.poolId = ids;
            ids++;
        }
        return objectPool;
    }

    private ObjectPool(int i, T t) {
        if (i > 0) {
            this.desiredCapacity = i;
            this.objects = new Object[this.desiredCapacity];
            this.objectsPointer = 0;
            this.modelObject = t;
            this.replenishPercentage = 1.0f;
            refillPool();
            return;
        }
        throw new IllegalArgumentException("Object Pool must be instantiated with a capacity greater than 0!");
    }

    public void setReplenishPercentage(float f) {
        if (f > 1.0f) {
            f = 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        this.replenishPercentage = f;
    }

    public float getReplenishPercentage() {
        return this.replenishPercentage;
    }

    private void refillPool() {
        refillPool(this.replenishPercentage);
    }

    private void refillPool(float f) {
        int i = this.desiredCapacity;
        int i2 = (int) (((float) i) * f);
        if (i2 < 1) {
            i2 = 1;
        } else if (i2 > i) {
            i2 = i;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            this.objects[i3] = this.modelObject.instantiate();
        }
        this.objectsPointer = i2 - 1;
    }

    public synchronized T get() {
        T t;
        if (this.objectsPointer == -1 && this.replenishPercentage > 0.0f) {
            refillPool();
        }
        t = (Poolable) this.objects[this.objectsPointer];
        t.currentOwnerId = Poolable.NO_OWNER;
        this.objectsPointer--;
        return t;
    }

    public synchronized void recycle(T t) {
        if (t.currentOwnerId == Poolable.NO_OWNER) {
            this.objectsPointer++;
            if (this.objectsPointer >= this.objects.length) {
                resizePool();
            }
            t.currentOwnerId = this.poolId;
            this.objects[this.objectsPointer] = t;
        } else if (t.currentOwnerId == this.poolId) {
            throw new IllegalArgumentException("The object passed is already stored in this pool!");
        } else {
            throw new IllegalArgumentException("The object to recycle already belongs to poolId " + t.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
        }
    }

    public synchronized void recycle(List<T> list) {
        while (list.size() + this.objectsPointer + 1 > this.desiredCapacity) {
            resizePool();
        }
        int size = list.size();
        int i = 0;
        while (i < size) {
            Poolable poolable = (Poolable) list.get(i);
            if (poolable.currentOwnerId == Poolable.NO_OWNER) {
                poolable.currentOwnerId = this.poolId;
                this.objects[this.objectsPointer + 1 + i] = poolable;
                i++;
            } else if (poolable.currentOwnerId == this.poolId) {
                throw new IllegalArgumentException("The object passed is already stored in this pool!");
            } else {
                throw new IllegalArgumentException("The object to recycle already belongs to poolId " + poolable.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
            }
        }
        this.objectsPointer += size;
    }

    private void resizePool() {
        int i = this.desiredCapacity;
        this.desiredCapacity = i * 2;
        Object[] objArr = new Object[this.desiredCapacity];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = this.objects[i2];
        }
        this.objects = objArr;
    }

    public int getPoolCapacity() {
        return this.objects.length;
    }

    public int getPoolCount() {
        return this.objectsPointer + 1;
    }
}
