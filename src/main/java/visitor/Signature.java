package visitor;


import java.util.function.Consumer;

public class Signature<T> extends Task<T> {
    public Consumer<T> consumer;

    public Signature(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public void apply(T arg, Visitor<T> visitor) {
        this.freeze();
        visitor.forSignature(this);
        consumer.accept(arg);
    }
}