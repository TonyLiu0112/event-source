package com.tony666.framework.transactions.core.aggregate;

import com.tony666.framework.transactions.core.Command;
import com.tony666.framework.transactions.core.Event;
import com.tony666.framework.transactions.core.ProcessCommandAggregate;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 领域模型类需要继承此对象，并实现process方法, process方法用于将输入的命令转换为事件.
 * <p>
 * 例如:
 *
 * <pre class="code">
 *  public EntityClass<EntityCommand> extends CommandProcessingAggregate<EntityCommand> {
 *
 *      public List<Event> process(EntityCreateCommand createCommand) {
 *          ...
 *      }
 *
 *      public List<Event> process(EntityUpdateCommand updateCommand) {
 *          ...
 *      }
 *
 *  }
 * </pre>
 * <p>
 * 对于每一个process方法，仅处理一种类型的命令, 返回此{@link Command}对于的事件({@link Event})集.
 *
 * @param <C>
 */
public class CommandProcessingAggregate<C extends Command> implements ProcessCommandAggregate<C> {

    @Override
    public List<Event> processCommand(C cmd) {
        try {
            //noinspection unchecked
            return (List<Event>) getClass().getMethod("process", cmd.getClass()).invoke(this, cmd);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("处理命令失败: " + e);
        }
    }
}
