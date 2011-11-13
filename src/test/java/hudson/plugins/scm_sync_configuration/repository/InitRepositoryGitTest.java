package hudson.plugins.scm_sync_configuration.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import hudson.plugins.scm_sync_configuration.SCMManipulator;
import hudson.plugins.scm_sync_configuration.model.ScmContext;
import hudson.plugins.scm_sync_configuration.scms.SCM;
import hudson.plugins.test.utils.scms.ScmUnderTestGit;

import java.io.File;
import java.util.Arrays;

import org.codehaus.plexus.util.FileUtils;
import org.junit.Test;



public class InitRepositoryGitTest extends InitRepositoryTest {

	public InitRepositoryGitTest() {
		super(new ScmUnderTestGit());
	}
	
	// on an empty git repository, the ssc is not setup correctly
	// it's not a problem in reality, the first checkin will create the master branch
	@Test
	public void shouldNotInitializeLocalRepositoryWhenNoMasterBranchInGitRepo() throws Throwable {
		SCM mockedSCM = createSCMMock();
		ScmContext scmContext = new ScmContext(mockedSCM, getSCMRepositoryURL());
		sscBusiness.init(scmContext);
		assertThat(sscBusiness.scmCheckoutDirectorySettledUp(scmContext), is(false));
	}

	@Test
	public void shouldInitializeLocalRepositoryWhenScmContextIsCorrectWithMasterBranchInGitRepo() throws Throwable {
		SCMManipulator scmManipulator = createMockedScmManipulator();
		File checkoutDirectory = createTmpDirectory(this.getClass().getSimpleName()+"_"+testName.getMethodName()+"__verifyCurrentScmContentMatchesHierarchy");
		scmManipulator.checkout(checkoutDirectory);
		FileUtils.fileAppend(checkoutDirectory + File.separator + ".gitkeep", "");
		scmManipulator.checkinFiles(checkoutDirectory, Arrays.asList(new File(".gitkeep")), "toto");
		
		SCM mockedSCM = createSCMMock();
		ScmContext scmContext = new ScmContext(mockedSCM, getSCMRepositoryURL());
		sscBusiness.init(scmContext);
		assertThat(sscBusiness.scmCheckoutDirectorySettledUp(scmContext), is(true));
	}

}
