package com.surenpi.jenkins.loadbalance;

import hudson.Extension;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import jenkins.model.ParameterizedJobMixIn;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.StaplerRequest;

import javax.annotation.Nonnull;
import java.util.logging.Logger;

/**
 * @author suren
 */
public final class BalanceProjectProperty extends JobProperty<Job<?, ?>>
{
    private long memory;
    private long disk;

    @DataBoundConstructor
    public BalanceProjectProperty() {}

    @Extension
    public static final class DescriptorImpl extends JobPropertyDescriptor
    {
        public static final String AGENT_LOAD = "agent_load";

        public boolean isApplicable(Class<? extends Job> jobType) {
            return ParameterizedJobMixIn.ParameterizedJob.class.isAssignableFrom(jobType);
        }

        @Nonnull
        @Override
        public String getDisplayName()
        {
            return "Agent Load";
        }

        @Override
        public JobProperty<?> newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            BalanceProjectProperty tpp = req.bindJSON(
                    BalanceProjectProperty.class,
                    formData.getJSONObject(AGENT_LOAD)
            );

            if (tpp == null) {
                LOGGER.fine("Couldn't bind JSON");
                return null;
            }

            return tpp;
        }
    }

    public long getMemory()
    {
        return memory;
    }

    @DataBoundSetter
    public void setMemory(long memory)
    {
        this.memory = memory;
    }

    public long getDisk()
    {
        return disk;
    }

    @DataBoundSetter
    public void setDisk(long disk)
    {
        this.disk = disk;
    }

    private static final Logger LOGGER = Logger.getLogger(BalanceProjectProperty.class.getName());
}