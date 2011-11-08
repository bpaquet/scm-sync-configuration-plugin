package hudson.plugins.scm_sync_configuration.util;

import hudson.plugins.scm_sync_configuration.ScmSyncConfigurationPlugin;
import hudson.plugins.test.utils.scms.ScmUnderTest;

// Class will start current ScmSyncConfigurationPlugin instance
public class ScmSyncConfigurationPluginBaseTest extends
		ScmSyncConfigurationBaseTest {

	
	public ScmSyncConfigurationPluginBaseTest(ScmUnderTest scmUnderTest) {
		super(scmUnderTest);
	}

	public void setup() throws Throwable {
		super.setup();
		
		// Let's start the plugin...
		ScmSyncConfigurationPlugin.getInstance().start();
	}
	
	public void teardown() throws Throwable {
		// Stopping current plugin
		ScmSyncConfigurationPlugin.getInstance().stop();

		super.teardown();
	}
}
