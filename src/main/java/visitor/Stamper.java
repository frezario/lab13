package visitor;

import java.util.ArrayList;
import java.util.List;

public class Stamper<T> implements Visitor<T> {

    private static boolean is_in_group = false;
    private static Integer current_group_id;
    private static Integer id = 1;
    private static int count = 0;
    private static final List<String> stack = new ArrayList<String>();

    @Override
    public void forSignature(Task<T> sign) {
        sign.setHeader(id.toString(), sign.getId());
        if (is_in_group) {
            stack.add(sign.getId());
            stack.add(id.toString());
            count++;
        }
        id++;
    }

    @Override
    public void forGroupStart(Task<T> group) {
        current_group_id = id;
        is_in_group = true;
        id = 1;
    }

    @Override
    public void forGroupEnd(Task<T> group) {
        id = current_group_id;
        while (count != 0) {
            int len = stack.size() - 1;
            String toSetID = stack.get(len);
            stack.remove(len);
            String toSetUUID = stack.get(len - 1);
            stack.remove(len - 1);
            group.setHeader(toSetID, toSetUUID);
            count--;
        }
        is_in_group = false;
    }
}