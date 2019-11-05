package com.ssafy.obosa.util;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CircularList<T>
{
    private List<T> list;
    private AtomicInteger counter = new AtomicInteger(0);

    public CircularList(List<T> list)
    {
        this.list = list;
    }

    public T getOne()
    {
        if(counter.get() + 1 >= list.size())
        {
            counter.set(-1);
        }
        return list.get(counter.addAndGet(1));
    }
}
