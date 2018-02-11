/**
 * Project Name:job-restrictions-plugin
 * File Name:MemoryRestriction.java
 * Package Name:com.synopsys.arc.jenkinsci.plugins.jobrestrictions.jobs
 * Date:Feb 9, 20186:33:08 PM
 * Authos:surenpi
 *
 */
package com.surenpi.jenkins.loadblance;

import com.synopsys.arc.jenkinsci.plugins.jobrestrictions.restrictions.JobRestriction;
import com.synopsys.arc.jenkinsci.plugins.jobrestrictions.restrictions.JobRestrictionDescriptor;
import hudson.Extension;
import hudson.model.Job;
import hudson.model.Project;
import hudson.model.Queue.BuildableItem;
import hudson.model.Run;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * ClassName: MemoryRestriction 
 * Function: TODO ADD FUNCTION. 
 * date: Feb 9, 2018 6:33:08 PM 
 * @author surenpi
 * @version 
 */
public class MemoryRestriction extends JobRestriction {

    private boolean memoryRestriction;

    @DataBoundConstructor
    public MemoryRestriction(boolean memoryRestriction)
    {
        this.memoryRestriction = memoryRestriction;
    }

    public boolean isMemoryRestriction()
    {
        return memoryRestriction;
    }

    /**
	 * @see com.synopsys.arc.jenkinsci.plugins.jobrestrictions.restrictions.JobRestriction#canTake(BuildableItem)
	 */
	@Override
	public boolean canTake(BuildableItem item) {
        if(item.task instanceof Project)
        {
            Project project = (Project) item.task;

            return canTake(project);
        }

		return true;
	}

	/**
	 * @see com.synopsys.arc.jenkinsci.plugins.jobrestrictions.restrictions.JobRestriction#canTake(Run)
	 */
	@Override
	public boolean canTake(Run run) {
	    return canTake(run.getParent());
	}

	private boolean canTake(Job job)
    {
        BlanceProjectProperty blance = (BlanceProjectProperty)
                job.getProperty(BlanceProjectProperty.class);

        if(memoryRestriction) {
            String blanceMem = blance.getBlance();
            return "1m".equals(blanceMem);
        }

        return true;
    }

    @Extension
    public static class DescriptorImpl extends JobRestrictionDescriptor
    {
        @Override
        public String getDisplayName()
        {
            return "MemoryRestriction";
        }
    }

}
