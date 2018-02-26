package com.surenpi.jenkins.loadbalance;

import hudson.model.Computer;
import hudson.model.Job;
import hudson.model.LoadBalancer;
import hudson.model.Queue;
import hudson.model.queue.MappingWorksheet;

import java.io.IOException;
import java.util.List;

/**
 * @author suren
 */
public class ComputeResourceBalancer extends LoadBalancer
{
    private LoadBalancer fallback;

    public ComputeResourceBalancer(LoadBalancer fallback)
    {
        this.fallback = fallback;
    }

    @Override
    public MappingWorksheet.Mapping map(Queue.Task task, MappingWorksheet worksheet)
    {
        Queue.Task subTask = task.getOwnerTask();
        if(subTask instanceof Job)
        {
            Job job = (Job) subTask;

            BalanceProjectProperty balance = (BalanceProjectProperty)
                    job.getProperty(BalanceProjectProperty.class);


            long memory = balance.getMemory();
            long disk = balance.getDisk();

            List<MappingWorksheet.WorkChunk> works = worksheet.works;
            List<MappingWorksheet.ExecutorChunk> chunks = works.get(0).applicableExecutorChunks();

            Computer computer = chunks.get(0).computer;

            try
            {
                computer.getNode().getRootPath().getUsableDiskSpace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }
}