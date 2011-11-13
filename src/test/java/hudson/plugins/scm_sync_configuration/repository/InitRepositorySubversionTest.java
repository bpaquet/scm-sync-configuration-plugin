package hudson.plugins.scm_sync_configuration.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hudson.plugins.scm_sync_configuration.model.ScmContext;
import hudson.plugins.scm_sync_configuration.scms.SCM;
import hudson.plugins.test.utils.scms.ScmUnderTestSubversion;

public class InitRepositorySubversionTest extends InitRepositoryTest {

	public InitRepositorySubversionTest() {
		super(new ScmUnderTestSubversion());
	}

	@Test
	public void shouldInitializeLocalRepositoryWhenScmContextIsCorrect()
			throws Throwable {
		SCM mockedSCM = createSCMMock();
		ScmContext scmContext = new ScmContext(mockedSCM, getSCMRepositoryURL());
		sscBusiness.init(scmContext);
		assertThat(sscBusiness.scmCheckoutDirectorySettledUp(scmContext),
				is(true));
	}

}
