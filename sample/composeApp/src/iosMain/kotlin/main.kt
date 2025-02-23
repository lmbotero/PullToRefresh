import androidx.compose.ui.window.ComposeUIViewController
import com.lumbot.pulltorefresh.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
