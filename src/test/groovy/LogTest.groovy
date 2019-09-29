import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test
import static com.lesfurets.jenkins.unit.MethodCall.callArgsToString
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class LogTest extends BasePipelineTest {
  static final String scriptName = 'vars/log.groovy'
  Map env = [:]

  @Override
  @Before
  void setUp() throws Exception {
    super.setUp()
    binding.setVariable('env', env)
    helper.registerAllowedMethod('error', [String.class], { s ->
      updateBuildStatus('FAILURE')
      throw new Exception(s)
    })
    helper.registerAllowedMethod('echo', [String.class], { s -> s })
  }

  @Test
  void test_without_text() throws Exception {
    def script = loadScript(scriptName)
    try {
      script.call()
    } catch(e) {
      //NOOP
    }
    printCallStack()
    assertTrue(helper.callStack.findAll { call -> call.methodName == 'error' }.any { call ->
      callArgsToString(call).contains('text is mandatory')
    })
    assertJobStatusFailure()
  }

  @Test
  void test_without_level() throws Exception {
    def script = loadScript(scriptName)
    script.call(text: 'foo')
    printCallStack()
    assertTrue(helper.callStack.findAll { call -> call.methodName == 'echo' }.any { call ->
      callArgsToString(call).contains('[INFO] - foo')
    })
    assertJobStatusSuccess()
  }

  @Test
  void test_with_level() throws Exception {
    def script = loadScript(scriptName)
    script.call(text: 'foo', level: 'ERROR')
    printCallStack()
    assertTrue(helper.callStack.findAll { call -> call.methodName == 'echo' }.any { call ->
      callArgsToString(call).contains('[ERROR] - foo')
    })
  }
}
