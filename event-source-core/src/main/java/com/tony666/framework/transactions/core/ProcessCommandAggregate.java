package com.tony666.framework.transactions.core;

import java.util.List;

/**
 * DDD, Aggregate design.
 *
 * @author Tony
 */
public interface ProcessCommandAggregate<C extends Command> {

    List<Event> processCommand(C cmd);

}
