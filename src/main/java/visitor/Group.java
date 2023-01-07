package visitor;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group<T> extends Task<T> {
    public String group_id;
    @Getter
    @Setter
    private List<Task<T>> tasks;

    Group(){
        tasks = new ArrayList<>();
    }

    public Group<T> addTask(Task<T> task) {
        tasks.add(task);
        return this;
    }

    @Override
    public void freeze() {
        super.freeze();
        group_id = UUID.randomUUID().toString();
        for (Task<T> task: tasks) {
            task.freeze();
        }
    }

    @Override
    public void apply(T arg, Visitor<T> visitor) {
        this.freeze();
        visitor.forSignature(this);
        visitor.forGroupStart(this);
        final List<Task<T>> tasks_ = tasks;
        for (Task<T> task: tasks_) {
            task.apply(arg, visitor);
        }
        visitor.forGroupEnd(this);
    }
}