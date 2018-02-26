package com.surenpi.jenkins.loadbalance;

import hudson.model.queue.MappingWorksheet;

import java.io.Serializable;
import java.util.Comparator;

class ExecutorChunkComparator implements Comparator<MappingWorksheet.ExecutorChunk>, Serializable
{
    @Override
    public int compare(MappingWorksheet.ExecutorChunk o1, MappingWorksheet.ExecutorChunk o2)
    {
        return 0;
    }
}