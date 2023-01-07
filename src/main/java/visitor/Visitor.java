package visitor;

public interface Visitor<T> {
    void forSignature(Task<T> sign);
    void forGroupStart(Task<T> group);
    void forGroupEnd(Task<T> group);
}