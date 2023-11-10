package domain;

import java.util.List;
import java.util.NoSuchElementException;

public class EventIterator implements ExtendedIterator<Event>{
    private List<Event> events;
    private int index;
    public EventIterator(List<Event> events){
        this.events = events;
    }

    @Override
    public boolean hasNext() {
        return this.events.size() > this.index;
    }

    @Override
    public Event next() {
        if (this.index >= this.events.size()) {
            throw new NoSuchElementException();
        }
        return this.events.get(this.index++);
    }

    @Override
    public Event previous() {
        return this.events.get(this.index--);
    }

    @Override
    public boolean hasPrevious() {
        return this.index > 0;
    }

    @Override
    public void goFirst() {
        this.index = 0;
    }

    @Override
    public void goLast() {
        this.index = this.events.size() - 1;
    }
    
}
