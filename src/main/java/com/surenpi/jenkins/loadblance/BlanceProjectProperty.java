package com.surenpi.jenkins.loadblance;

import hudson.Extension;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import jenkins.model.ParameterizedJobMixIn;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import javax.annotation.Nonnull;
import java.util.logging.Logger;

public final class BlanceProjectProperty extends JobProperty<Job<?, ?>>
{
    private String blance;

    @DataBoundConstructor
    public BlanceProjectProperty(String blance)
    {
        this.blance = blance;
    }

    public String getBlance()
    {
        return blance;
    }

    @Extension
    public static final class DescriptorImpl extends JobPropertyDescriptor
    {
        public static final String NAME = "loadblance";

        public boolean isApplicable(Class<? extends Job> jobType) {
            return ParameterizedJobMixIn.ParameterizedJob.class.isAssignableFrom(jobType);
        }

        @Nonnull
        @Override
        public String getDisplayName()
        {
            return "blance";
        }

        @Override
        public JobProperty<?> newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            BlanceProjectProperty tpp = req.bindJSON(
                    BlanceProjectProperty.class,
                    formData.getJSONObject(NAME)
            );

            if (tpp == null) {
                LOGGER.fine("Couldn't bind JSON");
                return null;
            }

            if (tpp.blance == null) {
                LOGGER.fine("projectUrl not found, nullifying GithubProjectProperty");
                return null;
            }

            return tpp;
        }
    }

    private static final Logger LOGGER = Logger.getLogger(BlanceProjectProperty.class.getName());
}